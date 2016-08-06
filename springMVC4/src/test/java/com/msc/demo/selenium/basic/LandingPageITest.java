package com.msc.demo.selenium.basic;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.msc.demo.selenium.SeleniumUtil;

public class LandingPageITest {

	  @Test
	  public void test() throws Exception {
		  /* Test logic */
		  WebDriver driver = SeleniumUtil.getWebDriver();
		  driver.get(SeleniumUtil.getBaseUrl());
		  
		  /* Landing page assertions */
		  Assert.assertEquals("Demo Bank", driver.getTitle());
		  Assert.assertEquals("Demo Bank", driver.findElement(By.id("title")).getText());
		  
		  driver.quit();  

	  }
	
}
