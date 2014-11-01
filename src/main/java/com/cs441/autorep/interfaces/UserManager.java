package com.cs441.autorep.interfaces;

import java.util.ArrayList;

import com.cs441.autorep.model.Inventory;

public interface UserManager {

	public ArrayList<String> getUserStoreId(String userId) throws Exception;
	
}
