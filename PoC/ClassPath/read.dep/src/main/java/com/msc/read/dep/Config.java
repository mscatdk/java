package com.msc.read.dep;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Config {
	
	private Gson gson = new Gson();
	

	public String readResource() {
		// load configuration
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		//ClassLoader classLoader = Config.class.getClassLoader();
		InputStream is = classLoader.getResourceAsStream("version.json");
		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			
			String line = null;
			StringBuilder sb = new StringBuilder();
			
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			
			JsonObject jsonObject = gson.fromJson(sb.toString(), JsonObject.class);
			
			return jsonObject.get("version").getAsString();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "Failed";
		
		
	}
	
}
