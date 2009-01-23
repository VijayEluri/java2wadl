package at.ac.tuwien.infosys.java2wadl.wadl;

import java.net.URI;

/**
 * A Method-reference-element is a child of a Resource-element that has an href-attribute whose value is a URI reference
 * to a Method-definition-element. A Method-reference-element MUST NOT have any other WADL-defined attributes or contain
 * any WADL-defined child elements. This form of Method-element may be used to reduce duplication when the same method
 * applies to more than one resources.
 * 
 * @author <a href="mailto:e0426062@student.tuwien.ac.at">Andreas Meingast</a>
 * @author <a href="mailto:e0325672@student.tuwien.ac.at">Anton Korosec</a>
 */
public interface IMethodReference extends IMethod {

	/**
	 * The value of the href-attribute is a URI reference to a Method-definition-element.
	 */
	public URI getHref();

	public boolean setHref(URI href);

}
