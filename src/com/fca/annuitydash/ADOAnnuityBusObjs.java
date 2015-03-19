package com.fca.annuitydash;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.fca.utils.SQLConnection;

public class ADOAnnuityBusObjs {
	
    public List<JSONProviderRange> findAllProviderCnt() {
        List<JSONProviderRange> list = new ArrayList<JSONProviderRange>();
        Connection c = null;
    	 
    	 String sql = "SELECT provider, product_cnt " + 
    	  "FROM table(fca_ds_rpt.pck_con_annuity_dash.provider_range('23-JAN-15'))"; 
        
        try {
            c = SQLConnection.getConnection();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                list.add(processProviderRangeRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			SQLConnection.close(c);
		}
        return list;
    }
    
    protected JSONProviderRange processProviderRangeRow(ResultSet rs) throws SQLException {
    	JSONProviderRange providerrange = new JSONProviderRange();

    	providerrange.setProvider(rs.getString("provider"));
    	providerrange.setProductCount(rs.getInt("product_cnt"));
    	
    	return providerrange;
    }
    
    public List<JSONProdProviderTime> findProdProviderTime() {
        List<JSONProdProviderTime> list = new ArrayList<JSONProdProviderTime>();
        Connection c = null;
    	 
    	 String sql = "SELECT yearmonth, provider_cnt, product_cnt " + 
    	  "FROM table(fca_ds_rpt.pck_con_annuity_dash.prod_prov_time('Conventional'))"; 
        
        try {
            c = SQLConnection.getConnection();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                list.add(processProdProvTimeRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			SQLConnection.close(c);
		}
        return list;
    }
    
    protected JSONProdProviderTime processProdProvTimeRow(ResultSet rs) throws SQLException {
    	JSONProdProviderTime prodProviderTime = new JSONProdProviderTime();
    	prodProviderTime.setYearMonth(rs.getDate("yearmonth"));
    	prodProviderTime.setProviderCount(rs.getInt("provider_cnt"));
    	prodProviderTime.setProductCount(rs.getInt("product_cnt"));
    	return prodProviderTime;
    }
    
    public List<JSONProductChanges> findProductChanges() {
        List<JSONProductChanges> list = new ArrayList<JSONProductChanges>();
        Connection c = null;
    	 
    	 String sql = "SELECT product, provider, z_xml_data_date, changes " + 
    	  "FROM table(fca_ds_rpt.pck_con_annuity_dash.product_changes('PANN'))"; 
        
        try {
            c = SQLConnection.getConnection();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                list.add(processProductChanges(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			SQLConnection.close(c);
		}
        return list;
    }
    
    protected JSONProductChanges processProductChanges(ResultSet rs) throws SQLException {
    	JSONProductChanges productChanges = new JSONProductChanges();
    	productChanges.setProduct(rs.getString("product"));
    	productChanges.setProvider(rs.getString("provider"));
    	productChanges.setChangeDate(rs.getDate("z_xml_data_date"));
    	productChanges.setChangeType(rs.getString("changes"));
    	return productChanges;
    }

    public List<JSONAnnuityOptions> findAnnuityOptions() {
        List<JSONAnnuityOptions> list = new ArrayList<JSONAnnuityOptions>();
        Connection c = null;
    	 
    	 String sql = "SELECT * " + 
    	  "FROM table(fca_ds_rpt.pck_con_annuity_dash.ca_product_matrix())"; 
        
        try {
            c = SQLConnection.getConnection();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                list.add(processAnnuityOptions(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			SQLConnection.close(c);
		}
        return list;
    }
    
    protected JSONAnnuityOptions processAnnuityOptions(ResultSet rs) throws SQLException {
    	JSONAnnuityOptions conAnnuityOptions = new JSONAnnuityOptions();
    	conAnnuityOptions.setProduct(rs.getString("product"));
    	conAnnuityOptions.setProvider(rs.getString("provider"));
    	conAnnuityOptions.setImpaired(rs.getInt("impaired"));
    	conAnnuityOptions.setEnhanced(rs.getInt("enhanced"));
    	conAnnuityOptions.setEscalating(rs.getInt("escalating"));
    	conAnnuityOptions.setJoint(rs.getInt("joint"));
    	conAnnuityOptions.setSingle(rs.getInt("single"));
    	conAnnuityOptions.setDeferred(rs.getInt("deferred"));
    	conAnnuityOptions.setAdvised(rs.getInt("advised")); 	
    	return conAnnuityOptions;
    }   
    
    public List<JSONFeatureChange> findFeatureChanges() {
        List<JSONFeatureChange> list = new ArrayList<JSONFeatureChange>();
        Connection c = null;
    	 
    	 String sql = "SELECT * " + 
    	  "FROM table(fca_ds_rpt.pck_con_annuity_dash.feature_change('PANN'))"; 
        
        try {
            c = SQLConnection.getConnection();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                list.add(processFeatureChanges(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			SQLConnection.close(c);
		}
        return list;
    }
    
    protected JSONFeatureChange processFeatureChanges(ResultSet rs) throws SQLException {
    	JSONFeatureChange featureChange = new JSONFeatureChange();
    	featureChange.setProduct(rs.getString("product"));
    	featureChange.setProvider(rs.getString("provider"));        
    	featureChange.setSubCategory(rs.getString("sub_category"));
    	featureChange.setFeature(rs.getString("feature"));
    	featureChange.setFeatureDate(rs.getDate("date_key"));
    	featureChange.setCurrentValue(rs.getString("current_value"));
    	featureChange.setPreviousValue(rs.getString("previous_value"));
	
    	return featureChange;
    }   
    
    public List<JSONTopFeatureChange> findTopFeatureChanges() {
        List<JSONTopFeatureChange> list = new ArrayList<JSONTopFeatureChange>();
        Connection c = null;
    	 
    	 String sql = "SELECT * " + 
    	  "FROM table(fca_ds_rpt.pck_con_annuity_dash.top_feature_change('PANN'))"; 
        
        try {
            c = SQLConnection.getConnection();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                list.add(processTopFeatureChanges(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			SQLConnection.close(c);
		}
        return list;
    }
    
    protected JSONTopFeatureChange processTopFeatureChanges(ResultSet rs) throws SQLException {
    	JSONTopFeatureChange topFeatureChange = new JSONTopFeatureChange();
    	topFeatureChange.setSubCategory(rs.getString("sub_category"));
    	topFeatureChange.setFeature(rs.getString("feature"));
    	topFeatureChange.setChangeCount(rs.getInt("change_count"));
    	return topFeatureChange;
    }   


}
