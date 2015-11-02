package com.fca.prodtrackerdash;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fca.utils.SQLConnection;

public class ADOProdTrackerBusObjs {

	public List<JSONRetirementTracker> findAllRetirementTracker(String dataDates) {
		List<JSONRetirementTracker> list = new ArrayList<JSONRetirementTracker>();
		Connection c = null;

	    String dataDate;
	    String compDate;
	    
	    String[] parts = dataDates.split("&");
	    dataDate = parts[0];
	    compDate = parts[1];
		
		String sql = "SELECT * FROM table(fca_ds_rpt.pck_ret_prod_tracker.gen_product_tracker_dash('" + dataDate.toUpperCase() + "','" + compDate.toUpperCase() + "'))";

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

	protected JSONRetirementTracker processProviderRangeRow(ResultSet rs)
			throws SQLException {
		JSONRetirementTracker retirementtracker = new JSONRetirementTracker();
		
		retirementtracker.setNode(rs.getString("NODE"));
		retirementtracker.setProductCount(rs.getInt("PRODUCT_COUNT"));
		retirementtracker.setProviderCount(rs.getInt("PROVIDER_COUNT"));
		retirementtracker.setCompProductCount(rs.getInt("PRODUCT_COMP_CNT"));
		retirementtracker.setCompProviderCount(rs.getInt("PROVIDER_COMP_CNT"));	
		retirementtracker.setNodeparent(rs.getString("PARENT_NODE"));
		retirementtracker.setTooltip(rs.getString("TOOLTIP"));

		return retirementtracker;
	}

	public List<JSONProductDates> findAllDataDates() {

		List<JSONProductDates> list = new ArrayList<JSONProductDates>();
		Connection c = null;

		String sql = "SELECT * FROM table(fca_ds_rpt.pck_gen_dashboard_export.gen_product_trackdates('RETIREMENT'))";

		try {
			c = SQLConnection.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				list.add(processDataDates(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			SQLConnection.close(c);
		}
		return list;
	}

	protected JSONProductDates processDataDates(ResultSet rs)
			throws SQLException {
		JSONProductDates productdates = new JSONProductDates();

		productdates.setDatadate(rs.getString("datadate"));
		productdates.setId(rs.getInt("id"));

		return productdates;
	}
	
	
	public List<JSONProdProvider> findProdProviders(String selections) {
		List<JSONProdProvider> list = new ArrayList<JSONProdProvider>();
		Connection c = null;
	    String selNode;
	    String dataDate;
	    
	    String[] parts = selections.split("--");
	    selNode = parts[0];
	    dataDate = parts[1];
	    
		String sql = "SELECT * FROM table(fca_ds_rpt.pck_ret_prod_tracker.gen_products_providers('" + selNode.toUpperCase() + "','" + dataDate.toUpperCase() + "'))";

		try {
			c = SQLConnection.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				list.add(processProductprovider(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			SQLConnection.close(c);
		}
		return list;
	}

	protected JSONProdProvider processProductprovider(ResultSet rs)
			throws SQLException {
		JSONProdProvider prodprovider = new JSONProdProvider();

		prodprovider.setProduct(rs.getString("PRODUCT"));
		prodprovider.setProvider(rs.getString("PROVIDER"));

		return prodprovider;
	}

}
