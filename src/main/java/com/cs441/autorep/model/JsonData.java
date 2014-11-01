package com.cs441.autorep.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonData {
	
	@JsonProperty("suggestions")
	private List<Suggestions> suggestions;

	public List<Suggestions> getSuggestions() {
		return suggestions;
	}

	@JsonProperty("suggestions")
	public void setSuggestions(List<Suggestions> suggestions) {
		this.suggestions = suggestions;
	}
}
