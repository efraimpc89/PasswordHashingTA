package com.efraim.phta.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Stats {

	@JsonProperty("totalRequests") 
    private int totalRequests;
    @JsonProperty("averageTime") 
    private int averageTime;
    
    @JsonProperty("totalRequests")
	public int getTotalRequests() {
    	return totalRequests;
	}
	
	@JsonProperty("totalRequests")
	public void setTotalRequests(int totalRequests) {
		this.totalRequests = totalRequests;
	}
	
	@JsonProperty("averageTime")
	public int getAverageTime() {
    	return totalRequests;
	}
	
	@JsonProperty("averageTime")
	public void setAverageTime(int averageTime) {
		this.averageTime = averageTime;
	}

	
}
