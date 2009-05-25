package at.ac.tuwien.infosys.java2wadl;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;
import org.xml.sax.SAXException;

import at.ac.tuwien.infosys.BaseTest;
import at.ac.tuwien.infosys.java2wadl.customerservice.CustomerService;
import at.ac.tuwien.infosys.java2wadl.helloworld.HelloWorldResource;
import at.ac.tuwien.infosys.java2wadl.items.ItemsResource;
import at.ac.tuwien.infosys.java2wadl.testinheritance.SubResource;
import at.ac.tuwien.infosys.java2wadl.testrequest.TestRequestResource;
import at.ac.tuwien.infosys.java2wadl.testresponse.TestResponseResource;

/**
 * @author <a href="mailto:e0426062@student.tuwien.ac.at">Andreas Meingast</a>
 * @author <a href="mailto:e0325672@student.tuwien.ac.at">Anton Korosec</a>
 */
public class Java2WadlTest extends BaseTest {

	@Test
	public void testCustomerService() throws FileNotFoundException, WadlException, SAXException, IOException {
		testClass(CustomerService.class);
	}

	@Test
	public void testHelloWorldResource() throws FileNotFoundException, WadlException, SAXException, IOException {
		testClass(HelloWorldResource.class);
	}

	@Test
	public void testRequestResource() throws FileNotFoundException, WadlException, SAXException, IOException {
		testClass(TestRequestResource.class);
	}

	@Test
	public void testResponseResource() throws FileNotFoundException, WadlException, SAXException, IOException {
		testClass(TestResponseResource.class);
	}

	@Test
	public void testItemsResource() throws FileNotFoundException, WadlException, SAXException, IOException {
		testClass(ItemsResource.class);
	}

	@Test
	public void testSubResource() throws FileNotFoundException, WadlException, SAXException, IOException {
		testClass(SubResource.class);
	}

	private void testClass(Class<?> testClass) throws WadlException, FileNotFoundException, SAXException, IOException {
		assertXmlEquals(loadResource(testClass.getCanonicalName() + ".txt"), java2Wadl.generate(testClass, "/base")
				.getWadlContent());
	}

	@Test
	public void testThrowsWithNullClass() throws WadlException {
		try {
			java2Wadl.generate((Class<?>) null, "/baseUri");
			fail();
		} catch (NullPointerException e) {}
	}
}
