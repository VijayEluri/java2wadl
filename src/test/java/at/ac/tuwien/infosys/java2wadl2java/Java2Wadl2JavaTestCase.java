package at.ac.tuwien.infosys.java2wadl2java;

import org.junit.Test;

import at.ac.tuwien.infosys.BaseTest;
import at.ac.tuwien.infosys.java2wadl.WadlException;
import at.ac.tuwien.infosys.java2wadl.customerservice.CustomerService;
import at.ac.tuwien.infosys.java2wadl.helloworld.HelloWorldResource;
import at.ac.tuwien.infosys.java2wadl.items.ItemsResource;
import at.ac.tuwien.infosys.java2wadl.testinheritance.SubResource;
import at.ac.tuwien.infosys.java2wadl.testrequest.TestRequestResource;
import at.ac.tuwien.infosys.java2wadl.testresponse.TestResponseResource;

public class Java2Wadl2JavaTestCase extends BaseTest {

	@Test
	public void testCustomerService() {
		testClass(CustomerService.class);
	}

	@Test
	public void testHelloWorldResource() {
		testClass(HelloWorldResource.class);
	}

	@Test
	public void testRequestResource() {
		testClass(TestRequestResource.class);
	}

	@Test
	public void testResponseResource() {
		testClass(TestResponseResource.class);
	}

	@Test
	public void testItemsResource() {
		testClass(ItemsResource.class);
	}

	@Test
	public void testSubResource() {
		testClass(SubResource.class);
	}

	private void testClass(Class<?> klass) {
		try {
			wadl2Java.generate(java2Wadl.generate(klass, "/base").getWadlContent(), "test");
		} catch (WadlException e) {
			throw new RuntimeException(e);
		}
	}
}
