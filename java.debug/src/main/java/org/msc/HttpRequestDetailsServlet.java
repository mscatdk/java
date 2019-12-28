package org.msc;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

@WebServlet(name="HttpRequestDetailsServlet", urlPatterns={"/httpRequestDetails"} )
public class HttpRequestDetailsServlet extends HttpServlet {
	
	private static final long serialVersionUID = -6384777523159040774L;
	private static Logger logger = LoggerFactory.getLogger(ClassLocatorServlet.class);
	
	private static Gson gson = new Gson();
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	processRequest(request, response);
    }

	@Override
    protected void doHead(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	processRequest(request, response);
    }

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	processRequest(request, response);
    }
	
	@Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	processRequest(request, response);
    }
	
	@Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	processRequest(request, response);
    }
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Map<String, String>> data = new HashMap<>();
    	
    	data.put("general", extractGeneralInformation(request));
    	data.put("properties", extractProperties());
    	data.put("header", extractHeader(request));
    	data.put("cookies", extractCookies(request));
    	data.put("attributes", extractAttributes(request));
    	
    	try (PrintWriter out = response.getWriter()) {
    		String json = gson.toJson(data);
    		out.print(json);
    		logger.info(json);
    	} catch (Exception e) {
    		logger.error("Unable to write message!", e);
    	}
	}
	
	private Map<String, String> extractAttributes(HttpServletRequest request) {
		Map<String, String> attributes = new HashMap<>();
		
		Enumeration<String> names = request.getAttributeNames();
		while(names.hasMoreElements()) {
			String name = names.nextElement();
			
			attributes.put(name, gson.toJson(request.getAttribute(name)));
		}
 		
		return attributes;
	}
	
	private Map<String, String> extractCookies(HttpServletRequest request) {
		Map<String, String> cookieData = new HashMap<>();
		
		Cookie[] cookies =  request.getCookies();
		
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				cookieData.put(cookie.getName(), gson.toJson(cookie));
			}
		}
 		
		return cookieData;
	}
	
	private Map<String, String> extractHeader(HttpServletRequest request) {
		Map<String, String> header = new HashMap<>();
		
		Enumeration<String> dd = request.getHeaderNames();
		while(dd.hasMoreElements()) {
			String name = dd.nextElement();
			
			header.put(name, request.getHeader(name));
		}
		return header;
	}
	
	private Map<String, String> extractGeneralInformation(HttpServletRequest request) {
		Map<String, String> general = new HashMap<>();
		
		general.put("hostname", extractHostname());
		general.put("remoteAddr", request.getRemoteAddr());
		general.put("remoteHost", request.getRemoteHost());
		general.put("remotePort", Integer.toString(request.getRemotePort()));
		general.put("remoteUser", request.getRemoteUser());
		general.put("httpMethod", request.getMethod());
				
		setHttpBody(request, general);
		setSessionId(request, general);
		
		return general;
	}

	private void setSessionId(HttpServletRequest request, Map<String, String> general) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			general.put("requestedSessionId", session.getId());
		}
	}

	private void setHttpBody(HttpServletRequest request, Map<String, String> general) {
		try {
			String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
			general.put("httpBody", body);
		} catch (Exception e) {
			logger.error("Unable to read body!", e);
		}
	}
    
    private Map<String, String> extractProperties() {
    	Properties pp = System.getProperties();
    	Map<String, String> properties = new HashMap<>();
    	
    	pp.forEach((s, p) -> properties.put((String)s,(String)p));
    	
    	return properties;
    }
    
	private String extractHostname() {
		try {
			InetAddress ip = InetAddress.getLocalHost();
			return ip.getHostName();
		} catch (Exception e) {
			return "N/A";
		}
	}

}
