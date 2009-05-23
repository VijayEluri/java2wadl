package at.ac.tuwien.infosys.java2wadl;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.ElementNameAndAttributeQualifier;
import org.custommonkey.xmlunit.XMLTestCase;
import org.junit.Test;
import org.xml.sax.SAXException;

import at.ac.tuwien.infosys.java2wadl.Java2Wadl;
import at.ac.tuwien.infosys.java2wadl.Wadl;
import at.ac.tuwien.infosys.java2wadl.WadlException;
import at.ac.tuwien.infosys.java2wadl.customerservice.CustomerService;
import at.ac.tuwien.infosys.java2wadl.helloworld.HelloWorldResource;
import at.ac.tuwien.infosys.java2wadl.items.ItemsResource;
import at.ac.tuwien.infosys.java2wadl.testinheritance.SubResource;
import at.ac.tuwien.infosys.java2wadl.testrequest.TestRequestResource;
import at.ac.tuwien.infosys.java2wadl.testresponse.TestResponseResource;
import at.ac.tuwien.infosys.java2wadl.util.ResourceUtil;

/**
 * @author <a href="mailto:e0426062@student.tuwien.ac.at">Andreas Meingast</a>
 * @author <a href="mailto:e0325672@student.tuwien.ac.at">Anton Korosec</a>
 */
public class Java2WadlTest extends XMLTestCase {

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
		Wadl actual = new Java2Wadl().generate(testClass, "/base");
		String shouldBe = ResourceUtil.loadResource(getClass().getClassLoader(), testClass.getCanonicalName() + ".txt");

		System.out.println(actual.getWadlContent());

		Diff diff = new Diff(shouldBe, actual.getWadlContent());
		diff.overrideElementQualifier(new ElementNameAndAttributeQualifier());
		assertXMLEqual(diff, true);
	}

	@Test
	public void testThrowsWithNullClass() throws WadlException {
		try {
			new Java2Wadl().generate((Class<?>) null, "/baseUri");
			fail();
		} catch (NullPointerException e) {}
	}
}
