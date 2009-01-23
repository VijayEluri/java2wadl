package at.ac.tuwien.infosys.java2wadl.wadl;

import java.net.URI;

/**
 * @see at.ac.tuwien.infosys.java2wadl.wadl.IMethodReference
 * 
 * @author <a href="mailto:e0426062@student.tuwien.ac.at">Andreas Meingast</a>
 * @author <a href="mailto:e0325672@student.tuwien.ac.at">Anton Korosec</a>
 */
public class MethodReference implements IMethodReference {

	private URI href;

	public URI getHref() {
		return href;
	}

	public boolean setHref(URI href) {
		this.href = href;
		return true;
	}

	@Override
	public String toString() {
		String result = "<method";

		if (href != null) {
			result += " href=\"" + href.toString() + "\"";
		}

		result += "/>" + "\n";

		return result;
	}
}
