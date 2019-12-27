package com.mscatdk.security;

import com.beust.jcommander.JCommander;

public class App {
	
    public static void main( String[] args ) {
		JCommander jc = new JCommander();
		GenerateKeyPairCommand generateKeyPairCommand = new GenerateKeyPairCommand(); 
		jc.addCommand("generateRSA", generateKeyPairCommand);
		
		GenerateJWT generateJWT = new GenerateJWT();
		jc.addCommand("generateJWT", generateJWT);
		
		ValidateJWT validateJWT = new ValidateJWT();
		jc.addCommand("validateJWT", validateJWT);
		jc.setProgramName("JWT Util");
		jc.parse(args);
		
		if (jc.getParsedCommand() == null) {
			jc.usage();
		} else {
			
			switch (jc.getParsedCommand()) {
			case "generateRSA":
				generateKeyPairCommand.exec();
				break;
			case "generateJWT":
				generateJWT.exec();
				break;
			case "validateJWT":
				validateJWT.exec();
				break;
			default:
				jc.usage();
			}
		}
    }
}
