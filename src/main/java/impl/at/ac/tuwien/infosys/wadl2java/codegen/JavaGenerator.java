package at.ac.tuwien.infosys.wadl2java.codegen;

import java.util.ArrayList;
import java.util.List;

import at.ac.tuwien.infosys.java2wadl.WadlException;
import at.ac.tuwien.infosys.java2wadl.wadl.IApplication;
import at.ac.tuwien.infosys.wadl2java.JavaSource;

public class JavaGenerator {
	public List<JavaSource> generate(String packageName, IApplication application) throws WadlException {
		List<JavaSource> sources = new ArrayList<JavaSource>();

		sources.addAll(new SchemaGenerator(application, packageName).generate());
		sources.addAll(new ResourceGenerator(application, packageName).generate());

		return sources;
	}
}
