package at.ac.tuwien.infosys.wadl2java.xml;

import org.w3c.dom.Node;

import at.ac.tuwien.infosys.java2wadl.WadlException;
import at.ac.tuwien.infosys.java2wadl.util.XmlUtil;
import at.ac.tuwien.infosys.java2wadl.wadl.IResourceType;
import at.ac.tuwien.infosys.java2wadl.wadl.ResourceType;

public class ResourceTypeParser {
	public IResourceType parse(Node node) throws WadlException {
		IResourceType resourceType = new ResourceType();
		
		addParams(node, resourceType);
		addMethods(node, resourceType);
		
		return resourceType;
	}

	private void addMethods(Node node, IResourceType resourceType) throws WadlException {
		for (Node methodNode : XmlUtil.getChildNodes(node, "method")) {
			resourceType.addMethod(new MethodParser().parse(methodNode));
		}
	}

	private void addParams(Node node, IResourceType resourceType) throws WadlException {
		for (Node paramNode : XmlUtil.getChildNodes(node, "param")) {
			resourceType.addParam(new ParamParser().parse(paramNode));
		}
	}
}
