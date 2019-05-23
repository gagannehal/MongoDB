package com.rest.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/lib")
public class LibraryServiceImpl implements LibraryService {

	@GET
	@Path("/{param}")
	public Response getMsg(@PathParam("param") String msg) {
		String output = "Jersey say : " + msg;
		return Response.status(200).entity(output).build();
	}

	@GET
	@Path("/echo/{message}")
	@Produces("text/plain")
	public String showMsg(@PathParam("message") String message) {
		System.out.println("Inside showMsg");
		return message;
	}

}
