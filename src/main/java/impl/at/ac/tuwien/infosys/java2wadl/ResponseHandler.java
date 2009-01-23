package at.ac.tuwien.infosys.java2wadl;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import at.ac.tuwien.infosys.java2wadl.util.XmlUtil;
import at.ac.tuwien.infosys.java2wadl.wadl.FaultDefinition;
import at.ac.tuwien.infosys.java2wadl.wadl.IApplication;
import at.ac.tuwien.infosys.java2wadl.wadl.IFaultDefinition;
import at.ac.tuwien.infosys.java2wadl.wadl.IResponse;
import at.ac.tuwien.infosys.java2wadl.wadl.ParamStyle;

/**
 * Class for handling all WADL-Response related suff.
 * 
 * @author <a href="mailto:e0426062@student.tuwien.ac.at">Andreas Meingast</a>
 * @author <a href="mailto:e0325672@student.tuwien.ac.at">Anton Korosec</a>
 */
public class ResponseHandler {
	/**
	 * Create a Response-instance for a given Java-method.
	 * 
	 * @param method
	 *            Java-method for which the response (returnType) should be handled.
	 * @param application
	 *            The WADL-application instance that should be enhanced.
	 * @return The created response instance.
	 * @throws WadlException
	 */
	public static IResponse handleResponse(Method method, IApplication application) throws WadlException {
		IResponse response = new at.ac.tuwien.infosys.java2wadl.wadl.Response();

		if (method.getReturnType() == Void.TYPE) {
			// returnType is VOID
			response.addRepresentation(RepresentationHandler.createRepresentationDefinition(null, 204));

		} else if (method.getReturnType() == Response.class || method.getReturnType() == GenericEntity.class) {
			// returnType is predefined WADL-standard returnType
			List<String> mediaTypes = getMediaTypes(method);

			for (String mediaType : mediaTypes) {
				response.addRepresentation(RepresentationHandler.createRepresentationDefinition(mediaType, 200));
			}

		} else if (XmlUtil.isPrimitiveType(method.getReturnType())) {
			// returnType is primitive Java-type
			response.addParam(ParamHandler.createParameter(method.getReturnType().getSimpleName(), method
					.getReturnType(), ParamStyle.header));

		} else {
			// returnType is (complex) Java-type
			List<String> mediaTypes = getMediaTypes(method);

			for (String mediaType : mediaTypes) {
				response.addRepresentation(RepresentationHandler.createRepresentationDefinition(method.getReturnType(),
						application, mediaType, 200));
			}
		}

		handleExceptions(method, response);
		return response;
	}

	private static List<String> getMediaTypes(Method m) {
		if (m.isAnnotationPresent(Produces.class)) {
			return Arrays.asList((m.getAnnotation(Produces.class)).value());
		} else if (m.getDeclaringClass().isAnnotationPresent(Produces.class)) {
			return Arrays.asList((m.getDeclaringClass().getAnnotation(Produces.class)).value());
		} else {
			return Arrays.asList(new String[] { null });
		}
	}

	private static void handleExceptions(Method method, IResponse response) {
		Class<?>[] es = method.getExceptionTypes();

		if (es != null && es.length > 0) {
			IFaultDefinition fault = new FaultDefinition();

			fault.addStatus(400);
			response.addFault(fault);
		}
	}
}
