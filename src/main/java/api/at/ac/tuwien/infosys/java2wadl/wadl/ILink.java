package at.ac.tuwien.infosys.java2wadl.wadl;

import java.net.URI;
import java.util.List;

/**
 * A Link-element is used to identify links to resources within representations. A Link-element is a child of a
 * Param-Element whose path-attribute identifies the portion of its parent representation that contains the link URI.
 * 
 * <pre>
 * &lt;xs:complexType&gt;
 *   &lt;xs:sequence&gt;
 *     &lt;xs:element ref=&quot;tns:doc&quot; minOccurs=&quot;0&quot; maxOccurs=&quot;unbounded&quot;/&gt;
 *   &lt;/xs:sequence&gt;
 *   &lt;xs:attribute name=&quot;resource_type&quot; type=&quot;xs:anyURI&quot;/&gt;
 *   &lt;xs:attribute name=&quot;rel&quot; type=&quot;xs:token&quot;/&gt;
 *   &lt;xs:attribute name=&quot;rev&quot; type=&quot;xs:token&quot;/&gt;
 * &lt;/xs:complexType&gt;
 * </pre>
 * 
 * @author <a href="mailto:e0426062@student.tuwien.ac.at">Andreas Meingast</a>
 * @author <a href="mailto:e0325672@student.tuwien.ac.at">Anton Korosec</a>
 */
public interface ILink {

	/**
	 * An optional URI reference to a ResourceType-element that defines the capabilities of the resource that the link
	 * identifies.
	 */
	public URI getResource_type();

	public boolean setResource_type(URI resourceType);

	/**
	 * An optional token that identifies the relationship of the resource identified by the link to the resource whose
	 * representation the link is embedded in. The value is scoped by the value of the ancestor Representation- or
	 * Fault-element's profile-attribute.
	 */
	public String getRel();

	public boolean setRel(String rel);

	/**
	 * An optional token that identifies the relationship of the resource whose representation the link is embedded in
	 * to the resource identified by the link. This is the reverse relationship to that identified by the rel-
	 * attribute. The value is scoped by the value of the ancestor Representation- or Fault-element's profile-attribute.
	 */
	public String getRev();

	public boolean setRev(String rev);

	/**
	 * A Link-element contains zero or more Doc-elements.
	 */
	public List<IDoc> getDocs();

	public boolean addDoc(IDoc doc);

	public String toString();

}
