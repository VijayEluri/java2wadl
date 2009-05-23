package at.ac.tuwien.infosys.wadl2java.xml;

import java.io.FileNotFoundException;

import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.ElementNameAndAttributeQualifier;
import org.junit.Test;

import at.ac.tuwien.infosys.java2wadl.Consts;
import at.ac.tuwien.infosys.java2wadl.WadlException;
import at.ac.tuwien.infosys.java2wadl.util.XmlUtil;
import at.ac.tuwien.infosys.java2wadl.wadl.IApplication;
import at.ac.tuwien.infosys.wadl2java.Wadl2JavaBaseTest;

public class ApplicationParserTestCase extends Wadl2JavaBaseTest {

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

		IApplicationParser applicationParser = new ApplicationParser();
		IApplication application = applicationParser.parse(xml);
		Diff diff;
		try {
			diff = new Diff(xml, XmlUtil.tidyXml(Consts.xml_header + application.toString()));
		} catch (Exception e) {
			throw new WadlException(e);
		}
		diff.overrideElementQualifier(new ElementNameAndAttributeQualifier());
		assertXMLEqual(diff, true);
	}
}
