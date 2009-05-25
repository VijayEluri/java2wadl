package at.ac.tuwien.infosys.wadl2java.codegen;

import java.util.List;

import at.ac.tuwien.infosys.java2wadl.WadlException;
import at.ac.tuwien.infosys.java2wadl.wadl.IApplication;
import at.ac.tuwien.infosys.java2wadl.wadl.IMethodDefinition;
import at.ac.tuwien.infosys.java2wadl.wadl.IParam;

class MethodGenerator {

	private IApplication application;

	public MethodGenerator(IApplication application) {
		this.application = application;
	}

	public String generate(List<String> resourcePaths, IMethodDefinition methodDefinition) throws WadlException {
		return new MethodForMethodDefinitionGenerator(application).generate(resourcePaths, methodDefinition);
	}

	public String generate(List<String> resourcePaths, IParam param) throws WadlException {
		return new MethodForParamGenerator(application).generate(resourcePaths, param);
	}
}
