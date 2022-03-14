package com.dialogflow.dialogflow.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.JsonObject;

@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseMessage {
	private Text text;
	private ObjectNode payload;
	public Text getText() {
		return text;
	}
	public void setText(Text text) {
		this.text = text;
	}
	public ObjectNode getPayload() {
		return payload;
	}
	public void setPayload(ObjectNode payload) {
		this.payload = payload;
	}
	
	
}
