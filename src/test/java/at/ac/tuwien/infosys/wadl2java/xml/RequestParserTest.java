package at.ac.tuwien.infosys.wadl2java.xml;

import java.io.FileNotFoundException;
import java.util.List;

import org.junit.Test;
import org.w3c.dom.Node;

import at.ac.tuwien.infosys.BaseTest;
import at.ac.tuwien.infosys.java2wadl.WadlException;
import at.ac.tuwien.infosys.java2wadl.util.XPathUtil;
import at.ac.tuwien.infosys.java2wadl.util.XmlUtil;
import at.ac.tuwien.infosys.java2wadl.wadl.IParam;
import at.ac.tuwien.infosys.java2wadl.wadl.IRepresentation;
import at.ac.tuwien.infosys.java2wadl.wadl.IRepresentationDefinition;
import at.ac.tuwien.infosys.java2wadl.wadl.IRequest;

public class RequestParserTest extends BaseTest {

	@Test
	public void testParseRepresentationRequest() throws FileNotFoundException, WadlException {
		String xml = loadResource("at.ac.tuwien.infosys.wadl2java.xml.RequestTestCase.txt");
		List<Node> nodes = XmlUtil.toList(XPathUtil.query(xml, "//request[1]"));

		assertEquals(1, nodes.size());
		IRequest request = new RequestParser().parse(nodes.get(0));

		for (IRepresentation representation : request.getRepresentations()) {
			IRepresentationDefinition repDef = (IRepresentationDefinition) representation;
			RepresentationDefinitionParserTest.assertValidRepresentationDefinition(repDef);
		}
	}

	@Test
	public void testParseParamRequest() throws FileNotFoundException, WadlException {
		String xml = loadResource("at.ac.tuwien.infosys.wadl2java.xml.RequestTestCase.txt");
		List<Node> nodes = XmlUtil.toList(XPathUtil.query(xml, "//request[2]"));

		assertEquals(1, nodes.size());
		IRequest request = new RequestParser().parse(nodes.get(0));

		for (IParam param : request.getParams()) {
			ParamParserTest.assertEqualsParamsFixture(param);
		}
	}
}
