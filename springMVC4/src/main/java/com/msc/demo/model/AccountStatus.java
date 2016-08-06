package com.msc.demo.model;

public enum AccountStatus {
	ACTIVE("Active"),
	TERMINATED("Terminated");
	
	private final String displayText;
	
	private AccountStatus(String displayText) {
		this.displayText = displayText;
	}

	public String getDisplayText() {
		return displayText;
	}
}
