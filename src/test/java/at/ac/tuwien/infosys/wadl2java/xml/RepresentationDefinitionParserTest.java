package at.ac.tuwien.infosys.wadl2java.xml;

import java.io.FileNotFoundException;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.w3c.dom.Node;

import at.ac.tuwien.infosys.java2wadl.WadlException;
import at.ac.tuwien.infosys.java2wadl.util.UriUtil;
import at.ac.tuwien.infosys.java2wadl.util.XPathUtil;
import at.ac.tuwien.infosys.java2wadl.util.XmlUtil;
import at.ac.tuwien.infosys.java2wadl.wadl.IParam;
import at.ac.tuwien.infosys.java2wadl.wadl.IRepresentationDefinition;
import at.ac.tuwien.infosys.wadl2java.Wadl2JavaBaseTest;

public class RepresentationDefinitionParserTest extends Wadl2JavaBaseTest {

	public static void assertValidRepresentationDefinition(IRepresentationDefinition representationDefinition) throws WadlException {
		assertEquals(UriUtil.createUri("id"), representationDefinition.getId());
		assertEquals("text", representationDefinition.getMediaType());
		assertEquals("element", representationDefinition.getElement());
		assertEquals(Arrays.asList(new URI[] { UriUtil.createUri("profile") }), representationDefinition.getProfiles());
		assertEquals(Arrays.asList(new Integer[] { 0 }), representationDefinition.getStatus());

		for (IParam param : representationDefinition.getParams()) {
			ParamParserTest.assertEqualsParamsFixture(param);
		}
	}
	
	@Test
	public void testParseRepresentationDefinition() throws FileNotFoundException, WadlException {
		String xml = loadResource("at.ac.tuwien.infosys.wadl2java.xml.RepresentationDefinitionTestCase.txt");
		List<Node> resultNodes = XmlUtil.toList(XPathUtil.query(xml, "//representation[1]"));

		assertEquals(1, resultNodes.size());
		for (Node resultNode : resultNodes) {
			assertValidRepresentationDefinition(new RepresentationDefinitionParser().parse(resultNode));
		}
	}
}
