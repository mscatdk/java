package com.msc.http;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.StopWatch;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class App {
	
	private static Logger logger = LoggerFactory.getLogger(App.class);
	
    public static void main( String[] args ) {
    	logger.info("Initialize library...");
    	getDataViaHTTP("http://www.google.com");
    	
    	/*
    	 * Local method call
    	 */
    	logger.info("Method call started...");
    	StopWatch methodStopWatch = StopWatch.createStarted();
    	getDataLocal();
    	long methodTime = methodStopWatch.getTime(TimeUnit.NANOSECONDS);
    	logger.info("Method call Done");   	
    	logger.info("Method call toke {} [ns]",methodTime);    	
    	
    	/*
    	 * HTTP Call
    	 */
    	logger.info("HTTP test started...");
    	StopWatch httpStopWatch = StopWatch.createStarted();
    	getDataViaHTTP("http://127.0.0.1:4567/hello");
    	long httpTime = httpStopWatch.getTime(TimeUnit.NANOSECONDS);
    	logger.info("HTTP test done");
    	
    	logger.info("HTTP call toke {} [ns]", httpTime);
    }
    
    private static String getDataViaHTTP(String url) {
     try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
    	 HttpGet httpget = new HttpGet(url);
       	 
       	 try (CloseableHttpResponse httpresponse = httpclient.execute(httpget)) {
       	 
       		 return EntityUtils.toString(httpresponse.getEntity());    	 
       	 }
     } catch (Exception e) {
    	 logger.error("Unable to complete the HTTP request", e);
    	 return null;
     }
   	 
    }
    
    private static String getDataLocal() {
   	 return "hello world!";
    }
}
