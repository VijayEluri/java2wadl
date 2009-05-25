package at.ac.tuwien.infosys.wadl2java.xml;

import java.io.FileNotFoundException;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.w3c.dom.Node;

import at.ac.tuwien.infosys.BaseTest;
import at.ac.tuwien.infosys.java2wadl.WadlException;
import at.ac.tuwien.infosys.java2wadl.util.UriUtil;
import at.ac.tuwien.infosys.java2wadl.util.XPathUtil;
import at.ac.tuwien.infosys.java2wadl.util.XmlUtil;
import at.ac.tuwien.infosys.java2wadl.wadl.IFaultDefinition;
import at.ac.tuwien.infosys.java2wadl.wadl.IParam;

public class FaultDefinitionParserTest extends BaseTest {

	public static void assertEqualsFaultDefinitionFixture(IFaultDefinition faultDefinition) throws WadlException {
		assertEquals(UriUtil.createUri("id"), faultDefinition.getId());
		assertEquals("text", faultDefinition.getMediaType());
		assertEquals("element", faultDefinition.getElement());
		assertEquals(Arrays.asList(new URI[] { UriUtil.createUri("profile") }), faultDefinition.getProfiles());
		assertEquals(Arrays.asList(new Integer[] { 0 }), faultDefinition.getStatus());

		for (IParam param : faultDefinition.getParams()) {
			ParamParserTest.assertEqualsParamsFixture(param);
		}
	}

	@Test
	public void testParseRepresentationDefinition() throws FileNotFoundException, WadlException {
		String xml = loadResource("at.ac.tuwien.infosys.wadl2java.xml.FaultDefinitionTestCase.txt");
		List<Node> resultNodes = XmlUtil.toList(XPathUtil.query(xml, "//fault[1]"));

		assertEquals(1, resultNodes.size());
		assertEqualsFaultDefinitionFixture(new FaultDefinitionParser().parse(resultNodes.get(0)));
	}
}
