package at.ac.tuwien.infosys.wadl2java.xml;

import org.w3c.dom.Node;

import at.ac.tuwien.infosys.java2wadl.WadlException;
import at.ac.tuwien.infosys.java2wadl.util.CollectionUtil;
import at.ac.tuwien.infosys.java2wadl.util.F;
import at.ac.tuwien.infosys.java2wadl.util.XmlUtil;
import at.ac.tuwien.infosys.java2wadl.wadl.IRequest;
import at.ac.tuwien.infosys.java2wadl.wadl.Request;

public class RequestParser {
	public IRequest parse(Node node) throws WadlException {
		final IRequest request = new Request();

		parseRepresentations(node, request);
		parseParams(node, request);
		return request;
	}

	private void parseParams(Node node, final IRequest request) throws WadlException {
		CollectionUtil.iterate(XmlUtil.getChildNodes(node, "param"), new F<Node>() {
			@Override
			public void apply(Node paramNode) throws WadlException {
				request.addParam(new ParamParser().parse(paramNode));
			}
		});
	}

	private void parseRepresentations(Node node, final IRequest request) throws WadlException {
		CollectionUtil.iterate(XmlUtil.getChildNodes(node, "representation"), new F<Node>() {
			@Override
			public void apply(Node representationNode) throws WadlException {
				request.addRepresentation(new RepresentationParser().parse(representationNode));
			}
		});
	}
}
