package at.ac.tuwien.infosys.wadl2java.xml;

import org.w3c.dom.Node;

import at.ac.tuwien.infosys.java2wadl.WadlException;
import at.ac.tuwien.infosys.java2wadl.util.XmlUtil;
import at.ac.tuwien.infosys.java2wadl.wadl.Grammars;
import at.ac.tuwien.infosys.java2wadl.wadl.IGrammars;
import at.ac.tuwien.infosys.java2wadl.wadl.Include;

public class GrammarsParser {
	public IGrammars parse(Node node) throws WadlException {
		final IGrammars grammars = new Grammars();

		for (Node includeNode : XmlUtil.getChildNodes(node, "include")) {
			grammars.addInclude(new Include(XmlUtil.getNodeAttribute(includeNode, "href")));	
		}
		
		return grammars;
	}
}
