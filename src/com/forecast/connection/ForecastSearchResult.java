package com.forecast.connection;

import java.util.ArrayList;
import java.util.List;

import com.forecast.json.Result;

public class ForecastSearchResult {

	private List<Result> results = new ArrayList<Result>();

	public ForecastSearchResult() {
		super();
	}

	public List<Result> getResults() {
		return results;
	}

	public void setResults(List<Result> results) {
		this.results = results;
	}
	
}
