package com.socket.server;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class App {
	
	public static void main(String... args) {
		System.out.println("Start!");
		
		// Create large String
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i<10000; i++) {
			sb.append("As of 2017, text messages are used by youth and adults for personal, family and social purposes and in business. Governmental and non-governmental organizations use text messaging for communication between colleagues. As with emailing, in the 2010s, the sending of short informal messages has become an accepted part of many cultures.[1] This makes texting a quick and easy way to communicate with friends and colleagues, including in contexts where a call would be impolite or inappropriate (e.g., calling very late at night or when one knows the other person is busy with family or work activities). Like e-mail and voice mail, and unlike calls (in which the caller hopes to speak directly with the recipient), texting does not require the caller and recipient to both be free at the same moment; this permits communication even between busy individuals. Text messages can also be used to interact with automated systems, for example, to order products or services from e-commerce websites, or to participate in online contests. Advertisers and service providers use direct text marketing to send messages to mobile users about promotions, payment due dates, and other notifications instead of using postal mail, email, 00 \n");
		}
		
		try (ServerSocket server = new ServerSocket(4567,1)) {
			while(true) {
				System.out.println("Accepting!");
				try (Socket socket = server.accept(); 
					BufferedWriter output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));) {
					System.out.println("Sending!");
					output.write(sb.toString());
				} catch (Exception w) {
					w.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
