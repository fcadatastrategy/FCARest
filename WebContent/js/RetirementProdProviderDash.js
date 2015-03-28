var ProviderApp = angular.module('ProviderApp', ['ui.grid','ui.grid.resizeColumns','ui.grid.pagination','ngResource']);  // note you can add multiple injectors ['ui.grid','blah']



ProviderApp.controller('DateSelectorCtrl',function ($scope, $http,uiGridConstants) {
	
	 
	 $scope.columns =[{ field: 'provider', cellClass: 'TableCell', headerCellClass: 'TableHeader', displayName: 'Provider', enableFiltering:false},
	                  { field: 'product', cellClass: 'TableCell', headerCellClass: 'TableHeader', displayName: 'Product'},
	                  ];
	 
	 $scope.gridOptions = {
			 enableSorting: true,
			 enablePaginationControls: false,
			 paginationPageSizes: [8,16,24],
			 paginationPageSize: 20,
			 enableFiltering: true,
			 enableHorizontalScrollbar: false,
			 enableScrollbars: false, 
			 rowHeight: 22,
			 columnDefs: $scope.columns
	 };
	 
	 $scope.gridOptions.enableHorizontalScrollbar = uiGridConstants.scrollbars.NEVER;
	 $scope.gridOptions.enableVerticalScrollbar = uiGridConstants.scrollbars.NEVER;
	 
	$scope.gridOptions.onRegisterApi = function (gridApi) {
			 $scope.gridApi = gridApi;
	 };
	
			 
	 $scope.hideGrid = {
			  grid: false
			};
	 
	 
	$http.get('/FCARest/track/prodtracker/retdatadates').success(function(data)  {
			$scope.datadates = data; //
			$scope.selectedDate = $scope.datadates[0];
			
			drawChart();
	});
	
	
	$scope.loadDataFromServer = function() {
			drawChart();
	    };	
	    
	$scope.loadProdProvidersFromServer = function(selectedNode) {
			//alert($scope.selectedDate.datadate);
			
			$scope.prodType = selectedNode.substring(0, selectedNode.search("<"));
			$scope.prodType = $scope.prodType.replace(/\s+/g, '');
			var callstring = $scope.prodType + "--" + $scope.selectedDate.datadate; 
			
			var url = "/FCARest/track/prodtracker/prodprovider/" + callstring;
			
			$http.get(url).success(function(data)  {							
				$scope.gridOptions.data = data;				
			});	
			
			 $scope.hideGrid = {
					  grid: true
					};
			
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



  /*   data.addRows([
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
    
  ]);*/ 

  // Get the selected date from the AngularJS model to pass into the URL JSON call
  var scope = angular.element($("#selectdiv")).scope();
  var seldate = scope.selectedDate.datadate;
  var urlbase = "/FCARest/track/prodtracker/retprodtracker/" + seldate;
	  
  // Get JSON From Web Service
  var rawjsonData = $.ajax({
      url: urlbase,
      dataType:"json",
      async: false
      }).responseText; 
  
  // parse JSON into Javascript Object
  var jsonData = JSON.parse(rawjsonData);
  
  // Process Javascript Object and add rows to data
  for(var i = 0; i < jsonData.length; i++) {
	    var obj = jsonData[i]; 
	    // console.log(obj.node);
	    // console.log(obj.nodeparent);
	    data.addRows([[obj.node, obj.nodeparent, obj.tooltip]]);
	}
  
  // hardcoded new product types
  data.addRows([
     ['New</br></br><div class="nodeproduct">0</div><div class="nodeprovider">0</div>', '', 'To be defined'],
     ['UFPLS</br></br><div class="nodeproduct">0</div><div class="nodeprovider">0</div>', 'New</br></br><div class="nodeproduct">0</div><div class="nodeprovider">0</div>', 'To be defined'],
     ['Cash Based Products</br></br><div class="nodeproduct">0</div><div class="nodeprovider">0</div>', 'New</br></br><div class="nodeproduct">0</div><div class="nodeprovider">0</div>', 'To be defined'],           
  ]);

  var chart = new google.visualization.OrgChart(document.getElementById('chart_div'));
  chart.draw(data, {allowHtml:true, size:'medium', nodeClass:'nodeclass'});
  
  google.visualization.events.addListener(chart, 'select', selectHandler);
    
  function selectHandler(e) {
	  // Get selected item and pass it back to the AngularJS to refresh providers
	  var selitem = data.getFormattedValue(chart.getSelection()[0].row , 0);	  
	  angular.element($("#selectdiv")).scope().loadProdProvidersFromServer(selitem);
	  

  }
  
}





