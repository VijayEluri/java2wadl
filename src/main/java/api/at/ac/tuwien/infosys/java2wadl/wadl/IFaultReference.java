package at.ac.tuwien.infosys.java2wadl.wadl;

import java.net.URI;

/**
 * @see at.ac.tuwien.infosys.java2wadl.wadl.IRepresentationReference
 * 
 * @author <a href="mailto:e0426062@student.tuwien.ac.at">Andreas Meingast</a>
 * @author <a href="mailto:e0325672@student.tuwien.ac.at">Anton Korosec</a>
 */
public interface IFaultReference extends IFault {

	public URI getHref();

	public boolean setHref(URI href);

}
