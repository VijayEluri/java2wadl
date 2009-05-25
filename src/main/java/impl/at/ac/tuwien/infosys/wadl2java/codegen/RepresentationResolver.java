package at.ac.tuwien.infosys.wadl2java.codegen;

import at.ac.tuwien.infosys.java2wadl.WadlException;
import at.ac.tuwien.infosys.java2wadl.util.UriUtil;
import at.ac.tuwien.infosys.java2wadl.wadl.IApplication;
import at.ac.tuwien.infosys.java2wadl.wadl.IRepresentation;
import at.ac.tuwien.infosys.java2wadl.wadl.IRepresentationDefinition;
import at.ac.tuwien.infosys.java2wadl.wadl.IRepresentationReference;

class RepresentationResolver {

	public IRepresentationDefinition resolve(IApplication application, IRepresentation representation) throws WadlException {
		if (representation instanceof IRepresentationDefinition) {
			return (IRepresentationDefinition) representation;
		}
		
		IRepresentationReference representationReference = (IRepresentationReference) representation;
		
		for (IRepresentation applicationRepresentation : application.getRepresentations()) {
			if (applicationRepresentation instanceof IRepresentationDefinition) {
				IRepresentationDefinition appRepDef = (IRepresentationDefinition) applicationRepresentation;
				
				String realUri = "#" + appRepDef.getId();
				if (representationReference.getHref().equals(UriUtil.createUri(realUri))) {
					return appRepDef;
				}
			}
		}
		
		throw new WadlException("No RepresentationDefinition for: " + representationReference.getHref());
	}

}
