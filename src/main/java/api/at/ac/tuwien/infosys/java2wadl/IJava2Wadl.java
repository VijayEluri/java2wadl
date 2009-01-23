package at.ac.tuwien.infosys.java2wadl;

/**
 * @author <a href="mailto:e0426062@student.tuwien.ac.at">Andreas Meingast</a>
 * @author <a href="mailto:e0325672@student.tuwien.ac.at">Anton Korosec</a>
 */
public interface IJava2Wadl {
	/**
	 * Generate a WADL-object based on a given type. Uses "/" as the baseUri for the resource class.
	 * 
	 * @param type
	 *            The {@link Class} for which the WADL should be generated.
	 * @return A WADL object containing the XML-schemas and the actual WADL-content.
	 * @throws WadlException
	 *             If errors occur during WADL generation.
	 */
	Wadl generate(Class<?> type) throws WadlException;

	/**
	 * Generate a WADL-object based on a given type and baseUri.
	 * 
	 * @param type
	 *            The {@link Class} for which the WADL should be generated.
	 * @param baseUri
	 *            The base URI for the resource class.
	 * @return A WADL object containing the XML-schemas and the actual WADL-content.
	 * @throws WadlException
	 *             If errors occur during WADL generation.
	 */
	Wadl generate(Class<?> type, String baseUri) throws WadlException;
}
