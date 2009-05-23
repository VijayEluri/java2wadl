package at.ac.tuwien.infosys.wadl2java;

import java.io.FileNotFoundException;

import org.custommonkey.xmlunit.XMLTestCase;
import org.junit.Before;

import at.ac.tuwien.infosys.java2wadl.util.ResourceUtil;

public class Wadl2JavaBaseTest extends XMLTestCase {

	protected IWadl2Java wadl2Java;

	@Before
	@Override
	public void setUp() {
		wadl2Java = new Wadl2Java();
	}

	protected String loadResource(String resourceName) throws FileNotFoundException {
		return ResourceUtil.loadResource(getClass().getClassLoader(), resourceName);
	}
}
