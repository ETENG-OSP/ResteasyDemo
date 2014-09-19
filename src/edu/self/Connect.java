package edu.self;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path(value = "/connect")
public class Connect {
	
	@GET
	@Path(value = "/test")
	public String test(String json) {
		EventAPI api = new MongoAPI();
		return api.doGet(json).toString();
	}

}
