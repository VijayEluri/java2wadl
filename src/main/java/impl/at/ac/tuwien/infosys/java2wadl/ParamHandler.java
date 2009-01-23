package at.ac.tuwien.infosys.java2wadl;

import java.lang.annotation.Annotation;
import java.util.List;

import javax.ws.rs.HeaderParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.MatrixParam;

import at.ac.tuwien.infosys.java2wadl.util.AssertUtil;
import at.ac.tuwien.infosys.java2wadl.util.XmlUtil;
import at.ac.tuwien.infosys.java2wadl.wadl.IParam;
import at.ac.tuwien.infosys.java2wadl.wadl.Param;
import at.ac.tuwien.infosys.java2wadl.wadl.ParamStyle;

/**
 * Class for handling all WADL-param related stuff.
 * 
 * @author <a href="mailto:e0426062@student.tuwien.ac.at">Andreas Meingast</a>
 * @author <a href="mailto:e0325672@student.tuwien.ac.at">Anton Korosec</a>
 */
public class ParamHandler {
	/**
	 * Search a given list of annotations for a parameterStyle-annotation annotation and return the appropriate
	 * parameter style.
	 * 
	 * @param annotations
	 *            A list of annotations eventually containing a parameterStyle-annotation. Must not be <code>null</code> .
	 * @return The found parameter style.
	 */
	public static ParamStyle getParamStyle(List<Annotation> annotations) {
		AssertUtil.assertNotNull(annotations);

		for (Annotation parameterAnnotation : annotations) {
			if (parameterAnnotation instanceof QueryParam) {
				return ParamStyle.query;
			} else if (parameterAnnotation instanceof HeaderParam) {
				return ParamStyle.header;
			} else if (parameterAnnotation instanceof MatrixParam) {
				return ParamStyle.matrix;
			}
		}

		return null;
	}

	/**
	 * Return the value of the first found {@link QueryParam}-annotation in annotations.
	 * 
	 * @param annotations
	 *            List of annotations - supposed to contain a QueryParam. Must not be <code>null</code>.
	 * @return The value of the found {@link QueryParam}.
	 */
	public static String getQueryParamName(List<Annotation> annotations) {
		AssertUtil.assertNotNull(annotations);

		for (Annotation parameterAnnotation : annotations) {
			if (parameterAnnotation instanceof QueryParam) {
				return ((QueryParam) parameterAnnotation).value();
			}
		}

		return "";
	}

	/*
	 * public static IParam createParameter(String name, Class<?> type, ParamStyle paramStyle) { createParameter(name,
	 * "", type, paramStyle); }
	 */

	/**
	 * Create a Param-instance based on a given name, primitive type and style.
	 * 
	 * @param name
	 *            The name of the parameter.
	 * @param type
	 *            The primitive type of the parameter.
	 * @param paramStyle
	 *            The style of the parameter. (see specification for supported styles)
	 * @return The created Param instance.
	 */
	public static IParam createParameter(String name, Class<?> type, ParamStyle paramStyle) {
		IParam param = new Param();

		param.setName(name);
		param.setStyle(paramStyle);

		// if ((paramStyle != null) && (paramStyle.equals(ParamStyle.query))) {
		// param.setName("FIXME"); // FIXME: name of QueryParam-value!!
		// }

		param.setType(XmlUtil.getPrimitiveXmlType(type));
		return param;
	}
}
