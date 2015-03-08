package com.fca.service;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.DELETE;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.FormParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.core.MediaType;

import com.fca.retdash.*;


@Path("/annuity")
public class RetirementRestService  {
	
		ADOConAnnuityBusObjs adoConAnunuity = new ADOConAnnuityBusObjs();
	
		@GET
		@Path("/providercnt")
		@Produces({  MediaType.APPLICATION_JSON })
		public List<ProviderRange> BuildProviderCountJSON() {
			return adoConAnunuity.findAllProviderCnt();
		}

		@GET
		@Path("/prodprovtime")
		@Produces({  MediaType.APPLICATION_JSON })
		public List<ProdProviderTime> BuildProdProvTimeJSON() {
			return adoConAnunuity.findProdProviderTime();
		}
		
		@GET
		@Path("/prodchanges")
		@Produces({  MediaType.APPLICATION_JSON })
		public List<ProductChanges> BuildProdChangesJSON() {
			return adoConAnunuity.findProductChanges();
		}

		@GET
		@Path("/annuityoptions")
		@Produces({  MediaType.APPLICATION_JSON })
		public List<ConAnnuityOptions> BuildAnnuityOptionsJSON() {
			return adoConAnunuity.findAnnuityOptions();
		}		
		
		@GET
		@Path("/featurechanges")
		@Produces({  MediaType.APPLICATION_JSON })
		public List<FeatureChange> BuildFeatureChangesJSON() {
			return adoConAnunuity.findFeatureChanges();
		}		
		
		@GET
		@Path("/topfeaturechanges")
		@Produces({  MediaType.APPLICATION_JSON })
		public List<TopFeatureChange> BuildTopFeatureChangesJSON() {
			return adoConAnunuity.findTopFeatureChanges();
		}		
}
