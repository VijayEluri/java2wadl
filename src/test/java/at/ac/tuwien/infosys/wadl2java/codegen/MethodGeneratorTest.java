package at.ac.tuwien.infosys.wadl2java.codegen;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import at.ac.tuwien.infosys.BaseTest;
import at.ac.tuwien.infosys.java2wadl.WadlException;
import at.ac.tuwien.infosys.java2wadl.wadl.IApplication;
import at.ac.tuwien.infosys.java2wadl.wadl.IMethod;
import at.ac.tuwien.infosys.java2wadl.wadl.IMethodDefinition;
import at.ac.tuwien.infosys.java2wadl.wadl.IResource;
import at.ac.tuwien.infosys.wadl2java.xml.ApplicationParser;

public class MethodGeneratorTest extends BaseTest {

	@Test
	public void testGenerateMethod() throws FileNotFoundException, WadlException {
		String xml = loadResource("at.ac.tuwien.infosys.wadl2java.codegen.MethodGeneratorTestCase.txt");
		String java = loadResource("at.ac.tuwien.infosys.wadl2java.codegen.MethodGeneratorTestCase.iava");
		IApplication application = new ApplicationParser().parse(xml);
		List<IResource> resources = application.getResources().getResources();

		assertEquals(1, resources.size());
		IResource resource = resources.get(0);

		List<IMethod> methods = resource.getMethods();

		assertEquals(1, methods.size());
		IMethod method = methods.get(0);

		assertTrue(method instanceof IMethodDefinition);
		assertEquals(java, generateMethod(application, (IMethodDefinition) method));
	}

	private String generateMethod(IApplication application, IMethodDefinition method) throws WadlException {
		List<String> resourcePaths = Arrays.asList(new String[] { "extraNamespace", "{id}" });

		return new MethodGenerator(application).generate(resourcePaths, method);
	}
}
