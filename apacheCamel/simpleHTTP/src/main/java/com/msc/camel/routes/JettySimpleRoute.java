package com.msc.camel.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class JettySimpleRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("jetty:http://127.0.0.1:6789/hello").process(new Processor() {
			
			public void process(Exchange exchange) throws Exception {
				Message msg = exchange.getOut();
				msg.setBody("Hello from Camel!");
				
			}
		});
		
	}

}
