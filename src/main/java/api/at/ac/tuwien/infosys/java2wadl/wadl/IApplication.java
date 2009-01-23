package at.ac.tuwien.infosys.java2wadl.wadl;

import java.util.List;

/**
 * The Application-element forms the root of a WADL description.
 * 
 * <pre>
 * &lt;xs:complexType&gt;
 *   &lt;xs:sequence&gt;
 *     &lt;xs:element ref=&quot;tns:doc&quot; minOccurs=&quot;0&quot; maxOccurs=&quot;unbounded&quot;/&gt;
 *     &lt;xs:element ref=&quot;tns:grammars&quot; minOccurs=&quot;0&quot;/&gt;
 *     &lt;xs:element ref=&quot;tns:resources&quot; minOccurs=&quot;0&quot;/&gt;
 *     &lt;xs:choice minOccurs=&quot;0&quot; maxOccurs=&quot;unbounded&quot;&gt;
 *       &lt;xs:element ref=&quot;tns:resource_type&quot;/&gt;
 *       &lt;xs:element ref=&quot;tns:method&quot;/&gt;
 *       &lt;xs:element ref=&quot;tns:representation&quot;/&gt;
 *       &lt;xs:element ref=&quot;tns:fault&quot;/&gt;
 *     &lt;/xs:choice&gt;
 *   &lt;/xs:sequence&gt;
 * &lt;/xs:complexType&gt;
 * </pre>
 * 
 * @author <a href="mailto:e0426062@student.tuwien.ac.at">Andreas Meingast</a>
 * @author <a href="mailto:e0325672@student.tuwien.ac.at">Anton Korosec</a>
 */
public interface IApplication {

	/**
	 * The Application-element contains zero or more Doc-elements.
	 */
	public List<IDoc> getDocs();

	public boolean addDoc(IDoc doc);

	/**
	 * The Application-element contains an optional Grammars-element.
	 */
	public IGrammars getGrammars();

	public boolean setGrammars(IGrammars grammars);

	/**
	 * The Application-element contains an optional Resources-element.
	 */
	public IResources getResources();

	public boolean setResources(IResources resources);

	/**
	 * The Application-element contains zero or more ResourceType-elements.
	 */
	public List<IResourceType> getResource_types();

	public boolean addResource_type(IResourceType resourceType);

	/**
	 * The Application-element contains zero or more Method-elements.
	 */
	public List<IMethod> getMethods();

	public boolean addMethod(IMethod method);

	/**
	 * The Application-element contains zero or more Representation-elements.
	 */
	public List<IRepresentation> getRepresentations();

	public boolean addRepresentation(IRepresentation representation);

	/**
	 * The Application-element contains zero or more Fault-elements.
	 */
	public List<IFault> getFaults();

	public boolean addFault(IFault fault);

	public String toString();

}
