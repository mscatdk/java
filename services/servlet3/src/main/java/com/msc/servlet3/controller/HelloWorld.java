package com.msc.servlet3.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.msc.servlet3.config.Config;

@WebServlet(name="HelloWorldServlet", urlPatterns={"/hello"}, initParams={ @WebInitParam(name="simpleParam", value="paramValue") } )
public class HelloWorld extends HttpServlet {

	private final static Logger LOGGER = Logger.getLogger(HelloWorld.class.getName());
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6341948582041360126L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.warning("GET -> BEGIN");
    	PrintWriter out = response.getWriter();
        String simpleParam = getServletConfig().getInitParameter("simpleParam");
        out.println("Hello World "+simpleParam);
        out.println(Config.getName("demo2"));
        out.close();
        LOGGER.info("GET -> BEGIN");
    }
  
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         doGet(request,response);
    }
	
}
