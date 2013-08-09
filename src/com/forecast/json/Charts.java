package com.forecast.json;

public class Charts{
   	private String period;
   	private String pressure;
   	private String sst;
   	private String swell;
   	private String wind;

 	public String getPeriod(){
		return this.period;
	}
	public void setPeriod(String period){
		this.period = period;
	}
 	public String getPressure(){
		return this.pressure;
	}
	public void setPressure(String pressure){
		this.pressure = pressure;
	}
 	public String getSst(){
		return this.sst;
	}
	public void setSst(String sst){
		this.sst = sst;
	}
 	public String getSwell(){
		return this.swell;
	}
	public void setSwell(String swell){
		this.swell = swell;
	}
 	public String getWind(){
		return this.wind;
	}
	public void setWind(String wind){
		this.wind = wind;
	}
}
