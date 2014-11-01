package com.cs441.autorep.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProdList implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("product")
	private String productId;
	
	@JsonProperty("count")
	private int count;
	
	public String getProductId() {
		return productId;
	}
	
	@JsonProperty("product")
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public int getCount() {
		return count;
	}
	
	@JsonProperty("count")
	public void setCount(int count) {
		this.count = count;
	}
	
}
