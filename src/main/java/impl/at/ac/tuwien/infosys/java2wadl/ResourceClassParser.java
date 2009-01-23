package at.ac.tuwien.infosys.java2wadl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

import at.ac.tuwien.infosys.java2wadl.util.AssertUtil;
import at.ac.tuwien.infosys.java2wadl.util.ReflectionUtil;
import at.ac.tuwien.infosys.java2wadl.util.UriUtil;
import at.ac.tuwien.infosys.java2wadl.wadl.IApplication;
import at.ac.tuwien.infosys.java2wadl.wadl.IMethodDefinition;
import at.ac.tuwien.infosys.java2wadl.wadl.IMethodReference;
import at.ac.tuwien.infosys.java2wadl.wadl.IResource;
import at.ac.tuwien.infosys.java2wadl.wadl.Resource;

/**
 * Class used for parsing the resource class.
 * 
 * @author <a href="mailto:e0426062@student.tuwien.ac.at">Andreas Meingast</a>
 * @author <a href="mailto:e0325672@student.tuwien.ac.at">Anton Korosec</a>
 */
public class ResourceClassParser {
	@SuppressWarnings("unchecked")
	private static Class<? extends Annotation>[] RESOURCE_METHOD_ANNOTATIONS = new Class[] { Path.class,
			HttpMethod.class, DELETE.class, GET.class, POST.class, PUT.class, HEAD.class };

	@SuppressWarnings("unchecked")
	private static final Class<? extends Annotation>[] HTTP_METHOD_ANNOTATIONS = new Class[] { HttpMethod.class,
			DELETE.class, GET.class, POST.class, PUT.class, HEAD.class };

	private static List<Class<?>> visitedTypes = new ArrayList<Class<?>>();

	/**
	 * Processes a given Java-class and enhances the given WADL-application with the information (resources, methods,
	 * etc) found.
	 * 
	 * @param type
	 *            The resource class that should be parsed.
	 * @param application
	 *            The WADL-application instance that should be enhanced.
	 * @param classResourcePath
	 *            The path for the given resource class.
	 * @return The enhanced WADL-application instance.
	 * @throws WadlException
	 */
	public IApplication processResource(Class<?> type, IApplication application, String classResourcePath)
			throws WadlException {
		AssertUtil.assertNotNull(type);
		AssertUtil.assertNotNull(application);
		AssertUtil.assertNotNull(classResourcePath);

		markAsVisited(type);

		for (Method method : ReflectionUtil.getMethodsWhereAnnotationPresent(type, RESOURCE_METHOD_ANNOTATIONS)) {
			String methodResourcePath = appendMethodPath(classResourcePath, method);
			IResource methodResource = buildResourceTree(application, methodResourcePath);

			if (!ReflectionUtil.hasOneOfGivenAnnotations(method, HTTP_METHOD_ANNOTATIONS)) {
				if (!wasVisited(method)) {
					processResource(method.getReturnType(), application, methodResourcePath); // recursive
					continue;
				}

				throw new WadlException("Error: Caught Sub-Resource-Locator cycle: " + method.getReturnType());
			}

			addMethodReference(methodResource, method);
			addMethodDefinition(application, method);
		}

		return application;
	}

	private boolean wasVisited(Method method) {
		return visitedTypes.contains(method.getReturnType());
	}

	private boolean markAsVisited(Class<?> type) {
		return visitedTypes.add(type);
	}

	private void addMethodDefinition(IApplication application, Method method) throws WadlException {
		IMethodDefinition methodDefinition = MethodHandler.getMethodDefinition(method, application);
		application.addMethod(methodDefinition);
	}

	private void addMethodReference(IResource methodResource, Method method) throws WadlException {
		IMethodReference methodReference = MethodHandler.getMethodReference(method);
		methodResource.addMethod(methodReference);
	}

	private IResource buildResourceTree(IApplication application, String methodResourcePath) {
		List<IResource> resourceList = application.getResources().getResources();
		IResource methodResource = new Resource();

		for (String token : UriUtil.splitUri(methodResourcePath)) {
			boolean exists = false;

			for (IResource r : resourceList) {
				if (r.getPath().equals(token)) {
					methodResource = r;
					resourceList = r.getResources();
					exists = true;
					break;
				}
			}

			if (exists) {
				continue;
			}

			methodResource = new Resource();
			methodResource.setPath(token);

			resourceList.add(methodResource);
			resourceList = methodResource.getResources();
		}

		return methodResource;
	}

	private String appendMethodPath(String classResourcePath, Method method) {
		String methodResourcePath = classResourcePath;

		if (method.isAnnotationPresent(Path.class)) {
			String path = method.getAnnotation(Path.class).value();

			methodResourcePath += path.startsWith("/") ? path : ("/" + path);
		}

		return methodResourcePath;
	}

	/**
	 * Returns the value of the Path-Annotation from the given Java-class.
	 * 
	 * @param type
	 *            A resource class.
	 * @return The value of a Path-Annotation from the given class.
	 */
	public static String getClassResourcePath(Class<?> type) {
		AssertUtil.assertNotNull(type);
		Path pathAnnotation = type.getAnnotation(Path.class);

		return pathAnnotation == null ? "" : pathAnnotation.value();
	}
}
