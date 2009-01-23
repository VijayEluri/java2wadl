package at.ac.tuwien.infosys.java2wadl.wadl;

import java.util.ArrayList;
import java.util.List;

/**
 * @see at.ac.tuwien.infosys.java2wadl.wadl.IGrammars
 * 
 * @author <a href="mailto:e0426062@student.tuwien.ac.at">Andreas Meingast</a>
 * @author <a href="mailto:e0325672@student.tuwien.ac.at">Anton Korosec</a>
 */
public class Grammars implements IGrammars {

	private List<IDoc> docs;
	private List<IInclude> includes;

	/**
	 * constructor
	 */
	public Grammars() {
		docs = new ArrayList<IDoc>();
		includes = new ArrayList<IInclude>();
	}

	public List<IDoc> getDocs() {
		return docs;
	}

	public List<IInclude> getIncludes() {
		return includes;
	}

	public boolean addDoc(IDoc doc) {
		if (docs == null) {
			return false;
		}

		return docs.add(doc);
	}

	public boolean addInclude(IInclude include) {
		if (includes == null) {
			return false;
		}

		for (IInclude myInclude : includes) {
			if (myInclude.getHref().equals(include.getHref())) {
				return true;
			}
		}

		return includes.add(include);
	}

	@Override
	public String toString() {
		String result = "<grammars>" + "\n";

		if ((docs != null) && !docs.isEmpty()) {
			for (IDoc d : docs) {
				result += d.toString();
			}
		}

		if ((includes != null) && !includes.isEmpty()) {
			for (IInclude i : includes) {
				result += i.toString();
			}
		}

		result += "</grammars>" + "\n";

		return result;
	}
}
