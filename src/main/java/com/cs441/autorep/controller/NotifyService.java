package com.cs441.autorep.controller;

import java.net.URLDecoder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class NotifyService {
	
	@RequestMapping(value = "/notify", method = RequestMethod.POST)
	@ResponseBody
	public String insertUser(@RequestBody String jsonString) throws Exception {
	    
		String decodedUrl = URLDecoder.decode( jsonString, "UTF-8" );
		
	    System.out.println(decodedUrl.substring(0,decodedUrl.length()-1));
	    return null;
	    
	}
	
}
