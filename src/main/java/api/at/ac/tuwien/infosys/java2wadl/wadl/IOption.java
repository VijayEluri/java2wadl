package at.ac.tuwien.infosys.java2wadl.wadl;

import java.util.List;

/**
 * An Option-element defines one of a set of possible values for the parameter represented by its parent Param-element.
 * 
 * <pre>
 * &lt;xs:complexType&gt;
 *   &lt;xs:sequence&gt;
 *     &lt;xs:element ref=&quot;tns:doc&quot; minOccurs=&quot;0&quot; maxOccurs=&quot;unbounded&quot;/&gt;
 *   &lt;/xs:sequence&gt;
 *   &lt;xs:attribute name=&quot;value&quot; type=&quot;xs:string&quot; use=&quot;required&quot;/&gt;
 * &lt;/xs:complexType&gt;
 * </pre>
 * 
 * @author <a href="mailto:e0426062@student.tuwien.ac.at">Andreas Meingast</a>
 * @author <a href="mailto:e0325672@student.tuwien.ac.at">Anton Korosec</a>
 */
public interface IOption {

	/**
	 * An Option-element has a required value-attribute that defines the value.
	 */
	public String getValue();

	public boolean setValue(String value);

	/**
	 * An Option-element contains zero or more Doc-elements that document the meaning of the value.
	 */
	public List<IDoc> getDocs();

	public boolean addDoc(IDoc doc);

	public String toString();

}
