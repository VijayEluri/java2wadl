package at.ac.tuwien.infosys.wadl2java.xml;

import org.w3c.dom.Node;

import at.ac.tuwien.infosys.java2wadl.WadlException;
import at.ac.tuwien.infosys.java2wadl.util.XmlUtil;
import at.ac.tuwien.infosys.java2wadl.wadl.IResources;
import at.ac.tuwien.infosys.java2wadl.wadl.Resources;

public class ResourcesParser {
	public IResources parse(Node node) throws WadlException {
		IResources resources = new Resources(XmlUtil.getNodeAttribute(node, "base"));
		
		for (Node resourceNode : XmlUtil.getChildNodes(node, "resource")) {
			resources.addResource(new ResourceParser().parse(resourceNode));
		}
		
		return resources;
	}
}
