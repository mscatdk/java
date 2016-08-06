package com.msc.demo.model;

public enum AccountType {
	FIXED_INTEREST("Fixed Interest"),
	CASH_PENSION("Cash Pension");
	
	private final String displayText;
	
	private AccountType(String displayText) {
		this.displayText = displayText;
	}

	public String getDisplayText() {
		return displayText;
	}
}
