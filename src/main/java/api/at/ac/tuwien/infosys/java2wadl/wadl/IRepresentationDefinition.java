package at.ac.tuwien.infosys.java2wadl.wadl;

import java.net.URI;
import java.util.List;

/**
 * A Representation-definition-element can be a child of a Request-, Response- or Application-element.
 * 
 * @author <a href="mailto:e0426062@student.tuwien.ac.at">Andreas Meingast</a>
 * @author <a href="mailto:e0325672@student.tuwien.ac.at">Anton Korosec</a>
 */
public interface IRepresentationDefinition extends IRepresentation {

	/**
	 * An identifier for the representation, required for globally defined representations, not allowed on locally
	 * embedded representations.
	 */
	public URI getId();

	public boolean setId(URI id);

	/**
	 * Indicates the media type of the representation.
	 */
	public String getMediaType();

	public boolean setMediaType(String mediaType);

	/**
	 * For XML-based representations, specifies the qualified name of the root element.
	 */
	public String getElement();

	public boolean setElement(String element);

	/**
	 * Similar to the HTML profile attribute, gives the location of one or more meta data profiles, separated by white
	 * space. The meta-data profiles define the meaning of the rel- and rev- attributes of descendent Link-elements.
	 */
	public List<URI> getProfiles();

	public boolean setProfiles(List<URI> profiles);

	public boolean addProfile(URI profile);

	/**
	 * Optionally present on response representation, provides a list of HTTP status codes associated with a praticular
	 * representation. Note that multiple sibling Representation-elements may share one or more HTTP status codes: such
	 * elements may provide equivalent information in different formats.
	 */
	public List<Integer> getStatus();

	public boolean setStatus(List<Integer> status);

	public boolean addStatus(Integer status);

	/**
	 * A Representation-element contains zero or more Doc-elements.
	 */
	public List<IDoc> getDocs();

	public boolean addDoc(IDoc doc);

	/**
	 * A Representation-element contains zero or more Param-elements. A Param-element is used to parametrize its parent
	 * Representation-element.
	 * 
	 * Representation parameters can have one of two different functions depending on the media type of the
	 * representation: 1. Define the content of the representation. 2. Provide a hint to processors about items of
	 * interes within a representation.
	 * 
	 * (For more information see point 2.10.3 of the WADL-specification)
	 */
	public List<IParam> getParams();

	public boolean addParam(IParam param);

}
