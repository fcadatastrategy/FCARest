var ProviderApp = angular.module('ProviderApp', ['ngAnimate','ngTouch','ui.grid','ui.grid.resizeColumns','ui.grid.pagination','ngResource']);  // note you can add multiple injectors ['ui.grid','blah']
//	var ProviderApp = angular.module('ProviderApp', []);

ProviderApp.controller('ProdProviderCtrl',function ($scope, $http, uiGridConstants) {

	 $scope.columns =[{ field: 'articleTitle', cellClass: 'TableCell', headerCellClass: 'TableHeader', displayName: 'Title', cellTooltip: true}, 
	                  {field: 'articlePubDate', cellClass: 'TableCell', headerCellClass: 'TableHeader', displayName: 'Date', enableFiltering:false, width: 80, sort: {direction: uiGridConstants.DESC, priority: 1}},
	                  {field: 'articleLink', displayName: '', enableFiltering:false, cellClass: 'LinkCell', headerClass: 'TableHeader', width: 20, 
		 					cellTemplate: '<div class="ngCellText" ng-class="col.colIndex()"><a href="{{COL_FIELD}}" target="_blank"><img src="Images/link.jpg" width="20" height="20"></img></a></div>  '},
	                  {field: 'articleDescription', displayName: '', enableFiltering:false, cellClass: 'ViewCell', headerClass: 'TableHeader', width: 20, 
		 					cellTemplate: '<img src="Images/view.jpg" title="{{COL_FIELD}}" width="20" height="20">view</img>  '}
	                  ];
	 
	 $scope.gridOptions = {
			 enableSorting: true,
			 cellTooltip: true,
			 enablePaginationControls: false,
			 paginationPageSizes: [8,16,24],
			 paginationPageSize: 8,
			 enableFiltering: true,
			 enableHorizontalScrollbar: false,
			 enableScrollbars: false, 
			 // rowHeight: 65,
			 columnDefs: $scope.columns
	 }
	 
	 $scope.gridOptions.enableHorizontalScrollbar = uiGridConstants.scrollbars.NEVER;
	 $scope.gridOptions.enableVerticalScrollbar = uiGridConstants.scrollbars.NEVER;
	 
	$scope.gridOptions.onRegisterApi = function (gridApi) {
			 $scope.gridApi = gridApi;
			 $scope.gridApi.core.on.sortChanged( $scope, function( grid, sort ) {
			 $scope.gridApi.core.notifyDataChange( uiGridConstants.dataChange.COLUMN ); })

	 }
	 
	$http.get('/FCARest/search/retirement/rsssearch/-').success(function(data)  {
			$scope.gridOptions.data = data; //.provider; removed as no longer required with POJO mapping in place.	
	});
});	 


ProviderApp.controller('MarketCtrl',function ($scope, $http, uiGridConstants) {
	
		 $scope.columns =[{ field: 'articleTitle', cellClass: 'TableCell', headerCellClass: 'TableHeader', title: 'Hello', displayName: 'Title', cellTooltip: true}, 
		                  {field: 'articlePubDate', cellClass: 'TableCell', headerCellClass: 'TableHeader', displayName: 'Date', enableFiltering:false, width: 80, sort: {direction: uiGridConstants.DESC, priority: 1}},
		                  {field: 'articleLink', displayName: '', enableFiltering:false, cellClass: 'LinkCell', headerClass: 'TableHeader', width: 20, 
			 					cellTemplate: '<div class="ngCellText" ng-class="col.colIndex()"><a href="{{COL_FIELD}}"  target="_blank"><img src="Images/link.jpg" width="20" height="20"></img></a></div>  '},
		                  {field: 'articleDescription', displayName: '', enableFiltering:false, cellClass: 'ViewCell', headerClass: 'TableHeader', width: 20, 
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
		 
		$http.get('/FCARest/search/retirement/rsssearch/*').success(function(data)  {
				$scope.gridOptions.data = data; //.provider; removed as no longer required with POJO mapping in place.	
		});
		
		$scope.winopen= function() { $window.open('//facebook.com');};
		
	});	 

ProviderApp.controller('InputCtrl',function ($scope, $http) {	
	
	$http.get('/FCARest/search/retirement/articlesum').success(function(data)  {
				$scope.summary = data;
			});
	
		$scope.sortField = 'marketArticleCount';
		$scope.reverse = true;
		
	});	 
	
ProviderApp.controller('SearchCtrl',function ($scope, $http, uiGridConstants) {
	
	$scope.url = '/FCARest/search/retirement/rsssearch'; 
	$scope.hideGrid = true;

	$scope.columns =[{ field: 'articleTitle', cellClass: 'TableCell', headerCellClass: 'TableHeader', displayName: 'Title', cellTooltip: true}, 
	                  {field: 'articlePubDate', cellClass: 'TableCell', headerCellClass: 'TableHeader', displayName: 'Date', enableFiltering:false, width: 80, sort: {direction: uiGridConstants.DESC, priority: 1}},
	                  {field: 'articleLink', displayName: '', enableFiltering:false, cellClass: 'LinkCell', headerClass: 'TableHeader', width: 20, 
		 					cellTemplate: '<div class="ngCellText" ng-class="col.colIndex()"><a href="{{COL_FIELD}}"  target="_blank"><img src="Images/link.jpg" width="20" height="20"></img></a></div>  '},
	                  {field: 'articleDescription', displayName: '', enableFiltering:false, cellClass: 'ViewCell', headerClass: 'TableHeader', width: 20, 
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
	 };
	 
	 $scope.gridOptions.enableHorizontalScrollbar = uiGridConstants.scrollbars.NEVER;
	 $scope.gridOptions.enableVerticalScrollbar = uiGridConstants.scrollbars.NEVER;
	 
	$scope.gridOptions.onRegisterApi = function (gridApi) {
			 $scope.gridApi = gridApi;
	 };	
	
	// The url of our search // The function that will be executed on button click (ng-click="search()") 
	$scope.search = function() { 
	// Create the http post request // the data holds the keywords // The request is a JSON request. 
		$scope.hideGrid = false;
		$http.get($scope.url + '/' + $scope.keywords). 
			success(function(data) { 
			// set model result to data contents - lookup angularjs instructions on models
				$scope.gridOptions.data = data; 
			});	
		}; 
		
	});	// Controller 
	