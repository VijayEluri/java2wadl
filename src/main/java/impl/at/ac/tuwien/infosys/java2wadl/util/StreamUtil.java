package at.ac.tuwien.infosys.java2wadl.util;

import static at.ac.tuwien.infosys.java2wadl.util.AssertUtil.assertNotNull;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;

/**
 * Utility class for reading and writing streams.
 * 
 * @author <a href="mailto:e0426062@student.tuwien.ac.at">Andreas Meingast</a>
 * @author <a href="mailto:e0325672@student.tuwien.ac.at">Anton Korosec</a>
 */
public class StreamUtil {
	/**
	 * Read content from a given {@link InputStream} and return it in form of a {@link String}.
	 * 
	 * @param in
	 *            The {@link InputStream} from which the contents is read.
	 * @throws NullPointerException
	 *             If in is null.
	 * @throws RuntimeException
	 *             If an exception occurs during reading.
	 * @return The content.
	 */
	public static String read(InputStream in) {
		assertNotNull(in);

		try {
			Reader reader = new InputStreamReader(in);
			StringBuilder builder = new StringBuilder();

			char[] buffer = new char[1024];
			int len;

			while ((len = reader.read(buffer)) >= 0) {
				builder.append(buffer, 0, len);
			}

			reader.close();
			return builder.toString();
		} catch (Throwable t) {
			throw new RuntimeException(t);
		}
	}

	/**
	 * Read content from a given {@link InputStream} and write it to a given {@link OutputStream}.
	 * 
	 * @param in
	 *            The {@link InputStream}
	 * @param out
	 *            The {@link OutputStream}
	 * @throws NullPointerException
	 *             If in is null.
	 * @throws NullPointerException
	 *             If out is null.
	 * @throws RuntimeException
	 *             If an exception occurs during reading or writing.
	 */
	public static void write(InputStream in, OutputStream out) {
		assertNotNull(in);
		assertNotNull(out);

		try {
			out.write(read(in).getBytes());
		} catch (Throwable t) {
			throw new RuntimeException(t);
		}
	}
}
