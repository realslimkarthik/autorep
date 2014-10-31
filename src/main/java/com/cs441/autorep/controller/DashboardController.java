package com.cs441.autorep.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cs441.autorep.interfaces.InventoryManager;

@Controller
public class DashboardController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	InventoryManager inventoryManager;
	
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String home(Model model) throws Exception {
		logger.info("Welcome to dashboard");
		
		System.out.println( inventoryManager.getInventory().size() );
		
		model.addAttribute("test", "testString" );
		
		return "dashboard";
	}
	
}