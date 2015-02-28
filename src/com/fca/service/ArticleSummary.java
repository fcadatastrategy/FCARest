package com.fca.service;

import java.sql.Date;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class ArticleSummary {

    private String PubName;
    private Integer AllArticleCount;
    private Integer MarketArticleCount;

	public ArticleSummary() {
    }
	
	public String getPubName() {
		return PubName;
	}

	public void setPubName(String PubName) {
		this.PubName = PubName;
	}

	public Integer getAllArticleCount() {
		return AllArticleCount;
	}

	public void setAllArticleCount(Integer AllArticleCount) {
		this.AllArticleCount = AllArticleCount;
	}
	
	public Integer getMarketArticleCount() {
		return MarketArticleCount;
	}

	public void setMarketArticleCount(Integer MarketArticleCount) {
		this.MarketArticleCount = MarketArticleCount;
	}	
	
}

