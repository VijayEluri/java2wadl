package at.ac.tuwien.infosys.wadl2java.xml;

import java.io.FileNotFoundException;
import java.util.List;

import org.junit.Test;
import org.w3c.dom.Node;

import at.ac.tuwien.infosys.BaseTest;
import at.ac.tuwien.infosys.java2wadl.WadlException;
import at.ac.tuwien.infosys.java2wadl.util.UriUtil;
import at.ac.tuwien.infosys.java2wadl.util.XPathUtil;
import at.ac.tuwien.infosys.java2wadl.util.XmlUtil;
import at.ac.tuwien.infosys.java2wadl.wadl.IRepresentationReference;

public class RepresentationReferenceParserTest extends BaseTest {

	@Test
	public static void assertEqualsRepresentationReferenceFixture(IRepresentationReference representationReference)
			throws WadlException {
		assertEquals(UriUtil.createUri("href"), representationReference.getHref());
	}

	@Test
	public void testParseRepresentationReference() throws FileNotFoundException, WadlException {
		String xml = loadResource("at.ac.tuwien.infosys.wadl2java.xml.RepresentationReferenceTestCase.txt");
		List<Node> resultNodes = XmlUtil.toList(XPathUtil.query(xml, "//*[1]"));

		assertEquals(1, resultNodes.size());
		assertEqualsRepresentationReferenceFixture(new RepresentationReferenceParser().parse(resultNodes.get(0)));
	}
}
