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

public class MethodReferenceParserTest extends BaseTest {
	@Test
	public void testParseMethodReference() throws FileNotFoundException, WadlException {
		String xml = loadResource("at.ac.tuwien.infosys.wadl2java.xml.MethodReferenceTestCase.txt");

		List<Node> nodes = XmlUtil.toList(XPathUtil.query(xml, "/method[1]"));

		assertEquals(1, nodes.size());
		assertEquals(UriUtil.createUri("uri"), new MethodReferenceParser().parse(nodes.get(0)).getHref());
	}
}
