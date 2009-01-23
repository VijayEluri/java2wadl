package at.ac.tuwien.infosys.java2wadl;

import java.util.Map;

/**
 * A wrapper class that contains the generated WADL String and referenced XML schemas.
 * 
 * @author <a href="mailto:e0426062@student.tuwien.ac.at">Andreas Meingast</a>
 * @author <a href="mailto:e0325672@student.tuwien.ac.at">Anton Korosec</a>
 */
public class Wadl {
	private final Map<String, String> schemas;

	private final String wadlContent;

	public Wadl(String wadlContent, Map<String, String> schemas) {
		this.wadlContent = wadlContent;
		this.schemas = schemas;
	}

	/**
	 * Returns a {@link Map} where its keys point to the filenames of the generated schemas and its values are the
	 * content of the filenames.
	 * 
	 * @return The {@link Map} containing filenames and contents of the generated schemas.
	 */
	public Map<String, String> getSchemas() {
		return schemas;
	}

	/**
	 * Generate a WADL-file based on a given type
	 * 
	 * @return The contents of the WADL-file.
	 */
	public String getWadlContent() {
		return wadlContent;
	}
}
