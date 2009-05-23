package at.ac.tuwien.infosys.wadl2java.xml;

import org.w3c.dom.Node;

import at.ac.tuwien.infosys.java2wadl.WadlException;
import at.ac.tuwien.infosys.java2wadl.util.XmlUtil;
import at.ac.tuwien.infosys.java2wadl.wadl.IMethod;

public class MethodParser {
	public IMethod parse(Node node) throws WadlException {
		if (XmlUtil.hasNodeAttribute(node, "href")) {
			return new MethodReferenceParser().parse(node);
		} else {
			return new MethodDefinitionParser().parse(node);
		}
	}
}
