package com.msc.demo.selenium.pagemodel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MoneyTransferPage extends AbstractPage {
	
	private final WebDriver driver;
	
	public MoneyTransferPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	public MoneyTransferPage setFromAccount(String accountNumber) {
		driver.findElement(By.linkText("Money Transfer")).click();
		driver.findElement(By.id("fromAccount")).sendKeys(accountNumber);	
		return this;
	}
	
	public MoneyTransferPage setToAccount(String accountNumber) {
		driver.findElement(By.id("toAccount")).clear();
		driver.findElement(By.id("toAccount")).sendKeys(accountNumber);
		return this;
	}
	
	public MoneyTransferPage setAmount(String amount) {
		driver.findElement(By.id("amount")).clear();
		driver.findElement(By.id("amount")).sendKeys(amount);
		return this;
	}
	
	public MoneyTransferPage commit() {
		driver.findElement(By.cssSelector("button.btn.btn-success")).click();
		return this;
	}

}
