var FirmApp = angular.module('FirmApp', ['ui.grid','ui.grid.selection','ui.grid.resizeColumns','ui.grid.pagination','ngResource','ui.grid.exporter'])

FirmApp.controller('FirmAppCtrl',function ($scope, $http,uiGridConstants) {

	// Firm View Grid Definitions
	 $scope.columns =[{ field: 'firmName', cellClass: 'SelectableTableCell', headerCellClass: 'TableHeader', displayName: 'Firm'},
	                  { field: 'firmProductCount', cellClass: 'TableCell', headerCellClass: 'TableHeader', displayName: 'Product Count', enableFiltering:false},
	                  ];
	 
	 $scope.gridOptions = {
			 enableSorting: true,
		     enableRowSelection: true,
		     enableRowHeaderSelection: false,
		     modifierKeysToMultiSelect: true,
		     selectionRowHeaderWidth: 35,
			 enablePaginationControls: false,
			 paginationPageSizes: [20,40,60],
			 paginationPageSize: 20,
			 enableFiltering: true,
			 enableHorizontalScrollbar: false,
			 enableScrollbars: false, 
			 rowHeight: 22,
			 columnDefs: $scope.columns,
			 exporterCsvFilename: 'firms.csv'	 		 
	 };
	 
	 $scope.gridOptions.enableHorizontalScrollbar = uiGridConstants.scrollbars.NEVER;
	 $scope.gridOptions.enableVerticalScrollbar = uiGridConstants.scrollbars.NEVER;
	 
	 $scope.ProductColumns =[ { field: 'productName', cellClass: 'TableCell', headerCellClass: 'TableHeader', displayName: 'Product Name', enableFiltering:false}
     ];

	 // Product View Grid Definitions
	 $scope.ProductGridOptions = {
	enableSorting: true,
	enableRowSelection: true,
	enableRowHeaderSelection: false,
	modifierKeysToMultiSelect: true,
	selectionRowHeaderWidth: 35,
	enablePaginationControls: false,
	paginationPageSizes: [20,40,60],
	paginationPageSize: 20,
	enableFiltering: true,
	enableHorizontalScrollbar: false,
	enableScrollbars: false, 
	rowHeight: 22,
	columnDefs: $scope.ProductColumns,
	exporterCsvFilename: 'products.csv'	 		 
	 };

	 $scope.ProductGridOptions.enableHorizontalScrollbar = uiGridConstants.scrollbars.NEVER;
	 $scope.ProductGridOptions.enableVerticalScrollbar = uiGridConstants.scrollbars.NEVER;	 
	 
	 
	$scope.gridOptions.onRegisterApi = function (gridApi) {
			 $scope.gridApi = gridApi;
			 
			 // Selection Event For Firm Grid, when selected invoke the product view with firm params.
			 gridApi.selection.on.rowSelectionChanged($scope,function(row){
				 var msg = 'row selected ' + row.isSelected;
				 $scope.mySelections = gridApi.selection.getSelectedRows();
				 console.log($scope.mySelections);
				 console.log($scope.mySelections[0]);

				 // http://localhost:8080/FCARest/viewer/viewer/products/2015-05-09%2000:00:00.0&Credit%20Card&New%20Day&Provider&15687
			 	 
				// Load Product Data From JSON 
				var url = "/FCARest/viewer/viewer/products/" + $scope.mySelections[0].firmDataDate + "&" +  $scope.mySelections[0].firmMarket + "&" + $scope.mySelections[0].firmName + "&" + $scope.mySelections[0].firmType + "&" + $scope.mySelections[0].firmId;
			    
				$http.get(url).success(function(data)  {
					$scope.ProductGridOptions.data = data;	
				}); 
				
				$scope.hideProductGrid = {grid: true};
			    
			 
			 }); // Grid view selection event
			 
	 };	 // Grid API
	 
	$scope.ProductGridOptions.onRegisterApi = function (gridApi) { $scope.ProductGridApi = gridApi; };
		
		
	// End Grid Definitions ----------------------------------------------------------------------------------------	
	 
	 $scope.hideGrid = {grid: false};
	 $scope.hideProductGrid = {grid: false};
	 
	 $scope.refreshSelections = function() {
			// Load Sectors
		 $scope.sectors = [{"sector":"<Select Sector>","id":1},{"sector":"Cash Savings","id":2},{"sector":"Credit Cards","id":3},{"sector":"Personal Loans","id":4},{"sector":"Retirement Income","id":5}];
		 $scope.selectedSector = $scope.sectors[0];	 
		 
		// Set Default Dates 
		 $scope.datadates = [{"datadate":"<Select Date>","id":1}];
		 $scope.selectedDate = $scope.datadates[0];	 	 
		
		// Set Default Firm Types
		 $scope.firmtypes = [{"firmtype":"<Select Firm Type>","id":1}];
		 $scope.selectedFirmType = $scope.firmtypes[0];	 	
		 
		 $scope.isDisabled= true; // disable selections.
		 
		// Hide existing data
		 $scope.hideGrid = {grid: false}; 
		 $scope.hideProductGrid = {grid: false};
	 }
	 
	$scope.refreshSelections();
	 
	$scope.loadDatesFromServer = function() {
		
		$scope.hideProductGrid = {grid: false};
		
		$scope.isDisabled=true; // enable dropdowns
		// Set Firm Types & Refresh Dates Based on Sector Selection
		if ($scope.selectedSector.sector == '<Select Sector>')  {
			$scope.refreshSelections();
		}
		else if ($scope.selectedSector.sector == 'Credit Cards') {
			 $scope.firmtypes = [{"firmtype":"<Select Firm Type>","id":1},{"firmtype":"Provider","id":2},{"firmtype":"Issuer","id":3},{"firmtype":"Affinity","id":4	}];
			 $scope.selectedFirmType = $scope.firmtypes[0];	 				
		}
		else {  // Its a Moneyfacts Daily Load Date - Hard set to credit cards!!
			 $scope.firmtypes = [{"firmtype":"<Select Firm Type>","id":1},{"firmtype":"All Firms","id":1}];
			 $scope.selectedFirmType = $scope.firmtypes[0];	 					
		}
		
		// If market has been selected get the dates for this date
		if ($scope.selectedSector.sector != '<Select Sector>')  {
			// Load Data Dates From JSON 
			var url = "/FCARest/viewer/viewer/seldates/" + $scope.selectedSector.sector 
				
			$http.get(url).success(function(data)  {
					$scope.datadates = data; //
					$scope.selectedDate = $scope.datadates[0];
					$scope.isDisabled=false; // enable dropdowns
			}); 
		} // if
		
		$scope.hideGrid = {grid: false};
		$scope.hideProductGrid = {grid: false};
	 };	// loadDatesFromServer
	    
	$scope.loadDataGrid = function() {
		
		// Loading Widget Invoke
		$scope.hideProductGrid = {grid: false};
		
		// If everything is has a valid selection then refresh the data grid, otherwise do nothing.
		if ( ($scope.selectedSector.sector != '<Select Sector>')  && ($scope.selectedDate.datadate != '<Select Date>') && ($scope.selectedFirmType.firmtype != '<Select Firm Type>') ) {	

			// Build the Rest URL Call
			var url = "/FCARest/viewer/viewer/firm/" + $scope.selectedDate.datadate+ "&" + $scope.selectedSector.sector + "&" + $scope.selectedFirmType.firmtype ;
			
			$scope.loading = true;
			$scope.hideGrid = {grid: false};
			
			$http.get(url).success(function(data)  {							
				$scope.gridOptions.data = data;	
				$scope.loading = false;
				$scope.hideGrid = {grid: true};
			});	
			
			
		} // if	
	  }; // loadDataGrid function	    
	  
	  
	$scope.export  = function() {
		$scope.gridApi.exporter.csvExport('all','all');
	} ;
	  
	    
});	 
