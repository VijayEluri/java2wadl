package at.ac.tuwien.infosys.java2wadl.testrequest;

import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

@Path("bla")
public class TestRequestResource {

	@GET
	@Path("{version}/{entry}")
	public String getBla(@PathParam("entry") String entryId, @PathParam("version") int version) {
		return "bla";
	}
	
	@POST
	public void doBlaMatrix(@MatrixParam("bla") String bla) {
	// do something...
	}

	@POST
	public void doBlaQuery(@QueryParam("format") String format) {
	// do something...
	}

	@POST
	public void doBlaDef(@DefaultValue("bla") String name) {
	// do something...
	}

	@DELETE
	public void delete(@DefaultValue("true") @QueryParam("all") boolean delAll) {
	// do something...
	}

}
