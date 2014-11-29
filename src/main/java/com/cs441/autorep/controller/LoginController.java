package com.cs441.autorep.controller;

import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cs441.autorep.interfaces.UserManager;
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
	
	@Autowired
	UserManager userManager;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String home(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		DB db = (new MongoClient("54.172.105.21",27017)).getDB("products");
		if(db.authenticate(username, password.toCharArray())){

			int userId = userManager.getUserId(username);
			request.getSession().setAttribute("userId", String.valueOf(userId));

			response.sendRedirect("/autorep/dashboard");
		}
		else
			return "login";

		return null;



	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String homeShow(HttpServletRequest request, HttpServletResponse response) {


		return "login";
	}

}
