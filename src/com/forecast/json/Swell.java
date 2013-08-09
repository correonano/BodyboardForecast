package com.forecast.json;

import java.util.List;

public class Swell{
   	private Float absMaxBreakingHeight;
   	private Float absMinBreakingHeight;
   	private Components components;
   	private Float maxBreakingHeight;
   	private Float minBreakingHeight;
   	private String unit;

 	public Float getAbsMaxBreakingHeight(){
		return this.absMaxBreakingHeight;
	}
	public void setAbsMaxBreakingHeight(Float absMaxBreakingHeight){
		this.absMaxBreakingHeight = absMaxBreakingHeight;
	}
 	public Float getAbsMinBreakingHeight(){
		return this.absMinBreakingHeight;
	}
	public void setAbsMinBreakingHeight(Float absMinBreakingHeight){
		this.absMinBreakingHeight = absMinBreakingHeight;
	}
 	public Components getComponents(){
		return this.components;
	}
	public void setComponents(Components components){
		this.components = components;
	}
 	public Float getMaxBreakingHeight(){
		return this.maxBreakingHeight;
	}
	public void setMaxBreakingHeight(Float maxBreakingHeight){
		this.maxBreakingHeight = maxBreakingHeight;
	}
 	public Float getMinBreakingHeight(){
		return this.minBreakingHeight;
	}
	public void setMinBreakingHeight(Float minBreakingHeight){
		this.minBreakingHeight = minBreakingHeight;
	}
 	public String getUnit(){
		return this.unit;
	}
	public void setUnit(String unit){
		this.unit = unit;
	}
}
