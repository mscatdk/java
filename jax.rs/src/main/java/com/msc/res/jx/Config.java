package com.msc.res.jx;

import javax.ws.rs.core.MediaType;

public class Config {

	public static final String producesTypes = MediaType.APPLICATION_XML;
	
	public static final String DemoPath = "demo";
	
	public static String[] demo() {
		return new String[]{"demo", "demo"};
	}
	
}
