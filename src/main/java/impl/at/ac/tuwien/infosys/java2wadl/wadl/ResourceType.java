package at.ac.tuwien.infosys.java2wadl.wadl;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * @see at.ac.tuwien.infosys.java2wadl.wadl.IResourceType
 * 
 * @author <a href="mailto:e0426062@student.tuwien.ac.at">Andreas Meingast</a>
 * @author <a href="mailto:e0325672@student.tuwien.ac.at">Anton Korosec</a>
 */
public class ResourceType implements IResourceType {

	private URI id;
	private List<IDoc> docs;
	private List<IParam> params;
	private List<IMethod> methods;

	/**
	 * constructor
	 */
	public ResourceType() {
		docs = new ArrayList<IDoc>();
		params = new ArrayList<IParam>();
		methods = new ArrayList<IMethod>();
	}

	public List<IDoc> getDocs() {
		return docs;
	}

	public URI getId() {
		return id;
	}

	public List<IMethod> getMethods() {
		return methods;
	}

	public List<IParam> getParams() {
		return params;
	}

	public boolean addDoc(IDoc doc) {
		if (docs == null) {
			return false;
		}

		return docs.add(doc);
	}

	public boolean addMethod(IMethod method) {
		if (methods == null) {
			return false;
		}

		return methods.add(method);
	}

	public boolean addParam(IParam param) {
		if (params == null) {
			return false;
		}

		return params.add(param);
	}

	public boolean setId(URI id) {
		this.id = id;
		return true;
	}

	@Override
	public String toString() {
		String result = "<resource_type";

		if (id != null) {
			result += " id=\"" + id.toString() + "\"";
		}

		result += ">" + "\n";

		if ((docs != null) && !docs.isEmpty()) {
			for (IDoc d : docs) {
				result += d.toString();
			}
		}

		if ((params != null) && !params.isEmpty()) {
			for (IParam p : params) {
				result += p.toString();
			}
		}

		if ((methods != null) && !methods.isEmpty()) {
			for (IMethod m : methods) {
				result += m.toString();
			}
		}

		result += "</resource_type>" + "\n";

		return result;
	}

}
