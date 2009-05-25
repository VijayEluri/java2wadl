package at.ac.tuwien.infosys.wadl2java;

import java.util.ArrayList;
import java.util.List;

import at.ac.tuwien.infosys.java2wadl.util.StringUtil;

public class JavaSource {

	public final String className;

	public final String packageName;

	private List<String> methods = new ArrayList<String>();

	private List<String> classAnnotations = new ArrayList<String>();

	public JavaSource(String className, String packageName) {
		this.className = className;
		this.packageName = packageName;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();

		if (!StringUtil.isEmpty(packageName)) {
			sb.append("package " + packageName + ";\n\n");
			sb.append("import javax.ws.rs.*;\n\n");
		}

		for (String annotation : classAnnotations) {
			sb.append(annotation + "\n");
		}

		sb.append("public class " + className + " {\n\n");

		for (String method : methods) {
			for (String methodLine : StringUtil.split(method.toString(), "\n")) {
				sb.append(indent(methodLine) + "\n");
			}
			sb.append("\n");
		}

		sb.append("}\n");

		return sb.toString();
	}

	public void addClassAnnotation(String annotation) {
		classAnnotations.add(annotation);
	}

	public void addMethodSource(String method) {
		methods.add(method);
	}
	
	@Override
	public boolean equals(Object other) {
		if (other instanceof JavaSource) {
			return toString().equals(other.toString());
		} else {
			return false;
		}
	}

	private String indent(String line) {
		return "\t" + line;
	}
}
