package com.forecast.json;

import java.util.List;

public class Condition{
   	private Float pressure;
   	private Float temperature;
   	private String unit;
   	private String unitPressure;
   	private String weather;

 	public Float getPressure(){
		return this.pressure;
	}
	public void setPressure(Float pressure){
		this.pressure = pressure;
	}
 	public Float getTemperature(){
		return this.temperature;
	}
	public void setTemperature(Float temperature){
		this.temperature = temperature;
	}
 	public String getUnit(){
		return this.unit;
	}
	public void setUnit(String unit){
		this.unit = unit;
	}
 	public String getUnitPressure(){
		return this.unitPressure;
	}
	public void setUnitPressure(String unitPressure){
		this.unitPressure = unitPressure;
	}
 	public String getWeather(){
		return this.weather;
	}
	public void setWeather(String weather){
		this.weather = weather;
	}
}
