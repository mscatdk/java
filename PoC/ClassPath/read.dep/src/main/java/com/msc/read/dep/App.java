package com.msc.read.dep;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class App
{
	private Gson gson = new Gson();
	private Config config = new Config();
	
	public static void main(String... args) {
		System.out.println("hmm");
		System.out.println(new Config().readResource());
	}

	public String getVersion() {		
		JsonObject obj = new JsonObject();
		obj.addProperty("Version", config.readResource());
		obj.addProperty("Name", "NewCode2");
		return gson.toJson(obj);
	}
}
