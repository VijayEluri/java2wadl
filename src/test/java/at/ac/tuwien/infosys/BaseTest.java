package at.ac.tuwien.infosys;

import java.io.FileNotFoundException;
import java.util.List;

import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.ElementNameAndAttributeQualifier;
import org.custommonkey.xmlunit.XMLTestCase;
import org.junit.Before;

import at.ac.tuwien.infosys.java2wadl.IJava2Wadl;
import at.ac.tuwien.infosys.java2wadl.Java2Wadl;
import at.ac.tuwien.infosys.java2wadl.WadlException;
import at.ac.tuwien.infosys.java2wadl.util.ResourceUtil;
import at.ac.tuwien.infosys.java2wadl.util.XmlUtil;
import at.ac.tuwien.infosys.wadl2java.IWadl2Java;
import at.ac.tuwien.infosys.wadl2java.JavaSource;
import at.ac.tuwien.infosys.wadl2java.Wadl2Java;

public abstract class BaseTest extends XMLTestCase {

	protected IJava2Wadl java2Wadl;
	
	protected IWadl2Java wadl2Java;

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();
		java2Wadl = new Java2Wadl();
		wadl2Java = new Wadl2Java();
	}
	
	protected String loadResource(String resourceName) throws FileNotFoundException {
		return ResourceUtil.loadResource(getClass().getClassLoader(), resourceName);
	}

	protected JavaSource getJavaSource(String className, List<JavaSource> sources) throws Exception {
		for (JavaSource javaSource : sources) {
			if (javaSource.className.equals(className)) {
				return javaSource;
			}
		}
	
		throw new Exception(className + " not found");
	}

	public void assertXmlEquals(String a, String b) throws WadlException {
		try {
			Diff diff = new Diff(XmlUtil.tidyXml(a), XmlUtil.tidyXml(b));
			diff.overrideElementQualifier(new ElementNameAndAttributeQualifier());
			assertXMLEqual(diff, true);
		} catch (Exception e) {
			throw new WadlException(e);
		}
	}
}
