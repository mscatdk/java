package com.msc.demo.model;

public class UIMessage {
	
	private final String message;
	
	private final String messageType;

	public UIMessage(String message, String messageType) {
		this.message = message;
		this.messageType = messageType;
	}
	
	public String getMessage() {
		return message;
	}

	public String getMessageType() {
		return messageType;
	}

}
