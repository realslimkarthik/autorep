package com.cs441.autorep.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cs441.autorep.interfaces.UserManager;

@Controller
public class UserTranLogsController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserTranLogsController.class);
	
	@Autowired
	UserManager userManager;
	

	@RequestMapping(value = "/utlogs", method = RequestMethod.GET)
	public ModelAndView showUTLogs(HttpServletRequest req, HttpServletResponse res) throws Exception {
		logger.info("Welcome to UTlogs");
	
		HttpSession session = req.getSession();
		//String usid = (String)session.getAttribute("userId");
		
		ArrayList<String> storeList = userManager.getUserStoreId((String)session.getAttribute("userId"));
		String currentStore = storeList.get(0);
		
		ModelAndView model = new ModelAndView("utlogs");
		//model.addObject("usid", usid );
		model.addObject("storeList", storeList );
		model.addObject("currentStore",currentStore);
		//model.addObject("inventoryList",getInventoryList(currentStore));
	//	model.addObject("currentStore",currentStore);
		
		return model;
	}
}
