package at.ac.tuwien.infosys.wadl2java.codegen;

import java.io.FileNotFoundException;
import java.util.List;

import org.junit.Test;

import at.ac.tuwien.infosys.BaseTest;
import at.ac.tuwien.infosys.java2wadl.WadlException;
import at.ac.tuwien.infosys.java2wadl.wadl.IApplication;
import at.ac.tuwien.infosys.java2wadl.wadl.IMethod;
import at.ac.tuwien.infosys.java2wadl.wadl.IMethodDefinition;
import at.ac.tuwien.infosys.java2wadl.wadl.IMethodReference;
import at.ac.tuwien.infosys.java2wadl.wadl.IResource;
import at.ac.tuwien.infosys.wadl2java.xml.ApplicationParser;

public class MethodResolverTest extends BaseTest {
	@Test
	public void testResolveMethod() throws FileNotFoundException, WadlException {
		String xml = loadResource("at.ac.tuwien.infosys.wadl2java.codegen.MethodResolverTestCase.txt");
		IApplication application = new ApplicationParser().parse(xml);
		List<IResource> resources = application.getResources().getResources();

		assertEquals(1, resources.size());
		IResource resource = resources.get(0);
		List<IMethod> methods = resource.getMethods();

		assertEquals(1, methods.size());
		IMethod method = methods.get(0);
		IMethodDefinition methodDefinition = (IMethodDefinition) application.getMethods().get(0);

		assertTrue(method instanceof IMethodReference);
		assertNotNull(methodDefinition);
		assertEquals(methodDefinition, new MethodResolver().resolve(application, method));
	}

	@Test
	public void testResolveUnresolvableMethodAndFail() throws FileNotFoundException, WadlException {
		String xml = loadResource("at.ac.tuwien.infosys.wadl2java.codegen.MethodResolverTestCase2.txt");
		IApplication application = new ApplicationParser().parse(xml);
		List<IResource> resources = application.getResources().getResources();

		assertEquals(1, resources.size());
		IResource resource = resources.get(0);
		List<IMethod> methods = resource.getMethods();

		assertEquals(1, methods.size());
		IMethod method = methods.get(0);
		
		try {
			new MethodResolver().resolve(application, method);
			fail();
		} catch (WadlException e) {
			assertEquals(e.getMessage(), "Unable to resolve method-reference for: #HelloWorldResource_getMessage_");
		}
	}
}
