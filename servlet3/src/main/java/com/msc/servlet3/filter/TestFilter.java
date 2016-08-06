package com.msc.servlet3.filter;

import java.io.*;
import java.util.logging.Logger;

import javax.servlet.*;
import javax.servlet.annotation.*;

import com.msc.servlet3.controller.HelloWorld;
 
@WebFilter(urlPatterns={"/*"},
        initParams={ @WebInitParam(name="simpleParam", value="paramValue") }, asyncSupported = true)
public class TestFilter implements Filter {
	
	private final static Logger LOGGER = Logger.getLogger(TestFilter.class.getName());
 
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        StringWriter sw = new StringWriter();
        PrintWriter writer = new PrintWriter(sw);
 
        LOGGER.warning("Logging on warning!");
        
        request.setAttribute("org.apache.catalina.ASYNC_SUPPORTED", true);
        writer.println("===============");
        writer.println("Filter intercepted!");
        writer.println("===============");
 
        // Log the resulting string
        writer.flush();
        filterConfig.getServletContext().
        log(sw.getBuffer().toString());
 
        chain.doFilter(request, response);
 
        LOGGER.info("Logging on info!");
    }
 
    private FilterConfig filterConfig = null;
    public void init(FilterConfig filterConfig) 
    throws ServletException {
        this.filterConfig = filterConfig;
    }
 
    public void destroy() {    }
}
