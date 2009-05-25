package at.ac.tuwien.infosys.wadl2java.codegen;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import at.ac.tuwien.infosys.java2wadl.wadl.IApplication;
import at.ac.tuwien.infosys.java2wadl.wadl.IInclude;
import at.ac.tuwien.infosys.wadl2java.JavaSource;

class SchemaGenerator {

	private static final Pattern classExtractor = Pattern.compile("(\\S+\\.)?(\\S+)\\.xsd$");

	private IApplication application;

	private String packageName;

	public SchemaGenerator(IApplication application, String packageName) {
		this.application = application;
		this.packageName = packageName;
	}

	public List<JavaSource> generate() {
		List<JavaSource> javaSources = new ArrayList<JavaSource>();

		for (IInclude include : application.getGrammars().getIncludes()) {
			JavaSource javaSource = new JavaSource(getClassName(include), packageName);
			
			javaSources.add(javaSource);
			
			// TODO: parse from XML file
		}

		return javaSources;
	}
	
	private String getClassName(IInclude include) {
		String className = include.getHref().toString();
		Matcher m = classExtractor.matcher(className);
		
		if (m.find()) {
			className = m.group(2);
		}
		
		return className;
	}
}
