package com.mscatdk.docker;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateViewRoute;

public class HelloAPI {
	
	public final TemplateViewRoute handleHalloHTML = (Request request, Response response) -> {
		RequestData requestData = new RequestData(request);
		return new ModelAndView(requestData.getHashMap(), "hello.vm");
	};

	public final Route handleHalloJSON = (Request request, Response response) -> {
		return new RequestData(request);
	};
}
