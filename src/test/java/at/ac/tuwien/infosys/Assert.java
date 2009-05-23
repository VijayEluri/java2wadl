package at.ac.tuwien.infosys;

import static org.junit.Assert.*;

public class Assert {
	public static void assertEqualsIgnoreWhiteSpace(String a, String b) {
		assertEquals(a.replaceAll("\\s+", ""), b.replaceAll("\\s+", ""));
	}
}
