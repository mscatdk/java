package com.nordea.cba.log;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

public class MyAppender extends AppenderSkeleton {

	public void close() {
		System.out.println("Closing appender");
		
	}

	public boolean requiresLayout() {	
		return true;
	}

	@Override
	protected void append(LoggingEvent event) {
		System.out.print("Log start \n");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String message = this.layout.format(event);
		System.out.print("Message: "+ message + " \n");
		System.out.print("Log stop \n");
	}

	
	
}
