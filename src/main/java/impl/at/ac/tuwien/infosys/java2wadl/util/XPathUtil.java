package at.ac.tuwien.infosys.java2wadl.util;

import java.io.StringReader;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import at.ac.tuwien.infosys.java2wadl.WadlException;
import at.ac.tuwien.infosys.java2wadl.external.NamespaceContextHelper;

public class XPathUtil {
	public static NodeList query(String xml, String query) throws WadlException {
		try {
			return (NodeList) xpathExpression(query).evaluate(new InputSource(new StringReader(xml)),
					XPathConstants.NODESET);
		} catch (XPathExpressionException e) {
			throw new WadlException(e);
		}
	}

	private static XPathExpression xpathExpression(String query) throws XPathExpressionException {
		return xpath().compile(query);
	}

	private static XPath xpath() {
		XPath xpath = XPathFactory.newInstance().newXPath();
		
		xpath.setNamespaceContext(new NamespaceContextHelper("wadl", "http://research.sun.com/wadl/2006/10"));
		return xpath;
	}
}
