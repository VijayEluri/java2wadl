package at.ac.tuwien.infosys.wadl2java.xml;

import static at.ac.tuwien.infosys.java2wadl.util.AssertUtil.assertNotNull;

import org.w3c.dom.Node;

import at.ac.tuwien.infosys.java2wadl.WadlException;
import at.ac.tuwien.infosys.java2wadl.util.XPathUtil;
import at.ac.tuwien.infosys.java2wadl.util.XmlUtil;
import at.ac.tuwien.infosys.java2wadl.wadl.Application;
import at.ac.tuwien.infosys.java2wadl.wadl.IApplication;

public class ApplicationParser implements IApplicationParser {

	@Override
	public IApplication parse(String wadlSchema) throws WadlException {
		assertNotNull(wadlSchema);

		IApplication application = new Application();

		parseGrammars(wadlSchema, application);
		parseResources(wadlSchema, application);
		parseResourceTypes(wadlSchema, application);
		parseRepresentations(wadlSchema, application);
		parseMethods(wadlSchema, application);
		parseFaults(wadlSchema, application);

		return application;
	}

	private void parseResources(String wadlSchema, IApplication application) throws WadlException {
		for (Node resourcesNode : XmlUtil.toList(XPathUtil.query(wadlSchema, "/wadl:application/wadl:resources"))) {
			application.setResources(new ResourcesParser().parse(resourcesNode));
		}
	}

	private void parseGrammars(String wadlSchema, IApplication application) throws WadlException {
		for (Node grammarNode : XmlUtil.toList(XPathUtil.query(wadlSchema, "/wadl:application/wadl:grammars"))) {
			application.setGrammars(new GrammarsParser().parse(grammarNode));
		}
	}

	private void parseFaults(String wadlSchema, IApplication application) throws WadlException {
		for (Node faultNode : XmlUtil.toList(XPathUtil.query(wadlSchema, "/wadl:application/wadl:fault"))) {
			application.addFault(new FaultParser().parse(faultNode));
		}
	}

	private void parseMethods(String wadlSchema, IApplication application) throws WadlException {
		for (Node methodNode : XmlUtil.toList(XPathUtil.query(wadlSchema, "/wadl:application/wadl:method"))) {
			application.addMethod(new MethodParser().parse(methodNode));
		}
	}

	private void parseRepresentations(String wadlSchema, IApplication application) throws WadlException {
		for (Node representationNode : XmlUtil.toList(XPathUtil.query(wadlSchema,
				"/wadl:application/wadl:representation"))) {
			application.addRepresentation(new RepresentationParser().parse(representationNode));
		}
	}

	private void parseResourceTypes(String wadlSchema, IApplication application) throws WadlException {
		for (Node resourceTypeNode : XmlUtil.toList(XPathUtil
				.query(wadlSchema, "/wadl:application/wadl:representation"))) {
			application.addResource_type(new ResourceTypeParser().parse(resourceTypeNode));
		}
	}
}
