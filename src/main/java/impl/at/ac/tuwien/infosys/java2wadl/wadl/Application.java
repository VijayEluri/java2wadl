package at.ac.tuwien.infosys.java2wadl.wadl;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import at.ac.tuwien.infosys.java2wadl.Consts;

/**
 * @see at.ac.tuwien.infosys.java2wadl.wadl.IApplication
 * 
 * @author <a href="mailto:e0426062@student.tuwien.ac.at">Andreas Meingast</a>
 * @author <a href="mailto:e0325672@student.tuwien.ac.at">Anton Korosec</a>
 */
public class Application implements IApplication {

	private final List<IDoc> docs;
	private IGrammars grammars;
	private IResources resources;
	private final List<IResourceType> resource_types;
	private final List<IMethod> methods;
	private final List<IRepresentation> representations;
	private final List<IFault> faults;

	/**
	 * constructor
	 */
	public Application() {
		docs = new ArrayList<IDoc>();
		resource_types = new ArrayList<IResourceType>();
		methods = new ArrayList<IMethod>();
		representations = new ArrayList<IRepresentation>();
		faults = new ArrayList<IFault>();
		grammars = new Grammars();
	}

	/**
	 * constructor
	 * 
	 * @param baseUri
	 *            The base URI for the WADL-Application-Element
	 */
	public Application(URI baseUri) {
		this();
		this.resources = new Resources();
		this.resources.setBase(baseUri);
	}

	public List<IDoc> getDocs() {
		return docs;
	}

	public List<IFault> getFaults() {
		return faults;
	}

	public IGrammars getGrammars() {
		return grammars;
	}

	public List<IMethod> getMethods() {
		return methods;
	}

	public List<IRepresentation> getRepresentations() {
		return representations;
	}

	public List<IResourceType> getResource_types() {
		return resource_types;
	}

	public IResources getResources() {
		return resources;
	}

	public boolean addDoc(IDoc doc) {
		if (docs == null) {
			return false;
		}

		return docs.add(doc);
	}

	public boolean addFault(IFault fault) {
		if (faults == null) {
			return false;
		}

		return faults.add(fault);
	}

	public boolean addMethod(IMethod method) {
		if (methods == null) {
			return false;
		}

		return methods.add(method);
	}

	public boolean addRepresentation(IRepresentation representation) {
		if (representations == null) {
			return false;
		}

		return representations.add(representation);
	}

	public boolean addResource_type(IResourceType resourceType) {
		if (resource_types == null) {
			return false;
		}

		return resource_types.add(resourceType);
	}

	public boolean setGrammars(IGrammars grammars) {
		this.grammars = grammars;
		return true;
	}

	public boolean setResources(IResources resources) {
		this.resources = resources;
		return true;
	}

	@Override
	public String toString() {
		String result = "<application ";

		for (String namespace : Consts.xml_namespaces) {
			result += namespace + " ";
		}

		result += ">\n";

		if ((docs != null) && !docs.isEmpty()) {
			for (IDoc d : docs) {
				result += d.toString();
			}
		}

		if (grammars != null) {
			result += grammars.toString();
		}

		if (resources != null) {
			result += resources.toString();
		}

		if ((representations != null) && !representations.isEmpty()) {
			for (IRepresentation r : representations) {
				result += r.toString();
			}
		}

		if ((resource_types != null) && !resource_types.isEmpty()) {
			for (IResourceType r : resource_types) {
				result += r.toString();
			}
		}

		if ((methods != null) && !methods.isEmpty()) {
			for (IMethod m : methods) {
				result += m.toString();
			}
		}

		if ((faults != null) && !faults.isEmpty()) {
			for (IFault f : faults) {
				result += f.toString();
			}
		}

		result += "</application>" + "\n";

		return result;
	}

}
