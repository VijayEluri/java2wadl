package at.ac.tuwien.infosys.wadl2java.xml;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Node;

import at.ac.tuwien.infosys.java2wadl.WadlException;
import at.ac.tuwien.infosys.java2wadl.util.StringUtil;
import at.ac.tuwien.infosys.java2wadl.util.UriUtil;
import at.ac.tuwien.infosys.java2wadl.util.XmlUtil;
import at.ac.tuwien.infosys.java2wadl.wadl.FaultDefinition;
import at.ac.tuwien.infosys.java2wadl.wadl.IFaultDefinition;

public class FaultDefinitionParser {

	public IFaultDefinition parse(Node node) throws WadlException {
		IFaultDefinition representationDefinition = new FaultDefinition();

		representationDefinition.setId(UriUtil.createUri(XmlUtil.getNodeAttribute(node, "id")));
		representationDefinition.setMediaType(XmlUtil.getNodeAttribute(node, "mediaType"));
		representationDefinition.setElement(XmlUtil.getNodeAttribute(node, "element"));
		representationDefinition.setProfiles(getProfiles(node));
		representationDefinition.setStatus(getStatuses(node));

		for (Node paramNode : XmlUtil.getChildNodes(node, "param")) {
			representationDefinition.addParam(new ParamParser().parse(paramNode));
		}

		return representationDefinition;
	}

	private List<Integer> getStatuses(Node node) {
		List<Integer> statusList = new ArrayList<Integer>();

		for (String status : StringUtil.split(XmlUtil.getNodeAttribute(node, "status"), " ")) {
			statusList.add(Integer.parseInt(status));
		}

		return statusList;
	}

	private List<URI> getProfiles(Node node) throws WadlException {
		List<URI> profileUris = new ArrayList<URI>();

		for (String profile : StringUtil.split(XmlUtil.getNodeAttribute(node, "profile"), " ")) {
			profileUris.add(UriUtil.createUri(profile));
		}

		return profileUris;
	}
}
