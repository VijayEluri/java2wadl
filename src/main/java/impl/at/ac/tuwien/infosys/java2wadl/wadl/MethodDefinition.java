package at.ac.tuwien.infosys.java2wadl.wadl;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * @see at.ac.tuwien.infosys.java2wadl.wadl.IMethodDefinition
 * 
 * @author <a href="mailto:e0426062@student.tuwien.ac.at">Andreas Meingast</a>
 * @author <a href="mailto:e0325672@student.tuwien.ac.at">Anton Korosec</a>
 */
public class MethodDefinition implements IMethodDefinition {

	private URI id;
	private HTTPMethod name;
	private final List<IDoc> docs;
	private IRequest request;
	private IResponse response;

	/**
	 * constructor
	 */
	public MethodDefinition() {
		docs = new ArrayList<IDoc>();
	}

	public List<IDoc> getDocs() {
		return docs;
	}

	public URI getId() {
		return id;
	}

	public HTTPMethod getName() {
		return name;
	}

	public IRequest getRequest() {
		return request;
	}

	public IResponse getResponse() {
		return response;
	}

	public boolean addDoc(IDoc doc) {
		if (docs == null) {
			return false;
		}

		return docs.add(doc);
	}

	public boolean setId(URI id) {
		this.id = id;
		return true;
	}

	public boolean setName(HTTPMethod httpMethod) {
		this.name = httpMethod;
		return true;
	}

	public boolean setRequest(IRequest request) {
		this.request = request;
		return true;
	}

	public boolean setResponse(IResponse response) {
		this.response = response;
		return true;
	}

	@Override
	public String toString() {
		String result = "<method";

		if (name != null) {
			result += " name=\"" + name.toString() + "\"";
		}

		if (id != null) {
			result += " id=\"" + id.toString() + "\"";
		}

		result += ">" + "\n";

		if ((docs != null) && !docs.isEmpty()) {
			for (IDoc d : docs) {
				result += d.toString();
			}
		}

		if (request != null && !request.isEmpty()) {
			result += request.toString();
		}

		if (response != null && !response.isEmpty()) {
			result += response.toString();
		}

		result += "</method>" + "\n";

		return result;
	}

}
