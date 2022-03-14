package com.dialogflow.dialogflow.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IntentInfo {

	private String lastMatchedIntent;
	private String displayName;
	private Float confidence;
	
	public String getLastMatchedIntent() {
		return lastMatchedIntent;
	}
	public void setLastMatchedIntent(String lastMatchedIntent) {
		this.lastMatchedIntent = lastMatchedIntent;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public Float getConfidence() {
		return confidence;
	}
	public void setConfidence(Float confidence) {
		this.confidence = confidence;
	}
	
	
}
