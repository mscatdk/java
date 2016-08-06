package com.msc.demo.selenium.pagemodel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.msc.demo.selenium.SeleniumUtil;

public class LandingPage extends AbstractPage {
	
	private final WebDriver driver;
			
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	public LandingPage searchForCustomer(String customerNumber) {
		  driver.findElement(By.xpath("//input[@se-id='customerSearch']")).clear();
		  driver.findElement(By.cssSelector("[se-id=customerSearch]")).sendKeys(customerNumber);
		  driver.findElement(By.id("customerSearchSubmit")).click();
		  
		  return this;
	} 
	
	public LandingPage gotoPage() {
		driver.get(SeleniumUtil.getBaseUrl());
		return this;
	}
	
	public String getHeading() {
		return driver.findElement(By.id("title")).getText();
	}
	
}
