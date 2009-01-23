package at.ac.tuwien.infosys.java2wadl.helloworld;

import javax.ws.rs.Path;
import javax.ws.rs.GET;

@Path("/helloworld")
public class HelloWorldResource {

	@GET
	public String getMessage() {
		return "Hello World!";
	}

}
