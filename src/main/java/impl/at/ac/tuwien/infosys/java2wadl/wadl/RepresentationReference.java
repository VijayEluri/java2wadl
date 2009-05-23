package at.ac.tuwien.infosys.java2wadl.wadl;

import java.net.URI;

import at.ac.tuwien.infosys.java2wadl.WadlException;
import at.ac.tuwien.infosys.java2wadl.util.UriUtil;

/**
 * @see at.ac.tuwien.infosys.java2wadl.wadl.IRepresentationReference
 * 
 * @author <a href="mailto:e0426062@student.tuwien.ac.at">Andreas Meingast</a>
 * @author <a href="mailto:e0325672@student.tuwien.ac.at">Anton Korosec</a>
 */
public class RepresentationReference implements IRepresentationReference {

	private URI href;

	public RepresentationReference() {}
	
	public RepresentationReference(String uri) throws WadlException {
		this();
		setHref(UriUtil.createUri(uri));
	}
	
	public URI getHref() {
		return href;
	}

	public boolean setHref(URI href) {
		this.href = href;
		return true;
	}

	@Override
	public String toString() {
		String result = "<representation";

		if (href != null) {
			result += " href=\"" + href.toString() + "\"";
		}

		result += "/>" + "\n";

		return result;
	}
}
