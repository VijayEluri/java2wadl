package at.ac.tuwien.infosys.java2wadl.wadl;

/**
 * Each WADL-defined element can have one or more child Doc-elements that can be used to document that element.
 * 
 * <pre>
 * &lt;xs:complexType mixed=&quot;true&quot;&gt;
 *   &lt;xs:attribute name=&quot;title&quot; type=&quot;xs:string&quot;/&gt;
 *   &lt;xs:attribute ref=&quot;xml:lang&quot;/&gt;
 * &lt;/xs:complexType&gt;
 * </pre>
 * 
 * @author <a href="mailto:e0426062@student.tuwien.ac.at">Andreas Meingast</a>
 * @author <a href="mailto:e0325672@student.tuwien.ac.at">Anton Korosec</a>
 */
public interface IDoc {

	/**
	 * The lang-attribute defines the language for the title-attribute value and the contents of the Doc-element. If an
	 * element contains more than one Doc-element then they MUST have distinct values for their lang-attribute.
	 */
	public String getLang();

	public boolean setLang(String lang);

	/**
	 * The title-attribute is a short plain text description of the element being documented, the value SHOULD be
	 * suitable for use as a title for the contained documentation.
	 */
	public String getTitle();

	public boolean setTitle(String title);

	public String getContent();

	public boolean setContent(String content);

	public String toString();

}
