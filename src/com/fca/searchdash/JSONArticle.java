package com.fca.searchdash;

import java.sql.Date;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class JSONArticle {

    private String ArticleTitle;
    private String ArticleDescription;
    private Date ArticlePubdate;
    private String ArticleLink;

	public JSONArticle() {
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

