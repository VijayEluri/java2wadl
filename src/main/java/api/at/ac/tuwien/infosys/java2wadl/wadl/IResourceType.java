package at.ac.tuwien.infosys.java2wadl.wadl;

import java.net.URI;
import java.util.List;

/**
 * A ResourceType-element describes a set of methods that, together, define the behavior of a type of resource. A
 * ResourceType may be used to define resource behavior that is expected to be supported by multiple resources.
 * 
 * <pre>
 * &lt;xs:complexType&gt;
 *   &lt;xs:sequence&gt;
 *     &lt;xs:element ref=&quot;tns:doc&quot; minOccurs=&quot;0&quot; maxOccurs=&quot;unbounded&quot;/&gt;
 *     &lt;xs:element ref=&quot;tns:param&quot; minOccurs=&quot;0&quot; maxOccurs=&quot;unbounded&quot;/&gt;
 *     &lt;xs:element ref=&quot;tns:method&quot; maxOccurs=&quot;unbounded&quot;/&gt;
 *   &lt;/xs:sequence&gt;
 *   &lt;xs:attribute name=&quot;id&quot; type=&quot;xs:ID&quot;/&gt;
 * &lt;/xs:complexType&gt;
 * </pre>
 * 
 * @author <a href="mailto:e0426062@student.tuwien.ac.at">Andreas Meingast</a>
 * @author <a href="mailto:e0325672@student.tuwien.ac.at">Anton Korosec</a>
 */
public interface IResourceType {

	/**
	 * A required attribute that identifies the ResourceType-element.
	 */
	public URI getId();

	public boolean setId(URI id);

	/**
	 * A ResourceType-element contains zero or more Doc-elements.
	 */
	public List<IDoc> getDocs();

	public boolean addDoc(IDoc doc);

	/**
	 * A ResourceType-element contains zero or more Param-elements whith one of the following values for its
	 * style-attribute: query, header.
	 */
	public List<IParam> getParams();

	public boolean addParam(IParam param);

	/**
	 * A ResourceType-element contains zero or more Method-elements, each of which describes a HTTP protocol method that
	 * can be applied to a resource of this type.
	 */
	public List<IMethod> getMethods();

	public boolean addMethod(IMethod method);

	public String toString();

}
