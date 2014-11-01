package com.cs441.autorep.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.cs441.autorep.interfaces.Notify;
import com.cs441.autorep.model.Suggestions;
import com.cs441.autorep.model.WarehouseSku;

public class NotifyImpl implements Notify{

	@Override
	public String insertToRepSuggestions(Suggestions[] suggestions)
			throws Exception {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			con = ConnectionFactory.getConnection();

			for(int i=0; i<suggestions.length; i++){
				
				int storeId = Integer.parseInt(suggestions[i].getStore());
				
				for(int j=0; j<suggestions[i].getProdList().length; j++){
					
					ps = con.prepareStatement("SELECT `warehousesku`.`id`,`warehousesku`.`Product_id`,"
							+ "`warehousesku`.`Warehouse_id`,`warehousesku`.`packSize`,`warehousesku`.`expiryDate`,"
							+ "`warehousesku`.`discount`,`warehousesku`.`dateOfMf`,`warehousesku`.`mrp`,"
							+ "`warehousesku`.`unitPrice`,`warehousesku`.`weight`,`warehousesku`.`note`,"
							+ "`warehousesku`.`Vendor_id` FROM `autorep`.`warehousesku` where Product_id=?");
					
					ps.setInt(1, Integer.parseInt( suggestions[i].getProdList()[j].getProduct() ) );
					
					rs = ps.executeQuery();
					
					int count = 0;
					
					while(rs.next()){
						
						count ++;
						
						if(count > Integer.parseInt(suggestions[i].getProdList()[j].getCount() ) ){
							break;
						}
						
						WarehouseSku sku = new WarehouseSku();
						
						sku.setStoreId(storeId);
						
						sku.setSkuId(rs.getInt(1));
						sku.setProductId(rs.getInt(2));
						sku.setWarehouseId(rs.getInt(3));
						sku.setPackSize(rs.getInt(4));
						sku.setExpiryDate(rs.getDate(5));
						sku.setDiscount(rs.getInt(6));
						sku.setDateOfMf(rs.getDate(7));
						sku.setMrp(rs.getInt(8));
						sku.setUnitPrice(rs.getInt(9));
						sku.setWeight(rs.getInt(10));
						sku.setNote(rs.getString(11));
						sku.setVendorId(rs.getInt(12));
						
						System.out.println("-- Warehouse SKU `"+sku.getSkuId()+"` is being replenished to store `"+sku.getStoreId()+"`."
								+ " It has product `"+sku.getProductId()+"` --");
					}

				}
			}
			
		}
		finally{
			con.close();
			ps.close();
			rs.close();
		}
		return null;
	}

}
