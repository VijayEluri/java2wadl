package at.ac.tuwien.infosys.java2wadl.customerservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

@Path("/customerservice/")
public class CustomerService {

	private Customers customers = new Customers();

	public CustomerService() {}

	@GET
	public Customers getCustomers() {
		return customers;
	}

	@GET
	@Path("/customers/{id}")
	@Produces("application/xml")
	public Customer getCustomer(@PathParam("id") String id) {
		return customers.get(id);
	}

	@PUT
	@Path("/customers/{id}")
	@Consumes("application/xml")
	public Response updateCustomer(@PathParam("id") String id, Customer customer) {
		customers.update(id, customer);
		return Response.ok().build();
	}

	@POST
	@Path("/customers")
	public Response addCustomer(Customer customer) throws WebApplicationException {
		customers.add(customer);
		return Response.ok().build();
	}

	@DELETE
	@Path("/customers/{id}/")
	public Response deleteCustomer(@PathParam("id") String id) {
		customers.delete(id);
		return Response.ok().build();
	}

	@Path("/orders/{orderId}/")
	public Order getOrder(@PathParam("orderId") String orderId) {
		return null;
	}
}