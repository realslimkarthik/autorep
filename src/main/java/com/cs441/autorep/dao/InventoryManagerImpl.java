package com.cs441.autorep.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.cs441.autorep.interfaces.InventoryManager;
import com.cs441.autorep.model.Inventory;

public class InventoryManagerImpl implements InventoryManager{

	@Override
	public ArrayList<Inventory> getInventory(String storeId) throws Exception{
		
		Connection con = ConnectionFactory.getConnection();
		
		String query = "SELECT s.`id`, s.`Product_id`, p.name,  s.`packSize`, s.`expiryDate`, s.`discount`, s.`dateOfMf`, "
				+ "s.`mrp`, s.`unitPrice`,s.`weight`, s.`note`, s.`Vendor_id`, s.`Warehouse_id` FROM `autorep`.`sku` s "
				+ "left outer join `autorep`.`product` p on s.`Product_id`=p.`id` where s.Store_id = ? order by product_id";
		
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, Integer.parseInt(storeId));
		
		ResultSet rs = ps.executeQuery();
		
		ArrayList<Inventory> results = new ArrayList<Inventory>();
		
		while(rs.next()){
			Inventory i = new Inventory();
			
			i.setSkuId(rs.getString(1));
			i.setProductId(rs.getString(2));
			i.setProductName(rs.getString(3));
			i.setPackSize(rs.getString(4));
			i.setExpiryDate(rs.getString(5));
			i.setDiscount(rs.getString(6));
			
			String dof = rs.getString(7);
			i.setDateOfMf(dof.substring(0,dof.lastIndexOf(" ")));
			
			i.setMrp(rs.getString(8));
			i.setUnitPrice(rs.getString(9));
			i.setWeight(rs.getString(10));
			i.setNote(rs.getString(11));
			i.setVendorId(rs.getString(12));
			i.setWarehouseId(rs.getString(13));
			
			results.add(i);
		}
		
		con.close();
		
		return results;
	}

}
