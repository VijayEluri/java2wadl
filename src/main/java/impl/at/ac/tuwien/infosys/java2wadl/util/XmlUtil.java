package at.ac.tuwien.infosys.java2wadl.util;

import static at.ac.tuwien.infosys.java2wadl.util.AssertUtil.assertNotEmpty;
import static at.ac.tuwien.infosys.java2wadl.util.AssertUtil.assertNotNull;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.tidy.Configuration;
import org.w3c.tidy.Tidy;
import org.xml.sax.SAXException;

import at.ac.tuwien.infosys.java2wadl.Consts;
import at.ac.tuwien.infosys.java2wadl.WadlException;

/**
 * Utility class for handling XML.
 * 
 * @author <a href="mailto:e0426062@student.tuwien.ac.at">Andreas Meingast</a>
 * @author <a href="mailto:e0325672@student.tuwien.ac.at">Anton Korosec</a>
 */
@SuppressWarnings("serial")
public class XmlUtil {

	private static final Map<String, String> schemaCache = new ConcurrentHashMap<String, String>();

	/**
	 * Return a list of dependent XML schemas
	 * 
	 * @return The {@link List}.
	 */
	public static Map<String, String> getSchemas() {
		return schemaCache;
	}

	/**
	 * Clears the cache for the XML schemas.
	 */
	public static void clearSchemaCache() {
		schemaCache.clear();
	}

	private static final Map<Class<?>, String> classToXmlSchemaMap = new HashMap<Class<?>, String>() {
		{
			put(boolean.class, Consts.xml_schema_ns_prefix + "boolean");
			put(Boolean.class, Consts.xml_schema_ns_prefix + "boolean");
			put(byte.class, Consts.xml_schema_ns_prefix + "byte");
			put(Byte.class, Consts.xml_schema_ns_prefix + "byte");
			put(double.class, Consts.xml_schema_ns_prefix + "double");
			put(Double.class, Consts.xml_schema_ns_prefix + "double");
			put(float.class, Consts.xml_schema_ns_prefix + "float");
			put(Float.class, Consts.xml_schema_ns_prefix + "float");
			put(int.class, Consts.xml_schema_ns_prefix + "integer");
			put(Integer.class, Consts.xml_schema_ns_prefix + "integer");
			put(long.class, Consts.xml_schema_ns_prefix + "long");
			put(Long.class, Consts.xml_schema_ns_prefix + "long");
			put(short.class, Consts.xml_schema_ns_prefix + "short");
			put(Short.class, Consts.xml_schema_ns_prefix + "short");
			put(String.class, Consts.xml_schema_ns_prefix + "string");
			put(BigDecimal.class, Consts.xml_schema_ns_prefix + "decimal");
			put(BigInteger.class, Consts.xml_schema_ns_prefix + "int");
			put(URI.class, Consts.xml_schema_ns_prefix + "anyURI");
			put(Calendar.class, Consts.xml_schema_ns_prefix + "dateTime");
			put(Date.class, Consts.xml_schema_ns_prefix + "dateTime");
		}
	};

	private static final Map<String, Class<?>> xmlSchemaToClassMap = new HashMap<String, Class<?>>() {
		{
			for (Class<?> c : classToXmlSchemaMap.keySet()) {
				if (!c.isPrimitive()) {
					put(classToXmlSchemaMap.get(c), c);
				}
			}
		}
	};

	/**
	 * Create a xml-schema from a given Java-type and save it to a file.
	 * 
	 * @param type
	 *            The class from which to create a xml-schema.
	 * @param baseDir
	 *            The directory where the schema-File should be created.
	 * @throws WadlException
	 */
	public static String generateSchema(final Class<?> type) throws WadlException {
		assertNotNull(type);

		final ByteArrayOutputStream os = new ByteArrayOutputStream();
		String schemaContent;

		try {
			JAXBContext.newInstance(type).generateSchema(new SchemaOutputResolver() {
				@Override
				public Result createOutput(String namespaceUri, String suggestedFileName) throws IOException {
					StreamResult s = new StreamResult(os);
					s.setSystemId(suggestedFileName);
					return s;
				}
			});

			// probably better to define targetNs while generating schema...
			schemaContent = new String(os.toByteArray());
			schemaContent = addTargetNamespaceToSchema(schemaContent, Consts.GRAMMAR_NS);

		} catch (Exception e) {
			throw new WadlException(e);
		}

		schemaCache.put(getSchemaName(type), schemaContent);
		return schemaContent;
	}

	/**
	 * Determine the name of a given type for the XML-schema file.
	 * 
	 * @param type
	 *            The type.
	 * @return The appropriate name.
	 */
	public static String getSchemaName(Class<?> type) {
		return type.getName() + ".xsd";
	}

	private static String addTargetNamespaceToSchema(String schemaContent, String targetNs)
			throws ParserConfigurationException, SAXException, IOException, TransformerFactoryConfigurationError,
			TransformerException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();

		InputStream in = new ByteArrayInputStream(schemaContent.getBytes());
		Document document = builder.parse(in);

		Element schemaRoot = document.getDocumentElement();
		schemaRoot.setAttribute("targetNamespace", targetNs);
		schemaRoot.setAttribute("elementFormDefault", "qualified");

		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");

		DOMSource source = new DOMSource(document);

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		StreamResult result = new StreamResult(bos);
		transformer.transform(source, result);
		return new String(bos.toByteArray());
	}

	/**
	 * Return the corresponding xsd-Type of the given Java-type.
	 * 
	 * @param type
	 *            A primitive Java-type.
	 * @return The xsd-Type of the given primitive Java-type.
	 */
	public static String getPrimitiveXmlType(Class<?> type) {
		assertNotNull(type);

		return classToXmlSchemaMap.get(type);
	}

	public static String getJavaTypeForXsdType(String xsdType) {
		assertNotNull(xsdType);

		if (xmlSchemaToClassMap.containsKey(xsdType)) {
			return xmlSchemaToClassMap.get(xsdType).getSimpleName();
		} else {
			return "";
		}
	}

	/**
	 * Check if the given type is a primitive Java-type.
	 * 
	 * @param type
	 *            The type to check.
	 * @return <code>true</code> if the given type is a primitive Java-type and <code>false</code> otherwise.
	 */
	public static boolean isPrimitiveType(Class<?> type) {
		assertNotNull(type);

		return classToXmlSchemaMap.containsKey(type);
	}

	/**
	 * Pretty-format the given xml-String.
	 * 
	 * @param xml
	 *            The xml-String to clean up.
	 * @return The tidy xml-String.
	 */
	public static String tidyXml(String xml) {
		assertNotEmpty(xml);

		final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(xml.getBytes());
		final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

		tidy().parse(byteArrayInputStream, byteArrayOutputStream);
		return new String(byteArrayOutputStream.toByteArray());

	}

	private static Tidy tidy() {
		final Tidy tidy = new Tidy();

		StringWriter errorStringWriter = new StringWriter();
		PrintWriter errorWriter = new PrintWriter(errorStringWriter, true);
		tidy.setErrout(errorWriter);

		tidy.setCharEncoding(Configuration.UTF8);
		tidy.setXmlTags(true);

		return tidy;
	}

	public static List<Node> toList(NodeList nodeList) {
		List<Node> list = new ArrayList<Node>(nodeList.getLength());

		for (int i = 0; i < nodeList.getLength(); i++) {
			list.add(nodeList.item(i));
		}

		return list;
	}

	public static String getNodeAttribute(Node node, String attributeName) {
		if (node == null || node.getAttributes() == null || node.getAttributes().getNamedItem(attributeName) == null) {
			return "";
		}
		String attributeValue = node.getAttributes().getNamedItem(attributeName).getTextContent();

		return null == attributeValue ? "" : attributeValue;
	}

	public static List<Node> getChildNodes(Node node, String nodeName) {
		List<Node> childNodes = new ArrayList<Node>();

		for (Node childNode : toList(node.getChildNodes())) {
			if (childNode.getNodeName().equals(nodeName)) {
				childNodes.add(childNode);
			}
		}

		return childNodes;
	}

	public static boolean hasChildNode(Node node, String nodeName) {
		return getChildNodes(node, nodeName).isEmpty();
	}

	public static boolean hasNodeAttribute(Node node, String nodeName) {
		return node.getAttributes().getNamedItem(nodeName) != null;
	}
}
