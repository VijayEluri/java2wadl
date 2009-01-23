package at.ac.tuwien.infosys.java2wadl.util;

import static at.ac.tuwien.infosys.java2wadl.util.AssertUtil.assertNotEmpty;
import static at.ac.tuwien.infosys.java2wadl.util.AssertUtil.assertNotNull;
import static at.ac.tuwien.infosys.java2wadl.util.StreamUtil.read;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Utility class for handling resources (loading files, etc)
 * 
 * @author <a href="mailto:e0426062@student.tuwien.ac.at">Andreas Meingast</a>
 * @author <a href="mailto:e0325672@student.tuwien.ac.at">Anton Korosec</a>
 */
public class ResourceUtil {
	/**
	 * Load a resource either from file-system or (if it does not exist there) from the class-path.
	 * 
	 * @param classLoader
	 *            The {@link ClassLoader} that is used to retrieve the file from the class-path.
	 * @param fileName
	 *            The name of the file.
	 * @throws FileNotFoundException
	 *             If the file does not exist at the given location or in the class-path.
	 * @throws NullPointerException
	 *             If classLoader is null.
	 * @throws RuntimeException
	 *             If fileName is empty.
	 * @return The content of the file.
	 */
	public static String loadResource(ClassLoader classLoader, String fileName) throws FileNotFoundException {
		assertNotNull(classLoader);
		assertNotEmpty(fileName);

		File file = new File(fileName);

		return read(file.exists() ? new FileInputStream(file) : loadResourceFromClasspath(classLoader, fileName));
	}

	/**
	 * Load a resource identified by fileName from the class-path which is accessed with a given {@link ClassLoader}.
	 * 
	 * @param classLoader
	 *            The {@link ClassLoader} that is used to retrieve the file from the class-path.
	 * @param fileName
	 *            The name of the file.
	 * @throws FileNotFoundException
	 *             If the file is not existent in the class-path.
	 * @throws NullPointerException
	 *             If classLoader is null.
	 * @throws RuntimeException
	 *             If fileName is null.
	 * @return An {@link InputStream} pointing to the content of the file.
	 */
	public static InputStream loadResourceFromClasspath(ClassLoader classLoader, String fileName)
			throws FileNotFoundException {
		assertNotNull(classLoader);
		assertNotEmpty(fileName);

		InputStream inputStream = classLoader.getResourceAsStream("META-INF/" + fileName);

		if (inputStream != null) {
			return inputStream;
		}

		throw new FileNotFoundException(fileName);
	}
}
