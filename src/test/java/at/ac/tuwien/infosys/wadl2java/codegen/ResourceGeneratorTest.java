package at.ac.tuwien.infosys.wadl2java.codegen;

import java.io.FileNotFoundException;
import java.util.List;

import org.junit.Test;

import at.ac.tuwien.infosys.Assert;
import at.ac.tuwien.infosys.BaseTest;
import at.ac.tuwien.infosys.java2wadl.WadlException;
import at.ac.tuwien.infosys.java2wadl.wadl.IApplication;
import at.ac.tuwien.infosys.wadl2java.JavaSource;
import at.ac.tuwien.infosys.wadl2java.xml.ApplicationParser;

public class ResourceGeneratorTest extends BaseTest {

	@Test
	public void testGenerateParamMethod() throws FileNotFoundException, WadlException {
		String xml = loadResource("at.ac.tuwien.infosys.wadl2java.codegen.ResourceGeneratorTestCase.txt");
		String java = loadResource("at.ac.tuwien.infosys.wadl2java.codegen.ResourceGeneratorTestCase.iava");
		IApplication application = new ApplicationParser().parse(xml);
		List<JavaSource> sources = new ResourceGenerator(application, "test.package").generate();

		assertEquals(1, sources.size());
		Assert.assertStringEqualsIgnoreCarriageReturns(java, sources.get(0).toString());
	}
}