package com.fca.consumerdash;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fca.utils.SQLConnection;

public class ADOConsumerBusObjs {

	public List<JSONConsumerOption> findAllConsumerChoice(String crossTabOption) {
		List<JSONConsumerOption> list = new ArrayList<JSONConsumerOption>();
		Connection c = null;

		String sql = "SELECT * FROM table(fca_ds_rpt.pck_ret_questionaire.gen_questionaire_dash('" + crossTabOption.toUpperCase() + "'))";

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

	protected JSONConsumerOption processProviderRangeRow(ResultSet rs)
			throws SQLException {
		JSONConsumerOption consumerOption = new JSONConsumerOption();
		
		consumerOption.setWeightedVal(rs.getInt("WEIGHTED_VAL"));
		consumerOption.setWeightedAvg(rs.getInt("WEIGHTED_AVG"));
		consumerOption.setChoiceDescription(rs.getString("DESCRIPTION"));
		consumerOption.setCrossTab(rs.getString("CROSSTAB"));

		return consumerOption;
	}



}
