package at.ac.tuwien.infosys.java2wadl.util;

import static at.ac.tuwien.infosys.java2wadl.util.AssertUtil.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Utility class for handling Strings.
 * 
 * @author <a href="mailto:e0426062@student.tuwien.ac.at">Andreas Meingast</a>
 * @author <a href="mailto:e0325672@student.tuwien.ac.at">Anton Korosec</a>
 */
public class StringUtil {
	/**
	 * Checks if a given String is empty.
	 * 
	 * A string is empty if it is null or its length is zero.
	 * 
	 * @param s
	 *            The String
	 * @return <code>true</code> if the string is empty, otherwise <code>false</code>.
	 */
	public static boolean isEmpty(String s) {
		return s == null || s.length() == 0;
	}

	/**
	 * Remove whitespace tokens from the given String.
	 * 
	 * <code>tokens ::= ( '\n' | '\t' | 'r' | ' ' )</code>
	 * 
	 * @param s
	 *            The String.
	 * @return The cleaned String.
	 */
	public static String stripWhiteSpaces(String s) {
		return s.replaceAll("\\s", "");
	}

	/**
	 * Join the Strings from the given Array.
	 * 
	 * @param xs
	 *            Array of separated Strings.
	 * @param j
	 *            The String to join the separate Strings.
	 * @return The joined String.
	 */
	public static String join(String[] xs, String j) {
		return join(Arrays.asList(xs), j);
	}

	/**
	 * Join the Strings from the given List.
	 * 
	 * @param xs
	 *            String-List of separated Strings.
	 * @param j
	 *            The String to join the separate Strings.
	 * @return The joined String.
	 */
	public static String join(List<String> xs, String j) {
		StringBuffer sb = new StringBuffer();
		boolean first = true;

		for (String s : xs) {
			if (first) {
				sb.append(s);
				first = false;
			} else {
				sb.append(j + s);
			}
		}

		return sb.toString();
	}

	/**
	 * Split the given String by the given splitter.
	 * 
	 * @param s
	 *            The String to split.
	 * @param splitter
	 *            The String by which to split the input-String.
	 * @return The list of the splitted String-tokens.
	 */
	public static List<String> split(String s, String splitter) {
		assertNotNull(s);
		assertNotNull(splitter);

		StringTokenizer st = new StringTokenizer(s, splitter);
		List<String> tokens = new ArrayList<String>(st.countTokens());

		while (st.hasMoreTokens()) {
			tokens.add(st.nextToken());
		}

		return tokens;
	}

	public static String collectUntil(String s, char splitter) {
		if (isEmpty(s)) {
			return "";
		}

		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == splitter) {
				break;
			}

			sb.append(s.charAt(i));
		}

		return sb.toString();
	}

	public static String capitalize(String s) {
		if (isEmpty(s)) {
			return "";
		}

		String firstLetter = s.substring(0, 1).toUpperCase();
		String rest = s.length() > 0 ? s.substring(1) : "";
		
		return firstLetter + rest;
	}
}
