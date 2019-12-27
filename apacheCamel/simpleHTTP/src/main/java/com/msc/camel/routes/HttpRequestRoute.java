package com.msc.camel.routes;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.processor.aggregate.GroupedExchangeAggregationStrategy;

public class HttpRequestRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("direct:serviceFacade")
		  .multicast(new GroupedExchangeAggregationStrategy()).parallelProcessing()
		    .enrich("http://google.com?q=Foo").enrich("http://google.com?q=Bar")
		  .end();
		
		from("jetty:http://127.0.0.1:6789/hi").enrich("direct:serviceFacade").setBody(exchangeProperty(Exchange.GROUPED_EXCHANGE));
		
	}
	
	

}
