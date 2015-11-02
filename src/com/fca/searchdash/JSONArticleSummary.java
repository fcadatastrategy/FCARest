package com.fca.searchdash;

import java.sql.Date;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class JSONArticleSummary {

    private String PubName;
    private Integer AllArticleCount;
    private Integer MarketArticleCount;
    private String PubImg;
    private String PubURL;

	public JSONArticleSummary() {
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
	
	public String getPubImg() {
		return PubImg;
	}

	public void setPubImg(String PubImg) {
		this.PubImg = PubImg;
	}	
	
	public String getPubURL() {
		return PubURL;
	}

	public void setPubURL(String PubURL) {
		this.PubURL = PubURL;
	}	
	
}

