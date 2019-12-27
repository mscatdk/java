package com.msc.demo.cucumber;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import com.msc.demo.selenium.SeleniumUtil;
import com.msc.demo.selenium.pagemodel.CustomerPage;
import com.msc.demo.selenium.pagemodel.LandingPage;
import com.msc.demo.selenium.pagemodel.MoneyTransferPage;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {

	public static WebDriver driver;
	public static LandingPage landingPage;
	public static CustomerPage customerPage;
	public static MoneyTransferPage moneyTransferPage;
	
	@Before
	public void beforeScenario() {
		driver = SeleniumUtil.getWebDriver();
		landingPage = new LandingPage(driver);
		customerPage = new CustomerPage(driver);
		moneyTransferPage = new MoneyTransferPage(driver);
	}
	
	@After
	public void afterScenario(Scenario scenario) {
		
		if  (scenario.isFailed()) {
			try {
				scenario.write("Current Page URL is " + driver.getCurrentUrl());
				byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
				scenario.embed(screenshot, "image/png");
				
			} catch (WebDriverException e) {
				System.err.println(e.getMessage());
			}
			
		}
		
		driver.quit();
	}
}
