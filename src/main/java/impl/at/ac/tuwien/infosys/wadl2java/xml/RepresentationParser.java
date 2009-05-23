package at.ac.tuwien.infosys.wadl2java.xml;

import org.w3c.dom.Node;

import at.ac.tuwien.infosys.java2wadl.WadlException;
import at.ac.tuwien.infosys.java2wadl.util.XmlUtil;
import at.ac.tuwien.infosys.java2wadl.wadl.IRepresentation;

public class RepresentationParser {
	public IRepresentation parse(Node node) throws WadlException {
		if (XmlUtil.hasNodeAttribute(node, "href")) {
			return new RepresentationReferenceParser().parse(node);
		} else {
			return new RepresentationDefinitionParser().parse(node);
		}
	}
}
