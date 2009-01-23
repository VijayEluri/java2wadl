package at.ac.tuwien.infosys.java2wadl.wadl;

/**
 * @see at.ac.tuwien.infosys.java2wadl.wadl.IDoc
 * 
 * @author <a href="mailto:e0426062@student.tuwien.ac.at">Andreas Meingast</a>
 * @author <a href="mailto:e0325672@student.tuwien.ac.at">Anton Korosec</a>
 */
public class Doc implements IDoc {

	private String lang;
	private String title;
	private String content;

	public String getLang() {
		return lang;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public boolean setLang(String lang) {
		this.lang = lang;
		return true;
	}

	public boolean setTitle(String title) {
		this.title = title;
		return true;
	}

	public boolean setContent(String content) {
		this.content = content;
		return true;
	}

	@Override
	public String toString() {
		String result = "<doc";

		if ((lang != null) && !lang.equals("")) {
			result += " xml:lang=\"" + lang + "\"";
		}

		if ((title != null) && !title.equals("")) {
			result += " title=\"" + title + "\"";
		}

		result += ">" + "\n";
		result += content;
		result += "</doc>" + "\n";

		return result;
	}

}
