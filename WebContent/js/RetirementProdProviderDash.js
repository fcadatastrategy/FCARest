var ProviderApp = angular.module('ProviderApp', ['ui.grid','ui.grid.resizeColumns','ui.grid.pagination','ngResource','ui.grid.selection','ui.grid.exporter']);  // note you can add multiple injectors ['ui.grid','blah']



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
			 columnDefs: $scope.columns,
			 exporterCsvFilename: 'productsproviders.csv'
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
			$scope.selectedCompDate = $scope.datadates[1];
			
			drawChart();
	});
	
	
	$scope.loadDataFromServer = function() {
			drawChart();
	    };	
	    
	$scope.loadProdProvidersFromServer = function(selectedNode) {
			//alert($scope.selectedDate.datadate);
			// Extract the relevant node name from the HTML string in the node.
			$scope.prodType = selectedNode.substring(selectedNode.search('<td class="nodetab">')+20, selectedNode.search("</br>"));
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
	  
	$scope.export  = function() {
		$scope.gridApi.exporter.csvExport('all','all');
	} ;
	  
	    
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
  ]);*/ 

  // Get the selected date from the AngularJS model to pass into the URL JSON call
  var scope = angular.element($("#selectdiv")).scope();
  var seldate = scope.selectedDate.datadate;
  var compdate = scope.selectedCompDate.datadate;
  var urlbase = "/FCARest/track/prodtracker/retprodtracker/" + seldate +"&" + compdate;
	  
  // Get JSON From Web Service
  var rawjsonData = $.ajax({
      url: urlbase,
      dataType:"json",
      async: false
      }).responseText; 
  
  // parse JSON into Javascript Object
  var jsonData = JSON.parse(rawjsonData);
  var prodCompare, provCompare;
  var htmlnodes = [];
  
  // Process Javascript Object and add rows to data
  for(var i = 0; i < jsonData.length; i++) {
	    var obj = jsonData[i]; 
	     //console.log(obj.node);
	     var formatednode = '<table width="75" class="nodetab"><tr><td class="nodetab">' + obj.node + '</br></br><div class="nodeproduct">' + obj.productCount + '</div><div class="nodeprovider">' + obj.providerCount + '</div>';
	     // Determine changes in counts
	     prodCompare = obj.productCount - obj.compProductCount;
	     provCompare = obj.providerCount - obj.compProviderCount;

	     // If provider count changed then show delta
	     if (provCompare>0) {
	       formatednode = formatednode + '</br></br><div class="nodeprovidercompup">' + String(Math.abs(provCompare)) +  '</div>';
	     } else if (provCompare<0) {
		   formatednode = formatednode + '</br></br><div class="nodeprovidercompdown">' + String(Math.abs(provCompare)) +  '</div>';
	     } else {
			   formatednode = formatednode + '</br></br><div class="nodeprovidercomppause">-</div>';			   
	     }	
	     
	     // If product count changed then show delta
	     if (prodCompare>0) {
	       formatednode = formatednode + '<div class="nodeproductcompup">' + String(Math.abs(prodCompare)) +  '</div>';
	     } else if (prodCompare<0) {
		   formatednode = formatednode + '<div class="nodeproductcompdown">' + String(Math.abs(prodCompare)) +  '</div>';
	     } else {
			   formatednode = formatednode + '<div class="nodeproductcomppause">-</div>';			   
	     }
	     
	     var parenthtml = "";
	     
	     // Store HTML node and return for parent node.
	     htmlnodes.push({node:obj.node, htmlnode:formatednode});
	     for (var z=0;z<htmlnodes.length;z++) {
	    	 if (obj.nodeparent==htmlnodes[z].node) {
	    		 parenthtml = htmlnodes[z].htmlnode;
	    	 }
	     }
	     
	    //alert('Node' + obj.node + '-Parent' + obj.nodeparent + 'Found Parent-'+ parenthtml);
	    // console.log(obj.nodeparent);
	    data.addRows([[formatednode, parenthtml, obj.tooltip]]);
	}
  
  // hardcoded new product types
  data.addRows([
     ['<table width="75" class="nodetab"><tr><td class="nodetab">New</br></br><div class="nodeproduct">0</div><div class="nodeprovider">0</div>', '', 'To be defined'],
     ['<table width="75" class="nodetab"><tr><td class="nodetab">Cash Based Products</br></br><div class="nodeproduct">0</div><div class="nodeprovider">0</div>', '<table width="75" class="nodetab"><tr><td class="nodetab">New</br></br><div class="nodeproduct">0</div><div class="nodeprovider">0</div>', 'To be defined'],           
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





