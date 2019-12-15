package com.mscatdk.security.rsa;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class KeyPairHandler {
	
	private Base64.Encoder encoder = Base64.getEncoder();
	private Base64.Decoder decoder = Base64.getDecoder();
	
	public Path getPrivateKeyPath(String basePath) {
		return Paths.get(basePath, "key");
	}
	
	public Path getPublicKeyPath(String basePath) {
		return Paths.get(basePath, "key.pub");
	}
	
	public void writeToFile(Key key, Path path) throws IOException {
		try (Writer out = new FileWriter(path.toString())) {
			out.write(encoder.encodeToString(key.getEncoded()));
		}
	}
	
	public RSAPublicKey loadPublicKey(Path path) throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {
		X509EncodedKeySpec ks = new X509EncodedKeySpec(loadKey(path));
		KeyFactory kf = KeyFactory.getInstance("RSA");
		return (RSAPublicKey) kf.generatePublic(ks);
	}
	
	public RSAPrivateKey loadPrivateKey(Path path) throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {
		PKCS8EncodedKeySpec ks = new PKCS8EncodedKeySpec(loadKey(path));
		KeyFactory kf = KeyFactory.getInstance("RSA");
		return (RSAPrivateKey) kf.generatePrivate(ks);
	}
	
	private byte[] loadKey(Path path) throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(path.toString()))) {
			String keyString = br.readLine();
			return decoder.decode(keyString);
		}
	}
	
	

}
