package com.fca.firmproductviewer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fca.prodtrackerdash.JSONProductDates;
import com.fca.utils.SQLConnection;

public class ADOFirmProdBusObjs {

	public List<JSONFirm> findAllFirms(String dataSelections) {
		List<JSONFirm> list = new ArrayList<JSONFirm>();
		Connection c = null;
		
	    String dataDate;
	    String market;
	    String firmType;
	    
	    String[] parts = dataSelections.split("&");
	    dataDate = parts[0];
	    market = parts[1];
	    firmType = parts[2];
		
		String sql = "SELECT * FROM table(fca_ds_rpt.pck_firm_product_viewer.gen_firm_view('" + dataDate.toUpperCase() + "','" + market.toUpperCase() + "','" + firmType.toUpperCase() + "'))";
	
		try {
			c = SQLConnection.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				list.add(processFirmRow(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			SQLConnection.close(c);
		}
		return list;
	}

	protected JSONFirm processFirmRow(ResultSet rs)
			throws SQLException {
		JSONFirm firm = new JSONFirm();
		
		firm.setFirmId(rs.getString("FIRM_ID"));
		firm.setFirmName(rs.getString("FIRM_NAME"));
		firm.setFirmMarket(rs.getString("FIRM_MARKET"));
		firm.setFirmType(rs.getString("FIRM_TYPE"));
		firm.setFirmDataDate(rs.getString("FIRM_DATA_DATE"));
		firm.setFIrmProductCount(rs.getInt("FIRM_PRODUCT_COUNT"));
		
		return firm;
	}


	public List<JSONFirmProdDate> findAllDatesByMarket(String market) {

		List<JSONFirmProdDate> list = new ArrayList<JSONFirmProdDate>();
		Connection c = null;

		String sql = "SELECT * FROM table(fca_ds_rpt.pck_firm_product_viewer.gen_avail_dates('" + market + "'))";

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

	protected JSONFirmProdDate processDataDates(ResultSet rs)
			throws SQLException {
		JSONFirmProdDate firmproddates = new JSONFirmProdDate();

		firmproddates.setDatadate(rs.getString("datadate"));
		firmproddates.setId(rs.getInt("id"));

		return firmproddates;
	}

	public List<JSONProduct> findProductByFirm(String dataSelections) {

		List<JSONProduct> list = new ArrayList<JSONProduct>();
		Connection c = null;
		
		String dataDate, market, firmName, firmType, firmId;
		
	    String[] parts = dataSelections.split("&");
	    dataDate = parts[0];
	    market = parts[1];
	    firmName = parts[2];
	    firmType = parts[3];
	    firmId  = parts[4];	    
	    	      
		String sql = "SELECT * FROM table(fca_ds_rpt.pck_firm_product_viewer.gen_get_product('" + dataDate + "','" + firmName + "','" + firmType + "','" + market + "','"  + firmId + "'))";
		
		try {
			c = SQLConnection.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				list.add(processProducts(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			SQLConnection.close(c);
		}
		return list;
	}

	protected JSONProduct processProducts(ResultSet rs)
			throws SQLException {
		JSONProduct products = new JSONProduct();

		products.setProductId(rs.getString("PRODUCT_ID"));
		products.setProductBK(rs.getString("PRODUCT_BK"));
		products.setProductName(rs.getString("PRODUCT_NAME"));		

		return products;
	}
	
	
}
