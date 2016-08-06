package com.msc.demo.model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class Account {

	private String customerNumber;

	private String accountNumber;
	
	private BigDecimal balance;
	
	private AccountType accountType;
	
	private AccountStatus accountStatus;
	
	public Account() { }
	
	public Account(String customerNumber, String accountNumber, BigDecimal balance, AccountType accountType,
			AccountStatus accountStatus) {
		super();
		this.customerNumber = customerNumber;
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.accountType = accountType;
		this.accountStatus = accountStatus;
	}
	
	public void updateBalance(BigDecimal value) {
		this.balance = this.balance.add(value);
	}
	
	public String getAccountTypeFormatted() {
		return this.accountType.getDisplayText();
	}
	
	public String getAccountStatusFormatted() {
		return this.accountStatus.getDisplayText();
	}

	public String getBalanceFormated() {
		return NumberFormat.getCurrencyInstance(Locale.CANADA).format(this.balance.setScale(2, 0));
	}

	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public AccountStatus getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(AccountStatus accountStatus) {
		this.accountStatus = accountStatus;
	}

}
