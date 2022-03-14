package com.dialogflow.dialogflow.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
public class WebhookRequest {
	private String detectIntentResponseId;
	private String languageCode;
	private FulfillmentInfo fulfillmentInfo;
	private IntentInfo intentInfo;
	private PageInfo pageInfo;
	private SessionInfo sessionInfo;
	private List<ResponseMessage> messages;
	private Object payload;
	private String text;
	private String triggerIntent;
	private String triggerEvent;

	public SessionInfo getSessionInfo() {
		return sessionInfo;
	}

	public void setSessionInfo(SessionInfo sessionInfo) {
		this.sessionInfo = sessionInfo;
	}

	public String getDetectIntentResponseId() {
		return detectIntentResponseId;
	}

	public void setDetectIntentResponseId(String detectIntentResponseId) {
		this.detectIntentResponseId = detectIntentResponseId;
	}

	public String getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	public FulfillmentInfo getFulfillmentInfo() {
		return fulfillmentInfo;
	}

	public void setFulfillmentInfo(FulfillmentInfo fulfillmentInfo) {
		this.fulfillmentInfo = fulfillmentInfo;
	}

	public IntentInfo getIntentInfo() {
		return intentInfo;
	}

	public void setIntentInfo(IntentInfo intentInfo) {
		this.intentInfo = intentInfo;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	public List<ResponseMessage> getMessages() {
		return messages;
	}

	public void setMessages(List<ResponseMessage> messages) {
		this.messages = messages;
	}

	public Object getPayload() {
		return payload;
	}

	public void setPayload(Object payload) {
		this.payload = payload;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTriggerIntent() {
		return triggerIntent;
	}

	public void setTriggerIntent(String triggerIntent) {
		this.triggerIntent = triggerIntent;
	}

	public String getTriggerEvent() {
		return triggerEvent;
	}

	public void setTriggerEvent(String triggerEvent) {
		this.triggerEvent = triggerEvent;
	}

}
