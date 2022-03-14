package com.dialogflow.dialogflow.entity;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SessionInfo {
	private String session;
	private Map<String, Object> parameters;
	public String getSession() {
		return session;
	}
	public void setSession(String session) {
		this.session = session;
	}
	public Map<String, Object> getParameters() {
		if(parameters == null) {
			parameters = new HashMap<String, Object>();
		}
		return parameters;
	}
	public void setParameters(Map<String, Object> parameters) {
		this.parameters = parameters;
	}
	
	
	
}
