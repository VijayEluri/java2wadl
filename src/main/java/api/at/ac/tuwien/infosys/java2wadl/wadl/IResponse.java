package at.ac.tuwien.infosys.java2wadl.wadl;

import java.util.List;

/**
 * A Response-element describes the output that results from performing an HTTP method on a resource.
 * 
 * <pre>
 * &lt;xs:complexType&gt;
 *   &lt;xs:sequence&gt;
 *     &lt;xs:element ref=&quot;tns:doc&quot; minOccurs=&quot;0&quot; maxOccurs=&quot;unbounded&quot;/&gt;
 *     &lt;xs:element ref=&quot;tns:param&quot; minOccurs=&quot;0&quot; maxOccurs=&quot;unbounded&quot;/&gt;
 *     &lt;xs:choice minOccurs=&quot;0&quot; maxOccurs=&quot;unbounded&quot;&gt;
 *       &lt;xs:element ref=&quot;tns:representation&quot;/&gt;
 *       &lt;xs:element ref=&quot;tns:fault&quot;/&gt;
 *     &lt;/xs:choice&gt;
 *   &lt;/xs:sequence&gt;
 * &lt;/xs:complexType&gt;
 * </pre>
 * 
 * @author <a href="mailto:e0426062@student.tuwien.ac.at">Andreas Meingast</a>
 * @author <a href="mailto:e0325672@student.tuwien.ac.at">Anton Korosec</a>
 */
public interface IResponse {

	/**
	 * A Response-element contains zero or more Doc-elements.
	 */
	public List<IDoc> getDocs();

	public boolean addDoc(IDoc doc);

	/**
	 * A Response-element contains zero or more Param-elements with a value of 'header' for their style-attriute, each
	 * of which specifies the details of a HTTP header for the response.
	 */
	public List<IParam> getParams();

	public boolean addParam(IParam param);

	/**
	 * A Response-element contains zero or more Representation-elements, each of which describes a resource
	 * representation that may result from performing the method. Sibling Representation-elements indicate logically
	 * equivalent alternatives.
	 */
	public List<IRepresentation> getRepresentations();

	public boolean addRepresentation(IRepresentation representation);

	/**
	 * A Response-element contains zero or more Fault-elements, each of which describes a fault condition that may
	 * occur.
	 */
	public List<IFault> getFaults();

	public boolean addFault(IFault fault);

	public boolean isEmpty();

	public String toString();

}
