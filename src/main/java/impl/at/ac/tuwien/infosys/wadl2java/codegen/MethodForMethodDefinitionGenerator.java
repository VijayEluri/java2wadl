package at.ac.tuwien.infosys.wadl2java.codegen;

import java.util.ArrayList;
import java.util.List;

import at.ac.tuwien.infosys.java2wadl.WadlException;
import at.ac.tuwien.infosys.java2wadl.util.StringUtil;
import at.ac.tuwien.infosys.java2wadl.util.WadlUtil;
import at.ac.tuwien.infosys.java2wadl.util.XmlUtil;
import at.ac.tuwien.infosys.java2wadl.wadl.IApplication;
import at.ac.tuwien.infosys.java2wadl.wadl.IMethodDefinition;
import at.ac.tuwien.infosys.java2wadl.wadl.IParam;
import at.ac.tuwien.infosys.java2wadl.wadl.IRepresentation;
import at.ac.tuwien.infosys.java2wadl.wadl.IRepresentationDefinition;
import at.ac.tuwien.infosys.java2wadl.wadl.ParamStyle;

class MethodForMethodDefinitionGenerator {

	private IApplication application;

	public MethodForMethodDefinitionGenerator(IApplication application) {
		this.application = application;
	}

	public String generate(List<String> resourcePaths, IMethodDefinition method) throws WadlException {
		StringBuffer sb = new StringBuffer();

		JavaMethod baseJavaMethod = new JavaMethod();
		baseJavaMethod.methodName = determineName(method);

		appendAnnotationsAndPathParameters(baseJavaMethod, resourcePaths, method);

		for (JavaMethod returnTypesMethod : determineReturnTypes(method, baseJavaMethod)) {
			for (JavaMethod javaMethod : determineSignature(method, returnTypesMethod)) {
				sb.append(javaMethod.toString());
				sb.append("\n");
			}
		}

		return sb.toString();
	}

	private List<JavaMethod> determineSignature(IMethodDefinition method, JavaMethod javaMethod) throws WadlException {
		List<JavaMethod> methods = new ArrayList<JavaMethod>();
		List<String> globalParameters = new ArrayList<String>();

		if (method.getRequest() == null || method.getRequest().isEmpty()) {
			methods.add(javaMethod);
			return methods;
		}

		for (IParam param : method.getRequest().getParams()) {
			if (ParamStyle.query.equals(param.getStyle())) {
				globalParameters.add(determineJavaParamType(param) + " " + param.getName().toString());
			}
		}

		for (IParam param : method.getRequest().getParams()) {
			JavaMethod javaMethodClone = javaMethod.clone();
			
			javaMethodClone.parameters.add(annotateParameter(param));
			methods.add(javaMethodClone);
		}

		for (IRepresentation representation : method.getRequest().getRepresentations()) {
			JavaMethod javaMethodClone = javaMethod.clone();
			IRepresentationDefinition repDef = new RepresentationResolver().resolve(application, representation);

			for (IParam param : repDef.getParams()) {
				String parameter = annotateParameter(param);
				
				javaMethodClone.parameters.add(parameter);
			}

			if (!StringUtil.isEmpty(repDef.getElement())) {
				String el = determineElementJavaType(repDef.getElement());

				javaMethodClone.parameters.add(el + " " + el.toLowerCase());
			}

			String mediaType = repDef.getMediaType();
			if (!StringUtil.isEmpty(mediaType)) {
				javaMethodClone.methodName += appendableMediaType(mediaType);
				javaMethodClone.annotations.add("@Consumes(\"" + mediaType + "\")");
			}
			
			for (String globalParameter : globalParameters) {
				javaMethodClone.parameters.add(globalParameter);
			}

			methods.add(javaMethodClone);
		}

		return methods;
	}

	private String annotateParameter(IParam param) {
		String parameter = "";
		
		if (null != param.getStyle()) {
			parameter += "@" + WadlUtil.fromParamStyle(param.getStyle()) + "(\"" + param.getName() + "\") ";
		}
		
		if (null != param.getDefault()) {
			parameter += "@DefaultValue(\"" + param.getDefault() + "\") ";
		}
		
		parameter += determineJavaParamType(param) + " " + param.getName();
		
		return parameter;
	}

	private String determineJavaParamType(IParam param) {
		return StringUtil.isEmpty(param.getType()) ? "String" : XmlUtil.getJavaTypeForXsdType(param.getType());
	}

	private String determineName(IMethodDefinition method) {
		String methodName = method.getId().toString();
		
		if (WadlUtil.isJava2WadlName(methodName)) {	
			methodName = methodName.replaceAll("^\\S+?_", "").replaceAll("_\\S*$", "");
		}
		return methodName;
	}

	private List<JavaMethod> determineReturnTypes(IMethodDefinition method, JavaMethod javaMethod) throws WadlException {
		List<JavaMethod> methods = new ArrayList<JavaMethod>();

		javaMethod.hasException = method.getResponse() != null && method.getResponse().getFaults().size() > 0;

		if (method.getResponse() == null || method.getResponse().isEmpty()) {
			methods.add(javaMethod);
			return methods;
		}

		for (IParam param : method.getResponse().getParams()) {
			JavaMethod javaMethodClone = javaMethod.clone();

			javaMethodClone.returnType = XmlUtil.getJavaTypeForXsdType(param.getType());
			methods.add(javaMethodClone);
		}

		for (IRepresentation representation : method.getResponse().getRepresentations()) {
			JavaMethod javaMethodClone = javaMethod.clone();

			IRepresentationDefinition representationDefinition = new RepresentationResolver().resolve(application,
					representation);
			String mediaType = representationDefinition.getMediaType();

			if (!StringUtil.isEmpty(representationDefinition.getElement())) {
				javaMethodClone.returnType = determineElementJavaType(representationDefinition.getElement());
			} else if (representationDefinition.getStatus().contains(200)) {
				javaMethodClone.returnType = "Response";
			}

			if (!StringUtil.isEmpty(mediaType)) {
				javaMethodClone.methodName += appendableMediaType(mediaType);
				javaMethodClone.annotations.add("@Produces(\"" + mediaType + "\")");
			}

			methods.add(javaMethodClone);
			// TODO: case return type determined by representation->param. is this valid?
		}

		return methods;
	}

	private String appendableMediaType(String mediaType) {
		return mediaType.replaceAll(".+\\/", "").toUpperCase();
	}
	
	private String determineElementJavaType(String element) throws WadlException {
		return StringUtil.capitalize(element.replaceAll("j2wns:", ""));
	}

	private void appendAnnotationsAndPathParameters(JavaMethod javaMethod, List<String> resourcePaths,
			IMethodDefinition method) {
		if (method.getName() != null) {
			javaMethod.annotations.add("@" + method.getName().toString());
		}

		for (String resourcePath : resourcePaths) {
			if (resourcePath.startsWith("{") && resourcePath.endsWith("}")) {
				String variable = resourcePath.replaceAll("^\\{", "").replaceAll("\\}$", "");

				javaMethod.parameters.add("@PathParam(\"" + variable + "\") String " + variable);
				javaMethod.pathParameters.add(variable);
			}
		}

		if (resourcePaths.size() > 0) {
			javaMethod.annotations.add("@Path(\"/" + StringUtil.join(resourcePaths, "/") + "\")");
		}
	}
}
