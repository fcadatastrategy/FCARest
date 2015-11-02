package com.fca.searchdash;

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

import org.apache.log4j.Logger;

import com.fca.annuitydash.ADOAnnuityBusObjs;
import com.fca.annuitydash.JSONProvider;
import com.fca.annuitydash.JSONProviderRange;
import com.fca.utils.Advertiser;

@Path("/retirement")
public class RestServiceSearch {

	ADOSearchBusObjs ado = new ADOSearchBusObjs();
	ADOAnnuityBusObjs adoConAnunuity = new ADOAnnuityBusObjs();
		
/*	@GET
	@Path("/provider")
	@Produces({  MediaType.APPLICATION_JSON })
	public List<JSONProvider> findAll() {
		return ado.findAllProviders();
		
	}
	
	@GET
	@Path("/adspend")
	@Produces({  MediaType.APPLICATION_JSON })
	public List<Advertiser> findAllAdv() {
		return ado.findAllAdvertisers();
		
	}*/
	
	@GET
	@Path("/articlesum")
	@Produces({  MediaType.APPLICATION_JSON })
	public List<JSONArticleSummary> findAllArticleSummary() {
		return ado.findArticleSummary();
		
	}
	
	@GET
	@Path("/rsssearch/{srchstr}")
	@Produces({  MediaType.APPLICATION_JSON })
	public List<JSONArticle> findKeywordArticles(@PathParam("srchstr") String srchString) {
		//System.out.println("Search String" + srchString);
		if (srchString.equals("*")) {
			return ado.findAllMarketArticles();
		}
		else if (srchString.equals("-")) {
			return ado.findAllProdProviderArticles();
		}
		else {
			return ado.findKeywordArticles(srchString);
		}

		
	}
	
	@GET
	@Path("/providercnt")
	@Produces({  MediaType.APPLICATION_JSON })
	public List<JSONProviderRange> BuildProdProvTimeJSON() {
		return adoConAnunuity.findAllProviderCnt();
	}	
	

}

