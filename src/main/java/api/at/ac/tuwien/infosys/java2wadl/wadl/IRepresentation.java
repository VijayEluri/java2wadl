package at.ac.tuwien.infosys.java2wadl.wadl;

/**
 * A Representation-element describes a representation of a resource's state. A Representation-element can either be a
 * representation definition or a reference to a representation defined elsewhere.
 * 
 * <pre>
 * &lt;xs:complexType&gt;
 *   &lt;xs:sequence&gt;
 *     &lt;xs:element ref=&quot;tns:doc&quot; minOccurs=&quot;0&quot; maxOccurs=&quot;unbounded&quot;/&gt;
 *     &lt;xs:element ref=&quot;tns:param&quot; minOccurs=&quot;0&quot; maxOccurs=&quot;unbounded&quot;/&gt;
 *   &lt;/xs:sequence&gt;
 *   &lt;xs:attribute name=&quot;id&quot; type=&quot;xs:ID&quot;/&gt;
 *   &lt;xs:attribute name=&quot;element&quot; type=&quot;xs:QName&quot;/&gt;
 *   &lt;xs:attribute name=&quot;status&quot; type=&quot;tns:statusCodeList&quot;/&gt;
 *   &lt;xs:attribute name=&quot;mediaType&quot; type=&quot;xs:string&quot;/&gt;
 *   &lt;xs:attribute name=&quot;href&quot; type=&quot;xs:anyURI&quot;/&gt;
 *   &lt;xs:attribute name=&quot;profile&quot; type=&quot;tns:uriList&quot;/&gt;
 * &lt;/xs:complexType&gt;
 * </pre>
 * 
 * @author <a href="mailto:e0426062@student.tuwien.ac.at">Andreas Meingast</a>
 * @author <a href="mailto:e0325672@student.tuwien.ac.at">Anton Korosec</a>
 */
public interface IRepresentation {

	public String toString();

}
