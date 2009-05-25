package at.ac.tuwien.infosys;

import at.ac.tuwien.infosys.java2wadl.IJava2Wadl;
import at.ac.tuwien.infosys.java2wadl.Java2Wadl;
import at.ac.tuwien.infosys.wadl2java.IWadl2Java;
import at.ac.tuwien.infosys.wadl2java.Wadl2Java;

public class Java2Wadl2JavaFactory {
	public static IJava2Wadl java2Wadl() {
		return new Java2Wadl();
	}

	public static IWadl2Java wadl2Java() {
		return new Wadl2Java();
	}
}
