package com.fca.service;

import java.sql.Date;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Article {

    private String ArticleTitle;
    private String ArticleDescription;
    private Date ArticlePubdate;
    private String ArticleLink;

	public Article() {
    }
	
	public String getArticleTitle() {
		return ArticleTitle;
	}

	public void setArticleTitle(String ArticleTitle) {
		this.ArticleTitle = ArticleTitle;
	}

	public String getArticleDescription() {
		return ArticleDescription;
	}

	public void setArticleDescription(String ArticleDescription) {
		this.ArticleDescription = ArticleDescription;
	}
	
	public Date getArticlePubDate() {
		return ArticlePubdate;
	}

	public void setArticlePubDate(Date ArticlePubdate) {
		this.ArticlePubdate = ArticlePubdate;
	}	
	
	public String getArticleLink() {
		return ArticleLink;
	}

	public void setArticleLink(String ArticleLink) {
		this.ArticleLink = ArticleLink;
	}	

}

