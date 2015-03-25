package com.fca.prodtrackerdash;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.fca.utils.SQLConnection;

public class ADOProdTrackerBusObjs {

	public List<JSONRetirementTracker> findAllRetirementTracker(String dataDate) {
		List<JSONRetirementTracker> list = new ArrayList<JSONRetirementTracker>();
		Connection c = null;

		String sql = "SELECT * FROM table(fca_ds_rpt.pck_gen_dashboard_export.gen_product_tracker_dash('" + dataDate.toUpperCase() + "'))";

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

}
