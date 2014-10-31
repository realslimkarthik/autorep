package com.cs441.autorep.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.cs441.autorep.interfaces.InventoryManager;
import com.cs441.autorep.model.Inventory;

public class InventoryManagerImpl implements InventoryManager{

	@Override
	public ArrayList<Inventory> getInventory() throws Exception{
		
		Connection con = ConnectionFactory.getConnection();
		
		PreparedStatement ps = con.prepareStatement("select 1 from DUAL");
		
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()){
			System.out.println(rs.getString(1)+" Connecting to DB ");
		}
		
		Inventory i = new Inventory();
		i.setProductId(1);
		i.setProductName("Soap");
		
		ArrayList<Inventory> a = new ArrayList<Inventory>();
		a.add(i);
		
		return a;
	}

}
