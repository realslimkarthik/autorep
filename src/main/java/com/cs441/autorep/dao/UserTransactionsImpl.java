package com.cs441.autorep.dao;

import java.net.UnknownHostException;

import com.cs441.autorep.interfaces.UserTransactions;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.util.JSON;

public class UserTransactionsImpl implements UserTransactions	 {

	public void UpdateLogs(String userlog)
		{
			
			
		 try{
			 
			 DB db = (new MongoClient("54.201.247.141",27017)).getDB("usertrans");
			 DBCollection collection = db.getCollection("tranlogs");
			 DBObject dbobject = (DBObject) JSON.parse(userlog);
			 collection.insert(dbobject);
			
			System.out.println("Done");
		 }
		 catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (MongoException e) {
				e.printStackTrace();
			}
		
		
	}
}
