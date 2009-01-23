package at.ac.tuwien.infosys.java2wadl.wadl;

import java.net.URI;
import java.util.List;

/**
 * @see at.ac.tuwien.infosys.java2wadl.wadl.IRepresentationDefinition
 * 
 * @author <a href="mailto:e0426062@student.tuwien.ac.at">Andreas Meingast</a>
 * @author <a href="mailto:e0325672@student.tuwien.ac.at">Anton Korosec</a>
 */
public interface IFaultDefinition extends IFault {

	public URI getId();

	public boolean setId(URI id);

	public String getMediaType();

	public boolean setMediaType(String mediaType);

	public String getElement();

	public boolean setElement(String element);

	public List<URI> getProfiles();

	public boolean setProfiles(List<URI> profiles);

	public boolean addProfile(URI profile);

	public List<Integer> getStatus();

	public boolean setStatus(List<Integer> status);

	public boolean addStatus(Integer status);

	/**
	 * A Fault-element contains zero or more Doc-elements.
	 */
	public List<IDoc> getDocs();

	public boolean addDoc(IDoc doc);

	/**
	 * Fault parameters are Param-elements that are direct children of a Fault- element. Fault parameters perform the
	 * same function for Fault-elements that representation parameters perform for Representation-elements.
	 */
	public List<IParam> getParams();

	public boolean addParam(IParam param);

}
