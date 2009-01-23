package at.ac.tuwien.infosys.java2wadl.wadl;

/**
 * A Method-element describes the input to and output from an HTTP protocol method that may be applied to a resource. A
 * Method-element can either be a method definition or a reference to a method defined elsewhere.
 * 
 * <pre>
 * &lt;xs:complexType&gt;
 *   &lt;xs:sequence&gt;
 *     &lt;xs:element ref=&quot;tns:doc&quot; minOccurs=&quot;0&quot; maxOccurs=&quot;unbounded&quot;/&gt;
 *     &lt;xs:element ref=&quot;tns:request&quot; minOccurs=&quot;0&quot;/&gt;
 *     &lt;xs:element ref=&quot;tns:response&quot; minOccurs=&quot;0&quot;/&gt;
 *   &lt;/xs:sequence&gt;
 *   &lt;xs:attribute name=&quot;id&quot; type=&quot;xs:ID&quot;/&gt;
 *   &lt;xs:attribute name=&quot;name&quot; type=&quot;tns:Method&quot;/&gt;
 *   &lt;xs:attribute name=&quot;href&quot; type=&quot;xs:anyURI&quot;/&gt;
 * &lt;/xs:complexType&gt;
 * </pre>
 * 
 * @author <a href="mailto:e0426062@student.tuwien.ac.at">Andreas Meingast</a>
 * @author <a href="mailto:e0325672@student.tuwien.ac.at">Anton Korosec</a>
 */
public interface IMethod {

	public String toString();

}
