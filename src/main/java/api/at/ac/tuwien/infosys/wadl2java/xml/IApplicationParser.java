package at.ac.tuwien.infosys.wadl2java.xml;

import at.ac.tuwien.infosys.java2wadl.WadlException;
import at.ac.tuwien.infosys.java2wadl.wadl.IApplication;

public interface IApplicationParser {
	IApplication parse(String wadlDescription) throws WadlException;
}
