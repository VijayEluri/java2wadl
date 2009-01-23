package at.ac.tuwien.infosys.java2wadl.wadl;

/**
 * A Fault-element is similar to a Representation-element in structure but differs in that it denotes an error
 * condition. A Fault-element has the same attributes as a Representation-element. As is the case with Representation-
 * elements, multiple sibling Fault-elements may share one or more HTTP status codes: such elements may describe more
 * granular fault conditions or may provide equivalent information in different formats.
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
public interface IFault {

	public String toString();

}
