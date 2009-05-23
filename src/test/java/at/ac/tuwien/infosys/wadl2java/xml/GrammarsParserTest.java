package at.ac.tuwien.infosys.wadl2java.xml;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.w3c.dom.Node;

import at.ac.tuwien.infosys.java2wadl.WadlException;
import at.ac.tuwien.infosys.java2wadl.util.XPathUtil;
import at.ac.tuwien.infosys.java2wadl.util.XmlUtil;
import at.ac.tuwien.infosys.java2wadl.wadl.IInclude;
import at.ac.tuwien.infosys.wadl2java.Wadl2JavaBaseTest;

public class GrammarsParserTest extends Wadl2JavaBaseTest {
	
	@Test
	public void testPresentInclude() throws FileNotFoundException, WadlException {
		assertIncludesPresent(loadResource("at.ac.tuwien.infosys.java2wadl.items.ItemsResource.txt"),
				"at.ac.tuwien.infosys.java2wadl.items.Item.xsd");
	}

	@Test
	public void testEmptyGrammars() throws FileNotFoundException, WadlException {
		assertIncludesPresent(loadResource("at.ac.tuwien.infosys.java2wadl.helloworld.HelloWorldResource.txt"));
	}

	private void assertIncludesPresent(String xml, String... includeNames) throws WadlException {
		List<Node> grammarNodes = XmlUtil.toList(XPathUtil.query(xml, "/wadl:application/wadl:grammars"));
		
		assertEquals(1, grammarNodes.size());
		for (Node grammarNode : grammarNodes) {
			for (String includeName : includeNames) {
				assertContainsInclude(new GrammarsParser().parse(grammarNode).getIncludes(), includeName);
			}
		}
	}

	private void assertContainsInclude(List<IInclude> includes, String includeName) {
		List<String> includeNames = new ArrayList<String>();

		for (IInclude include : includes) {
			includeNames.add(include.getHref().toString());
		}

		assertTrue(includeNames.contains(includeName));
	}
}
