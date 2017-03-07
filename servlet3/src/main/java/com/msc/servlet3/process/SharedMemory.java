package com.msc.servlet3.process;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SharedMemory {

	private static final Map<String, List<String>> messageMap = new HashMap<>();
	
	public static void addMessage(String id, String msg) {
		List<String> messages = messageMap.get(id);
		if (messages == null) {
			messages = new ArrayList<>();
			messages.add(msg);
			messageMap.put(id, messages);
		} else {
			messages.add(msg);			
		}	
	}
	
	public static String getMessage(String id) {
		StringBuffer sb = new StringBuffer();
		
		for (String s : messageMap.get(id)) {
			sb.append(s + "\n");
		}
		
		return sb.toString();
	}
}
