package org.msc;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(name="ClassLocatorServlet", urlPatterns={"/classLocator"} )
public class ClassLocatorServlet extends HttpServlet {
	
	private static final long serialVersionUID = -7961823325544192645L;
	private static Logger logger = LoggerFactory.getLogger(ClassLocatorServlet.class);
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	logger.debug("ClassLocatorServlet -> BEGIN");
    	
    	try (PrintWriter out = response.getWriter()) {
    		String className = request.getParameter("className");
    		
    		if (className == null || className.isEmpty()) {
    			out.println("No className URL parameter.");
    		} else {
    			className = "/" + className.replace('.', '/') + ".class";
    			
    			URL classPath = this.getClass().getResource(className);
    			
    			if (classPath != null) {
    				out.println(String.format("Class Name: %s Location: %s", className, classPath.getFile()));
    			} else {
    				out.println(String.format("Class Name: %s not found!", className));
    			}
    		}
    	} catch (Exception e) {
    		logger.error("Unable to process the request!", e);
    	}
        
        logger.debug("ClassLocatorServlet -> END");
    }
	
}
