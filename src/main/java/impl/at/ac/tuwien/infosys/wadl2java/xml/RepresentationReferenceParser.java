package at.ac.tuwien.infosys.wadl2java.xml;

import org.w3c.dom.Node;

import at.ac.tuwien.infosys.java2wadl.WadlException;
import at.ac.tuwien.infosys.java2wadl.util.XmlUtil;
import at.ac.tuwien.infosys.java2wadl.wadl.IRepresentationReference;
import at.ac.tuwien.infosys.java2wadl.wadl.RepresentationReference;

public class RepresentationReferenceParser {
	public IRepresentationReference parse(Node node) throws WadlException {
		return new RepresentationReference(XmlUtil.getNodeAttribute(node, "href"));
	}
}
