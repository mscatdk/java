package com.msc.demo.common;

import java.util.ResourceBundle;

public class MessageUtil {
	
	private static ResourceBundle message;
	
	static {
		message = ResourceBundle.getBundle("MessagesBundle");
	}

	public static String getMessage(String key) {
		String msg;
	
		try {
			if (message == null || (msg = message.getString(key)) == null) {
				return "???? NOT FOUND ????";
			} else {
				return msg;
			}
		} catch (Exception e) {
			return "???? NOT FOUND ????";
		}
	}
	
}
