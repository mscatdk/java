package com.msc.upload.service.controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="Landing page", urlPatterns={"/index"} )
public class Index extends HttpServlet {
	
	private final static Logger LOGGER = Logger.getLogger(Index.class.getName());

	/**
	 * 
	 */
	private static final long serialVersionUID = -5126443817829600850L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.warning("GET -> BEGIN");
    	
        
        request.getRequestDispatcher("/index.jsp").forward(request, response);
        
        LOGGER.info("GET -> END");
    }
	
}
