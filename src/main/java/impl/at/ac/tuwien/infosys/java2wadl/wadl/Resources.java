package at.ac.tuwien.infosys.java2wadl.wadl;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import at.ac.tuwien.infosys.java2wadl.WadlException;
import at.ac.tuwien.infosys.java2wadl.util.UriUtil;

/**
 * @see at.ac.tuwien.infosys.java2wadl.wadl.IResources
 * 
 * @author <a href="mailto:e0426062@student.tuwien.ac.at">Andreas Meingast</a>
 * @author <a href="mailto:e0325672@student.tuwien.ac.at">Anton Korosec</a>
 */
public class Resources implements IResources {

	private URI base;
	private final List<IDoc> docs;
	private final List<IResource> resources;

	/**
	 * constructor
	 */
	public Resources() {
		docs = new ArrayList<IDoc>();
		resources = new ArrayList<IResource>();
	}
	
	public Resources(String uri) throws WadlException {
		this();
		setBase(UriUtil.createUri(uri));
	}

	public URI getBase() {
		return base;
	}

	public List<IDoc> getDocs() {
		return docs;
	}

	public List<IResource> getResources() {
		return resources;
	}

	public boolean addDoc(IDoc doc) {
		if (docs == null) {
			return false;
		}

		return docs.add(doc);
	}

	public boolean addResource(IResource resource) {
		if (resources == null) {
			return false;
		}

		return resources.add(resource);
	}

	public boolean setBase(URI base) {
		this.base = base;
		return true;
	}

	@Override
	public String toString() {
		String result = "<resources";

		if (base != null) {
			result += " base=\"" + base.toString() + "\"";
		}

		result += ">" + "\n";

		if ((docs != null) && !docs.isEmpty()) {
			for (IDoc d : docs) {
				result += d.toString();
			}
		}

		if ((resources != null) && !resources.isEmpty()) {
			for (IResource r : resources) {
				result += r.toString();
			}
		}

		result += "</resources>" + "\n";

		return result;
	}
}
