package at.ac.tuwien.infosys.java2wadl.wadl;

import java.net.URI;
import java.util.List;

/**
 * The Include-element allows the definitions of one or more data format descriptions to be included by reference. Use
 * of the Include-element is logically equivalent to in-lining the referenced document within the WADL Grammars-element.
 * 
 * <pre>
 * &lt;xs:complexType&gt;
 *   &lt;xs:sequence&gt;
 *     &lt;xs:element ref=&quot;tns:doc&quot; minOccurs=&quot;0&quot; maxOccurs=&quot;unbounded&quot;/&gt;
 *   &lt;/xs:sequence&gt;
 *   &lt;xs:attribute name=&quot;href&quot; type=&quot;xs:anyURI&quot;/&gt;
 * &lt;/xs:complexType&gt;
 * </pre>
 * 
 * @author <a href="mailto:e0426062@student.tuwien.ac.at">Andreas Meingast</a>
 * @author <a href="mailto:e0325672@student.tuwien.ac.at">Anton Korosec</a>
 */
public interface IInclude {

	/**
	 * The href-attribute procides a URI for the referenced definitions.
	 */
	public URI getHref();

	public boolean setHref(URI href);

	/**
	 * An Include-element contains zero or more Doc-elements.
	 */
	public List<IDoc> getDocs();

	public boolean addDoc(IDoc doc);

	public String toString();

}
