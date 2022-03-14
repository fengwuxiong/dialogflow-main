package com.dialogflow.dialogflow.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Text {
	private List<String> text;
	private Boolean allowPlaybackInterruption;
	
	public List<String> getText() {
		return text;
	}
	public void setText(List<String> text) {
		this.text = text;
	}
	public Boolean getAllowPlaybackInterruption() {
		return allowPlaybackInterruption;
	}
	public void setAllowPlaybackInterruption(Boolean allowPlaybackInterruption) {
		this.allowPlaybackInterruption = allowPlaybackInterruption;
	}
	
	
}
