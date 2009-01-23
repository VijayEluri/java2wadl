package at.ac.tuwien.infosys.java2wadl.wadl;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * @see at.ac.tuwien.infosys.java2wadl.wadl.IParam
 * 
 * @author <a href="mailto:e0426062@student.tuwien.ac.at">Andreas Meingast</a>
 * @author <a href="mailto:e0325672@student.tuwien.ac.at">Anton Korosec</a>
 */
public class Param implements IParam {

	private URI id;
	private String name;
	private ParamStyle style;
	private String type;
	private String default_;
	private String path;
	private Boolean required;
	private Boolean repeating;
	private String fixed;
	private final List<IDoc> docs;
	private final List<IOption> options;
	private ILink link;

	/**
	 * constructor
	 */
	public Param() {
		docs = new ArrayList<IDoc>();
		options = new ArrayList<IOption>();
		// type = Consts.xml_schema_ns_prefix + "string";
	}

	public String getDefault() {
		return default_;
	}

	public List<IDoc> getDocs() {
		return docs;
	}

	public String getFixed() {
		return fixed;
	}

	public URI getId() {
		return id;
	}

	public ILink getLink() {
		return link;
	}

	public String getName() {
		return name;
	}

	public List<IOption> getOptions() {
		return options;
	}

	public String getPath() {
		return path;
	}

	public Boolean getRepeating() {
		return repeating;
	}

	public Boolean getRequired() {
		return required;
	}

	public ParamStyle getStyle() {
		return style;
	}

	public String getType() {
		return type;
	}

	public boolean addDoc(IDoc doc) {
		if (docs == null) {
			return false;
		}

		return docs.add(doc);
	}

	public boolean addOption(IOption option) {
		if (options == null) {
			return false;
		}

		return options.add(option);
	}

	public boolean setDefault(String default_) {
		this.default_ = default_;
		return true;
	}

	public boolean setFixed(String fixed) {
		this.fixed = fixed;
		return true;
	}

	public boolean setId(URI id) {
		this.id = id;
		return true;
	}

	public boolean setLink(ILink link) {
		this.link = link;
		return true;
	}

	public boolean setName(String name) {
		this.name = name;
		return true;
	}

	public boolean setPath(String path) {
		this.path = path;
		return true;
	}

	public boolean setRepeating(Boolean repeating) {
		this.repeating = repeating;
		return true;
	}

	public boolean setRequired(Boolean required) {
		this.required = required;
		return true;
	}

	public boolean setStyle(ParamStyle paramStyle) {
		this.style = paramStyle;
		return true;
	}

	public boolean setType(String type) {
		this.type = type;
		return true;
	}

	@Override
	public String toString() {
		String result = "<param";

		if (id != null) {
			result += " id=\"" + id.toString() + "\"";
		}

		if ((name != null) && !name.equals("")) {
			result += " name=\"" + name + "\"";
		}

		if (style != null) {
			result += " style=\"" + style.toString() + "\"";
		}

		if ((type != null) && !type.equals("")) {
			result += " type=\"" + type + "\"";
		}

		if ((default_ != null) && !default_.equals("")) {
			result += " default=\"" + default_ + "\"";
		}

		if ((path != null) && !path.equals("")) {
			result += " path=\"" + path + "\"";
		}

		if (required != null) {
			result += " required=\"" + required.toString() + "\"";
		}

		if (repeating != null) {
			result += " repeating=\"" + repeating.toString() + "\"";
		}

		if ((fixed != null) && !fixed.equals("")) {
			result += " fixed=\"" + fixed + "\"";
		}

		result += ">" + "\n";

		if ((docs != null) && !docs.isEmpty()) {
			for (IDoc d : docs) {
				result += d.toString();
			}
		}

		if ((options != null) && !options.isEmpty()) {
			for (IOption o : options) {
				result += o.toString();
			}
		}

		if (link != null) {
			result += link.toString();
		}

		result += "</param>" + "\n";

		return result;
	}

}
