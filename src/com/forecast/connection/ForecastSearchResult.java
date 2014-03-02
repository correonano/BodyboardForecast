package com.forecast.connection;

import java.util.ArrayList;
import java.util.List;

import com.forecast.json.ForecastItem;

public class ForecastSearchResult {

	private List<ForecastItem> results = new ArrayList<ForecastItem>();
	private String name;

	public ForecastSearchResult() {
		super();
	}

	public List<ForecastItem> getResults() {
		return results;
	}

	public void setResults(List<ForecastItem> results) {
		this.results = results;
	}

	public synchronized String getName() {
		return name;
	}

	public synchronized void setName(String name) {
		this.name = name;
	}
	
}
