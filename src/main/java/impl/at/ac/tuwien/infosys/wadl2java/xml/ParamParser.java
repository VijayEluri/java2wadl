package at.ac.tuwien.infosys.wadl2java.xml;

import org.w3c.dom.Node;

import at.ac.tuwien.infosys.java2wadl.WadlException;
import at.ac.tuwien.infosys.java2wadl.util.UriUtil;
import at.ac.tuwien.infosys.java2wadl.util.WadlUtil;
import at.ac.tuwien.infosys.java2wadl.util.XmlUtil;
import at.ac.tuwien.infosys.java2wadl.wadl.IParam;
import at.ac.tuwien.infosys.java2wadl.wadl.Param;

public class ParamParser {
	public IParam parse(Node node) throws WadlException {
		IParam param = new Param();

		param.setName(XmlUtil.getNodeAttribute(node, "name"));
		param.setId(UriUtil.createUri(XmlUtil.getNodeAttribute(node, "id")));
		param.setStyle(WadlUtil.toParamStyle(XmlUtil.getNodeAttribute(node, "style")));
		param.setType(XmlUtil.getNodeAttribute(node, "type"));
		param.setDefault(XmlUtil.getNodeAttribute(node, "default"));
		param.setFixed(XmlUtil.getNodeAttribute(node, "fixed"));
		param.setPath(XmlUtil.getNodeAttribute(node, "path"));

		if (XmlUtil.hasNodeAttribute(node, "required")) {
			param.setRequired(Boolean.valueOf(XmlUtil.getNodeAttribute(node, "required")));
		}
		
		if (XmlUtil.hasNodeAttribute(node, "repeating")) {
			param.setRepeating(Boolean.valueOf(XmlUtil.getNodeAttribute(node, "repeating")));
		}
		
		return param;
	}
}