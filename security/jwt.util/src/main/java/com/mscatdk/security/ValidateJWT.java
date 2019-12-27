package com.mscatdk.security;

import java.security.interfaces.RSAPublicKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.beust.jcommander.Parameter;
import com.mscatdk.security.rsa.KeyPairHandler;

public class ValidateJWT extends DefaultCommand implements CommandInterface {
	
	private static KeyPairHandler keyPairHandler = new KeyPairHandler();
	
	private static Logger logger = LoggerFactory.getLogger(ValidateJWT.class);

	@Parameter(names = "-t", description = "token")
	protected String token;
	
	@Override
	public void exec() {
		logger.debug("Token: {}", token);
		try {
			RSAPublicKey publicKey = keyPairHandler.loadPublicKey(keyPairHandler.getPublicKeyPath(super.keyPath));
			
		    Algorithm algorithm = Algorithm.RSA256(publicKey, null);
		    JWTVerifier verifier = JWT.require(algorithm)
		        .withIssuer("FakeToken")
		        .build();
		    verifier.verify(token);
		    logger.info("The token is valid");
		} catch (JWTVerificationException ex){
			logger.error("Token is invalid", ex);
		} catch (Exception e) {
			logger.error("Unable to validate token", e);
		}
		
	}

}
