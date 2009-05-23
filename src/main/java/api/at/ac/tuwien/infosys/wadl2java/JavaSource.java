package at.ac.tuwien.infosys.wadl2java;

public class JavaSource {

	public final String className;
	
	public final String sourceCode;
	
	public JavaSource(String className, String sourceCode) {
		this.className = className;
		this.sourceCode = sourceCode;
	}
	
	@Override
	public String toString() {
		return sourceCode;
	}
}
