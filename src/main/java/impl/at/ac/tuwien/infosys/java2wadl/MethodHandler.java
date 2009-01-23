package at.ac.tuwien.infosys.java2wadl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;

import at.ac.tuwien.infosys.java2wadl.util.AssertUtil;
import at.ac.tuwien.infosys.java2wadl.util.ReflectionUtil;
import at.ac.tuwien.infosys.java2wadl.util.UriUtil;
import at.ac.tuwien.infosys.java2wadl.wadl.HTTPMethod;
import at.ac.tuwien.infosys.java2wadl.wadl.IApplication;
import at.ac.tuwien.infosys.java2wadl.wadl.IMethodDefinition;
import at.ac.tuwien.infosys.java2wadl.wadl.IMethodReference;
import at.ac.tuwien.infosys.java2wadl.wadl.MethodDefinition;
import at.ac.tuwien.infosys.java2wadl.wadl.MethodReference;

/**
 * Class for handling all WADL-method related stuff.
 * 
 * @author <a href="mailto:e0426062@student.tuwien.ac.at">Andreas Meingast</a>
 * @author <a href="mailto:e0325672@student.tuwien.ac.at">Anton Korosec</a>
 */
public class MethodHandler {

	/**
	 * Create a MethodReference-instance for a given Java-method.
	 * 
	 * @param method
	 *            Java-method for which a reference-object should be created.
	 * @return The created method-reference instance.
	 * @throws WadlException
	 */
	public static IMethodReference getMethodReference(Method method) throws WadlException {
		IMethodReference methodReference = new MethodReference();
		methodReference.setHref(UriUtil.createUri(Consts.METHOD_ID_PREFIX + ReflectionUtil.getFullMethodName(method)));

		return methodReference;
	}

	/**
	 * Create a MethodDefinition-instance for a given Java-method.
	 * 
	 * @param method
	 *            Java-method for which a WADL-method instance should be created.
	 * @param application
	 *            The WADL-application instance that should be enhanced.
	 * @return The created method-definition instance.
	 * @throws WadlException
	 */
	public static IMethodDefinition getMethodDefinition(Method method, IApplication application) throws WadlException {
		AssertUtil.assertNotNull(method);
		AssertUtil.assertNotNull(application);

		IMethodDefinition methodDefinition = new MethodDefinition();

		methodDefinition.setId(getMethodId(method));
		methodDefinition.setName(getHTTPMethod(method));

		methodDefinition.setRequest(RequestHandler.handleRequest(method, application));
		methodDefinition.setResponse(ResponseHandler.handleResponse(method, application));

		return methodDefinition;
	}

	private static URI getMethodId(Method method) throws WadlException {
		return UriUtil.createUri(ReflectionUtil.getFullMethodName(method));
	}

	private static HTTPMethod getHTTPMethod(Method method) {
		List<Annotation> declaredAnnotations = ReflectionUtil.getMethodAnnotations(method);

		if (ReflectionUtil.containsClass(declaredAnnotations, HttpMethod.class)) {
			String value = method.getAnnotation(HttpMethod.class).value();

			if (value.equals("DELETE")) {
				return HTTPMethod.DELETE;
			} else if (value.equals("GET")) {
				return HTTPMethod.GET;
			} else if (value.equals("POST")) {
				return HTTPMethod.POST;
			} else if (value.equals("PUT")) {
				return HTTPMethod.PUT;
			} else if (value.equals("HEAD")) {
				return HTTPMethod.HEAD;
			} else {
				return null;
			}

		} else if (ReflectionUtil.containsClass(declaredAnnotations, DELETE.class)) {
			return HTTPMethod.DELETE;
		} else if (ReflectionUtil.containsClass(declaredAnnotations, GET.class)) {
			return HTTPMethod.GET;
		} else if (ReflectionUtil.containsClass(declaredAnnotations, POST.class)) {
			return HTTPMethod.POST;
		} else if (ReflectionUtil.containsClass(declaredAnnotations, PUT.class)) {
			return HTTPMethod.PUT;
		} else if (ReflectionUtil.containsClass(declaredAnnotations, HEAD.class)) {
			return HTTPMethod.HEAD;
		}

		return null;
	}
}
