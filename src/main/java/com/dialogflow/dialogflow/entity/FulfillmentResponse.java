package com.dialogflow.dialogflow.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FulfillmentResponse {
	private List<ResponseMessage> messages;

	public List<ResponseMessage> getMessages() {
		if(messages == null) {
			messages = new ArrayList<ResponseMessage>();
		}
		return messages;
	}

	public void setMessages(List<ResponseMessage> messages) {
		this.messages = messages;
	}
	
	
}
