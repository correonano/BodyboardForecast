package com.forecast.json;

import java.util.List;

public class Components{
   	private Combined combined;
   	private Primary primary;
   	private Secondary secondary;
   	private Tertiary tertiary;

 	public Combined getCombined(){
		return this.combined;
	}
	public void setCombined(Combined combined){
		this.combined = combined;
	}
 	public Primary getPrimary(){
		return this.primary;
	}
	public void setPrimary(Primary primary){
		this.primary = primary;
	}
 	public Secondary getSecondary(){
		return this.secondary;
	}
	public void setSecondary(Secondary secondary){
		this.secondary = secondary;
	}
 	public Tertiary getTertiary(){
		return this.tertiary;
	}
	public void setTertiary(Tertiary tertiary){
		this.tertiary = tertiary;
	}
}
