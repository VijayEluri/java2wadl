package at.ac.tuwien.infosys.java2wadl.wadl;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import at.ac.tuwien.infosys.java2wadl.util.StringUtil;

/**
 * @see at.ac.tuwien.infosys.java2wadl.wadl.IResource
 * 
 * @author <a href="mailto:e0426062@student.tuwien.ac.at">Andreas Meingast</a>
 * @author <a href="mailto:e0325672@student.tuwien.ac.at">Anton Korosec</a>
 */
public class Resource implements IResource {

	private URI id;
	private String path;
	private List<URI> type;
	private String queryType;
	private final List<IDoc> docs;
	private final List<IParam> params;
	private final List<IMethod> methods;
	private final List<IResource> resources;

	/**
	 * constructor
	 */
	public Resource() {
		type = new ArrayList<URI>();
		docs = new ArrayList<IDoc>();
		params = new ArrayList<IParam>();
		methods = new ArrayList<IMethod>();
		resources = new ArrayList<IResource>();
		// queryType = "application/x-www-form-urlencoded";
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

	public String getPath() {
		return path;
	}

	public String getQueryType() {
		return queryType;
	}

	public List<IResource> getResources() {
		return resources;
	}

	public List<URI> getType() {
		return type;
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

	public boolean addResource(IResource resource) {
		if (resources == null) {
			return false;
		}

		return resources.add(resource);
	}

	public boolean addType(URI type_) {
		if (this.type == null) {
			return false;
		}

		return this.type.add(type_);
	}

	public boolean setId(URI id) {
		if (!StringUtil.isEmpty(id.toString())) {
			this.id = id;
		}
		return true;
	}

	public boolean setPath(String path) {
		this.path = path;
		return true;
	}

	public boolean setQueryType(String queryType) {
		this.queryType = queryType;
		return true;
	}

	public boolean setType(List<URI> type) {
		this.type = type;
		return true;
	}

	@Override
	public String toString() {
		String result = "<resource";

		if (id != null) {
			result += " id=\"" + id.toString() + "\"";
		}

		if ((path != null) && !path.equals("")) {
			result += " path=\"" + path + "\"";
		}

		if ((type != null) && !type.isEmpty()) {
			result += " type=\"";

			int i = 0;
			for (; i < (type.size() - 1); i++) {
				result += type.get(i).toString() + " ";
			}

			result += type.get(i).toString();
			result += "\"";
		}

		if ((queryType != null) && !queryType.equals("")) {
			result += " queryType=\"" + queryType + "\"";
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

		if ((resources != null) && !resources.isEmpty()) {
			for (IResource r : resources) {
				result += r.toString();
			}
		}

		result += "</resource>" + "\n";

		return result;
	}

}
