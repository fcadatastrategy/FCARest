package com.fca.searchdash;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fca.annuitydash.JSONProvider;
import com.fca.utils.Advertiser;
import com.fca.utils.SQLConnection;

public class ADOSearchBusObjs {
	
    
    protected JSONProvider processProviderRow(ResultSet rs) throws SQLException {
    	JSONProvider provider = new JSONProvider();
    	provider.setId(rs.getInt("id"));
    	provider.setProviderName(rs.getString("ProviderName"));
    	provider.setProductName(rs.getString("Product"));
    	return provider;
    }

/*    public List<Advertiser> findAllAdvertisers() {
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
    */
    
    public List<JSONArticle> findAllMarketArticles() {
        List<JSONArticle> list = new ArrayList<JSONArticle>();
        Connection c = null;
    	 
    	 String sql = "SELECT DISTINCT title, description, pubdate, url link " + 
    	  "FROM table(fca_ds_rpt.pck_rss_search.meta_search_rss(30,'Retirement','Retirement'))"; 
        
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
    
    public List<JSONArticle> findAllProdProviderArticles() {
        List<JSONArticle> list = new ArrayList<JSONArticle>();
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
    
    public List<JSONArticle> findKeywordArticles(String srchstr) {
        List<JSONArticle> list = new ArrayList<JSONArticle>();
        Connection c = null;
    	
        String sql = "SELECT title, description, pubdate, url link " + 
    	  "FROM table(fca_ds_rpt.pck_rss_search.search_rss(30,'" + srchstr.toUpperCase() + "','RSS'))  ";    	
        
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
    
    protected JSONArticle processArticleRow(ResultSet rs) throws SQLException {
    	JSONArticle article = new JSONArticle();
    	article.setArticleTitle(rs.getString("title"));
    	article.setArticleDescription(rs.getString("description"));
    	article.setArticlePubDate(rs.getDate("pubdate"));
    	article.setArticleLink(rs.getString("link"));
    	return article;
    }
    
    public List<JSONArticleSummary> findArticleSummary() {
        List<JSONArticleSummary> list = new ArrayList<JSONArticleSummary>();
        Connection c = null;
    	
        String sql = "SELECT all_arts.pubname, all_articles, nvl(market_arts.mark_articles,0) mark_articles, icon.z_org_img_link PubImg, icon.z_org_website_link PubURL " + 
        				" FROM (  " + 
        						" SELECT count(title) all_articles, pubname  " +  
        						" FROM table(fca_ds_rpt.pck_rss_search.search_rss(7,'','RSS'))  " + 
        						" GROUP BY pubname ) all_arts,  " + 
        						" (SELECT count(title) mark_articles, pubname   " + 
        						" FROM table(fca_ds_rpt.pck_rss_search.meta_search_rss(7,'Retirement','Retirement'))  " + 
        						" GROUP BY pubname ) market_arts, " + 
								" fca_ds_rpt.z_rss_org_icons icon  " + 
								" WHERE all_arts.pubname = icon.z_xml_src_desc " + 
        						" AND all_arts.pubname = market_arts.pubname (+) ";    	
        
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
    
    protected JSONArticleSummary processArticleSummaryRow(ResultSet rs) throws SQLException {
    	JSONArticleSummary articlesum = new JSONArticleSummary();
    	articlesum.setPubName(rs.getString("pubname"));
    	articlesum.setAllArticleCount(rs.getInt("all_articles"));
    	articlesum.setMarketArticleCount(rs.getInt("mark_articles"));
    	articlesum.setPubImg(rs.getString("PubImg"));
    	articlesum.setPubURL(rs.getString("PubURL"));
    	return articlesum;
    }
    
}
