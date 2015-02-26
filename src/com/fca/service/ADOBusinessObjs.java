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
    
    
    public List<Article> findAllArticles() {
        List<Article> list = new ArrayList<Article>();
        Connection c = null;
    	String sql = "SELECT title, description, to_char(pub_date) pubdate " +
			"FROM FCA_DS_RPT.R_RSS_FEED  " + 
    	    "WHERE UPPER(title) LIKE '%SIPP%' ";
        
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
    	String sql = "SELECT title, description, pub_date pubdate, link link " +
			"FROM FCA_DS_RPT.R_RSS_FEED  " + 
    	    "WHERE UPPER(title) LIKE '%" + srchstr.toUpperCase() + "%' ";
        
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
    
}
