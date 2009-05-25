package at.ac.tuwien.infosys.java2wadl;

import java.util.Arrays;

import at.ac.tuwien.infosys.java2wadl.util.UriUtil;
import at.ac.tuwien.infosys.java2wadl.util.XmlUtil;
import at.ac.tuwien.infosys.java2wadl.wadl.IApplication;
import at.ac.tuwien.infosys.java2wadl.wadl.IInclude;
import at.ac.tuwien.infosys.java2wadl.wadl.IRepresentationDefinition;
import at.ac.tuwien.infosys.java2wadl.wadl.Include;
import at.ac.tuwien.infosys.java2wadl.wadl.RepresentationDefinition;

/**
 * Class for handling all WADL-Representation related stuff.
 * 
 * @author <a href="mailto:e0426062@student.tuwien.ac.at">Andreas Meingast</a>
 * @author <a href="mailto:e0325672@student.tuwien.ac.at">Anton Korosec</a>
 */
class RepresentationHandler {
	/**
	 * Create representaton-definition instance for a complex type.
	 * 
	 * @param type
	 *            The Java-type of the element described with the representation-definition.
	 * @param application
	 *            The WADL-application instance that should be enhanced.
	 * @param mediaType
	 *            The mediatype of the representation.
	 * @param statusCode
	 *            The statuscode for the representation.
	 * @return The created representation instance.
	 * @throws WadlException
	 */
	public static IRepresentationDefinition createRepresentationDefinition(Class<?> type, IApplication application,
			String mediaType, Integer statusCode) throws WadlException {
		IRepresentationDefinition representation = createRepresentationDefinition(mediaType, statusCode);
		XmlUtil.generateSchema(type);
		IInclude include = new Include();

		include.setHref(UriUtil.createUri(XmlUtil.getSchemaName(type)));
		application.getGrammars().addInclude(include);
		representation.setElement(Consts.GRAMMAR_NS + ":" + type.getSimpleName().toLowerCase());
		return representation;
	}

	/**
	 * Create a basic representation-definition instance (only mediaType and statusCode set).
	 * 
	 * @param mediaType
	 *            The mediatype of the representation.
	 * @param statusCode
	 *            The statuscode for the representation.
	 * @return The created representation instance.
	 */
	public static IRepresentationDefinition createRepresentationDefinition(String mediaType, Integer statusCode) {
		IRepresentationDefinition representation = new RepresentationDefinition();

		representation.setMediaType(mediaType);
		representation.setStatus(Arrays.asList(statusCode == null ? new Integer[] {} : new Integer[] { statusCode }));
		return representation;
	}
}
