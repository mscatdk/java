package com.msc.servlet3.listener;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.msc.servlet3.config.Config;
import com.msc.servlet3.config.LoggingConfig;

@javax.servlet.annotation.WebListener
public class SessionListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("Context destroyed!");
		ThreadPoolExecutor executor = (ThreadPoolExecutor) sce.getServletContext().getAttribute("executor");
		executor.shutdown();
	}

	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("Context created!");
		new Config().initialize();
		
		LoggingConfig loggingConfig = new LoggingConfig();
		// create the thread pool
		ThreadPoolExecutor executor = new ThreadPoolExecutor(100, 200, 50000L, TimeUnit.MILLISECONDS,
				new ArrayBlockingQueue<Runnable>(100));
		sce.getServletContext().setAttribute("executor", executor);	
	}
}