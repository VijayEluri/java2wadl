package at.ac.tuwien.infosys.java2wadl.util;

import static at.ac.tuwien.infosys.java2wadl.util.AssertUtil.assertNotNull;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import at.ac.tuwien.infosys.java2wadl.WadlException;

/**
 * Utility class for handling URIs.
 * 
 * @author <a href="mailto:e0426062@student.tuwien.ac.at">Andreas Meingast</a>
 * @author <a href="mailto:e0325672@student.tuwien.ac.at">Anton Korosec</a>
 */
public class UriUtil {

	/**
	 * Create a URI from the given String.
	 * 
	 * @param uri
	 *            The uri for the URI-instance.
	 * @return The created URI.
	 * @throws WadlException
	 */
	public static URI createUri(String uri) throws WadlException {
		assertNotNull(uri);

		try {
			return new URI(uri);
		} catch (URISyntaxException e) {
			throw new WadlException(e);
		}
	}

	/**
	 * Split the given URI-String into tokens. The splitter is "/".
	 * 
	 * @param uri
	 *            The URI-String to split.
	 * @return The list of splitted String-tokens.
	 */
	public static List<String> splitUri(String uri) {
		return StringUtil.split(uri, "/");
	}
}
