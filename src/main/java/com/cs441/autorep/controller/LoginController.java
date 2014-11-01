package com.cs441.autorep.controller;

import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mongodb.DB;
import com.mongodb.MongoClient;

/**
 * Handles requests for the application home page.
 */
@Controller
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws UnknownHostException 
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String home(HttpServletRequest request, HttpServletResponse response) throws UnknownHostException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		DB db = (new MongoClient("54.201.247.141",27017)).getDB("products");
		if(db.authenticate(username, password.toCharArray())){
			return "dashboard";
		}
		else
			return "login";
		
		
		
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String homeShow(HttpServletRequest request, HttpServletResponse response) {
		
	
		return "login";
	}
	
}
