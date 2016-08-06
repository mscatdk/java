package com.msc.demo.selenium.pagemodel;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.msc.demo.selenium.SeleniumUtil;

public class CustomerPage extends AbstractPage {
	
	private final WebDriver driver;
	private Map<String, String[]> accountDetails = new HashMap<>();
	
	public static final int ACCOUNT_NUMBER = 1;
	public static final int ACCOUNT_TYPE = 2;
	public static final int ACCOUNT_BALANCE = 3;
	public static final int ACCOUNT_STATUS = 4;
	
	public void saveAccountDetails(String accountNumber) {
		String[] account = new String[5];
		account[ACCOUNT_NUMBER] = getAccountColumn(accountNumber, ACCOUNT_NUMBER);
		account[ACCOUNT_TYPE] = getAccountColumn(accountNumber, ACCOUNT_TYPE);
		account[ACCOUNT_BALANCE] = getAccountColumn(accountNumber, ACCOUNT_BALANCE);
		account[ACCOUNT_STATUS] = getAccountColumn(accountNumber, ACCOUNT_STATUS);
		
		accountDetails.put(accountNumber, account);
	}
	
	public BigDecimal getSavedValueBigDecimal(String accountNumber, int index) {
		String str = getSavedValue(accountNumber, index);
		return getBigDecimal(str);
	}
	
	public String getSavedValue(String accountNumber, int index) {
		String[] accounts = accountDetails.get(accountNumber);
		if (accounts == null) {
			return null;
		}
		
		return accounts[index];
	}
	
	public CustomerPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public CustomerPage gotoPage(String customerNumber) {
		String url = String.format("%scustomerOverview?customerNumber=%s", SeleniumUtil.getBaseUrl(), customerNumber);
		driver.get(url);
		return this;
	}
	
	public CustomerPage gotoMoneyTransfer() {
		driver.findElement(By.linkText("Money Transfer")).click();
		return this;
	}
	
	public BigDecimal getAccountBalance(String accountNumber) {
		String balance = getAccountColumn(accountNumber, ACCOUNT_BALANCE);
		return getBigDecimal(balance);
	}
	
	private String getAccountColumn(String accountNumber, int column) {
		String xPath = String.format("//td[text()='%s']/../td[%s]",accountNumber, column);
		return getTextFromXPath(xPath);
	}	
	
	public String getCustomerNumber() {
		  return getTextFromXPath("//td[text()='Customer Number:']/../td[2]");
	}
	
	public String getCustomerName() {
		return getTextFromXPath("//td[text()='Name:']/../td[2]");
	}
	
}
