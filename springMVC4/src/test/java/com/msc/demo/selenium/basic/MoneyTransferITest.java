package com.msc.demo.selenium.basic;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.msc.demo.selenium.SeleniumUtil;

public class MoneyTransferITest {

	  @Test
	  public void test() throws Exception {
		  /* Test Parameters */
		  String customerNumber = "6666664444";
		  String accountNumber1 = "8888888822";
		  String accountNUmber2 = "3337777777";
		  BigDecimal amount = new BigDecimal("1000");
		  
		  /* Test logic */
		  WebDriver driver = SeleniumUtil.getWebDriver();
		  driver.get(SeleniumUtil.getBaseUrl());

		  /* Perform customer search */
		  driver.findElement(By.xpath("//input[@se-id='customerSearch']")).clear();
		  driver.findElement(By.cssSelector("[se-id=customerSearch]")).sendKeys(customerNumber);
		  driver.findElement(By.id("customerSearchSubmit")).click();
		  
		  /* Save data */
		  BigDecimal balance1Before =  getAccountValue(driver, accountNumber1);
		  BigDecimal balance2Before =  getAccountValue(driver, accountNUmber2);
		  
		  /* Perform Money Transfer */
		  driver.findElement(By.linkText("Money Transfer")).click();
		  driver.findElement(By.id("fromAccount")).sendKeys(accountNUmber2);
		  driver.findElement(By.id("toAccount")).clear();
		  driver.findElement(By.id("toAccount")).sendKeys(accountNumber1);
		  driver.findElement(By.id("amount")).clear();
		  driver.findElement(By.id("amount")).sendKeys("1000");
		  driver.findElement(By.cssSelector("button.btn.btn-success")).click();
		  
		  
		  /* Money Transfer assertions */
		  String message = driver.findElement(By.xpath("//div[@id='UIMessages']")).getText();
		  Assert.assertEquals("The money transfer has been completed succesfully!", message);
		  
		  BigDecimal balance1After =  getAccountValue(driver, accountNumber1);
		  BigDecimal balance2After =  getAccountValue(driver, accountNUmber2);
		  
		  /* Check account balance */
		  Assert.assertTrue(balance1Before.add(amount).compareTo(balance1After) == 0);
		  Assert.assertTrue(balance2Before.subtract(amount).compareTo(balance2After) == 0);
		  
		  driver.quit();  

	  }
	  
	  private BigDecimal getAccountValue(WebDriver driver, String accountNumber) {
		  String xPath = String.format("//td[text()='%s']/../td[3]",accountNumber);
		  String balanceString = driver.findElement(By.xpath(xPath)).getText();
		  balanceString = balanceString.replace("$", "").replace(",", "");
		  return new BigDecimal(balanceString);
	  }
	
}
