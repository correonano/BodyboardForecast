package com.forecast.json;

public class ChartItem {
	
	private String url;
	private String name;
	
	public ChartItem(String url, String name){
		this.url = url;
		this.name = name;
	}
	public synchronized String getUrl() {
		return url;
	}
	public synchronized void setUrl(String url) {
		this.url = url;
	}
	public synchronized String getName() {
		return name;
	}
	public synchronized void setName(String name) {
		this.name = name;
	}
	
	

}
