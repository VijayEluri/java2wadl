package at.ac.tuwien.infosys.wadl2java.codegen;

import java.util.List;

import at.ac.tuwien.infosys.java2wadl.WadlException;
import at.ac.tuwien.infosys.java2wadl.wadl.IApplication;
import at.ac.tuwien.infosys.java2wadl.wadl.IParam;

class MethodForParamGenerator {

	private IApplication application;

	public MethodForParamGenerator(IApplication application) {
		this.application = application;
	}

	public String generate(List<String> resourcePaths, IParam param) throws WadlException {
		assert application != null;
		
		throw new WadlException("Unsupported operation");
	}
}
