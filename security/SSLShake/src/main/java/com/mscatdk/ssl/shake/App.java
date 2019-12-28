package com.mscatdk.ssl.shake;

import java.io.IOException;

import org.apache.http.NoHttpResponseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Check whether or not a SSL connection can be established to a specific hostname on a specific port
 *
 */
public class App {
	
	private static Logger logger = LoggerFactory.getLogger(App.class);
	
	public static void main(String[] args) throws IOException {
		
		if (args.length < 2) {
			printUsage();
			System.exit(-1);
		}
		
		if (args[1] == null || !(args[1].matches("^\\d+$"))) {
			logger.error("Error: Port number must be integer");
			printUsage();
			System.exit(-1);
		}
		
		try (CloseableHttpClient client = HttpClients.createDefault()) {
			HttpHead httpHead = new HttpHead(String.format("https://%s:%s", args[0], args[1]));
			try (CloseableHttpResponse response = client.execute(httpHead)) {
				logger.info("HTTP status line: {}", response.getStatusLine());
				logger.info("Connected Succesfully - HTTP");
			} catch (NoHttpResponseException e) {
				logger.info("Connected Succesfully - TCP (NOT HTTP)");
			}
		}
	}
	
	private static void printUsage() {
		logger.info("Usage: SSLShake hostname port");
	}
}
