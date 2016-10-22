package com.msc.read.zip;


public class ZipVerion {
	
	public String getVersion() {
		return new Config().readResource();
	}
	
}