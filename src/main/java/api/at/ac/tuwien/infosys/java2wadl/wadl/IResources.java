package at.ac.tuwien.infosys.java2wadl.wadl;

import java.net.URI;
import java.util.List;

/**
 * The Resources-element acts as a container for the resources provided by the application.
 * 
 * <pre>
 * &lt;xs:complexType&gt;
 *   &lt;xs:sequence&gt;
 *     &lt;xs:element ref=&quot;tns:doc&quot; minOccurs=&quot;0&quot; maxOccurs=&quot;unbounded&quot;/&gt;
 *     &lt;xs:element ref=&quot;tns:resource&quot; maxOccurs=&quot;unbounded&quot;/&gt;
 *   &lt;/xs:sequence&gt;
 *   &lt;xs:attribute name=&quot;base&quot; type=&quot;xs:anyURI&quot;/&gt;
 * &lt;/xs:complexType&gt;
 * </pre>
 * 
 * @author <a href="mailto:e0426062@student.tuwien.ac.at">Andreas Meingast</a>
 * @author <a href="mailto:e0325672@student.tuwien.ac.at">Anton Korosec</a>
 */
public interface IResources {

	/**
	 * A Resources-element has a base-attribute that provides the base URI for each child resource identifier.
	 */
	public URI getBase();

	public boolean setBase(URI base);

	/**
	 * A Resources-element contains zero or more Doc-elements.
	 */
	public List<IDoc> getDocs();

	public boolean addDoc(IDoc doc);

	/**
	 * Resource-elements describe the resources provided by the application.
	 */
	public List<IResource> getResources();

	public boolean addResource(IResource resource);

	public String toString();

}
