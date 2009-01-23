package at.ac.tuwien.infosys.java2wadl.testinheritance;

import javax.ws.rs.GET;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

@Consumes("application/xml")
@Produces("application/xml")
public class SuperResource {

	@GET
	public TestObj getObj() {
		return new TestObj();
	}

}
