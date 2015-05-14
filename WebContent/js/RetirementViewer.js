var ProviderApp = angular.module('ViewerApp', ['acute.select']);  // note you can add multiple injectors ['ui.grid','blah']

ProviderApp.controller('ViewerCtrl',function ($scope, $http,uiGridConstants) {
	
	 
	 $scope.columns =[{ field: 'provider', cellClass: 'TableCell', headerCellClass: 'TableHeader', displayName: 'Provider', enableFiltering:false},
	                  { field: 'product', cellClass: 'TableCell', headerCellClass: 'TableHeader', displayName: 'Product'},
	                  ];
	 
	// Product Types
	 $scope.prodtypes = [{"prodtype":"SIPP","id":1},{"prodtype":"PPP","id":2},{"prodtype":"Stakeholder","id":3}];
	 $scope.selectedProdtype = $scope.prodtypes[0];
		
	// Providers
	 $scope.providers = [{"provider":"Aviva","id":1},{"provider":"Friends Provident","id":2},{"provider":"Cofunds","id":3}];
	 $scope.selectedProvider = $scope.providers[0];
	 
	// Products
	 $scope.products = [{"product":"Aviva SIPP","id":1},{"product":"Product 2","id":2},{"product":"Product 3","id":3}];
	 $scope.selectedProduct = $scope.products[0]; 
	 
	$http.get('/FCARest/track/prodtracker/retdatadates').success(function(data)  {
			$scope.datadates = data; //
			$scope.selectedDate = $scope.datadates[0];
	});
		    
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
	  
	  
	    
});	 





