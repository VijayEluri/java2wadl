package at.ac.tuwien.infosys.wadl2java.xml;

import org.w3c.dom.Node;

import at.ac.tuwien.infosys.java2wadl.WadlException;
import at.ac.tuwien.infosys.java2wadl.util.XmlUtil;
import at.ac.tuwien.infosys.java2wadl.wadl.IResponse;
import at.ac.tuwien.infosys.java2wadl.wadl.Response;

public class ResponseParser {
	public IResponse parse(Node node) throws WadlException {
		IResponse response = new Response();

		for (Node representationNode : XmlUtil.getChildNodes(node, "representation")) {
			response.addRepresentation(new RepresentationParser().parse(representationNode));
		}

		for (Node faultNode : XmlUtil.getChildNodes(node, "fault")) {
			response.addFault(new FaultParser().parse(faultNode));
		}

		for (Node paramNode : XmlUtil.getChildNodes(node, "param")) {
			response.addParam(new ParamParser().parse(paramNode));
		}

		return response;
	}
}
