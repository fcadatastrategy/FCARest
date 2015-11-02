package com.fca.prodtrackerdash;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.fca.searchdash.JSONArticle;

@Path("/prodtracker")
public class RestProductTracker {

	ADOProdTrackerBusObjs adoProdTracker = new ADOProdTrackerBusObjs();

	@GET
	@Path("/retdatadates")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<JSONProductDates> BuildProviderCountJSON() {
		return adoProdTracker.findAllDataDates();
	}
	
	@GET
	@Path("/retprodtracker/{datadate}")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<JSONRetirementTracker> BuildProdProvTimeJSON(@PathParam("datadate") String dataDate) {
		return adoProdTracker.findAllRetirementTracker(dataDate);
	}

	@GET
	@Path("/prodprovider/{selections}")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<JSONProdProvider> JSONProdProvider(@PathParam("selections") String selections) {
		return adoProdTracker.findProdProviders(selections);
	}
	
}
