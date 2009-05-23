package at.ac.tuwien.infosys.wadl2java.xml;

import org.w3c.dom.Node;

import at.ac.tuwien.infosys.java2wadl.WadlException;
import at.ac.tuwien.infosys.java2wadl.util.XmlUtil;
import at.ac.tuwien.infosys.java2wadl.wadl.IMethodReference;
import at.ac.tuwien.infosys.java2wadl.wadl.MethodReference;

public class MethodReferenceParser {

	public IMethodReference parse(Node node) throws WadlException {
		return new MethodReference(XmlUtil.getNodeAttribute(node, "href"));
	}

}
