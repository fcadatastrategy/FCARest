var ProviderApp = angular.module('ProviderApp', ['ngResource']);  // note you can add multiple injectors ['ui.grid','blah']



ProviderApp.controller('DateSelectorCtrl',function ($scope, $http) {

	$scope.datadates = [{ date: "23/01/2015", id: 1 }, { date: "16/12/2015", id: 2 }];
	$scope.selectedDate = $scope.datadates[0];
	 
	//$http.get('/FCARest/dash/annuity/featurechanges').success(function(data)  {
	//		$scope.gridOptions.data = data; //.provider; removed as no longer required with POJO mapping in place.	
	//});
	
	$scope.loadDataFromServer = function() {

			alert("Angular Hello");
			drawChart();

	    };
});	 


// -----------------------------------------------------------------------------------------
// Google Charts JS
//-----------------------------------------------------------------------------------------

//google.load("visualization", "1.1", {packages:["bar","orgchart"]});
google.load('visualization', '1', {packages: ['orgchart']});
google.setOnLoadCallback(drawChart);


function drawChart() {
  var data = new google.visualization.DataTable();
  
  data.addColumn('string', 'Name');
  data.addColumn('string', 'Manager');
  data.addColumn('string', 'ToolTip');



  data.addRows([
    ['Retirement Income</br><div class="nodeproduct">210</div><div class="nodeprovider">110</div>', '', 'All Retirement Income Products'], 
    
    ['Annuity From Defined Contribution Pension</br><div class="nodeproduct">26</div><div class="nodeprovider">15</div>', 'Retirement Income</br><div class="nodeproduct">210</div><div class="nodeprovider">110</div>', 'The President'],
    ['Purchased Annuity</br></br><div class="nodeproduct">6</div><div class="nodeprovider">4</div>', 'Retirement Income</br><div class="nodeproduct">210</div><div class="nodeprovider">110</div>', 'Income Options and income protection freely available'],
    
    ['Income Drawdown with Defined Contribution Pension</br><div class="nodeproduct">155</div><div class="nodeprovider">93</div>', 'Retirement Income</br><div class="nodeproduct">210</div><div class="nodeprovider">110</div>', 'Income Options and income protection freely available'],
    ['PPP</br><div class="nodeproduct">12</div><div class="nodeprovider">6</div>', 'Income Drawdown with Defined Contribution Pension</br><div class="nodeproduct">155</div><div class="nodeprovider">93</div>', 'The President'],
    ['SIPP</br><div class="nodeproduct">132</div><div class="nodeprovider">84</div>', 'Income Drawdown with Defined Contribution Pension</br><div class="nodeproduct">155</div><div class="nodeprovider">93</div>', 'The President'],
    ['Stakeholder Pension</br><div class="nodeproduct">11</div><div class="nodeprovider">10</div>', 'Income Drawdown with Defined Contribution Pension</br><div class="nodeproduct">155</div><div class="nodeprovider">93</div>', 'The President'],

    
    ['Blended Solutions</br></br><div class="nodeproduct">4</div><div class="nodeprovider">3</div>', 'Retirement Income</br><div class="nodeproduct">210</div><div class="nodeprovider">110</div>', 'The President'],
    ['Variable / 3rd Way Annuity</br><div class="nodeproduct">4</div><div class="nodeprovider">3</div>', 'Blended Solutions</br></br><div class="nodeproduct">4</div><div class="nodeprovider">3</div>', 'The President'],

    ['New</br></br><div class="nodeproduct">0</div><div class="nodeprovider">0</div>', 'Retirement Income</br><div class="nodeproduct">210</div><div class="nodeprovider">110</div>', 'To be defined'],
    ['UFPLS</br></br><div class="nodeproduct">0</div><div class="nodeprovider">0</div>', 'New</br></br><div class="nodeproduct">0</div><div class="nodeprovider">0</div>', 'To be defined'],
    ['Cash Based Products</br></br><div class="nodeproduct">0</div><div class="nodeprovider">0</div>', 'New</br></br><div class="nodeproduct">0</div><div class="nodeprovider">0</div>', 'To be defined'],

    
    ['Conventional</br><div class="nodeproduct">13</div><div class="nodeprovider">13</div>', 'Annuity From Defined Contribution Pension</br><div class="nodeproduct">26</div><div class="nodeprovider">15</div>', 'Income Options and income protection freely available'],
    ['Fixed / Short Term</br><div class="nodeproduct">6</div><div class="nodeprovider">4</div>', 'Annuity From Defined Contribution Pension</br><div class="nodeproduct">26</div><div class="nodeprovider">15</div>', ''],
    ['Investment Linked</br><div class="nodeproduct">7</div><div class="nodeprovider">7</div>', 'Annuity From Defined Contribution Pension</br><div class="nodeproduct">26</div><div class="nodeprovider">15</div>', 'VP'],
    ['Standard</br><div class="nodeproduct">10</div><div class="nodeprovider">10</div>', 'Conventional</br><div class="nodeproduct">13</div><div class="nodeprovider">13</div>', 'Bob Sponge'],
    ['Enhanced</br><div class="nodeproduct">3</div><div class="nodeprovider">3</div>', 'Conventional</br><div class="nodeproduct">13</div><div class="nodeprovider">13</div>', 'Bob Sponge']
  ]);

  // Get the selected date from the AngularJS model to pass into the URL JSON call
  var scope = angular.element($("#test")).scope();
  var seldate = scope.selectedDate.date;
  if (seldate=="16/12/2015") {
	  data.addRows([['Hello','Daniel','blah']]);
  };

  var chart = new google.visualization.OrgChart(document.getElementById('chart_div'));
  chart.draw(data, {allowHtml:true, size:'medium', nodeClass:'nodeclass'});
  

/*  // Get JSON From Web Service
  var rawjsonData = $.ajax({
      url: "/FCARest/dash/annuity/providercnt",
      dataType:"json",
      async: false
      }).responseText; 
  
  // parse JSON into Javascript Object
  var jsonData = JSON.parse(rawjsonData);
  
  // Process Javascript Object and add rows to data
  for(var i = 0; i < jsonData.length; i++) {
	    var obj = jsonData[i]; 
	    // console.log(obj.provider);
	    // console.log(obj.productCount);
	    data.addRows([[obj.provider, Number(obj.productCount)]]);
	}*/
  
  
}
