package com.fca.annuitydash;

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

import com.fca.annuitydash.*;


@Path("/annuity")
public class RestServiceAnnuity  {
	
		ADOAnnuityBusObjs adoConAnunuity = new ADOAnnuityBusObjs();
	
		@GET
		@Path("/providercnt")
		@Produces({  MediaType.APPLICATION_JSON })
		public List<JSONProviderRange> BuildProviderCountJSON() {
			return adoConAnunuity.findAllProviderCnt();
		}

		@GET
		@Path("/prodprovtime")
		@Produces({  MediaType.APPLICATION_JSON })
		public List<JSONProdProviderTime> BuildProdProvTimeJSON() {
			return adoConAnunuity.findProdProviderTime();
		}
		
		@GET
		@Path("/prodchanges")
		@Produces({  MediaType.APPLICATION_JSON })
		public List<JSONProductChanges> BuildProdChangesJSON() {
			return adoConAnunuity.findProductChanges();
		}

		@GET
		@Path("/annuityoptions")
		@Produces({  MediaType.APPLICATION_JSON })
		public List<JSONAnnuityOptions> BuildAnnuityOptionsJSON() {
			return adoConAnunuity.findAnnuityOptions();
		}		
		
		@GET
		@Path("/featurechanges")
		@Produces({  MediaType.APPLICATION_JSON })
		public List<JSONFeatureChange> BuildFeatureChangesJSON() {
			return adoConAnunuity.findFeatureChanges();
		}		
		
		@GET
		@Path("/topfeaturechanges")
		@Produces({  MediaType.APPLICATION_JSON })
		public List<JSONTopFeatureChange> BuildTopFeatureChangesJSON() {
			return adoConAnunuity.findTopFeatureChanges();
		}		
}
