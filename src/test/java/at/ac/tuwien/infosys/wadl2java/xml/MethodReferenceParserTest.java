package at.ac.tuwien.infosys.wadl2java.xml;

import java.io.FileNotFoundException;
import java.util.List;

import org.junit.Test;
import org.w3c.dom.Node;

import at.ac.tuwien.infosys.java2wadl.WadlException;
import at.ac.tuwien.infosys.java2wadl.util.UriUtil;
import at.ac.tuwien.infosys.java2wadl.util.XPathUtil;
import at.ac.tuwien.infosys.java2wadl.util.XmlUtil;
import at.ac.tuwien.infosys.wadl2java.Wadl2JavaBaseTest;

public class MethodReferenceParserTest extends Wadl2JavaBaseTest {
	@Test
	public void testParseMethodReference() throws FileNotFoundException, WadlException {
		String xml = loadResource("at.ac.tuwien.infosys.wadl2java.xml.MethodReferenceTestCase.txt");
		
		List<Node> nodes = XmlUtil.toList(XPathUtil.query(xml, "/method[1]"));
		
		assertEquals(1, nodes.size());
		for (Node n : nodes) {
			assertEquals(UriUtil.createUri("uri"), new MethodReferenceParser().parse(n).getHref());
		}
	}
}
