package at.ac.tuwien.infosys.wadl2java.codegen;

import at.ac.tuwien.infosys.java2wadl.WadlException;
import at.ac.tuwien.infosys.java2wadl.util.UriUtil;
import at.ac.tuwien.infosys.java2wadl.wadl.IApplication;
import at.ac.tuwien.infosys.java2wadl.wadl.IMethod;
import at.ac.tuwien.infosys.java2wadl.wadl.IMethodDefinition;
import at.ac.tuwien.infosys.java2wadl.wadl.IMethodReference;

class MethodResolver {

	public IMethodDefinition resolve(IApplication application, IMethod method) throws WadlException {
		if (method instanceof IMethodDefinition) {
			return (IMethodDefinition) method;
		} else if (method instanceof IMethodReference) {
			return resolveMethodReference(application, (IMethodReference) method);
		}

		throw new WadlException("Invalid method-type. Expected IMethodDefinition or IMethodReference, got: "
				+ method.getClass());
	}

	private IMethodDefinition resolveMethodReference(IApplication application, IMethodReference methodReference)
			throws WadlException {
		for (IMethod applicationMethod : application.getMethods()) {
			if (applicationMethod instanceof IMethodReference) {
				IMethodReference applicationMethodReference = (IMethodReference) applicationMethod;

				if (applicationMethodReference.getHref().equals(methodReference.getHref())) {
					throw new WadlException("Unable to resolve method-reference for: " + methodReference.getHref()
							+ " due to cyclic dependency.");
				}
			} else if (applicationMethod instanceof IMethodDefinition) {
				IMethodDefinition applicationMethodDefinition = (IMethodDefinition) applicationMethod;

				if (UriUtil.createUri("#" + applicationMethodDefinition.getId()).equals(methodReference.getHref())) {
					return applicationMethodDefinition;
				}
			}
		}

		throw new WadlException("Unable to resolve method-reference for: " + methodReference.getHref());
	}
}
