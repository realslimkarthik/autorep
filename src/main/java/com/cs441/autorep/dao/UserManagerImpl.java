package com.cs441.autorep.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.cs441.autorep.interfaces.UserManager;

public class UserManagerImpl implements UserManager {

	@Override
	public ArrayList<String> getUserStoreId(String userId) throws Exception {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		ArrayList<String> results;

		try {

			con = ConnectionFactory.getConnection();

			ps = con.prepareStatement("select Store_id from autorep.managerpermissions where Manager_id = ?");

			ps.setInt(1, Integer.parseInt(userId));
			rs = ps.executeQuery();

			results = new ArrayList<String>();

			while (rs.next()) {
				results.add(rs.getString("Store_id"));
			}

		}

		finally {
			con.close();
			ps.close();
			rs.close();
		}

		return results;
	}

}
