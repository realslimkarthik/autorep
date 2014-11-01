package com.cs441.autorep.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Suggestions {
	
	@JsonProperty("store")
	private String store;
	
	@JsonProperty("prodList")
	private List<ProdList> prodList;

	public String getStore() {
		return store;
	}

	@JsonProperty("store")
	public void setStore(String store) {
		this.store = store;
	}

	public List<ProdList> getProdList() {
		return prodList;
	}

	@JsonProperty("prodList")
	public void setProdList(List<ProdList> prodList) {
		this.prodList = prodList;
	}
	
}
