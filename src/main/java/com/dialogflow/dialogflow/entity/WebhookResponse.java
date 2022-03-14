package com.dialogflow.dialogflow.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class WebhookResponse {
	private FulfillmentResponse fulfillmentResponse;
	private SessionInfo sessionInfo;
	private PageInfo pageInfo;
	private Object payload;
	private String targetPage;
	private String targetFlow;

	public SessionInfo getSessionInfo() {
		return sessionInfo;
	}

	public void setSessionInfo(SessionInfo sessionInfo) {
		this.sessionInfo = sessionInfo;
	}

	public FulfillmentResponse getFulfillmentResponse() {
		return fulfillmentResponse;
	}

	public void setFulfillmentResponse(FulfillmentResponse fulfillmentResponse) {
		this.fulfillmentResponse = fulfillmentResponse;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	public Object getPayload() {
		return payload;
	}

	public void setPayload(Object payload) {
		this.payload = payload;
	}

	public String getTargetPage() {
		return targetPage;
	}

	public void setTargetPage(String targetPage) {
		this.targetPage = targetPage;
	}

	public String getTargetFlow() {
		return targetFlow;
	}

	public void setTargetFlow(String targetFlow) {
		this.targetFlow = targetFlow;
	}
	
	

}
