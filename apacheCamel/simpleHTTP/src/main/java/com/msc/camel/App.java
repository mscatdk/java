package com.msc.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.msc.camel.data.Customer;
import com.msc.camel.routes.HttpRequestRoute;
import com.msc.camel.routes.JettySimpleRoute;

public class App {
	
	private static Logger logger = LoggerFactory.getLogger(App.class);
	
    public static void main( String[] args ) throws Exception {
    	CamelContext context = new DefaultCamelContext();
    	
    	context.addRoutes(new JettySimpleRoute());
    	context.addRoutes(new HttpRequestRoute());
    	
    	context.start();
    }
}
