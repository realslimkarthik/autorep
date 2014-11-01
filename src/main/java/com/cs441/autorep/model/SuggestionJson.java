package com.cs441.autorep.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SuggestionJson {

	@JsonProperty("data")
	private JsonData jsonData;

	public JsonData getJsonData() {
		return jsonData;
	}

	@JsonProperty("data")
	public void setJsonData(JsonData jsonData) {
		this.jsonData = jsonData;
	}
	
}
