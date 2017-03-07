package com.msc.servlet3.controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.msc.servlet3.process.SharedMemory;

@WebServlet(name="ReadUpdateServlet", urlPatterns={"/read"} )
public class ReadUpdate extends HttpServlet {
	
	private final static Logger LOGGER = Logger.getLogger(HelloWorld.class.getName());
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6341948582041360126L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.warning("GET -> BEGIN");
    	String id = request.getParameter("id");
    	LOGGER.info(id);
    	response.getWriter().println(SharedMemory.getMessage(id));
        LOGGER.info("GET -> BEGIN");
    }

}
