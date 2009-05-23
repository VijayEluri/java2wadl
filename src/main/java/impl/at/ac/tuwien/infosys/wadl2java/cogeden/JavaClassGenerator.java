package at.ac.tuwien.infosys.wadl2java.cogeden;

import at.ac.tuwien.infosys.java2wadl.wadl.IApplication;

public class JavaClassGenerator {

	private IApplication application;

	private String packageName;

	public JavaClassGenerator(String packageName, IApplication application) {
		this.packageName = packageName;
		this.application = application;
	}

	public String generate() {
		return "";
	}
}
