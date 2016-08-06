package com.msc.demo.selenium.pagemodel.test;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import com.msc.demo.selenium.SeleniumUtil;
import com.msc.demo.selenium.pagemodel.LandingPage;

public class LandingPageITest {

	  @Test
	  public void test() throws Exception {		  
		  /* Test logic */
		  WebDriver driver = SeleniumUtil.getWebDriver();
		  
		  LandingPage landingPage = new LandingPage(driver);
		  
		  landingPage.gotoPage();
		  
		  /* Landing page assertions */
		  Assert.assertEquals("Demo Bank", landingPage.getTitle());
		  Assert.assertEquals("Demo Bank", landingPage.getHeading());
		  
		  driver.quit();  

	  }
	  
	  
	
}
