package at.ac.tuwien.infosys.java2wadl.wadl;

import java.util.ArrayList;
import java.util.List;

/**
 * @see at.ac.tuwien.infosys.java2wadl.wadl.IResponse
 * 
 * @author <a href="mailto:e0426062@student.tuwien.ac.at">Andreas Meingast</a>
 * @author <a href="mailto:e0325672@student.tuwien.ac.at">Anton Korosec</a>
 */
public class Response implements IResponse {

	private final List<IDoc> docs;
	private final List<IParam> params;
	private final List<IRepresentation> representations;
	private final List<IFault> faults;

	/**
	 * constructor
	 */
	public Response() {
		docs = new ArrayList<IDoc>();
		params = new ArrayList<IParam>();
		representations = new ArrayList<IRepresentation>();
		faults = new ArrayList<IFault>();
	}

	public List<IDoc> getDocs() {
		return docs;
	}

	public List<IFault> getFaults() {
		return faults;
	}

	public List<IParam> getParams() {
		return params;
	}

	public List<IRepresentation> getRepresentations() {
		return representations;
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

	public boolean addParam(IParam param) {
		if (params == null) {
			return false;
		}

		return params.add(param);
	}

	public boolean addRepresentation(IRepresentation representation) {
		if (representations == null) {
			return false;
		}

		return representations.add(representation);
	}

	@Override
	public String toString() {
		String result = "<response>" + "\n";

		if ((docs != null) && !docs.isEmpty()) {
			for (IDoc d : docs) {
				result += d.toString();
			}
		}

		if ((params != null) && !params.isEmpty()) {
			for (IParam p : params) {
				result += p.toString();
			}
		}

		if ((representations != null) && !representations.isEmpty()) {
			for (IRepresentation r : representations) {
				result += r.toString();
			}
		}

		if ((faults != null) && !faults.isEmpty()) {
			for (IFault f : faults) {
				result += f.toString();
			}
		}

		result += "</response>" + "\n";

		return result;
	}

	@Override
	public boolean isEmpty() {
		return docs.isEmpty() && params.isEmpty() && representations.isEmpty() && faults.isEmpty();
	}

}
