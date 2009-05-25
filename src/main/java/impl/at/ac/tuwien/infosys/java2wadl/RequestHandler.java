package at.ac.tuwien.infosys.java2wadl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;

import at.ac.tuwien.infosys.java2wadl.util.ReflectionUtil;
import at.ac.tuwien.infosys.java2wadl.util.XmlUtil;
import at.ac.tuwien.infosys.java2wadl.wadl.IApplication;
import at.ac.tuwien.infosys.java2wadl.wadl.IParam;
import at.ac.tuwien.infosys.java2wadl.wadl.IRequest;
import at.ac.tuwien.infosys.java2wadl.wadl.ParamStyle;
import at.ac.tuwien.infosys.java2wadl.wadl.Request;

/**
 * Class for handling all WADL-Request related suff.
 * 
 * @author <a href="mailto:e0426062@student.tuwien.ac.at">Andreas Meingast</a>
 * @author <a href="mailto:e0325672@student.tuwien.ac.at">Anton Korosec</a>
 */
class RequestHandler {
	/**
	 * Create a Request-instance for a given Java-method.
	 * 
	 * @param method
	 *            Java-method for which the request (parameters) should be handled.
	 * @param application
	 *            The WADL-application instance that should be enhanced.
	 * @return The created request instance.
	 * @throws WadlException
	 */
	public static IRequest handleRequest(Method method, IApplication application) throws WadlException {
		List<Class<?>> parameterTypes = Arrays.asList(method.getParameterTypes());
		List<List<Annotation>> annotations = ReflectionUtil.getParameterAnnotations(method);
		IRequest request = new Request();

		for (int i = 0; i < parameterTypes.size(); i++) {
			Class<?> parameterType = parameterTypes.get(i);
			List<Annotation> parameterAnnotations = annotations.get(i);

			if (ReflectionUtil.containsClass(parameterAnnotations, Context.class)
					|| ReflectionUtil.containsClass(parameterAnnotations, PathParam.class)) {
				// ignore parameters - annotated with Context or PathParam
				continue;
			}

			if (!XmlUtil.isPrimitiveType(parameterType)) {
				List<String> mediaTypes = getMediaTypes(method);

				for (String mediaType : mediaTypes) {
					request.addRepresentation(RepresentationHandler.createRepresentationDefinition(parameterType,
							application, mediaType, null));
				}

			} else {
				ParamStyle style = ParamHandler.getParamStyle(parameterAnnotations);
				String name = parameterType.getSimpleName() + "_" + i;

				if ((style != null) && style.equals(ParamStyle.query)) {
					name = ParamHandler.getQueryParamName(parameterAnnotations);
				}

				IParam param = ParamHandler.createParameter(name, parameterType, style);

				param.setDefault(getDefaultValue(parameterAnnotations));
				param.setRequired(true);
				request.addParam(param);
			}
		}

		return request;
	}

	private static List<String> getMediaTypes(Method m) {
		if (m.isAnnotationPresent(Consumes.class)) {
			return Arrays.asList((m.getAnnotation(Consumes.class)).value());
		} else if (m.getDeclaringClass().isAnnotationPresent(Consumes.class)) {
			return Arrays.asList((m.getDeclaringClass().getAnnotation(Consumes.class)).value());
		} else {
			return Arrays.asList(new String[] { null });
		}
	}

	private static String getDefaultValue(List<Annotation> annotations) {
		for (Annotation parameterAnnotation : annotations) {
			if (parameterAnnotation instanceof DefaultValue) {
				return ((DefaultValue) parameterAnnotation).value();
			}
		}

		return "";
	}
}
