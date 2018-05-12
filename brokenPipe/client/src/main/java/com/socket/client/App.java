package com.socket.client;

import java.net.Socket;

public class App {
	
	public static void main(String...  args) {
		String hostname = "localhost";
		if (args.length >= 1) {
			hostname = args[0];
		}
		
		try  {
			System.out.println("Using hostname: " + hostname);
			Socket s = new Socket(hostname, 4567);
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
