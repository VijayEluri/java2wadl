package at.ac.tuwien.infosys.java2wadl.wadl;

import java.util.ArrayList;
import java.util.List;

/**
 * @see at.ac.tuwien.infosys.java2wadl.wadl.IOption
 * 
 * @author <a href="mailto:e0426062@student.tuwien.ac.at">Andreas Meingast</a>
 * @author <a href="mailto:e0325672@student.tuwien.ac.at">Anton Korosec</a>
 */
public class Option implements IOption {

	private String value;
	private List<IDoc> docs;

	/**
	 * constructor
	 */
	public Option() {
		docs = new ArrayList<IDoc>();
	}

	public List<IDoc> getDocs() {
		return docs;
	}

	public String getValue() {
		return value;
	}

	public boolean addDoc(IDoc doc) {
		if (docs == null) {
			return false;
		}

		return docs.add(doc);
	}

	public boolean setValue(String value) {
		this.value = value;
		return true;
	}

	@Override
	public String toString() {
		String result = "<option";

		if ((value != null) && !value.isEmpty()) {
			result += " value=\"" + value + "\"";
		}

		result += ">" + "\n";

		if ((docs != null) && !docs.isEmpty()) {
			for (IDoc d : docs) {
				result += d.toString();
			}
		}

		result += "</option>" + "\n";

		return result;
	}

}
