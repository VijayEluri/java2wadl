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

public class ResourcesParserTest extends BaseTest {

	@Test
	public void testSetBaseUrl() throws FileNotFoundException, WadlException {
		String xml = loadResource("at.ac.tuwien.infosys.java2wadl.items.ItemsResource.txt");
		List<Node> resourceNodes = XmlUtil.toList(XPathUtil.query(xml, "/wadl:application/wadl:resources"));
		
		assertEquals(1, resourceNodes.size());
		for (Node resourcesNode : resourceNodes) {
			assertEquals(UriUtil.createUri("/base"), new ResourcesParser().parse(resourcesNode).getBase());
		}
	}
}
