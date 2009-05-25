package at.ac.tuwien.infosys;

import static org.junit.Assert.*;

public class Assert {	
	public static void assertStringEqualsIgnoreCarriageReturns(String a, String b) {
		assertEquals(a.replaceAll("\r", ""), b.replaceAll("\r", ""));
	}
}
