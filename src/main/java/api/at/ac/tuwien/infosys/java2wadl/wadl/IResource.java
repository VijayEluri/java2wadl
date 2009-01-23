package at.ac.tuwien.infosys.java2wadl.wadl;

import java.net.URI;
import java.util.List;

/**
 * A Resource-element describes a set of resources, each identified by a URI that follows a common pattern.
 * 
 * <pre>
 * &lt;xs:complexType&gt;
 *   &lt;xs:sequence&gt;
 *     &lt;xs:element ref=&quot;tns:doc&quot; minOccurs=&quot;0&quot; maxOccurs=&quot;unbounded&quot;/&gt;
 *     &lt;xs:element ref=&quot;tns:param&quot; minOccurs=&quot;0&quot; maxOccurs=&quot;unbounded&quot;/&gt;
 *     &lt;xs:choice minOccurs=&quot;0&quot; maxOccurs=&quot;unbounded&quot;&gt;
 *       &lt;xs:element ref=&quot;tns:method&quot;/&gt;
 *       &lt;xs:element ref=&quot;tns:resource&quot;/&gt;
 *     &lt;/xs:choice&gt;
 *   &lt;/xs:sequence&gt;
 *   &lt;xs:attribute name=&quot;id&quot; type=&quot;xs:ID&quot;/&gt;
 *   &lt;xs:attribute name=&quot;type&quot; type=&quot;tns:resource_type_list&quot;/&gt;
 *   &lt;xs:attribute name=&quot;queryType&quot; type=&quot;xs:string&quot; default=&quot;application/x-www-form-urlencoded&quot;/&gt;
 *   &lt;xs:attribute name=&quot;path&quot; type=&quot;xs:string&quot;/&gt;
 * &lt;/xs:complexType&gt;
 * </pre>
 * 
 * @author <a href="mailto:e0426062@student.tuwien.ac.at">Andreas Meingast</a>
 * @author <a href="mailto:e0325672@student.tuwien.ac.at">Anton Korosec</a>
 */
public interface IResource {

	/**
	 * An optional attribute that identifies the Resource-element.
	 */
	public URI getId();

	public boolean setId(URI id);

	/**
	 * An optional attribute. If present, it provides a relative URI template for the identifier of the resource. The
	 * resource's base URI is given by the Resource-element's parent Resource- or Resources-element. The value of the
	 * path-attribute may be static or may contain embedded template parameters.
	 */
	public String getPath();

	public boolean setPath(String path);

	/**
	 * An optional attribute whose type is a space-separated list of URIs. Each value in the list identifies a
	 * ResourceType-element that defines a set of methods supported by the resource.
	 */
	public List<URI> getType();

	public boolean setType(List<URI> type);

	public boolean addType(URI type);

	/**
	 * Defines the media type for the query component of the resourve URI. Defaults to
	 * 'application/x-www-form-urlencoded' if not specified which results in query strings being formatted as specified.
	 */
	public String getQueryType();

	public boolean setQueryType(String queryType);

	/**
	 * A Resource-element contains zero or more Doc-elements.
	 */
	public List<IDoc> getDocs();

	public boolean addDoc(IDoc doc);

	/**
	 * A Resource-element contains zero or more Param-elements with one of the following values for its style-attriute:
	 * template, matrix, query, header.
	 */
	public List<IParam> getParams();

	public boolean addParam(IParam param);

	/**
	 * A Resource-element contains zero or more Method-elements, each of which describes the input to and output from an
	 * HTTP protocol method that can be applied to the resource. Such locally-defined methods are added to any methods
	 * included in ResourceType-elements referred to using the type-attribute.
	 */
	public List<IMethod> getMethods();

	public boolean addMethod(IMethod method);

	/**
	 * A Resource-element contains zero or more Resource-elements that describe sub-resources. Such sub-resources
	 * inherit matrix and template parameters from the parent resource since their URI is relative to that of the parent
	 * resource but they do not inherit query or header parameters specified globally for the parent resource.
	 */
	public List<IResource> getResources();

	public boolean addResource(IResource resource);

	public String toString();

}
