package at.ac.tuwien.infosys.wadl2java.xml;

import org.w3c.dom.Node;

import at.ac.tuwien.infosys.java2wadl.WadlException;
import at.ac.tuwien.infosys.java2wadl.util.XmlUtil;
import at.ac.tuwien.infosys.java2wadl.wadl.FaultReference;
import at.ac.tuwien.infosys.java2wadl.wadl.IFaultReference;

public class FaultReferenceParser {
	public IFaultReference parse(Node node) throws WadlException {
		return new FaultReference(XmlUtil.getNodeAttribute(node, "href"));
	}
}
