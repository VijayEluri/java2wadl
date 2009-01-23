package at.ac.tuwien.infosys.java2wadl.customerservice;

import java.util.HashMap;
import java.util.Map;

public class Customers {

	public Map<String, Customer> customerMap;

	public Customers() {
		customerMap = new HashMap<String, Customer>();
	}

	public Customer get(String id) {
		return customerMap.get(id);
	}

	public void update(String id, Customer customer) {
		customerMap.put(id, customer);
	}

	public void add(Customer customer) {
		customerMap.put(((Integer) customerMap.size()).toString(), customer);
	}

	public void delete(String id) {
		customerMap.remove(id);
	}

}
