package com.forecast.json;


public class Result{
   	private Charts charts;
   	private Condition condition;
   	private Integer fadedRating;
   	private Long issueTimestamp;
   	private Long localTimestamp;
   	private Integer solidRating;
   	private Swell swell;
   	private Long timestamp;
   	private Wind wind;

 	public Charts getCharts(){
		return this.charts;
	}
	public void setCharts(Charts charts){
		this.charts = charts;
	}
 	public Condition getCondition(){
		return this.condition;
	}
	public void setCondition(Condition condition){
		this.condition = condition;
	}
 	public Integer getFadedRating(){
		return this.fadedRating;
	}
	public void setFadedRating(Integer fadedRating){
		this.fadedRating = fadedRating;
	}
 	public Long getIssueTimestamp(){
		return this.issueTimestamp;
	}
	public void setIssueTimestamp(Long issueTimestamp){
		this.issueTimestamp = issueTimestamp;
	}
 	public Long getLocalTimestamp(){
		return this.localTimestamp;
	}
	public void setLocalTimestamp(Long localTimestamp){
		this.localTimestamp = localTimestamp;
	}
 	public Integer getSolidRating(){
		return this.solidRating;
	}
	public void setSolidRating(Integer solidRating){
		this.solidRating = solidRating;
	}
 	public Swell getSwell(){
		return this.swell;
	}
	public void setSwell(Swell swell){
		this.swell = swell;
	}
 	public Long getTimestamp(){
		return this.timestamp;
	}
	public void setTimestamp(Long timestamp){
		this.timestamp = timestamp;
	}
 	public Wind getWind(){
		return this.wind;
	}
	public void setWind(Wind wind){
		this.wind = wind;
	}
}
