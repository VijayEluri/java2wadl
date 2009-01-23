package at.ac.tuwien.infosys.java2wadl.wadl;

import java.net.URI;
import java.util.List;

/**
 * A Param-element describes a parameterized component of its parent element and may be a child of a Resource-,
 * Request-, Response-, Representation- or a Fault-element.
 * 
 * <pre>
 * &lt;xs:complexType&gt;
 *   &lt;xs:sequence&gt;
 *     &lt;xs:element ref=&quot;tns:doc&quot; minOccurs=&quot;0&quot; maxOccurs=&quot;unbounded&quot;/&gt;
 *     &lt;xs:element ref=&quot;tns:option&quot; minOccurs=&quot;0&quot; maxOccurs=&quot;unbounded&quot;/&gt;
 *     &lt;xs:element ref=&quot;tns:link&quot; minOccurs=&quot;0&quot;/&gt;
 *   &lt;/xs:sequence&gt;
 *   &lt;xs:attribute name=&quot;name&quot; type=&quot;xs:NMTOKEN&quot; use=&quot;required&quot;/&gt;
 *   &lt;xs:attribute name=&quot;style&quot; type=&quot;tns:ParamStyle&quot; use=&quot;required&quot;/&gt;
 *   &lt;xs:attribute name=&quot;id&quot; type=&quot;xs:ID&quot;/&gt;
 *   &lt;xs:attribute name=&quot;type&quot; type=&quot;xs:QName&quot; default=&quot;xs:string&quot;/&gt;
 *   &lt;xs:attribute name=&quot;default&quot; type=&quot;xs:string&quot;/&gt;
 *   &lt;xs:attribute name=&quot;required&quot; type=&quot;xs:boolean&quot; default=&quot;false&quot;/&gt;
 *   &lt;xs:attribute name=&quot;repeating&quot; type=&quot;xs:boolean&quot; default=&quot;false&quot;/&gt;
 *   &lt;xs:attribute name=&quot;fixed&quot; type=&quot;xs:string&quot;/&gt;
 *   &lt;xs:attribute name=&quot;path&quot; type=&quot;xs:string&quot;/&gt;
 * &lt;/xs:complexType&gt;
 * </pre>
 * 
 * @author <a href="mailto:e0426062@student.tuwien.ac.at">Andreas Meingast</a>
 * @author <a href="mailto:e0325672@student.tuwien.ac.at">Anton Korosec</a>
 */
public interface IParam {

	/**
	 * An optional identifier that may be used to refer to a parameter definition using a URI reference.
	 */
	public URI getId();

	public boolean setId(URI id);

	/**
	 * The name of the parameter. Required.
	 */
	public String getName();

	public boolean setName(String name);

	/**
	 * Indicates the parameter style. See ParamStyle for the allowed values and the context(s) in which each value may
	 * be used.
	 */
	public ParamStyle getStyle();

	public boolean setStyle(ParamStyle paramStyle);

	/**
	 * Optionally indicates the type of the parameter as an XML qualified name, defaults to 'xsd:string'.
	 */
	public String getType();

	public boolean setType(String type);

	/**
	 * Optionally provides a value that is considered identical to an unspecified parameter value.
	 */
	public String getDefault();

	public boolean setDefault(String default_);

	/**
	 * When the parent element is a Representation-element, this attribute optionally provides a path to the value of
	 * the parameter within the representation. For XML representations, use of XPath is recommended.
	 */
	public String getPath();

	public boolean setPath(String path);

	/**
	 * Optionally indicates whether the parameter is required to be present or not, defaults to false (parameter not
	 * required).
	 */
	public Boolean getRequired();

	public boolean setRequired(Boolean required);

	/**
	 * Optionally indicates whether the parameter is single valued or may have multiple values, defaults to false
	 * (parameter is single valued).
	 */
	public Boolean getRepeating();

	public boolean setRepeating(Boolean repeating);

	/**
	 * Optionally provides a fixed value for the parameter.
	 */
	public String getFixed();

	public boolean setFixed(String fixed);

	/**
	 * A Param-element contains zero or more Doc-elements.
	 */
	public List<IDoc> getDocs();

	public boolean addDoc(IDoc doc);

	/**
	 * A Param-element contains zero or more Option-elements.
	 */
	public List<IOption> getOptions();

	public boolean addOption(IOption option);

	/**
	 * A Param-element contains an optional Link-element.
	 */
	public ILink getLink();

	public boolean setLink(ILink link);

	public String toString();

}
