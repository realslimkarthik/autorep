package com.cs441.autorep.dao;

import java.util.ArrayList;

import com.cs441.autorep.interfaces.InventoryManager;
import com.cs441.autorep.model.Inventory;

public class InventoryManagerImpl implements InventoryManager{

	@Override
	public ArrayList<Inventory> getInventory() {
		
		Inventory i = new Inventory();
		i.setProductId(1);
		i.setProductName("Soap");
		
		ArrayList<Inventory> a = new ArrayList<Inventory>();
		a.add(i);
		
		return a;
	}

}
