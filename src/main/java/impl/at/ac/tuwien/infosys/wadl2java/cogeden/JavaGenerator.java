package at.ac.tuwien.infosys.wadl2java.cogeden;

import java.util.ArrayList;
import java.util.List;

import at.ac.tuwien.infosys.java2wadl.wadl.IApplication;
import at.ac.tuwien.infosys.wadl2java.JavaSource;

public class JavaGenerator {
	
	private String packageName;
	
	private IApplication application;

	public JavaGenerator(String packageName, IApplication application) {
		this.packageName = packageName;
		this.application = application;
	}

	public List<JavaSource> generate() {
		// foreach //resources/resource
		new JavaClassGenerator(packageName, application).generate();

		return new ArrayList<JavaSource>();
	}
}
