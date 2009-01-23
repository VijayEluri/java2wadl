package at.ac.tuwien.infosys.java2wadl.wadl;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * @see at.ac.tuwien.infosys.java2wadl.wadl.ILink
 * 
 * @author <a href="mailto:e0426062@student.tuwien.ac.at">Andreas Meingast</a>
 * @author <a href="mailto:e0325672@student.tuwien.ac.at">Anton Korosec</a>
 */
public class Link implements ILink {

	private URI resource_type;
	private String rel;
	private String rev;
	private List<IDoc> docs;

	/**
	 * constructor
	 */
	public Link() {
		docs = new ArrayList<IDoc>();
	}

	public List<IDoc> getDocs() {
		return docs;
	}

	public String getRel() {
		return rel;
	}

	public URI getResource_type() {
		return resource_type;
	}

	public String getRev() {
		return rev;
	}

	public boolean addDoc(IDoc doc) {
		if (docs == null) {
			return false;
		}

		return docs.add(doc);
	}

	public boolean setRel(String rel) {
		this.rel = rel;
		return true;
	}

	public boolean setResource_type(URI resourceType) {
		this.resource_type = resourceType;
		return true;
	}

	public boolean setRev(String rev) {
		this.rev = rev;
		return true;
	}

	@Override
	public String toString() {
		String result = "<link";

		if (resource_type != null) {
			result += " resource_type=\"" + resource_type.toString() + "\"";
		}

		if ((rel != null) && !rel.equals("")) {
			result += " rel=\"" + rel + "\"";
		}

		if ((rev != null) && !rev.equals("")) {
			result += " rev=\"" + rev + "\"";
		}

		result += ">" + "\n";

		if ((docs != null) && !docs.isEmpty()) {
			for (IDoc d : docs) {
				result += d.toString();
			}
		}

		result += "</link>" + "\n";

		return result;
	}

}
