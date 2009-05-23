package at.ac.tuwien.infosys.wadl2java.xml;

import org.w3c.dom.Node;

import at.ac.tuwien.infosys.java2wadl.WadlException;
import at.ac.tuwien.infosys.java2wadl.util.UriUtil;
import at.ac.tuwien.infosys.java2wadl.util.WadlUtil;
import at.ac.tuwien.infosys.java2wadl.util.XmlUtil;
import at.ac.tuwien.infosys.java2wadl.wadl.IMethodDefinition;
import at.ac.tuwien.infosys.java2wadl.wadl.MethodDefinition;

public class MethodDefinitionParser {

	public IMethodDefinition parse(Node node) throws WadlException {
		IMethodDefinition methodDefinition = new MethodDefinition();

		methodDefinition.setId(UriUtil.createUri(XmlUtil.getNodeAttribute(node, "id")));
		methodDefinition.setName(WadlUtil.toHttpMethod(XmlUtil.getNodeAttribute(node, "name")));
		setRequest(node, methodDefinition);
		setResponse(node, methodDefinition);
		return methodDefinition;
	}

	private void setResponse(Node node, IMethodDefinition method) throws WadlException {
		for (Node responseNode : XmlUtil.getChildNodes(node, "response")) {
			method.setResponse(new ResponseParser().parse(responseNode));
		}
	}

	private void setRequest(Node node, IMethodDefinition method) throws WadlException {
		for (Node requestNode : XmlUtil.getChildNodes(node, "request")) {
			method.setRequest(new RequestParser().parse(requestNode));
		}
	}
}
