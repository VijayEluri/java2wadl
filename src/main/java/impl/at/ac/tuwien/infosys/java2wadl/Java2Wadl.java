package at.ac.tuwien.infosys.java2wadl;

import static at.ac.tuwien.infosys.java2wadl.util.AssertUtil.assertNotNull;
import at.ac.tuwien.infosys.java2wadl.util.UriUtil;
import at.ac.tuwien.infosys.java2wadl.util.XmlUtil;
import at.ac.tuwien.infosys.java2wadl.wadl.Application;
import at.ac.tuwien.infosys.java2wadl.wadl.IApplication;

/**
 * Main class for generating a WADL based on a given resource class.
 * 
 * @author <a href="mailto:e0426062@student.tuwien.ac.at">Andreas Meingast</a>
 * @author <a href="mailto:e0325672@student.tuwien.ac.at">Anton Korosec</a>
 */
public class Java2Wadl implements IJava2Wadl {

	public Wadl generate(Class<?> type) throws WadlException {
		return generate(type, "/");
	}

	public Wadl generate(Class<?> type, String base) throws WadlException {
		assertNotNull(type);
		assertNotNull(base);

		XmlUtil.clearSchemaCache();
		return proceed(type, new Application(UriUtil.createUri(base)));
	}

	private Wadl proceed(Class<?> type, IApplication application) throws WadlException {
		new ResourceClassParser().processResource(type, application, ResourceClassParser.getClassResourcePath(type));

		String wadlContent = XmlUtil.tidyXml(Consts.xml_header + application.toString());

		return new Wadl(wadlContent, XmlUtil.getSchemas());
	}
}
