package com.cs441.autorep.controller;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cs441.autorep.interfaces.InventoryManager;
import com.cs441.autorep.interfaces.UserManager;

@Controller
public class DashboardController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	UserManager userManager;
	
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView showDashboard(HttpServletRequest req, HttpServletResponse res) throws Exception {
		logger.info("Welcome to dashboard");
		
		HttpSession session = req.getSession();
		session.setAttribute("userId", "1");
		
		ArrayList<String> storeList = userManager.getUserStoreId((String)session.getAttribute("userId"));
		
		ModelAndView model = new ModelAndView("dashboard");
		model.addObject("storeList", storeList );
		
		return model;
	}
	
}