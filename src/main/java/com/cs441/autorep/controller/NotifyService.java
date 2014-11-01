package com.cs441.autorep.controller;

import java.net.URLDecoder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cs441.autorep.model.Data;
import com.cs441.autorep.model.ProdList;
import com.cs441.autorep.model.SuggestionJson;
import com.cs441.autorep.model.Suggestions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@Controller
public class NotifyService {
	
	@RequestMapping(value = "/notify", method = RequestMethod.POST)
	@ResponseBody
	public String insertUser(@RequestBody String jsonString) throws Exception {
	    
		String decodedUrl = URLDecoder.decode( jsonString, "UTF-8" );
		decodedUrl = decodedUrl.substring(0,decodedUrl.length()-1);
		
	    //System.out.println(decodedUrl.substring(0,decodedUrl.length()-1));
	    
	    Gson gson = new GsonBuilder().create();
        SuggestionJson suggestions = gson.fromJson(decodedUrl, SuggestionJson.class);
        //System.out.println(suggestions.getData().getSuggestions().length);
        
        Suggestions[] s = suggestions.getData().getSuggestions();
        
        for(int i=0; i<s.length; i++){
        	String storeId = s[i].getStore();
        	System.out.println(storeId);
        	
        	for(int j=0; j<s[i].getProdList().length; j++){
        		String product = s[i].getProdList()[j].getProduct();
        		String count = s[i].getProdList()[j].getCount();
        		
        		System.out.println("\t"+product);
        		System.out.println("\t"+count);
        	}
        }
	    
	    return "hahahaha";
	    
	}
	
	
}
