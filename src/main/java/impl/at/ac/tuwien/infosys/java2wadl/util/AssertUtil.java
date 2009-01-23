package at.ac.tuwien.infosys.java2wadl.util;

import static at.ac.tuwien.infosys.java2wadl.util.StringUtil.isEmpty;

/**
 * @author <a href="mailto:e0426062@student.tuwien.ac.at">Andreas Meingast</a>
 * @author <a href="mailto:e0325672@student.tuwien.ac.at">Anton Korosec</a>
 */
public class AssertUtil {
	/**
	 * Assert that a given {@link Object} o is not null.
	 * 
	 * @param o
	 *            The object.
	 * @throws NullPointerException
	 *             If o is null.
	 */
	public static void assertNotNull(Object o) {
		assertCondition(o == null, new NullPointerException());
	}

	/**
	 * Assert that a given Array is not null and not empty.
	 * 
	 * @param os
	 *            The array.
	 */
	public static void assertNotEmpty(Object[] os) {
		assertNotNull(os);
		assertCondition(os.length == 0, "Array must not be empty");
	}

	/**
	 * Assert that a given {@link String} s is not empty.
	 * 
	 * @see at.ac.tuwien.infosys.java2wadl.util.StringUtil#isEmpty(String)
	 * @param s
	 *            The {@link String}.
	 * @throws RuntimeException
	 *             If the String is empty.
	 */
	public static void assertNotEmpty(String s) {
		assertCondition(isEmpty(s), s + " must not be empty");
	}

	/**
	 * Assert that a given condition holds.
	 * 
	 * @param condition
	 *            The condition.
	 * @param msg
	 *            The message that is passed to the RuntimeException.
	 * @throws RuntimeException
	 *             If the condition does not hold.
	 */
	public static void assertCondition(boolean condition, String msg) {
		assertCondition(condition, new RuntimeException(msg));
	}

	/**
	 * Assert that a given condition holds, otherwise throw e.
	 * 
	 * @param <T>
	 * @param condition
	 *            The condition.
	 * @param e
	 *            The exception that is thrown if the condition does not hold.
	 */
	private static <T extends RuntimeException> void assertCondition(boolean condition, T e) {
		if (condition) {
			throw e;
		}
	}
}
