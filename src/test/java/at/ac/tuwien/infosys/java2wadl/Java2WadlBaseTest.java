package at.ac.tuwien.infosys.java2wadl;

import static at.ac.tuwien.infosys.java2wadl.util.ResourceUtil.loadResource;
import static at.ac.tuwien.infosys.java2wadl.util.StringUtil.stripWhiteSpaces;

import java.io.FileNotFoundException;

import org.custommonkey.xmlunit.XMLTestCase;
import org.junit.Before;
import org.junit.Test;

/**
 * @author <a href="mailto:e0426062@student.tuwien.ac.at">Andreas Meingast</a>
 * @author <a href="mailto:e0325672@student.tuwien.ac.at">Anton Korosec</a>
 */
public class Java2WadlBaseTest extends XMLTestCase {

	protected IJava2Wadl java2Wadl;

	@Before
	@Override
	public void setUp() {
		java2Wadl = new Java2Wadl();
	}

	@Test
	public void testLoadFile() throws FileNotFoundException {
		assertEquals("1", loadResource(getClass().getClassLoader(), "testfile.txt"));
	}

	@Test
	public void testLoadNonExistentFile() {
		try {
			loadResource(getClass().getClassLoader(), "123");
			fail();
		} catch (FileNotFoundException e) {}
	}

	@Test
	public void testStripWhiteSpaces() {
		assertEquals("aaaa", stripWhiteSpaces("\na\t \r   a \t\na\ra"));
	}
}
