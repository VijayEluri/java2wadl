package at.ac.tuwien.infosys.java2wadl.items;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;

@Path("/items")
public class ItemsResource {
	Map<String, Item> items;

	@GET
	@Path("/ids/{itemsid}")
	@Produces( { "application/json", "application/xml" })
	public Item getItem(@PathParam("itemsid") String itemId) {
		return getItems().get(itemId);
	}

	@PUT
	@Path("/ids/{itemsid}")
	@Consumes( { "application/json", "application/xml" })
	public void putItem(@PathParam("itemsid") String itemId, Item item) {
		getItems().put(itemId, item);
	}

	@DELETE
	@Path("/ids/{itemsid}")
	public void deleteItem(@PathParam("itemsid") String itemId) {
		getItems().remove(itemId);
	}

	public Map<String, Item> getItems() {
		if (null == items) {
			items = new HashMap<String, Item>();
			items.put("I01", new Item("I01", "This is item #1."));
			items.put("I02", new Item("I02", "This is item #2."));
			items.put("I03", new Item("I03", "This is item #3."));
			items.put("I04", new Item("I04", "This is item #4."));
			items.put("I05", new Item("I05", "This is item #5."));
		}
		return items;
	}

}
