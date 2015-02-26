var ProviderApp = angular.module('ProviderApp', ['ui.grid','ui.grid.resizeColumns','ui.grid.pagination','ngResource']);  // note you can add multiple injectors ['ui.grid','blah']
//	var ProviderApp = angular.module('ProviderApp', []);

ProviderApp.controller('ProdProviderCtrl',function ($scope, $http, uiGridConstants) {

	 $scope.columns =[{ field: 'articleTitle', cellClass: 'TableCell', headerCellClass: 'TableHeader', displayName: 'Title'}, 
	                  {field: 'articlePubDate', cellClass: 'TableCell', headerCellClass: 'TableHeader', displayName: 'Date', enableFiltering:false, width: 80, sort: {direction: uiGridConstants.DESC, priority: 1}},
	                  {field: 'articleLink', displayName: '', enableFiltering:false, cellClass: 'LinkCell', headerClass: 'TableHeader', width: 20, 
		 					cellTemplate: '<div class="ngCellText" ng-class="col.colIndex()"><a href="{{COL_FIELD}}"><img src="Images/link.jpg" width="20" height="20"></img></a></div>  '},
	                  {field: 'articleDescription', displayName: '', enableFiltering:false, cellClass: 'ViewCell', headerClass: 'TableHeader', width: 40, 
		 					cellTemplate: '<img src="Images/view.jpg" title="{{COL_FIELD}}" width="20" height="20">view</img>  '}
	                  ];
	 
	 $scope.gridOptions = {
			 enableSorting: true,
			 enablePaginationControls: false,
			 paginationPageSizes: [8,16,24],
			 paginationPageSize: 8,
			 enableFiltering: true,
			 enableHorizontalScrollbar: false,
			 enableScrollbars: false, 
			 columnDefs: $scope.columns
	 }
	 
	 $scope.gridOptions.enableHorizontalScrollbar = uiGridConstants.scrollbars.NEVER;
	 $scope.gridOptions.enableVerticalScrollbar = uiGridConstants.scrollbars.NEVER;
	 
	$scope.gridOptions.onRegisterApi = function (gridApi) {
			 $scope.gridApi = gridApi;
	 }
	 
	$http.get('http://localhost:8080/FCARest/rest/sipp/rsssearch/SIPP').success(function(data)  {
			$scope.gridOptions.data = data; //.provider; removed as no longer required with POJO mapping in place.	
	});
});	 


// 'http://ec2-54-72-253-133.eu-west-1.compute.amazonaws.com:8080/FCARest/rest/sipp/provider')

ProviderApp.controller('MarketCtrl',function ($scope, $http, uiGridConstants) {
	
		 $scope.columns =[{ field: 'articleTitle', cellClass: 'TableCell', headerCellClass: 'TableHeader', displayName: 'Title'}, 
		                  {field: 'articlePubDate', cellClass: 'TableCell', headerCellClass: 'TableHeader', displayName: 'Date', enableFiltering:false, width: 80, sort: {direction: uiGridConstants.DESC, priority: 1}},
		                  {field: 'articleLink', displayName: '', enableFiltering:false, cellClass: 'LinkCell', headerClass: 'TableHeader', width: 20, 
			 					cellTemplate: '<div class="ngCellText" ng-class="col.colIndex()"><a href="{{COL_FIELD}}"><img src="Images/link.jpg" width="20" height="20"></img></a></div>  '},
		                  {field: 'articleDescription', displayName: '', enableFiltering:false, cellClass: 'ViewCell', headerClass: 'TableHeader', width: 40, 
			 					cellTemplate: '<img src="Images/view.jpg" title="{{COL_FIELD}}" width="20" height="20">view</img>  '}
		                  ];
		 
		 $scope.gridOptions = {
				 enableSorting: true,
				 enablePaginationControls: false,
				 paginationPageSizes: [8,16,24],
				 paginationPageSize: 8,
				 enableFiltering: true,
				 enableHorizontalScrollbar: false,
				 enableScrollbars: false, 
				 columnDefs: $scope.columns
		 }
		 
		 $scope.gridOptions.enableHorizontalScrollbar = uiGridConstants.scrollbars.NEVER;
		 $scope.gridOptions.enableVerticalScrollbar = uiGridConstants.scrollbars.NEVER;
		 
		$scope.gridOptions.onRegisterApi = function (gridApi) {
				 $scope.gridApi = gridApi;
		 }
		 
		$http.get('http://localhost:8080/FCARest/rest/sipp/rsssearch/Pension').success(function(data)  {
				$scope.gridOptions.data = data; //.provider; removed as no longer required with POJO mapping in place.	
		});
	});	 

ProviderApp.controller('InputCtrl',function ($scope, $http) {	
	
	$http.get('http://localhost:8080/FCARest/rest/sipp/rsssearch/SIPP').success(function(data)  {
				$scope.advertiser = data;
				// $scope.adGridOptions.data = data;
			});
	
		$scope.sortField = 'adSpend';
		$scope.reverse = false;
		
	//	$scope.adGridOptions = {
				
	//	}
		
	});	 
	
ProviderApp.controller('SearchCtrl',function ($scope, $http, uiGridConstants) {
	
	$scope.url = 'http://localhost:8080/FCARest/rest/sipp/rsssearch'; 
	$scope.hideGrid = true;

	$scope.columns =[{ field: 'articleTitle', cellClass: 'TableCell', headerCellClass: 'TableHeader', displayName: 'Title'}, 
	                  {field: 'articlePubDate', cellClass: 'TableCell', headerCellClass: 'TableHeader', displayName: 'Date', enableFiltering:false, width: 80, sort: {direction: uiGridConstants.DESC, priority: 1}},
	                  {field: 'articleLink', displayName: '', enableFiltering:false, cellClass: 'LinkCell', headerClass: 'TableHeader', width: 20, 
		 					cellTemplate: '<div class="ngCellText" ng-class="col.colIndex()"><a href="{{COL_FIELD}}"><img src="Images/link.jpg" width="20" height="20"></img></a></div>  '},
	                  {field: 'articleDescription', displayName: '', enableFiltering:false, cellClass: 'ViewCell', headerClass: 'TableHeader', width: 40, 
		 					cellTemplate: '<img src="Images/view.jpg" title="{{COL_FIELD}}" width="20" height="20">view</img>  '}
	                  ];
	
	$scope.gridOptions = {
			 enableSorting: true,
			 enablePaginationControls: false,
			 paginationPageSizes: [10,20,30],
			 paginationPageSize: 10,
			 enableFiltering: true,
			 enableHorizontalScrollbar: false,
			 enableScrollbars: false, 
			 columnDefs: $scope.columns
	 }
	 
	 $scope.gridOptions.enableHorizontalScrollbar = uiGridConstants.scrollbars.NEVER;
	 $scope.gridOptions.enableVerticalScrollbar = uiGridConstants.scrollbars.NEVER;
	 
	$scope.gridOptions.onRegisterApi = function (gridApi) {
			 $scope.gridApi = gridApi;
	 }	
	
	// The url of our search // The function that will be executed on button click (ng-click="search()") 
	$scope.search = function() { 
	// Create the http post request // the data holds the keywords // The request is a JSON request. 
		$scope.hideGrid = false;
		$http.get($scope.url + '/' + $scope.keywords). 
			success(function(data) { 
			// set model result to data contents - lookup angularjs instructions on models
				$scope.gridOptions.data = data; 
			})	
		}; 
		
	});	// Controller 
	