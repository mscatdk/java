package com.mscatdk.security;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.mscatdk.security.rsa.KeyPairHandler;

public class GenerateJWT extends DefaultCommand implements CommandInterface {

	private static KeyPairHandler keyPairHandler = new KeyPairHandler();
	
	private static Logger logger = LoggerFactory.getLogger(GenerateJWT.class);

	@Override
	public void exec() {
		try {
			RSAPublicKey publicKey = keyPairHandler.loadPublicKey(keyPairHandler.getPublicKeyPath(super.keyPath));
			RSAPrivateKey privateKey = keyPairHandler.loadPrivateKey(keyPairHandler.getPrivateKeyPath(super.keyPath));
			Algorithm algorithm = Algorithm.RSA256(publicKey, privateKey);
			String token = JWT.create()
				        		.withIssuer("FakeToken")
				        		.withSubject("Mike")
				        		.withClaim("role", "user")
				        		.sign(algorithm);
			
			logger.info("Key: {}", token);
		} catch (Exception e){
			logger.error("Unable to generate JWT", e);
		}
	}

}
