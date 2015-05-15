var ProviderApp = angular.module('ViewerApp', ['acute.select']);  // note you can add multiple injectors ['ui.grid','blah']

ProviderApp.controller('ViewerCtrl',function ($scope, $http) {
	
	 
	 $scope.columns =[{ field: 'provider', cellClass: 'TableCell', headerCellClass: 'TableHeader', displayName: 'Provider', enableFiltering:false},
	                  { field: 'product', cellClass: 'TableCell', headerCellClass: 'TableHeader', displayName: 'Product'},
	                  ];
	 
	// Product Types
	 $scope.prodtypes = [{"prodtype":"SIPP","id":1},{"prodtype":"PPP","id":2},{"prodtype":"Stakeholder","id":3}];
	 $scope.selectedProdtype = $scope.prodtypes[0];

	// Return all states when dropdown first opens
	 $scope.getProdTypes = function (callback) {
	     callback($scope.prodtypes);
	 };
	 
	// Providers
	 $scope.providers = [{"provider":"Aviva","id":1},{"provider":"Friends Provident","id":2},{"provider":"Cofunds","id":3}];
	 $scope.selectedProvider = $scope.providers[0];
	 
		// Return all states when dropdown first opens
	 $scope.getProviders = function (callback) {
	     callback($scope.providers);
	 };	 
	 
	// Products
	 $scope.products = [{"product":"Aviva SIPP","id":1},{"product":"Product 2","id":2},{"product":"Product 3","id":3}];
	 $scope.selectedProduct = $scope.products[0]; 
	 
	 $scope.getProducts = function (callback) {
	     callback($scope.products);
	 };	 
	
	// Data Dates 
	$http.get('/FCARest/track/prodtracker/retdatadates').success(function(data)  {
			$scope.datadates = data; //			 


	});
	 
	 //$scope.datadates1 = [{"datadate":"11-05-2015","id":1},{"datadate":"04-05-2015","id":2}];
	$scope.selectedDate = null;
	
/*	 $scope.getDataDates = function (callback) {
	     callback($scope.datadates);
	 };	*/

	    
});	 





