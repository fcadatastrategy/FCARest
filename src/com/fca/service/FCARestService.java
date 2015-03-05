package com.fca.service;

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

@Path("/sipp")
public class FCARestService {

	ADOBusinessObjs ado = new ADOBusinessObjs();
	ADOConAnnuityBusObjs adoConAnunuity = new ADOConAnnuityBusObjs();
		
	@GET
	@Path("/provider")
	@Produces({  MediaType.APPLICATION_JSON })
	public List<Provider> findAll() {
		return ado.findAllProviders();
		
	}
	
	@GET
	@Path("/adspend")
	@Produces({  MediaType.APPLICATION_JSON })
	public List<Advertiser> findAllAdv() {
		return ado.findAllAdvertisers();
		
	}
	
	@GET
	@Path("/articlesum")
	@Produces({  MediaType.APPLICATION_JSON })
	public List<ArticleSummary> findAllArticleSummary() {
		return ado.findArticleSummary();
		
	}
	
	@GET
	@Path("/rsssearch/{srchstr}")
	@Produces({  MediaType.APPLICATION_JSON })
	public List<Article> findKeywordArticles(@PathParam("srchstr") String srchString) {
		System.out.println("Search String" + srchString);
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
	public List<ProviderRange> BuildProdProvTimeJSON() {
		return adoConAnunuity.findAllProviderCnt();
	}	
	

}

