package at.ac.tuwien.infosys.java2wadl;

/**
 * @author <a href="mailto:e0426062@student.tuwien.ac.at">Andreas Meingast</a>
 * @author <a href="mailto:e0325672@student.tuwien.ac.at">Anton Korosec</a>
 */
@SuppressWarnings("serial")
public class WadlException extends Exception {

	/**
	 * 
	 */
	public WadlException() {
		super();
	}

	/**
	 * 
	 * @param s
	 */
	public WadlException(String s) {
		super(s);
	}

	/**
	 * 
	 * @param t
	 */
	public WadlException(Throwable t) {
		super(t);
	}
}
