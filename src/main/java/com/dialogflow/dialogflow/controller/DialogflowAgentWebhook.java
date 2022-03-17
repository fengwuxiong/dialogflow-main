package com.dialogflow.dialogflow.controller;

import com.dialogflow.dialogflow.entity.PageInfo;
import com.dialogflow.dialogflow.entity.ResponseMessage;
import com.dialogflow.dialogflow.entity.WebhookRequest;
import com.dialogflow.dialogflow.entity.WebhookResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping
public class DialogflowAgentWebhook{

	private String startflow = "projects/dolores-prod/locations/global/agents/a8ddc597-d9d4-408b-8895-60a19120c676/flows/00000000-0000-0000-0000-000000000000";

	private String session = "projects/dolores-prod/locations/global/agents/a8ddc597-d9d4-408b-8895-60a19120c676/environments/draft/sessions/";
	@PostMapping(value="/endConversation")
	@ResponseBody
	public WebhookResponse endConversation(@RequestBody WebhookRequest webHookRequest) throws Exception {
		System.out.println("request: " + new ObjectMapper().writeValueAsString(webHookRequest));
		WebhookResponse webhookResponse = new WebhookResponse();
		String sessionId = UUID.randomUUID().toString();
		for(String key : webHookRequest.getSessionInfo().getParameters().keySet()) {
			webHookRequest.getSessionInfo().getParameters().put(key,null);
		}
		webHookRequest.getSessionInfo().setSession(session+sessionId);
		webhookResponse.setTargetFlow(startflow);
		webhookResponse.setSessionInfo(webHookRequest.getSessionInfo());
		System.out.println("response: " + new ObjectMapper().writeValueAsString(webhookResponse));
		return webhookResponse;

	}

	@PostMapping(value="/addParameters")
	@ResponseBody
	public WebhookResponse addParameters(@RequestBody WebhookRequest webHookRequest) throws Exception {
		System.out.println("request: " + new ObjectMapper().writeValueAsString(webHookRequest));
		WebhookResponse webhookResponse = new WebhookResponse();
		webHookRequest.getSessionInfo().getParameters().put("userName", "Ryan");
		webHookRequest.getSessionInfo().getParameters().put("sessionId", "521776277617");
		webhookResponse.setSessionInfo(webHookRequest.getSessionInfo());
		String currentPage = webHookRequest.getPageInfo().getCurrentPage();
		System.out.println("response: " + new ObjectMapper().writeValueAsString(webhookResponse));
		return webhookResponse;
	}

	@PostMapping(value="/action")
	@ResponseBody
	public WebhookResponse action(@RequestBody WebhookRequest webHookRequest) throws JsonMappingException, JsonProcessingException {
		System.out.println("request: " + new ObjectMapper().writeValueAsString(webHookRequest));
		WebhookResponse webhookResponse = new WebhookResponse();
		ObjectMapper mapper = new ObjectMapper();
		String itemPicker = "[{\"type\":\"html\",\"payload\":{\"html\":\"OK, qibuk, if an item you bought or sold hasn't arrived yet, we'll help you sort this out. Just a sec while I check your recent purchases and sold items.\"},\"source\":\"BOTC3598\"},{\"type\":\"html\",\"payload\":{\"html\":\"Right, choose the one that hasn't arrived.\"},\"source\":\"BOTC3598\"},{\"type\":\"template\",\"payload\":{\"template\":{\"name\":\"itempickerTemplate\",\"type\":\"multiple_itempicker\",\"widgets\":[{\"dropdown\":{\"key\":\"topicId\",\"defaultValue\":\"2925\",\"options\":[{\"name\":\"All\",\"value\":\"2925\"},{\"name\":\"Purchases\",\"value\":\"4042\"},{\"name\":\"Sold\",\"value\":\"4128\"}],\"action\":{\"itemPicker\":{\"actionPayloadType\":\"ajaxCallType\",\"url\":\"/helpbot/v1/itempicker\",\"method\":\"POST\",\"parameters\":[{\"key\":\"pageNumber\",\"value\":\"${pageNumber}\"},{\"key\":\"topicId\",\"value\":\"${topicId}\"},{\"key\":\"itemEntity\",\"value\":\"${itemEntity}\"}]}}}},{\"searchInput\":{\"key\":\"itemEntity\",\"placeholder\":\"Search by item details\",\"action\":{\"itemPicker\":{\"actionPayloadType\":\"ajaxCallType\",\"url\":\"/helpbot/v1/itempicker\",\"method\":\"POST\",\"parameters\":[{\"key\":\"pageNumber\",\"value\":\"${pageNumber}\"},{\"key\":\"topicId\",\"value\":\"${topicId}\"},{\"key\":\"itemEntity\",\"value\":\"${itemEntity}\"}]}}}}],\"items\":{\"status\":\"OK\",\"total\":3,\"currentPage\":1,\"displayNum\":10,\"dataSet\":[{\"title\":{\"value\":\"DCP -- 3-FixedPrice Item1635273197415-1635273197415**704e7902-b038-4025-a75f-04b\"},\"itemId\":{\"value\":\"200080334757\"},\"transactionId\":{\"value\":\"28646582010\"},\"saleDate\":{\"value\":\"02/09/22\"},\"pictureUrl\":{\"value\":\"https://i.qa.ebayimg.com/00/s/NDVYMTEw/z/v9gAAOSw1kViBLUo/$_1.GIF?set_id=8800004005\"},\"quantity\":{\"value\":\"1\"},\"uniqueOrderId\":{\"value\":\"26-00026-06874\"}},{\"title\":{\"value\":\"DCP -- 3-FixedPrice Item1635273197415-1635273197415**7ba8a928-dfd7-486c-ad85-3fb\"},\"itemId\":{\"value\":\"200080334741\"},\"transactionId\":{\"value\":\"28646576010\"},\"saleDate\":{\"value\":\"02/09/22\"},\"pictureUrl\":{\"value\":\"https://i.qa.ebayimg.com/00/s/NDVYMTEw/z/v8AAAOSw1kViBLRI/$_1.GIF?set_id=8800004005\"},\"quantity\":{\"value\":\"1\"},\"uniqueOrderId\":{\"value\":\"24-00025-76888\"}},{\"title\":{\"value\":\"DCP -- **f18ef735-1b30-48f1-9adc-dd605bb5ad4aUK Fixed Price Best Offer        \"},\"itemId\":{\"value\":\"200080334135\"},\"transactionId\":{\"value\":\"28646507010\"},\"saleDate\":{\"value\":\"02/09/22\"},\"pictureUrl\":{\"value\":\"https://i.qa.ebayimg.com/00/s/NDVYMTEw/z/FUIAAOSwpbpiBKhB/$_1.GIF?set_id=8800004005\"},\"quantity\":{\"value\":\"1\"},\"uniqueOrderId\":{\"value\":\"18-00026-29996\"}}],\"action\":{\"botTracking\":{\"actionPayloadType\":\"ajaxCallType\",\"url\":\"/helpbot/v1/botTracking\",\"method\":\"POST\",\"parameters\":[{\"key\":\"operation\",\"value\":\"itemPickerTracking\"},{\"key\":\"clientContext\",\"value\":\"{\\\"from\\\":\\\"helphub\\\",\\\"clientInfo\\\":{\\\"channelTopicId\\\":\\\"4041\\\",\\\"pageId\\\":\\\"2493094\\\",\\\"appId\\\":\\\"-1\\\",\\\"isAsync\\\":false,\\\"isContactUs\\\":true,\\\"guestContinue\\\":false,\\\"botId\\\":1645428631411,\\\"enableHearingImpairedLink\\\":true}}\"},{\"key\":\"attributeMap\",\"value\":\"{\\\"ITEM_PICKER_CLICKED\\\":\\\"1\\\",\\\"guid\\\":\\\"1b028b6317fc0a857017f105ffffff61\\\"}\"}]},\"sendReply\":{\"actionPayloadType\":\"sendReplyType\",\"event\":\"display_yes\",\"parameters\":[{\"key\":\"itemId\",\"value\":\"${itemId}\"},{\"key\":\"transactionId\",\"value\":\"${transactionId}\"}]},\"postMessage\":{\"actionPayloadType\":\"postMessageType\",\"elements\":[{\"type\":\"html\",\"payload\":{\"html\":\"Checking on...\"},\"source\":null}]}}},\"action\":{\"itemPicker\":{\"actionPayloadType\":\"ajaxCallType\",\"url\":\"/helpbot/v1/itempicker\",\"method\":\"POST\",\"parameters\":[{\"key\":\"pageNumber\",\"value\":\"${pageNumber}\"},{\"key\":\"topicId\",\"value\":\"${topicId}\"},{\"key\":\"itemEntity\",\"value\":\"${itemEntity}\"}]}}}},\"source\":\"BOTC3598\"},{\"type\":\"quick_replies\",\"payload\":{\"quick_replies\":[{\"type\":\"post_back\",\"title\":\"Don't see item\",\"action\":{\"sendReply\":{\"actionPayloadType\":\"sendReplyType\",\"text\":\"Don't see item\",\"parameters\":[]}}},{\"type\":\"post_back\",\"title\":\"Stop this\",\"action\":{\"sendReply\":{\"actionPayloadType\":\"sendReplyType\",\"text\":\"Stop this\",\"parameters\":[]}}}]},\"source\":\"BOTC3598\"}]";
		String card = "[{\"type\":\"html\",\"payload\":{\"html\":\"Since shipping carriers are experiencing delays right now, please consider waiting another day or two for your item to arrive.\"},\"source\":\"BOTC3680\"},{\"type\":\"html\",\"payload\":{\"html\":\"Or, if you've already waited qibuk, you can complete this to request a refund or replacement.\"},\"source\":\"BOTC3680\"},{\"type\":\"template\",\"payload\":{\"template\":{\"name\":\"inrSubmissionCard\",\"type\":\"generic_card\",\"header\":{\"itemId\":{\"value\":\"200080334757\"},\"pictureUrl\":{\"value\":\"https://i.qa.ebayimg.com/00/s/NDVYMTEw/z/v9gAAOSw1kViBLUo/$_1.GIF?set_id=8800004005\"},\"title\":{\"value\":\"DCP -- 3-FixedPrice Item1635273197415-1635273197415**704e7902-b038-4025-a75f-04b\"},\"uniqueOrderId\":{\"value\":\"26-00026-06874\"}},\"sections\":[{\"widgets\":[{\"dropdown\":{\"key\":\"inrOption\",\"label\":\"Let the seller know which you'd prefer\",\"required\":true,\"options\":[{\"name\":\"I want a refund\",\"value\":\"FULL_REFUND\"},{\"name\":\"I want the item\",\"value\":\"STILL_WANT_ITEM\"}]}},{\"quantityInput\":{\"key\":\"userEnteredQuantity\",\"label\":\"How many items didn't arrive?\",\"required\":true,\"defaultValue\":\"1\",\"maxNumber\":1,\"action\":{}}},{\"textArea\":{\"key\":\"comments\",\"label\":\"Message to seller\",\"required\":true,\"defaultValue\":\"I didn't get item\",\"placeholder\":\"Message to seller\",\"maxLength\":1500}}]},{\"widgets\":[{\"type\":\"post_back\",\"title\":\"Send request\",\"action\":{\"sendReply\":{\"actionPayloadType\":\"sendReplyType\",\"text\":\"Send request\",\"parameters\":[{\"key\":\"itemId\",\"value\":\"200080334757\"},{\"key\":\"transactionId\",\"value\":\"28646582010\"},{\"key\":\"inrOption\",\"value\":\"${inrOption}\"},{\"key\":\"comments\",\"value\":\"${comments}\"},{\"key\":\"userEnteredQuantity\",\"value\":\"${userEnteredQuantity}\"}]}}},{\"type\":\"post_back\",\"title\":\"Stop this\",\"action\":{\"sendReply\":{\"actionPayloadType\":\"sendReplyType\",\"text\":\"Stop this\",\"parameters\":[]}}}]}]}},\"source\":\"BOTC3680\"}]";
		String submit = "[{\"type\":\"html\",\"payload\":{\"html\":\"<div class=\\\"done\\\">\\n <div class=\\\"done1\\\"></div>\\n <div class=\\\"done2\\\">\\n  Job done, qibuk! We've sent your request.\\n </div>\\n</div>\"},\"source\":\"BOTC4082\"},{\"type\":\"html\",\"payload\":{\"html\":\"<p>OK, the seller has until the end of 04 Mar 2022 to work something out with you. If they haven&#39;t resolved it by then, you can ask us to step in and help.</p>\\n\"},\"source\":\"BOT1131,BOT1175\"},{\"type\":\"html\",\"payload\":{\"html\":\"<p>Hope that&#39;s clear. If there&#39;s anything else I can help with, please ask.</p>\\n\"},\"source\":\"BOT1131,BOT1175\"},{\"type\":\"html\",\"payload\":{\"html\":\"Please take our quick survey to let us know how our automated assistant worked for you.\"},\"source\":\"BOTC4082\"},{\"type\":\"buttons\",\"payload\":{\"buttons\":[{\"type\":\"post_back\",\"title\":\"Tell us what you think\",\"action\":{\"sendReply\":{\"actionPayloadType\":\"sendReplyType\",\"text\":\"Tell us what you think\",\"parameters\":[]}}}],\"buttonNumber\":3},\"source\":\"BOTC4082\"},{\"type\":\"quick_replies\",\"payload\":{\"quick_replies\":[{\"type\":\"post_back\",\"title\":\"Done for now\",\"action\":{\"sendReply\":{\"actionPayloadType\":\"sendReplyType\",\"text\":\"Done for now\",\"parameters\":[{\"key\":\"ifdBotContentIds\",\"value\":\"BOT1131,BOT1175\"}]}}},{\"type\":\"post_back\",\"title\":\"Rate this chat\",\"action\":{\"sendReply\":{\"actionPayloadType\":\"sendReplyType\",\"text\":\"Rate this chat\",\"parameters\":[{\"key\":\"ifdBotContentIds\",\"value\":\"BOT1131,BOT1175\"}]}}},{\"type\":\"post_back\",\"title\":\"Still need help\",\"action\":{\"sendReply\":{\"actionPayloadType\":\"sendReplyType\",\"text\":\"Still need help\",\"parameters\":[{\"key\":\"ifdBotContentIds\",\"value\":\"BOT1131,BOT1175\"}]}}}]},\"source\":null}]";
		String noItems = "{\"custom_messages\":{\"inquiraDocID\":\"BOTC1099\",\"topic_id\":\"\"}}";
		String noItemsBuyer = "{\"custom_messages\":{\"inquiraDocID\":\"BOTC1100\",\"topic_id\":\"\"}}";
		String noItemsSeller = "{\"custom_messages\":{\"inquiraDocID\":\"BOTC1101\",\"topic_id\":\"\"}}";
		String noItemsUnsure = "{\"custom_messages\":{\"inquiraDocID\":\"BOTC1102\",\"topic_id\":\"\"}}";
		String inrSubmitCancel = "{\"custom_messages\":{\"inquiraDocID\":\"BOTC1325\",\"topic_id\":\"\"}}";
		String inrForGuest = "{\"custom_messages\":{\"inquiraDocID\":\"BOTC4013\",\"topic_id\":\"\"}}";
		String inrForBuyer = "{\"custom_messages\":{\"inquiraDocID\":\"BOTC4014\",\"topic_id\":\"\"}}";
		String inrForSeller = "{\"custom_messages\":{\"inquiraDocID\":\"BOTC4015\",\"topic_id\":\"\"}}";

		String rulePath = webHookRequest.getFulfillmentInfo().getTag();
		String override = (String) webHookRequest.getSessionInfo().getParameters().get("override");
		if(rulePath != null && rulePath.equals("userLoginCheck")) {
			webHookRequest.getSessionInfo().getParameters().put("state", "signInUsecase");
		}else if(rulePath != null && rulePath.equals("getItemDetails")) {
			webHookRequest.getSessionInfo().getParameters().put("state", "itemWithoutCaseBuyer");
		}else if(rulePath != null && rulePath.equals("checkINREligibility")) {
			webHookRequest.getSessionInfo().getParameters().put("state", "inrEligible");
		}else if(rulePath != null && rulePath.equals("buildINRSubmissionCard")) {
			ArrayNode jsonArr = mapper.readValue(card, ArrayNode.class);
			ObjectNode jsonObj = mapper.createObjectNode();
			jsonObj.set("elements", jsonArr);
			ResponseMessage message = new ResponseMessage();
			message.setPayload(jsonObj);
			webhookResponse.getFulfillmentResponse().getMessages().add(message);
		}else if(rulePath != null && rulePath.equals("submitINRRequest")) {
			ArrayNode jsonArr = mapper.readValue(submit, ArrayNode.class);
			ObjectNode jsonObj = mapper.createObjectNode();
			jsonObj.set("elements", jsonArr);
			ResponseMessage message = new ResponseMessage();
			message.setPayload(jsonObj);
			webhookResponse.getFulfillmentResponse().getMessages().add(message);
		}else if(rulePath != null && rulePath.equals("cancelINRRequest")) {
			ObjectNode jsonObj = mapper.readValue(inrSubmitCancel, ObjectNode.class);
			ResponseMessage message = new ResponseMessage();
			message.setPayload(jsonObj);
			webhookResponse.getFulfillmentResponse().getMessages().add(message);
		}else if(rulePath != null && rulePath.equals("getItempicker")) {
			webHookRequest.getSessionInfo().getParameters().put("state", "itempickerCard");
			ArrayNode jsonArr = mapper.readValue(itemPicker, ArrayNode.class);
			ObjectNode jsonObj = mapper.createObjectNode();
			jsonObj.set("elements", jsonArr);
			ResponseMessage message = new ResponseMessage();
			message.setPayload(jsonObj);
			webhookResponse.getFulfillmentResponse().getMessages().add(message);
		}else if(rulePath != null && rulePath.equals("checkMoreItems")) {
			webHookRequest.getSessionInfo().getParameters().put("state", "noItemsFound");
		}else if(rulePath != null && rulePath.equals("noItemsFound")) {
			ObjectNode jsonObj = mapper.readValue(noItems, ObjectNode.class);
			ResponseMessage message = new ResponseMessage();
			message.setPayload(jsonObj);
			webhookResponse.getFulfillmentResponse().getMessages().add(message);
		}else if(rulePath != null && rulePath.equals("noItemsForBuyerUsecase")) {
			ObjectNode jsonObj = mapper.readValue(noItemsBuyer, ObjectNode.class);
			ResponseMessage message = new ResponseMessage();
			message.setPayload(jsonObj);
			webhookResponse.getFulfillmentResponse().getMessages().add(message);
		}else if(rulePath != null && rulePath.equals("noItemsForSellerUsecase")) {
			ObjectNode jsonObj = mapper.readValue(noItemsSeller, ObjectNode.class);
			ResponseMessage message = new ResponseMessage();
			message.setPayload(jsonObj);
			webhookResponse.getFulfillmentResponse().getMessages().add(message);
		}else if(rulePath != null && rulePath.equals("noItemsForUnsureUsecase")) {
			ObjectNode jsonObj = mapper.readValue(noItemsUnsure, ObjectNode.class);
			ResponseMessage message = new ResponseMessage();
			message.setPayload(jsonObj);
			webhookResponse.getFulfillmentResponse().getMessages().add(message);
		}else if(rulePath != null && rulePath.equals("inrForGuestUser")) {
			ObjectNode jsonObj = mapper.readValue(inrForGuest, ObjectNode.class);
			ResponseMessage message = new ResponseMessage();
			message.setPayload(jsonObj);
			webhookResponse.getFulfillmentResponse().getMessages().add(message);
		}else if(rulePath != null && rulePath.equals("inrForGuestBuyer")) {
			ObjectNode jsonObj = mapper.readValue(inrForBuyer, ObjectNode.class);
			ResponseMessage message = new ResponseMessage();
			message.setPayload(jsonObj);
			webhookResponse.getFulfillmentResponse().getMessages().add(message);
		}else if(rulePath != null && rulePath.equals("inrForGuestSeller")) {
			ObjectNode jsonObj = mapper.readValue(inrForSeller, ObjectNode.class);
			ResponseMessage message = new ResponseMessage();
			message.setPayload(jsonObj);
			webhookResponse.getFulfillmentResponse().getMessages().add(message);
		}
		webhookResponse.setSessionInfo(webHookRequest.getSessionInfo());
		return webhookResponse;
	}
}