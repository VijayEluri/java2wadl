package at.ac.tuwien.infosys.java2wadl.customerservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

public class Order {
	private Long id;

	public Order() {}

	public Long getId() {
		return id;
	}

	@GET
	@Path("products/{productId}/")
	public Product getProduct(@PathParam("productId") Long productId) {
		return null;
	}
}
