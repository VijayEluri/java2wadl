package at.ac.tuwien.infosys.wadl2java;

import static at.ac.tuwien.infosys.java2wadl.util.AssertUtil.assertNotNull;

import java.util.List;

import at.ac.tuwien.infosys.java2wadl.WadlException;
import at.ac.tuwien.infosys.wadl2java.codegen.JavaGenerator;
import at.ac.tuwien.infosys.wadl2java.xml.ApplicationParser;

public class Wadl2Java implements IWadl2Java {
	@Override
	public List<JavaSource> generate(String wadlSchema, String packageName) throws WadlException {
		assertNotNull(wadlSchema);
		assertNotNull(packageName);

		return new JavaGenerator().generate(packageName, new ApplicationParser().parse(wadlSchema));
	}
}
