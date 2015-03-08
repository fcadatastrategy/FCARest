package com.fca.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fca.retdash.*;

public class ADOConAnnuityBusObjs {
	
    public List<ProviderRange> findAllProviderCnt() {
        List<ProviderRange> list = new ArrayList<ProviderRange>();
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
    
    protected ProviderRange processProviderRangeRow(ResultSet rs) throws SQLException {
    	ProviderRange providerrange = new ProviderRange();

    	providerrange.setProvider(rs.getString("provider"));
    	providerrange.setProductCount(rs.getInt("product_cnt"));
    	
    	return providerrange;
    }
    
    public List<ProdProviderTime> findProdProviderTime() {
        List<ProdProviderTime> list = new ArrayList<ProdProviderTime>();
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
    
    protected ProdProviderTime processProdProvTimeRow(ResultSet rs) throws SQLException {
    	ProdProviderTime prodProviderTime = new ProdProviderTime();
    	prodProviderTime.setYearMonth(rs.getDate("yearmonth"));
    	prodProviderTime.setProviderCount(rs.getInt("provider_cnt"));
    	prodProviderTime.setProductCount(rs.getInt("product_cnt"));
    	return prodProviderTime;
    }
    
    public List<ProductChanges> findProductChanges() {
        List<ProductChanges> list = new ArrayList<ProductChanges>();
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
    
    protected ProductChanges processProductChanges(ResultSet rs) throws SQLException {
    	ProductChanges productChanges = new ProductChanges();
    	productChanges.setProduct(rs.getString("product"));
    	productChanges.setProvider(rs.getString("provider"));
    	productChanges.setChangeDate(rs.getDate("z_xml_data_date"));
    	productChanges.setChangeType(rs.getString("changes"));
    	return productChanges;
    }

    public List<ConAnnuityOptions> findAnnuityOptions() {
        List<ConAnnuityOptions> list = new ArrayList<ConAnnuityOptions>();
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
    
    protected ConAnnuityOptions processAnnuityOptions(ResultSet rs) throws SQLException {
    	ConAnnuityOptions conAnnuityOptions = new ConAnnuityOptions();
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
    
    public List<FeatureChange> findFeatureChanges() {
        List<FeatureChange> list = new ArrayList<FeatureChange>();
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
    
    protected FeatureChange processFeatureChanges(ResultSet rs) throws SQLException {
    	FeatureChange featureChange = new FeatureChange();
    	featureChange.setProduct(rs.getString("product"));
    	featureChange.setProvider(rs.getString("provider"));        
    	featureChange.setSubCategory(rs.getString("sub_category"));
    	featureChange.setFeature(rs.getString("feature"));
    	featureChange.setFeatureDate(rs.getDate("date_key"));
    	featureChange.setCurrentValue(rs.getString("current_value"));
    	featureChange.setPreviousValue(rs.getString("previous_value"));
	
    	return featureChange;
    }   
    
    public List<TopFeatureChange> findTopFeatureChanges() {
        List<TopFeatureChange> list = new ArrayList<TopFeatureChange>();
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
    
    protected TopFeatureChange processTopFeatureChanges(ResultSet rs) throws SQLException {
    	TopFeatureChange topFeatureChange = new TopFeatureChange();
    	topFeatureChange.setSubCategory(rs.getString("sub_category"));
    	topFeatureChange.setFeature(rs.getString("feature"));
    	topFeatureChange.setChangeCount(rs.getInt("change_count"));
    	return topFeatureChange;
    }   


}
