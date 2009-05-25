package at.ac.tuwien.infosys.wadl2java.codegen;

import java.util.ArrayList;
import java.util.List;

import at.ac.tuwien.infosys.java2wadl.WadlException;
import at.ac.tuwien.infosys.java2wadl.util.StringUtil;
import at.ac.tuwien.infosys.java2wadl.util.WadlUtil;
import at.ac.tuwien.infosys.java2wadl.wadl.IApplication;
import at.ac.tuwien.infosys.java2wadl.wadl.IMethod;
import at.ac.tuwien.infosys.java2wadl.wadl.IMethodDefinition;
import at.ac.tuwien.infosys.java2wadl.wadl.IParam;
import at.ac.tuwien.infosys.java2wadl.wadl.IResource;
import at.ac.tuwien.infosys.wadl2java.JavaSource;

class ResourceGenerator {

	private IApplication application;

	private String packageName;

	public ResourceGenerator(IApplication application, String packageName) {
		this.application = application;
		this.packageName = packageName;
	}

	public List<JavaSource> generate() throws WadlException {
		List<JavaSource> resources = new ArrayList<JavaSource>();

		for (IResource resource : application.getResources().getResources()) {
			JavaSource javaSource = new JavaSource(getClassName(resource), packageName);

			String path = resource.getPath();// determineResourcePath(resource);
			
			javaSource.addClassAnnotation("@Path(\"" + path + "\")");
			resources.add(processResource(new ArrayList<String>(), resource, javaSource));
		}

		return resources;
	}

	private String getClassName(IResource resource) throws WadlException {
		String className = resource.getPath();

		if (!application.getMethods().isEmpty()) {
			IMethodDefinition md = new MethodResolver().resolve(application, application.getMethods().get(0));

			if (md.getId() != null && WadlUtil.isJava2WadlName(md.getId().toString())) {
				className = StringUtil.collectUntil(md.getId().toString(), '_');
			}
		}

		return className;
	}

	private JavaSource processResource(List<String> resourcePaths, IResource resource, JavaSource javaSource)
			throws WadlException {
		for (IMethod method : resource.getMethods()) {
			IMethodDefinition methodDefinition = new MethodResolver().resolve(application, method);

			javaSource.addMethodSource(new MethodGenerator(application).generate(resourcePaths, methodDefinition));
		}

		for (IResource subResource : resource.getResources()) {
			List<String> subResourcePaths = new ArrayList<String>();

			subResourcePaths.addAll(resourcePaths);
			subResourcePaths.add(subResource.getPath());

			processResource(subResourcePaths, subResource, javaSource);
		}

		for (IParam param : resource.getParams()) {
			javaSource.addMethodSource(new MethodGenerator(application).generate(resourcePaths, param));
		}

		return javaSource;
	}
}
