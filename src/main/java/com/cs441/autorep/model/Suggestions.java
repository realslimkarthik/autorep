package com.cs441.autorep.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Suggestions {
	
	private String store;
	
	private ProdList[] prodList;

	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}

	public ProdList[] getProdList() {
		return prodList;
	}

	public void setProdList(ProdList[] prodList) {
		this.prodList = prodList;
	}

	
}
