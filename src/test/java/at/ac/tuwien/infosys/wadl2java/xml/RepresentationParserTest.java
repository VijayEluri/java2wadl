package at.ac.tuwien.infosys.wadl2java.xml;

import java.io.FileNotFoundException;

import org.junit.Test;
import org.w3c.dom.Node;

import at.ac.tuwien.infosys.BaseTest;
import at.ac.tuwien.infosys.java2wadl.WadlException;
import at.ac.tuwien.infosys.java2wadl.util.XPathUtil;
import at.ac.tuwien.infosys.java2wadl.util.XmlUtil;
import at.ac.tuwien.infosys.java2wadl.wadl.RepresentationDefinition;
import at.ac.tuwien.infosys.java2wadl.wadl.RepresentationReference;

public class RepresentationParserTest extends BaseTest {
	@Test
	public void testFaultParser() throws FileNotFoundException, WadlException {
		assertFaultClass(loadResource("at.ac.tuwien.infosys.wadl2java.xml.RepresentationDefinitionTestCase.txt"),
				RepresentationDefinition.class);
		assertFaultClass(loadResource("at.ac.tuwien.infosys.wadl2java.xml.RepresentationReferenceTestCase.txt"),
				RepresentationReference.class);
	}

	private void assertFaultClass(String xml, Class<?> klass) throws WadlException {
		for (Node n : XmlUtil.toList(XPathUtil.query(xml, "/representation[1]"))) {
			assertTrue(new RepresentationParser().parse(n).getClass().equals(klass));
		}
	}
}
