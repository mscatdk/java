package com.maven.webApp1.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DefaultController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3274645414083968813L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Properties prop = new Properties();
		InputStream in = null;
		try {
			in=getClass().getResourceAsStream("/test.properties");
			
			prop.load(in);
			
			String message = prop.getProperty("message");
			
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<h1>" + message + "</h1>");
		} finally {
			if (in != null) {
				in.close();
			}
		}
		
	}
	
}
