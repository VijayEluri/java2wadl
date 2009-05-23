package at.ac.tuwien.infosys.wadl2java.xml;

import java.io.FileNotFoundException;
import java.util.List;

import org.junit.Test;
import org.w3c.dom.Node;

import at.ac.tuwien.infosys.java2wadl.WadlException;
import at.ac.tuwien.infosys.java2wadl.util.UriUtil;
import at.ac.tuwien.infosys.java2wadl.util.XPathUtil;
import at.ac.tuwien.infosys.java2wadl.util.XmlUtil;
import at.ac.tuwien.infosys.java2wadl.wadl.IParam;
import at.ac.tuwien.infosys.java2wadl.wadl.ParamStyle;
import at.ac.tuwien.infosys.wadl2java.Wadl2JavaBaseTest;

public class ParamParserTest extends Wadl2JavaBaseTest {

	public static void assertEqualsParamsFixture(IParam param) throws WadlException {
		assertEquals("name", param.getName());
		assertEquals(UriUtil.createUri("id"), param.getId());
		assertEquals(ParamStyle.plain, param.getStyle());
		assertEquals("type", param.getType());
		assertEquals("default", param.getDefault());
		assertEquals("path", param.getPath());
		assertEquals(new Boolean(false), param.getRequired());
		assertEquals(new Boolean(false), param.getRepeating());
		assertEquals("fixed", param.getFixed());
	}
	
	@Test
	public void testParamAttributes() throws FileNotFoundException, WadlException {
		String xml = loadResource("at.ac.tuwien.infosys.wadl2java.xml.ParamParserTestCase.txt");
		List<Node> resultNodes = XmlUtil.toList(XPathUtil.query(xml, "//param[1]"));

		assertEquals(1, resultNodes.size());
		for (Node node : resultNodes) {
			assertEqualsParamsFixture(new ParamParser().parse(node));
		}
	}
}
