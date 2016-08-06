package com.msc.demo.selenium.basic;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.msc.demo.selenium.SeleniumUtil;

public class CustomerOverviewITest {

	  @Test
	  public void test() throws Exception {
		  /* Test Parameters */
		  String customerNumber = "6666664444";
		  
		  /* Test logic */
		  WebDriver driver = SeleniumUtil.getWebDriver();
		  driver.get(SeleniumUtil.getBaseUrl());
		  
		  /* Landing page assertions */
		  Assert.assertEquals("Demo Bank", driver.getTitle());
		  Assert.assertEquals("Demo Bank", driver.findElement(By.id("title")).getText());
		  
		  /* Perform customer search */
		  driver.findElement(By.xpath("//input[@se-id='customerSearch']")).clear();
		  driver.findElement(By.cssSelector("[se-id=customerSearch]")).sendKeys(customerNumber);
		  driver.findElement(By.id("customerSearchSubmit")).click();
		  
		  /* Customer Overview assertions */
		  Assert.assertEquals(customerNumber, driver.findElement(By.xpath("//td[text()='Customer Number:']/../td[2]")).getText());
		  
		  String name = driver.findElement(By.xpath("//td[text()='Name:']/../td[2]")).getText();
		  Assert.assertEquals("Bonnie Henderson", name);
		  
		  driver.quit();  

	  }
	  	
}
