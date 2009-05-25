package at.ac.tuwien.infosys.wadl2java.xml;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Node;

import at.ac.tuwien.infosys.java2wadl.WadlException;
import at.ac.tuwien.infosys.java2wadl.util.StringUtil;
import at.ac.tuwien.infosys.java2wadl.util.UriUtil;
import at.ac.tuwien.infosys.java2wadl.util.XmlUtil;
import at.ac.tuwien.infosys.java2wadl.wadl.IResource;
import at.ac.tuwien.infosys.java2wadl.wadl.Resource;

public class ResourceParser {
	public IResource parse(Node node) throws WadlException {
		IResource resource = new Resource();

		resource.setPath(XmlUtil.getNodeAttribute(node, "path"));
		resource.setId(UriUtil.createUri(XmlUtil.getNodeAttribute(node, "id")));
		resource.setType(parseTypes(node));
		resource.setQueryType(XmlUtil.getNodeAttribute(node, "queryType"));

		for (Node paramNode : XmlUtil.getChildNodes(node, "param")) {
			resource.addParam(new ParamParser().parse(paramNode));
		}

		for (Node methodNode : XmlUtil.getChildNodes(node, "method")) {
			resource.addMethod(new MethodParser().parse(methodNode));
		}

		for (Node resourceNode : XmlUtil.getChildNodes(node, "resource")) {
			resource.addResource(new ResourceParser().parse(resourceNode));
		}

		return resource;
	}

	private List<URI> parseTypes(Node node) throws WadlException {
		List<URI> uriList = new ArrayList<URI>();

		for (String status : StringUtil.split(XmlUtil.getNodeAttribute(node, "types"), " ")) {
			uriList.add(UriUtil.createUri(status));
		}

		return uriList;
	}
}
