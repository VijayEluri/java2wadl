package at.ac.tuwien.infosys.java2wadl;

/**
 * Class defining the constants used within the application.
 * 
 * @author <a href="mailto:e0426062@student.tuwien.ac.at">Andreas Meingast</a>
 * @author <a href="mailto:e0325672@student.tuwien.ac.at">Anton Korosec</a>
 */
public final class Consts {

	/**
	 * XML header of the generated WADL
	 */
	public static final String xml_header = "<?xml version=\"1.0\"?>";

	/**
	 * WADL namespace
	 */
	public static final String wadl_ns = "xmlns=\"http://research.sun.com/wadl/2006/10\"";

	/**
	 * XML schema namespace
	 */
	public static final String xml_schema_ns = "xmlns:xs=\"http://www.w3.org/2001/XMLSchema\"";

	/**
	 * namespace-prefix for the schema-elements
	 */
	public static final String xml_schema_ns_prefix = "xs:";

	/**
	 * collection of the namespaces defined within the WADL-application-element
	 */
	public static final String[] xml_namespaces = new String[] { wadl_ns, xml_schema_ns };

	/**
	 * prefix for the reference to a methods id
	 */
	public static final String METHOD_ID_PREFIX = "#";

	/**
	 * targetNamespace for the generated schemas
	 */
	public static final String GRAMMAR_NS = "j2wns";
}
