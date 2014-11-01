package com.cs441.autorep.interfaces;

import com.cs441.autorep.model.Suggestions;

public interface Notify {

	public String insertToRepSuggestions(Suggestions[] suggestions) throws Exception;
}
