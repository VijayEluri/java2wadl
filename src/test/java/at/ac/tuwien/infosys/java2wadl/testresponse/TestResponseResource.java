package at.ac.tuwien.infosys.java2wadl.testresponse;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

@Path("/testresponse")
public class TestResponseResource {
	private Element element = new Element();

	// response is void
	@DELETE
	public void deleteElement() throws WebApplicationException {
		element = null;
	}

	// response is primitive Java-Type
	@GET
	public String getElementName() {
		if (element != null) {
			return element.name;
		}

		return "";
	}

	// response is jaxRS-Response
	@POST
	@Consumes("application/xml")
	public Response updateElement(Element el) {
		element = el;
		return Response.ok().build();
	}

	// response is Java-object
	@POST
	@Consumes("application/xml")
	@Produces("application/xml")
	public Element postElement(Element el) {
		element = el;
		return element;
	}

}
