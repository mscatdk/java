package com.msc.demo.selenium.pagemodel.test;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import com.msc.demo.selenium.SeleniumUtil;
import com.msc.demo.selenium.pagemodel.CustomerPage;
import com.msc.demo.selenium.pagemodel.LandingPage;

public class CustomerOverviewITest {

	  @Test
	  public void test() throws Exception {
		  /* Test Parameters */
		  String customerNumber = "6666664444";
		  
		  /* Test logic */
		  WebDriver driver = SeleniumUtil.getWebDriver();
		  
		  LandingPage landingPage = new LandingPage(driver);
		  CustomerPage customerPage = new CustomerPage(driver); 
		  
		  landingPage.gotoPage().searchForCustomer(customerNumber);
		  
		  /* Customer Overview assertions */
		  Assert.assertEquals(customerNumber, customerPage.getCustomerNumber());
		  Assert.assertEquals("Bonnie Henderson", customerPage.getCustomerName());
		  
		  driver.quit();  

	  }
	  
}
