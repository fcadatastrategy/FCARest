package com.fca.firmproductviewer;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/viewer")
public class RestFirmProd {

	ADOFirmProdBusObjs adoFirmProd = new ADOFirmProdBusObjs();

	
	@GET
	@Path("/firm/{dataSelections}")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<JSONFirm> BuildFirmJSON(@PathParam("dataSelections") String dataSelections) {
		return adoFirmProd.findAllFirms(dataSelections);
	}
	
	@GET
	@Path("/seldates/{marketDate}")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<JSONFirmProdDate> BuildFirmProdDateJSON(@PathParam("marketDate") String marketDate) {
		return adoFirmProd.findAllDatesByMarket(marketDate);
	}
	
	@GET
	@Path("/products/{dataSelections}")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<JSONProduct> findProductByFirm(@PathParam("dataSelections") String dataSelections) {
		return adoFirmProd.findProductByFirm(dataSelections);
	}	
	
}
