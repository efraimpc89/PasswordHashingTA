package com.efraim.phta.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Password {

	@JsonProperty("password") 
	private String password;
	
	@JsonProperty("password")
	public String getPassword() {
		return password;
	}
	
	@JsonProperty("password")
	public void setPassword(String password) {
		this.password = password;
	}
}
