package com.msc.demo.selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

public class SeleniumUtil {
	
	  private final static String baseUrl = "http://127.0.0.1:8080/demo/";
	  
	  public static WebDriver getPhantomJSDriver() {
			DesiredCapabilities dCap = new DesiredCapabilities();
			// Download phantomjs here: http://phantomjs.org/download.html
			dCap.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "C:/tools/phantomjs-2.0.0-windows/bin/phantomjs.exe");
			
			WebDriver driver = new PhantomJSDriver(dCap);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().setSize(new Dimension(1920, 1080));
			
			return driver;
	  }
	  
	  public static WebDriver getFirefoxDriver() {
		  	WebDriver driver = new FirefoxDriver();
		    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		  	return driver;
	  }
	  
	  public static WebDriver getChromeDriver() {
		  //Download chrome driver here: http://chromedriver.storage.googleapis.com/index.html
		  System.setProperty("webdriver.chrome.driver", "C:/tools/chromedriver_win32/chromedriver.exe");
		  WebDriver driver = new ChromeDriver();
		  return driver;
	  }
	  
	  public static WebDriver getWebDriver() {
		  	return getPhantomJSDriver();
	  }
	  
	  
	  public static String getBaseUrl() {
		  	return baseUrl;
	  }

}
