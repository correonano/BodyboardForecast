package com.forecast.json;

import java.util.List;

public class Secondary{
   	private String compassDirection;
   	private Float direction;
   	private Float height;
   	private Float period;

 	public String getCompassDirection(){
		return this.compassDirection;
	}
	public void setCompassDirection(String compassDirection){
		this.compassDirection = compassDirection;
	}
 	public Float getDirection(){
		return this.direction;
	}
	public void setDirection(Float direction){
		this.direction = direction;
	}
 	public Float getHeight(){
		return this.height;
	}
	public void setHeight(Float height){
		this.height = height;
	}
 	public Float getPeriod(){
		return this.period;
	}
	public void setPeriod(Float period){
		this.period = period;
	}
}
