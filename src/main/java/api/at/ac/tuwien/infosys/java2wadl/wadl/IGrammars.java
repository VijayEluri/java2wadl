package at.ac.tuwien.infosys.java2wadl.wadl;

import java.util.List;

/**
 * The Grammars-element acts as a container for definitions of the format of data exchanged during execution of the
 * protocol described by the WADL document. Such definitions may be included inline or by reference using the
 * Include-element. No particular data format definition language is mandated.
 * 
 * <pre>
 * &lt;xs:complexType&gt;
 *   &lt;xs:sequence&gt;
 *     &lt;xs:element ref=&quot;tns:doc&quot; minOccurs=&quot;0&quot; maxOccurs=&quot;unbounded&quot;/&gt;
 *     &lt;xs:element minOccurs=&quot;0&quot; maxOccurs=&quot;unbounded&quot; ref=&quot;tns:include&quot;/&gt;
 *   &lt;/xs:sequence&gt;
 * &lt;/xs:complexType&gt;
 * </pre>
 * 
 * @author <a href="mailto:e0426062@student.tuwien.ac.at">Andreas Meingast</a>
 * @author <a href="mailto:e0325672@student.tuwien.ac.at">Anton Korosec</a>
 */
public interface IGrammars {

	/**
	 * A Grammars-element contains zero or more Doc-elements.
	 */
	public List<IDoc> getDocs();

	public boolean addDoc(IDoc doc);

	/**
	 * A Grammars-element contains zero or more Include-elements.
	 */
	public List<IInclude> getIncludes();

	public boolean addInclude(IInclude include);

	public String toString();

}
