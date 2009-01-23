package at.ac.tuwien.infosys.java2wadl.wadl;

/**
 * @author <a href="mailto:e0426062@student.tuwien.ac.at">Andreas Meingast</a>
 * @author <a href="mailto:e0325672@student.tuwien.ac.at">Anton Korosec</a>
 * 
 */
public enum ParamStyle {
	/**
	 * param Parent Elements: Representation or Fault Usage: Specifies a component of the representation formatted as a
	 * string encoding of the parameter value according to the rules of the media type.
	 */
	plain,

	/**
	 * param Parent Elements: Resource, ResourceType or Request Usage: Specifies a URI query parameter represented
	 * according to the rules for the query component media type specified by the queryType-attribute
	 * 
	 * param Parent Elements: Representation or Fault Usage: Specifies a component of the representation as a name value
	 * pair formatted according to the rules of the media type.
	 */
	query,

	/**
	 * param Parent Element: Resource Usage: Specifies a matrix URI component
	 */
	matrix,

	/**
	 * param Parent Elements: Resource, ResourceType or Request Usage: Specifies a HTTP header that pertains to the HTTP
	 * request (Resource or Request) or HTTP response (Response)
	 */
	header,

	/**
	 * param Parent Element: Resource Usage: The parameter is represented as a string encoding of the parameter value
	 * and is substituted into the value of the path-attribute of the Resource-element.
	 */
	template
}
