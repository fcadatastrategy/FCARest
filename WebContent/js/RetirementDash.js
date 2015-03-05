var ProviderApp = angular.module('ProviderApp', ['ui.grid','ui.grid.resizeColumns','ui.grid.pagination','ngResource']);  // note you can add multiple injectors ['ui.grid','blah']

ProviderApp.controller('ProductChangesCtrl',function ($scope, $http, uiGridConstants) {
	 
	$http.get('/FCARest/rest/annuity/prodchanges').success(function(data)  {
			$scope.changes = data; 
			
			$scope.sortField = 'changeDate';
			$scope.reverse = false;
	});
});	 // Controller


// Google Charts JS

google.load("visualization", "1.1", {packages:["bar","corechart"]});
google.setOnLoadCallback(drawChart);


function drawChart() {
  var data = new google.visualization.DataTable();
  
  data.addColumn('string', 'Provider');
  data.addColumn('number', 'Products');

  var options = {
	title: "Provider Range",
	titleTextStyle: {fontSize: 14, fontName: 'Lato', color: 'black', bold: true},
    chart: {
      	subtitle: 'Jan 23rd 2015',
    },
    legend: {position: "none"},
    vAxis: {title: "", direction: -1},
    bars: 'horizontal' // Required for Material Bar Charts.
  };

  var chart = new google.charts.Bar(document.getElementById('barchart_material'));

  // Get JSON From Web Service
  var rawjsonData = $.ajax({
      url: "/FCARest/rest/annuity/providercnt",
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
	}
  
  chart.draw(data, google.charts.Bar.convertOptions(options));
  
}

//-----------------------------------------COMBO CHART

google.setOnLoadCallback(drawVisualization);

function drawVisualization() {
  // Some raw data (not necessarily accurate)
  var data = new google.visualization.DataTable();
  
  var customClassNames = {
		'titleTextStyle' : 'TableHeader'  
  };
  
  data.addColumn('string', 'Month');
  data.addColumn('number', 'Product');
  data.addColumn('number', 'Provider');

  var options = {
    title : 'Products and Providers Over Time',
    titleTextStyle: {fontSize: 14, fontName: 'Lato'},
    animation: { startup: true, duration: 1000},
    vAxis: {title: "Count"},
    seriesType: "bars",
    cssClassNames : customClassNames,
    series: {1: {type: "line"}}
  };
  
  // Get JSON From Web Service
  var rawjsonData = $.ajax({
      url: "/FCARest/rest/annuity/prodprovtime",
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
	    data.addRows([[obj.yearMonth, Number(obj.productCount), Number(obj.providerCount)]]);
	}
  
  var chart = new google.visualization.ComboChart(document.getElementById('chart_div'));
  chart.draw(data, options);
  
}


 


