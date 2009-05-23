package at.ac.tuwien.infosys.wadl2java.xml;

import java.io.FileNotFoundException;

import org.junit.Test;
import org.w3c.dom.Node;

import at.ac.tuwien.infosys.java2wadl.WadlException;
import at.ac.tuwien.infosys.java2wadl.util.XPathUtil;
import at.ac.tuwien.infosys.java2wadl.util.XmlUtil;
import at.ac.tuwien.infosys.java2wadl.wadl.FaultDefinition;
import at.ac.tuwien.infosys.java2wadl.wadl.FaultReference;
import at.ac.tuwien.infosys.wadl2java.Wadl2JavaBaseTest;

public class FaultParserTest extends Wadl2JavaBaseTest {
	@Test
	public void testFaultParser() throws FileNotFoundException, WadlException {
		assertFaultClass(loadResource("at.ac.tuwien.infosys.wadl2java.xml.FaultDefinitionTestCase.txt"),
				FaultDefinition.class);
		assertFaultClass(loadResource("at.ac.tuwien.infosys.wadl2java.xml.FaultReferenceTestCase.txt"),
				FaultReference.class);
	}

	private void assertFaultClass(String xml, Class<?> klass) throws WadlException {
		for (Node n : XmlUtil.toList(XPathUtil.query(xml, "/fault[1]"))) {
			assertTrue(new FaultParser().parse(n).getClass().equals(klass));
		}
	}
}
