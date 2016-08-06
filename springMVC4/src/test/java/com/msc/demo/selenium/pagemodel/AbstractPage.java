package com.msc.demo.selenium.pagemodel;

import java.math.BigDecimal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AbstractPage {
	
	private final WebDriver driver;
	
	public AbstractPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getTitle() {
		return driver.getTitle();
	}
	
	public String getTextFromXPath(String xPath) {
		return driver.findElement(By.xpath(xPath)).getText();
	}
	
	public BigDecimal getBigDecimal(String str) {
		return new BigDecimal(str.replaceAll("[^0-9.]", ""));
	}
	
	public String getMessage() {
		  String message = driver.findElement(By.xpath("//div[@id='UIMessages']")).getText();
		  return message;
	}

}
