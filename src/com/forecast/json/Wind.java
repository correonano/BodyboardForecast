package com.forecast.json;

public class Wind{
   	private String chill;
   	private String compassDirection;
   	private Integer direction;
   	private String gusts;
   	private Integer speed;
   	private String unit;

 	public String getChill(){
		return this.chill;
	}
	public void setChill(String chill){
		this.chill = chill;
	}
 	public String getCompassDirection(){
		return this.compassDirection;
	}
	public void setCompassDirection(String compassDirection){
		this.compassDirection = compassDirection;
	}
 	public Integer getDirection(){
		return this.direction;
	}
	public void setDirection(Integer direction){
		this.direction = direction;
	}
 	public String getGusts(){
		return this.gusts;
	}
	public void setGusts(String gusts){
		this.gusts = gusts;
	}
 	public Integer getSpeed(){
		return this.speed;
	}
	public void setSpeed(Integer speed){
		this.speed = speed;
	}
 	public String getUnit(){
		return this.unit;
	}
	public void setUnit(String unit){
		this.unit = unit;
	}
}
