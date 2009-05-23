package at.ac.tuwien.infosys.java2wadl.util;

import java.util.Collection;

import at.ac.tuwien.infosys.java2wadl.WadlException;

public class CollectionUtil {
	public static <V, T extends Collection<V>> void iterate(T ts, F<V> f) throws WadlException {
		for (V t : ts) {
			f.apply(t);
		}
	}
}
