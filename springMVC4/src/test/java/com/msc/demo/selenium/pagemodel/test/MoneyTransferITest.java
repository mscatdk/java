package com.msc.demo.selenium.pagemodel.test;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import com.msc.demo.selenium.SeleniumUtil;
import com.msc.demo.selenium.pagemodel.CustomerPage;
import com.msc.demo.selenium.pagemodel.LandingPage;
import com.msc.demo.selenium.pagemodel.MoneyTransferPage;


public class MoneyTransferITest {

	  @Test
	  public void test() throws Exception {
		  /* Test Parameters */
		  String customerNumber = "6666664444";
		  String accountNumber1 = "8888888822";
		  String accountNumber2 = "3337777777";
		  BigDecimal amount = new BigDecimal("1000");
		  
		  /* Test logic */
		  WebDriver driver = SeleniumUtil.getWebDriver();
		  
		  LandingPage landingPage = new LandingPage(driver);
		  CustomerPage customerPage = new CustomerPage(driver);
		  MoneyTransferPage moneyTransferPage = new MoneyTransferPage(driver); 
		  
		  landingPage.gotoPage().searchForCustomer(customerNumber);
		  
		  /* Save data */
		  customerPage.saveAccountDetails(accountNumber1);
		  customerPage.saveAccountDetails(accountNumber2);
		  
		  customerPage.gotoMoneyTransfer();
		  moneyTransferPage	.setFromAccount(accountNumber2)
		  					.setToAccount(accountNumber1)
		  					.setAmount(amount.toString())
		  					.commit();
		  
		  /* Check message */
		  Assert.assertEquals("The money transfer has been completed succesfully!", customerPage.getMessage());
		  
		  /* Check account balance */
		  checkBalanceChange(customerPage, accountNumber1, amount);
		  checkBalanceChange(customerPage, accountNumber2, amount.negate());
		  
		  driver.quit();  

	  }
	  
	  public void checkBalanceChange(CustomerPage customerPage, String accountNumber, BigDecimal amount) {
		  BigDecimal oldBalance = customerPage.getSavedValueBigDecimal(accountNumber, CustomerPage.ACCOUNT_BALANCE);
		  BigDecimal currentBalance = customerPage.getAccountBalance(accountNumber);
		  
		  Assert.assertTrue(oldBalance.add(amount).compareTo(currentBalance) == 0);
	  }
	  
}
