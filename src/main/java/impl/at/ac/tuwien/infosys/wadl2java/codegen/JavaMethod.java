package at.ac.tuwien.infosys.wadl2java.codegen;

import java.util.ArrayList;
import java.util.List;

import at.ac.tuwien.infosys.java2wadl.util.StringUtil;

class JavaMethod implements Cloneable {
	public final List<String> annotations = new ArrayList<String>();
		
	public final List<String> parameters = new ArrayList<String>();
	
	public final List<String> pathParameters = new ArrayList<String>();
	
	public boolean hasException = false;
	
	public String returnType = "void";
	
	public String methodName = "";
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		sb.append(StringUtil.join(annotations, "\n"));
		sb.append("\npublic ");
		sb.append(returnType);
		sb.append(" ");
		sb.append(methodName);
		sb.append("(");
		sb.append(StringUtil.join(parameters, ", "));
		sb.append(")");
		sb.append(hasException ? " throws WebApplicationException" : "");
		sb.append(" {\n");
		sb.append("\t// TODO: Auto-generated method stub\n");
		sb.append("void".equals(returnType) ? "" : "\treturn null;\n");
		sb.append("}\n");
		
		return sb.toString();
	}
	
	@Override
	public JavaMethod clone() {
		JavaMethod clone = new JavaMethod();
		
		for (String annotation : annotations) {
			clone.annotations.add(annotation);
		}

		for (String param : parameters) {
			clone.parameters.add(param);
		}
		
		for (String param : pathParameters) {
			clone.pathParameters.add(param);
		}
		
		clone.hasException = hasException;
		clone.returnType = returnType;
		clone.methodName = methodName;
		
		return clone;
	}
}
