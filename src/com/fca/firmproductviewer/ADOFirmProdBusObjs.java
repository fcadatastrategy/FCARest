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
	    
	    String[] parts = dataSelections.split("&");
	    dataDate = parts[0];
	    market = parts[1];
		
		String sql = "SELECT * FROM table(fca_ds_rpt.pck_firm_product_viewer.gen_firm_view('" + dataDate.toUpperCase() + "','" + market.toUpperCase() + "','NA'))";
	
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
		firm.setFirmDataDate(rs.getString("FIRM_PRODUCT_COUNT"));
		
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

}
