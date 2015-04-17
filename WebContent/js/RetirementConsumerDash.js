var ConsumerApp = angular.module('ConsumerApp', ['ui.grid','ui.grid.resizeColumns','ui.grid.pagination','ngResource','ui.grid.selection','ui.grid.exporter']);  // note you can add multiple injectors ['ui.grid','blah']

ConsumerApp.controller('ConsumerCtrl',function ($scope, $http,uiGridConstants) {
	
	var callstring = 'PANN'; // $scope.prodType + "--" + $scope.selectedDate.datadate; 

	var url = "/FCARest/consumer/consumer/choice/" + callstring;

	$http.get(url).success(function(data)  {							
		$scope.datarows = data;				
	});	
	
	$scope.loadDataFromServer = function() {
		drawChart();
    };	
    
    // Form Options
	$scope.formData = {
	          absval : 0,
	          allfactors : 'Show All Factors Considered in Choice',
	          retstatus : 'Retirement Status'
	    };
	
	$scope.loadDataFromServer = function() {
		drawChart();
    };	
	
});
	
// -----------------------------------------------------------------------------------------
// Google Charts JS
//-----------------------------------------------------------------------------------------

google.load("visualization", "1.1", {packages:["bar"]});
google.setOnLoadCallback(drawChart);


function drawChart() {
  var data = new google.visualization.DataTable();
  
  data.addColumn('string', 'Question');

  // Get the selected options from the AngularJS model to pass into the URL JSON call
  var scope = angular.element($("#selectdiv")).scope();
  var absval = scope.formData.absval;
  var urlparam;
  
  if (scope.formData.allfactors=="Show All Factors Considered in Choice" ) {
	  urlparam = "ALL"; 
	  }
	  else { 
		  urlparam = "SINGLE"; 
		  };	  

  
  //var compdate = scope.selectedCompDate.datadate;
  var urlbase = "/FCARest/consumer/consumer/choice/" + urlparam;
	  
  // Get JSON From Web Service
  var rawjsonData = $.ajax({
      url: urlbase,
      dataType:"json",
      async: false
      }).responseText; 
  
  var rows = new Array();
  var cols = new Array();
  
  // parse JSON into Javascript Object
  var jsonData = JSON.parse(rawjsonData);
  
  // Process Rows Into Array
  for(var i = 0; i < jsonData.length; i++) {
	    var obj = jsonData[i]; 
	    if (rows.indexOf(obj.choiceDescription)==-1 ) {
	    	rows.push(obj.choiceDescription);
	    };
	    if (cols.indexOf(obj.crossTab)==-1 ) {
	    	cols.push(obj.crossTab);
	    	data.addColumn('number', obj.crossTab);
	    };
	}
  
  function getValueFromJSON(rowValue,colValue,jsonDataObj,absvalue) {	  
	  for(var i = 0; i < jsonDataObj.length; i++) {
		  var obj1 = jsonDataObj[i];
		  if (rowValue==obj1.choiceDescription & colValue==obj1.crossTab) {
			  if (absvalue==1) {
				  return obj1.weightedVal;
				  exit;			  
			  }
			  else {
				  return obj1.weightedAvg;
				  exit;
			  }

		  } // if 
	}  // for	  
  }
  
  // Create a Pivotted data set iterating over the JSON by row and column
  var row, col, prevrow;
  for (var z=0;z<rows.length;z++) {	  
	  var datarow = new Array();	 
	  datarow.push(rows[z]);
			  
	  for (var y=0;y<cols.length;y++) {		  			  
		  	row = rows[z];
		  	col = cols[y];		  	
		  	datarow.push(getValueFromJSON(row,col,jsonData,absval));		  
	  }
	  data.addRows([datarow]);
  }
  
  var axislabel
  if (absval==0) {
	  axislabel = "Percentage %";			  
  }
  else {
	  axislabel = "No Of Responses";
  }
 
  var options = {
          chart: {
              title: 'I know my needs',
              subtitle: 'Which of the following did you honestly take account?',
          },          
          bars: 'horizontal', // Required for Material Bar Charts.
          axes: {
              x: {
                0: { side: 'bottom', label: axislabel} // Top x-axis.
              }
            },
        };
  
  var chart = new google.charts.Bar(document.getElementById('chart_div'));
  chart.draw(data, options);
  
}





