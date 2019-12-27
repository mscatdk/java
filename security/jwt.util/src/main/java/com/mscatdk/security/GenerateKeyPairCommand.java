package com.mscatdk.security;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.beust.jcommander.Parameters;
import com.mscatdk.security.rsa.KeyPairHandler;

@Parameters(commandDescription = "Generate RSA key pair")
public class GenerateKeyPairCommand extends DefaultCommand implements CommandInterface {

	private static Logger logger = LoggerFactory.getLogger(GenerateKeyPairCommand.class);
	
	KeyPairHandler keyPairHandler = new KeyPairHandler();
	
	public void exec() {
		try {
			KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
			kpg.initialize(2048);
			KeyPair kp = kpg.generateKeyPair();
			
			keyPairHandler.writeToFile(kp.getPublic(), keyPairHandler.getPublicKeyPath(super.keyPath));
			logger.info("Public key format: {}", kp.getPublic().getFormat());
			keyPairHandler.writeToFile(kp.getPrivate(), keyPairHandler.getPrivateKeyPath(super.keyPath));
			logger.info("Private key format: {}", kp.getPrivate().getFormat());
			logger.info("Key pair has been generated!");
		} catch (Exception e) {
			logger.error("Unable to generate keypair!", e);
		}
	}
	
}
