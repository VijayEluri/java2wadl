package at.ac.tuwien.infosys.java2wadl.util;

import java.io.FileNotFoundException;

import org.junit.Test;

import at.ac.tuwien.infosys.BaseTest;

public class ResourceUtilTest extends BaseTest {

	@Test
	public void testLoadFile() throws FileNotFoundException {
		assertEquals("1", loadResource("testfile.txt"));
	}

	@Test
	public void testLoadNonExistentFile() {
		try {
			loadResource("123");
			fail();
		} catch (FileNotFoundException e) {}
	}
}
