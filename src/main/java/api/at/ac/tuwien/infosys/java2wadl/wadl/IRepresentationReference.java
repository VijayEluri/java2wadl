package at.ac.tuwien.infosys.java2wadl.wadl;

import java.net.URI;

/**
 * A Representation-reference-element can be a child of a Request- or Response-element. It has a href-attribute whose
 * value is a URI reference to a Representation-definition-element. A Representation-reference-element MUST NOT have any
 * other WADL-defined attributes or contain any WADL-defined child elements. This form of Representation-element may be
 * used to reduce duplication when the same representation is used in multiple locations.
 * 
 * @author <a href="mailto:e0426062@student.tuwien.ac.at">Andreas Meingast</a>
 * @author <a href="mailto:e0325672@student.tuwien.ac.at">Anton Korosec</a>
 */
public interface IRepresentationReference extends IRepresentation {

	/**
	 * The value of the href-attribute is a URI reference to a Method-definition-element.
	 */
	public URI getHref();

	public boolean setHref(URI href);

}
