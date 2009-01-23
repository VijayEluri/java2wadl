package at.ac.tuwien.infosys.java2wadl.wadl;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * @see at.ac.tuwien.infosys.java2wadl.wadl.IInclude
 * 
 * @author <a href="mailto:e0426062@student.tuwien.ac.at">Andreas Meingast</a>
 * @author <a href="mailto:e0325672@student.tuwien.ac.at">Anton Korosec</a>
 */
public class Include implements IInclude {

	private URI href;
	private List<IDoc> docs;

	/**
	 * constructor
	 */
	public Include() {
		docs = new ArrayList<IDoc>();
	}

	public List<IDoc> getDocs() {
		return docs;
	}

	public URI getHref() {
		return href;
	}

	public boolean addDoc(IDoc doc) {
		if (docs == null) {
			return false;
		}

		return docs.add(doc);
	}

	public boolean setHref(URI href) {
		this.href = href;
		return true;
	}

	@Override
	public String toString() {
		String result = "<include";

		if (href != null) {
			result += " href=\"" + href.toString() + "\"";
		}

		result += ">" + "\n";

		if ((docs != null) && !docs.isEmpty()) {
			for (IDoc d : docs) {
				result += d.toString();
			}
		}

		result += "</include>" + "\n";

		return result;
	}

}
