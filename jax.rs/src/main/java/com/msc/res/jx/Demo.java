package com.msc.res.jx;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.msc.res.jx.model.DataObj;

@Path(Config.DemoPath)
public class Demo {

	@GET
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Path("test")
	public Response test() throws URISyntaxException {
		DataObj dataObj = new DataObj("Michael", "We are flying"); 
		
		return Response.created(new URI("http://127.0.0.1:8080/res.jx/demo/test")).entity(dataObj).build();
	}
  
	@GET
	@Path("de")
	public Response de() {
		throw new WebApplicationException(500);
	}
}
