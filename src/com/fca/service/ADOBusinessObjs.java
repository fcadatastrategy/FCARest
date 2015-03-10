package com.fca.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ADOBusinessObjs {
	
    public List<Provider> findAllProviders() {
        List<Provider> list = new ArrayList<Provider>();
        Connection c = null;
    	String sql = "SELECT DISTINCT PROVIDER_ID id, PROVIDER ProviderName, PRODUCT Product " +
			"FROM FCA_DS_STG.S_DQ_PPP_DATA_BROCHURE  " + 
    	    "WHERE Z_XML_DATA_DATE = ( SELECT MAX(Z_XML_DATA_DATE) FROM FCA_DS_STG.S_DQ_PPP_DATA_BROCHURE )";
        
        try {
            c = SQLConnection.getConnection();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                list.add(processProviderRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			SQLConnection.close(c);
		}
        return list;
    }
    
    protected Provider processProviderRow(ResultSet rs) throws SQLException {
    	Provider provider = new Provider();
    	provider.setId(rs.getInt("id"));
    	provider.setProviderName(rs.getString("ProviderName"));
    	provider.setProductName(rs.getString("Product"));
    	return provider;
    }

    public List<Advertiser> findAllAdvertisers() {
        List<Advertiser> list = new ArrayList<Advertiser>();
        Connection c = null;
    	String sql = "SELECT Product AdvertiserProduct, SUM(DISCOUNTEDSPEND) AdSpend " +
			"FROM FCA_DS_STG.S_EB_ADVSPEND  " + 
    	    "GROUP BY Product ";
        
        try {
            c = SQLConnection.getConnection();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                list.add(processAdvertiserRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			SQLConnection.close(c);
		}
        return list;
    }
    
    protected Advertiser processAdvertiserRow(ResultSet rs) throws SQLException {
    	Advertiser advertiser = new Advertiser();
    	advertiser.setAdvertiserProduct(rs.getString("AdvertiserProduct"));
    	advertiser.setAdSpend(rs.getDouble("AdSpend"));
    	return advertiser;
    }
    
    
    public List<Article> findAllMarketArticles() {
        List<Article> list = new ArrayList<Article>();
        Connection c = null;
    	 
    	 String sql = "SELECT DISTINCT title, description, pubdate, url link " + 
    	  "FROM table(fca_ds_rpt.pck_rss_search.meta_search_rss(365,'Retirement','Retirement'))"; 
        
        try {
            c = SQLConnection.getConnection();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                list.add(processArticleRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			SQLConnection.close(c);
		}
        return list;
    }
    
    public List<Article> findAllProdProviderArticles() {
        List<Article> list = new ArrayList<Article>();
        Connection c = null;
    	 
    	 String sql = "SELECT DISTINCT title, description, pubdate, link " + 
    	  "FROM fca_ds_rpt.z_rss_provprod_demo_data"; 
        
        try {
            c = SQLConnection.getConnection();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                list.add(processArticleRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			SQLConnection.close(c);
		}
        return list;
    }    
    
    public List<Article> findKeywordArticles(String srchstr) {
        List<Article> list = new ArrayList<Article>();
        Connection c = null;
    	
        String sql = "SELECT title, description, pubdate, url link " + 
    	  "FROM table(fca_ds_rpt.pck_rss_search.search_rss(365,'" + srchstr.toUpperCase() + "','RSS'))  ";    	
        
        try {
            c = SQLConnection.getConnection();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                list.add(processArticleRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			SQLConnection.close(c);
		}
        return list;
    }
    
    protected Article processArticleRow(ResultSet rs) throws SQLException {
    	Article article = new Article();
    	article.setArticleTitle(rs.getString("title"));
    	article.setArticleDescription(rs.getString("description"));
    	article.setArticlePubDate(rs.getDate("pubdate"));
    	article.setArticleLink(rs.getString("link"));
    	return article;
    }
    
    public List<ArticleSummary> findArticleSummary() {
        List<ArticleSummary> list = new ArrayList<ArticleSummary>();
        Connection c = null;
    	
        String sql = "SELECT all_arts.pubname, all_articles, nvl(market_arts.mark_articles,0) mark_articles " + 
        				" FROM (  " + 
        						" SELECT count(title) all_articles, pubname  " +  
        						" FROM table(fca_ds_rpt.pck_rss_search.search_rss(7,'','RSS'))  " + 
        						" GROUP BY pubname ) all_arts,  " + 
        						" (SELECT count(title) mark_articles, pubname   " + 
        						" FROM table(fca_ds_rpt.pck_rss_search.meta_search_rss(7,'Retirement','Retirement'))  " + 
        						" GROUP BY pubname ) market_arts  " + 
        						" WHERE all_arts.pubname = market_arts.pubname (+) ";    	
        
        try {
            c = SQLConnection.getConnection();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                list.add(processArticleSummaryRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			SQLConnection.close(c);
		}
        return list;
    }
    
    protected ArticleSummary processArticleSummaryRow(ResultSet rs) throws SQLException {
    	ArticleSummary articlesum = new ArticleSummary();
    	articlesum.setPubName(rs.getString("pubname"));
    	articlesum.setAllArticleCount(rs.getInt("all_articles"));
    	articlesum.setMarketArticleCount(rs.getInt("mark_articles"));
    	return articlesum;
    }
    
}
