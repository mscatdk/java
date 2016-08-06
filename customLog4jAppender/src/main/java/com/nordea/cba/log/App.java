package com.nordea.cba.log;

import org.apache.log4j.Logger;

/**
 * Hello world!
 *
 */
public class App 
{
	final static Logger logger = Logger.getLogger(App.class);
	
    public static void main( String[] args )
    {
    	logger.error("Hello world");
        System.out.println( "Hello World!" );
    }
}
