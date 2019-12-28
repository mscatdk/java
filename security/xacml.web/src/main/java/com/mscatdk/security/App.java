package com.mscatdk.security;

import static spark.Spark.before;
import static spark.Spark.get;
import static spark.Spark.halt;
import static spark.Spark.port;

import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import org.eclipse.jetty.http.HttpHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;


public class App {

	private static Logger logger = LoggerFactory.getLogger(App.class);
	
	private static final String PUBLIC_KEY_BASE64 = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAobSln7UJG5K4KwiVDhUTHCP7Ye8ASow/Ulf6j89RnVZFMKyW10f0Zz7Oy9O31WvxHuAcki466+/lFoVT2BkMPNgZfY/whsNyEMWbUIQEyQBdbErj8ITcDF09ajCIJfXCe2YqemETMsgM8w858xk4donAG9l20Ck6m7TPKtEYCxhlsFq0hQBDCch/QE7FVbyRMZAI0fTz5QVKlH1hu5lrMd71e/wUR2GrdTPRRpv9sswGlaUbFeC1EiQfhuei1r2wxjzU2/425KToyjLc0BKkCuE6416T+pPjcJ5RC3FNw/NtAFmbrHgQ51TeXNpD9KitgmdyDBe5ek73LPswE4Qi0wIDAQAB";
	
	// Test token: eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJNaWtlIiwicm9sZSI6InVzZXIiLCJpc3MiOiJGYWtlVG9rZW4ifQ.WmidfA8pQ_PAIYdOoxX5LYoE43i2A6Jmpl--mhcMRpZvIz71gDBE6QibwtA1hqBvENxm6E_Y_NHzbJ-AigxPsk2NlEKZ6HJM6K-iV6ZtKQl9lkIxeCXKHrNaVViPTWs0aMufbm1QOJsSsIZ-u3EPmy411KLMiHCzOFOh6EhdY_fx30G13rncvmaJM5pFtO77mfWZnNzJ6bifgkz5qV-jcLbfw1sZYTnZ6jUritE0Yabr6tFnWUmeRbQQ3uiHW8YZG6Hh9oN9Fu8rMWnwxL5CKhpjDycKxiFIPl6KIfH2LLALYqUaIrtcrEyFMLZbKJzMOoA7yTs4eOJdoWVUefNT4g
	
	public static void main( String[] args ) {    	
    	port(9090);
    	
    	Authorization authorization = Authorization.getInstance();
    	
    	before((request, response) -> {    		
    		String auth = request.headers(HttpHeader.AUTHORIZATION.asString());
    		if (auth != null) {
    			logger.info("Authorization header is set!");
    			
    			DecodedJWT decodedJWT;
				if ((decodedJWT = isJWTValid(auth)) != null && authorization.authorization(decodedJWT, request)) {
					return;
    			}
    			
    		}
    		    		
    	    halt(401, "You are not welcome here");
    	});
    	
    	get("/hello", (req, res) -> "Hello World!");
    	
    	get("/secret", (req, res) -> "This is secret!");
		
    }
	
	private static DecodedJWT isJWTValid(String jwt) {
		try {
			logger.info("Token: {}", jwt);
			Base64.Decoder decoder = Base64.getDecoder();
			X509EncodedKeySpec ks = new X509EncodedKeySpec(decoder.decode(PUBLIC_KEY_BASE64));
			KeyFactory kf = KeyFactory.getInstance("RSA");
			RSAPublicKey publicKey = (RSAPublicKey) kf.generatePublic(ks);
			
		    Algorithm algorithm = Algorithm.RSA256(publicKey, null);
		    JWTVerifier verifier = JWT.require(algorithm)
		        .withIssuer("FakeToken")
		        .build();
		    return verifier.verify(jwt);
		} catch (Exception ex){
			logger.error("Token is invalid", ex);
			return null;
		} 
	}
}
