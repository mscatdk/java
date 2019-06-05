package com.mscatdk.docker;

import static spark.Spark.get;
import static spark.Spark.port;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import spark.template.velocity.VelocityTemplateEngine;

/**
 * Return the following information
 * 
 *
 */
public class App {
	
	private static Logger logger = LoggerFactory.getLogger(App.class);
	
	private static final String HELLO_URL = "/hello";
	
    public static void main( String[] args ) {
    	logger.info("Starting...");
    	HelloAPI halloAPI = new HelloAPI();
    	port(9090);
    	
    	get(HELLO_URL, halloAPI.handleHalloHTML, new VelocityTemplateEngine());
    	get(HELLO_URL, "*/*", halloAPI.handleHalloHTML, new VelocityTemplateEngine());
    	get(HELLO_URL, "application/json", halloAPI.handleHalloJSON, new JsonTransformer());
    	
    	logger.info("Ready!");
    }
    
}
