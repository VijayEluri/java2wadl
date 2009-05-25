package at.ac.tuwien.infosys.wadl2java.xml;

import java.io.FileNotFoundException;

import org.junit.Test;

import at.ac.tuwien.infosys.BaseTest;
import at.ac.tuwien.infosys.java2wadl.Consts;
import at.ac.tuwien.infosys.java2wadl.WadlException;

public class ApplicationParserTestCase extends BaseTest {

	@Test
	public void testResponse() throws FileNotFoundException, WadlException {
		assertParse("at.ac.tuwien.infosys.java2wadl.testresponse.TestResponseResource.txt");
	}

	@Test
	public void testRequest() throws FileNotFoundException, WadlException {
		assertParse("at.ac.tuwien.infosys.java2wadl.testrequest.TestRequestResource.txt");
	}

	@Test
	public void testSubResource() throws FileNotFoundException, WadlException {
		assertParse("at.ac.tuwien.infosys.java2wadl.testinheritance.SubResource.txt");
	}

	@Test
	public void testCustomerService() throws FileNotFoundException, WadlException {
		assertParse("at.ac.tuwien.infosys.java2wadl.customerservice.CustomerService.txt");
	}

	@Test
	public void testHellWorld() throws FileNotFoundException, WadlException {
		assertParse("at.ac.tuwien.infosys.java2wadl.helloworld.HelloWorldResource.txt");
	}

	@Test
	public void testItems() throws FileNotFoundException, WadlException {
		assertParse("at.ac.tuwien.infosys.java2wadl.items.ItemsResource.txt");
	}

	private void assertParse(String fileName) throws FileNotFoundException, WadlException {
		String xml = loadResource(fileName);

		assertXmlEquals(xml, Consts.xml_header + new ApplicationParser().parse(xml).toString());
	}
}
