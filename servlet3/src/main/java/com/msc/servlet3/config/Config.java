package com.msc.servlet3.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONObject;

public class Config {
	
	public static Map<String, JSONObject> data;
	
	public synchronized void initialize() {
		// load configuration
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("app.json").getFile());
		StringBuilder sb = new StringBuilder();
		
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			
			JSONObject json = new JSONObject(sb.toString());
			data = new HashMap<>();
			for (Iterator<String> obj = json.keys(); obj.hasNext();) {
				String key = obj.next();
				data.put(key, json.getJSONObject(key));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getName(String key) {
		return data.get(key).getString("name");
	}

}
