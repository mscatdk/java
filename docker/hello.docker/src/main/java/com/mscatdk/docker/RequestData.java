package com.mscatdk.docker;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

import spark.Request;

public class RequestData {
	
	private final String hostname;
	private final String clientIp;
	private final Map<String, String> headers;
	
	public RequestData(Request request) {
		this.hostname = extractHostname();
		this.clientIp = request.ip();
		this.headers = createHeaderMap(request);
	}

	private String extractHostname() {
		try {
			InetAddress ip = InetAddress.getLocalHost();
			return ip.getHostName();
		} catch (Exception e) {
			return "N/A";
		}
	}
	
    private Map<String, String> createHeaderMap(Request req) {
    	Map<String, String> headerMap = new HashMap<>();
    	for (String name : req.headers()) {
    		headerMap.put(name, req.headers(name));
    	}
    	
    	return headerMap;
    }
   
	public String getHostname() {
		return hostname;
	}

	public String getClientIp() {
		return clientIp;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}
    
    public Map<String, Object> getHashMap() {
    	Map<String, Object> data = new HashMap<>();
    	data.put("hostname", this.hostname);
    	data.put("client_ip", this.getClientIp());
		data.put("headers", this.headers);
		
		return data;
    }
}
