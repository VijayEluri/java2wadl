package at.ac.tuwien.infosys.wadl2java.xml;

import java.io.FileNotFoundException;

import org.junit.Test;
import org.w3c.dom.Node;

import at.ac.tuwien.infosys.java2wadl.WadlException;
import at.ac.tuwien.infosys.java2wadl.util.XPathUtil;
import at.ac.tuwien.infosys.java2wadl.util.XmlUtil;
import at.ac.tuwien.infosys.java2wadl.wadl.MethodDefinition;
import at.ac.tuwien.infosys.java2wadl.wadl.MethodReference;
import at.ac.tuwien.infosys.wadl2java.Wadl2JavaBaseTest;

public class MethodParserTest extends Wadl2JavaBaseTest {

	@Test
	public void testMethodParser() throws FileNotFoundException, WadlException {
		assertMethodClass(loadResource("at.ac.tuwien.infosys.wadl2java.xml.MethodDefinitionTestCase.txt"),
				MethodDefinition.class);
		assertMethodClass(loadResource("at.ac.tuwien.infosys.wadl2java.xml.MethodReferenceTestCase.txt"),
				MethodReference.class);
	}

	private void assertMethodClass(String xml, Class<?> klass) throws WadlException {
		for (Node n : XmlUtil.toList(XPathUtil.query(xml, "/method[1]"))) {
			assertTrue(new MethodParser().parse(n).getClass().equals(klass));
		}
	}
}
