package at.ac.tuwien.infosys.java2wadl.testinheritance;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/sub")
public class SubResource extends SuperResource {

	private TestObj obj = null;

	@POST
	public void setObj(TestObj obj) {
		this.obj = obj;
	}

}
