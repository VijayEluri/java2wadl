package at.ac.tuwien.infosys.java2wadl.wadl;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * @see at.ac.tuwien.infosys.java2wadl.wadl.IRepresentationDefinition
 * 
 * @author <a href="mailto:e0426062@student.tuwien.ac.at">Andreas Meingast</a>
 * @author <a href="mailto:e0325672@student.tuwien.ac.at">Anton Korosec</a>
 */
public class RepresentationDefinition implements IRepresentationDefinition {

	private URI id;
	private String mediaType;
	private String element;
	private List<URI> profiles;
	private List<Integer> status;
	private List<IDoc> docs;
	private List<IParam> params;

	/**
	 * constructor
	 */
	public RepresentationDefinition() {
		profiles = new ArrayList<URI>();
		status = new ArrayList<Integer>();
		docs = new ArrayList<IDoc>();
		params = new ArrayList<IParam>();
	}

	public List<IDoc> getDocs() {
		return docs;
	}

	public String getElement() {
		return element;
	}

	public URI getId() {
		return id;
	}

	public String getMediaType() {
		return mediaType;
	}

	public List<IParam> getParams() {
		return params;
	}

	public List<URI> getProfiles() {
		return profiles;
	}

	public List<Integer> getStatus() {
		return status;
	}

	public boolean addDoc(IDoc doc) {
		if (docs == null) {
			return false;
		}

		return docs.add(doc);
	}

	public boolean addParam(IParam param) {
		if (params == null) {
			return false;
		}

		return params.add(param);
	}

	public boolean addProfile(URI profile) {
		if (profiles == null) {
			return false;
		}

		return profiles.add(profile);
	}

	public boolean addStatus(Integer status_) {
		if (this.status == null) {
			return false;
		}

		return this.status.add(status_);
	}

	public boolean setElement(String element) {
		this.element = element;
		return true;
	}

	public boolean setId(URI id) {
		this.id = id;
		return true;
	}

	public boolean setMediaType(String mediaType) {
		this.mediaType = mediaType;
		return true;
	}

	public boolean setProfiles(List<URI> profiles) {
		this.profiles = profiles;
		return true;
	}

	public boolean setStatus(List<Integer> status) {
		this.status = status;
		return true;
	}

	@Override
	public String toString() {
		String result = "<representation";

		if (id != null) {
			result += " id=\"" + id.toString() + "\"";
		}

		if ((mediaType != null) && !mediaType.equals("")) {
			result += " mediaType=\"" + mediaType + "\"";
		}

		if ((element != null) && !element.equals("")) {
			result += " element=\"" + element + "\"";
		}

		if ((profiles != null) && !profiles.isEmpty()) {
			result += " profile=\"";

			int i = 0;
			for (; i < (profiles.size() - 1); i++) {
				result += profiles.get(i).toString() + " ";
			}

			result += profiles.get(i).toString();
			result += "\"";
		}

		if ((status != null) && !status.isEmpty()) {
			result += " status=\"";

			int i = 0;
			for (; i < (status.size() - 1); i++) {
				result += status.get(i).toString() + " ";
				// is the status-code list really separated by " "? (no info in wadl-specification)
			}
			result += status.get(i).toString();

			result += "\"";
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

		result += "</representation>" + "\n";

		return result;
	}

}
