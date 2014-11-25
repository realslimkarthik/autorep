package com.cs441.autorep.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cs441.autorep.dao.ConnectionFactory;
import com.cs441.autorep.interfaces.UserManager;

@Controller
public class StockController {
	
	private static final Logger logger = LoggerFactory.getLogger(StockController.class);
	
	@Autowired
	UserManager userManager;
	
	@RequestMapping(value = "/stockInventory", method = RequestMethod.POST)
	public ArrayList<HashMap<String, Integer>> showDashboard(HttpServletRequest req, HttpServletResponse res) throws Exception {
		logger.info("Welcome to dashboard");
		
		Connection con = null;
		PreparedStatement ps = null;
		ArrayList<ResultSet> rsArray = new ArrayList<ResultSet>();
		ResultSet rs = null;
		
		ArrayList<HashMap<String, Integer>> results = new ArrayList<HashMap<String,Integer>>();
		
		HttpSession session = req.getSession();
		session.getAttribute("userId");
		
		ArrayList<String> storeList = userManager.getUserStoreId((String)session.getAttribute("userId"));
		
		String query = "SELECT name, count(id) FROM autorep.sku where store_id = ?";
		con = ConnectionFactory.getConnection();
		ps = con.prepareStatement(query);
		
		HashMap<String, Integer> result = new HashMap<String, Integer>();
		for(String store: storeList) {
			ps.setInt(1, Integer.parseInt(store));
			rs = ps.executeQuery();
			rsArray.add(rs);
		}
		
		for(ResultSet r: rsArray) {
			result = new HashMap<String, Integer>();
			result.put(r.getString(1), Integer.parseInt(r.getString(2)));
			results.add(result);
		}
		
		return results;
	}
}
