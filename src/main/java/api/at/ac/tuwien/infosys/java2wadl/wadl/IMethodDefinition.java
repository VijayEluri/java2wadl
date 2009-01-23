package at.ac.tuwien.infosys.java2wadl.wadl;

import java.net.URI;
import java.util.List;

/**
 * A Method-definition-element is a child of a Resource- or Application-element and has the following attributes: name,
 * id.
 * 
 * @author <a href="mailto:e0426062@student.tuwien.ac.at">Andreas Meingast</a>
 * @author <a href="mailto:e0325672@student.tuwien.ac.at">Anton Korosec</a>
 */
public interface IMethodDefinition extends IMethod {

	/**
	 * An identifier for the method, required for globally defined methods, not allowed on locally embedded methods.
	 */
	public URI getId();

	public boolean setId(URI id);

	/**
	 * Indicates the HTTP method used. It is permissible to have multiple child Method-elements that have the same value
	 * of the name-attribute for a given resource; such siblings represent distinct variations of the same HTTP method
	 * and will typically have different input data.
	 */
	public HTTPMethod getName();

	public boolean setName(HTTPMethod httpMethod);

	/**
	 * A Method-element contains zero or more Doc-elements.
	 */
	public List<IDoc> getDocs();

	public boolean addDoc(IDoc doc);

	/**
	 * Descripes the input to the method as a collection of parameters and an optional resource representation.
	 */
	public IRequest getRequest();

	public boolean setRequest(IRequest request);

	/**
	 * Describes the output of the method as a collection of alternate resource representations.
	 */
	public IResponse getResponse();

	public boolean setResponse(IResponse response);

}
