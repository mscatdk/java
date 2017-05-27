package com.mscatdk.SSLShake;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * Check whether or not a SSL connection can be established to a specific hostname on a specific port
 *
 */
public class App {
	
	public static void main(String[] args) throws ClientProtocolException, IOException {
		
		if (args.length < 2) {
			System.out.println("Usage: SSLShake hostname port");
			System.exit(-1);
		}
		
		if (args[1] == null || !(args[1].matches("^\\d+$"))) {
			System.out.println("Error: Port number must be integer");
			System.out.println("Usage: SSLShake hostname port");
			System.exit(-1);
		}
		
		try (CloseableHttpClient client = HttpClients.createDefault()) {
			HttpHead httpHead = new HttpHead(String.format("https://%s:%s", args[0], args[1]));
			try (CloseableHttpResponse response = client.execute(httpHead)) {
				System.out.println(response.getStatusLine().toString());
				System.out.println("Connected Succesfully");
			}
		}
	}
}
