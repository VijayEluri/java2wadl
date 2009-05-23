package at.ac.tuwien.infosys.wadl2java;

import java.util.List;

import at.ac.tuwien.infosys.java2wadl.WadlException;

public interface IWadl2Java {
	List<JavaSource> generate(String wadlSchema, String packageName) throws WadlException;
}
