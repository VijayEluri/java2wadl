package at.ac.tuwien.infosys.java2wadl.util;

import at.ac.tuwien.infosys.java2wadl.WadlException;

public interface F<T> {
	void apply(T t) throws WadlException;
}
