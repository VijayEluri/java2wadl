package at.ac.tuwien.infosys.wadl2java.helloworld;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import at.ac.tuwien.infosys.java2wadl.WadlException;
import at.ac.tuwien.infosys.wadl2java.JavaSource;
import at.ac.tuwien.infosys.wadl2java.Wadl2JavaBaseTest;

public class HelloWorldTest extends Wadl2JavaBaseTest {

	@Test
	public void testGenerate() throws FileNotFoundException, WadlException {
		List<JavaSource> shouldBe = new ArrayList<JavaSource>();
		List<JavaSource> actualSources = wadl2Java.generate(loadWadl(), getClass().getPackage().toString());

		shouldBe.add(new JavaSource("HelloWorldResource", loadResource()));
		assertEquals(shouldBe, actualSources);
	}

	protected String loadWadl() throws FileNotFoundException {
		return loadResource(getClass().getCanonicalName() + ".wadl");
	}

	protected String loadResource() throws FileNotFoundException {
		return loadResource(getClass().getCanonicalName() + ".iava");
	}
}
