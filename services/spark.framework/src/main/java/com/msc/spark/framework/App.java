package com.msc.spark.framework;

import static spark.Spark.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App 
{
    public static void main( String[] args ) {
    	
    	Logger logger = LoggerFactory.getLogger(App.class);
    	
    	port(9090);
    	
    	before((request, response) -> {
    		
    		String jwt = request.session().attribute("jwt");
    		
    		if (jwt != null) {
    			logger.info("JWT token set!");
    			/*
    			 * We should check it
    			 */
    			return;
    		}
    	    
    		String auth = request.headers("Authorization");
    		if (auth != null) {
    			logger.info("Authorization header is set!");
    			request.session().attribute("jwt", "random");
    			return;
    		}
    		
    	    halt(401, "You are not welcome here");
    	});
    	
    	get("/hello", (req, res) -> "Hello World");
    
        get("/hello/:name", (request, response) -> {
            return "Hello: " + request.params(":name");
        });
        
        after((request, response) -> {
        	logger.info("After filter");
        });
        
    }
   
}
