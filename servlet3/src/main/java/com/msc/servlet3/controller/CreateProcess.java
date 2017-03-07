package com.msc.servlet3.controller;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.msc.servlet3.process.TestProcess;

@WebServlet(name="CreateProcessServlet", urlPatterns={"/create"} )
public class CreateProcess extends HttpServlet {

	private final static Logger LOGGER = Logger.getLogger(CreateProcess.class.getName());
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6341948582041360126L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.warning("GET -> BEGIN");
        ServletContext sce = request.getServletContext();
        ThreadPoolExecutor executor = (ThreadPoolExecutor) sce.getAttribute("executor");
        
        String id = UUID.randomUUID().toString();
        LOGGER.info("ID: " + id);
        executor.execute(new TestProcess(id));
        
        response.getWriter().println("Created " + id);
        LOGGER.info("GET -> BEGIN");
    }
	
}
