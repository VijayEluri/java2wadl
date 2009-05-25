package at.ac.tuwien.infosys.wadl2java.codegen;

import java.io.FileNotFoundException;
import java.util.List;

import org.junit.Test;

import at.ac.tuwien.infosys.Assert;
import at.ac.tuwien.infosys.BaseTest;
import at.ac.tuwien.infosys.java2wadl.WadlException;
import at.ac.tuwien.infosys.wadl2java.JavaSource;

public class HelloWorldTest extends BaseTest {

	@Test
	public void testGenerate() throws FileNotFoundException, WadlException {
		String xml = loadResource("at.ac.tuwien.infosys.wadl2java.codegen.HelloWorldTest.wadl");
		String java = loadResource("at.ac.tuwien.infosys.wadl2java.codegen.HelloWorldTest.iava");
		List<JavaSource> actualSources = wadl2Java.generate(xml, "at.ac.tuwien.infosys.wadl2java.codegen");

		assertEquals(1, actualSources.size());
		Assert.assertStringEqualsIgnoreCarriageReturns(java, actualSources.get(0).toString());
	}
}
