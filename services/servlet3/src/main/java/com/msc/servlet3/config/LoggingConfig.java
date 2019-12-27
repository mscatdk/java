package com.msc.servlet3.config;

import java.io.InputStream;
import java.util.logging.LogManager;

public class LoggingConfig {
	public LoggingConfig() {
		try {
			// Load a properties file from class path that way can't be achieved
			// with java.util.logging.config.file
			final LogManager logManager = LogManager.getLogManager();
			InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("logging.properties");
			//File must be in public webcontent same place as JSP files
			//final InputStream is = getClass().getResourceAsStream("logging.properties");
			// http://www.nailedtothex.org/roller/kyle/entry/java-util-logging-programmatic-configuration
			logManager.readConfiguration(is);
		} catch (Exception e) {
			// The runtime won't show stack traces if the exception is thrown
			e.printStackTrace();
		}
	}
}
