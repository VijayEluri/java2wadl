package at.ac.tuwien.infosys.java2wadl.wadl;

import java.util.List;

/**
 * A Request-element describes the input to be included when applying an HTTP method to a resource.
 * 
 * <pre>
 * &lt;xs:complexType&gt;
 *   &lt;xs:sequence&gt;
 *     &lt;xs:element ref=&quot;tns:doc&quot; minOccurs=&quot;0&quot; maxOccurs=&quot;unbounded&quot;/&gt;
 *     &lt;xs:element ref=&quot;tns:param&quot; minOccurs=&quot;0&quot; maxOccurs=&quot;unbounded&quot;/&gt;
 *     &lt;xs:element ref=&quot;tns:representation&quot; minOccurs=&quot;0&quot; maxOccurs=&quot;unbounded&quot;/&gt;
 *   &lt;/xs:sequence&gt;
 * &lt;/xs:complexType&gt;
 * </pre>
 * 
 * @author <a href="mailto:e0426062@student.tuwien.ac.at">Andreas Meingast</a>
 * @author <a href="mailto:e0325672@student.tuwien.ac.at">Anton Korosec</a>
 */
public interface IRequest {

	/**
	 * A Request-element contains zero or more Doc-elements.
	 */
	public List<IDoc> getDocs();

	public boolean addDoc(IDoc doc);

	/**
	 * A Request-element contains zero or more Param-elements with one of the following values for their style-attriute:
	 * query, header.
	 */
	public List<IParam> getParams();

	public boolean addParam(IParam param);

	/**
	 * A Request-element contains zero or more Representation-elements. Note that the use of Representation-elements is
	 * confined to HTTP methods that accept an entity body in the request (e.g. PUT or POST). Sibling
	 * Representation-elements represent logically equivalent alternatives, e.g., a particular resource might support
	 * multiple XML grammars for a particular request.
	 */
	public List<IRepresentation> getRepresentations();

	public boolean addRepresentation(IRepresentation representation);

	boolean isEmpty();

	public String toString();

}
