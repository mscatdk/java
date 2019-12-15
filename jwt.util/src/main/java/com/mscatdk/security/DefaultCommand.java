package com.mscatdk.security;

import com.beust.jcommander.Parameter;

public class DefaultCommand {
	
	@Parameter(names = "-p", description = "Key location")
	protected String keyPath = "./";

}
