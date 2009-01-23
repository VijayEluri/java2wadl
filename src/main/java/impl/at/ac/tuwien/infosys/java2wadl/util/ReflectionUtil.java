package at.ac.tuwien.infosys.java2wadl.util;

import static at.ac.tuwien.infosys.java2wadl.util.AssertUtil.assertNotNull;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import at.ac.tuwien.infosys.java2wadl.wadl.Param;

/**
 * Utility class for handling reflection stuff.
 * 
 * @author <a href="mailto:e0426062@student.tuwien.ac.at">Andreas Meingast</a>
 * @author <a href="mailto:e0325672@student.tuwien.ac.at">Anton Korosec</a>
 */
public class ReflectionUtil {

	/**
	 * For a give {@link Method} m determine {@link Annotation}s of it's {@link Param}s in the following manner:
	 * <ul>
	 * <li>Check if the given {@link Method} has {@link Annotation}s. If <code>true</code>, return a {@link List}
	 * of it's {@link Param} annotations.</li>
	 * <li>If <code>false</code> recurse on the super-class.</li>
	 * If the super-class is <code>null</code>, return an empty {@link List}.
	 * </ul>
	 * 
	 * {@link Annotation}s for {@link Param}s are mapped into a {@link List} of {@link List}s.
	 * 
	 * @see java.lang.reflect.Method#getParameterAnnotations .
	 * 
	 * @param m
	 *            The {@link Method} for which to determine the {@link Annotation}s.
	 * @return The {@link List} of {@link Annotation} for the given {@link Method}.
	 */
	public static List<List<Annotation>> getParameterAnnotations(Method m) {
		Method method = (Method) getFirstAnnotatedAccessibleObject(m.getDeclaringClass(), m);

		if (method != null) {
			Annotation[][] annotations = method.getParameterAnnotations();
			List<List<Annotation>> response = new ArrayList<List<Annotation>>();

			if (annotations != null) {
				for (int i = 0; i < annotations.length; i++) {
					response.add(Arrays.asList(annotations[i]));
				}
			}

			return response;
		}
		return new ArrayList<List<Annotation>>();
	}

	/**
	 * For a give {@link Method} m determine {@link Annotation}s in the following manner:
	 * <ul>
	 * <li>Check if the given {@link Method} has {@link Annotation}s. If <code>true</code>, return them.</li>
	 * <li>If <code>false</code> recurse on the super-class.</li>
	 * If the super-class is <code>null</code>, return <code>null</code>.
	 * </ul>
	 * 
	 * @param m
	 *            The {@link Method} for which to determine the {@link Annotation}s.
	 * @return The {@link List} of {@link Annotation} for the given {@link Method}.
	 */
	public static List<Annotation> getMethodAnnotations(Method m) {
		Method method = (Method) getFirstAnnotatedAccessibleObject(m.getDeclaringClass(), m);

		if (m != null) {
			return Arrays.asList(method.getDeclaredAnnotations());
		}
		return new ArrayList<Annotation>();
	}

	private static AccessibleObject getFirstAnnotatedAccessibleObject(Class<?> type, AccessibleObject ao) {
		if (type == null) {
			return null;
		} else if (ao.getDeclaredAnnotations() != null) {
			return ao;
		} else {
			Class<?> superClass = type.getSuperclass();
			try {
				if (superClass != null) {
					if (ao instanceof Field) {
						Field f = (Field) ao;

						return getFirstAnnotatedAccessibleObject(superClass, superClass.getField(f.getName()));
					} else if (ao instanceof Method) {
						Method m = (Method) ao;

						return getFirstAnnotatedAccessibleObject(superClass, superClass.getMethod(m.getName(), m
								.getParameterTypes()));
					} else if (ao instanceof Constructor<?>) {
						Constructor<?> c = (Constructor<?>) ao;

						return getFirstAnnotatedAccessibleObject(superClass, superClass.getConstructor(c
								.getParameterTypes()));
					} else {
						throw new RuntimeException("Unsupported Accessible Object");
					}
				}
				return null;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * Recursively (w.r.t. class-hierarchy) look up the first {@link Class} where at least one Annotation is present.
	 * 
	 * @param type
	 *            The {@link Class} for which to find the first annotated super-class. May be <code>null</code>.
	 * @return The first {@link Class} in the {@link Class}-hierarchy where an {@link Annotation} is present.
	 */
	public static Class<?> getFirstAnnotatedClass(Class<?> type) {
		if (type == null) {
			return null;
		} else if (type.getDeclaredAnnotations() != null) {
			return type;
		} else {
			return getFirstAnnotatedClass(type.getSuperclass());
		}
	}

	/**
	 * For a given {@link Class} generate a {@link List} of {@link Method}s where at least one {@link Annotation} from
	 * annotations is present.
	 * 
	 * @param type
	 *            The {@link Class} that will be checked. Must not be <code>null</code>.
	 * @param annotations
	 *            An Array of annotation that will be used to check the {@link Class}. Must not be <code>null</code>.
	 * @return A {@link List} of {@link Method}s where at least one {@link Annotation} from the parameter annotations
	 *         is present
	 */
	public static List<Method> getMethodsWhereAnnotationPresent(Class<?> type, Class<? extends Annotation>[] annotations) {
		assertNotNull(type);
		assertNotNull(annotations);

		List<Method> annotatedMethods = new ArrayList<Method>();
		List<Class<? extends Annotation>> annotationsList = Arrays.asList(annotations);

		for (Method m : type.getMethods()) {
			for (Annotation a : getMethodAnnotations(m)) {
				if (annotationsList.contains(a.annotationType())) {
					annotatedMethods.add(m);
					break;
				}
			}
		}

		return annotatedMethods;
	}

	/**
	 * Determines the name of a given method. The name consists of:
	 * 
	 * <ul>
	 * <li>The declaring {@link Class}
	 * <li>The actual name of the {@link Method}
	 * <li>The names of the ParameterTypes.
	 * </ul>
	 * 
	 * Since Java does not allow overloading on return-types, the return-type is ignored.
	 * 
	 * @param method
	 *            The method for which the name will be generated. Must not be <code>null</code>.
	 * @return The name.
	 */
	public static String getFullMethodName(Method method) {
		assertNotNull(method);

		List<String> parameterTypes = new ArrayList<String>();

		for (Class<?> c : method.getParameterTypes()) {
			parameterTypes.add(c.getSimpleName());
		}

		return method.getDeclaringClass().getSimpleName() + "_" + method.getName() + "_"
				+ StringUtil.join(parameterTypes, "_");
	}

	/**
	 * Checks if a given {@link Method} is annotated with at least one {@link Annotation} present in annotations.
	 * 
	 * @param method
	 *            The {@link Method} that will be checked.
	 * @param annotations
	 *            An Array of annotations.
	 * @return <code>true</code> if there is at least one annotation present. Otherwise <code>false</code>.
	 */
	public static boolean hasOneOfGivenAnnotations(Method method, Class<? extends Annotation>[] annotations) {
		assertNotNull(method);
		assertNotNull(annotations);

		List<Class<? extends Annotation>> annotationsList = Arrays.asList(annotations);

		for (Annotation a : getMethodAnnotations(method)) {
			if (annotationsList.contains(a.annotationType())) {
				return true;
			}
		}

		return false;
	}

	/**
	 * For a given {@link List} of {@link Annotation}s check if there is at least one {@link Annotation} with
	 * {@link Class} type.
	 * 
	 * @param as
	 *            The {@link List} of {@link Annotation}s. Must not be <code>null</code>.
	 * @param type
	 *            The type of the {@link Class} against which the {@link Annotation} will be checked. Must not be
	 *            <code>null</code>.
	 * @return <code>true</code> if there is at least one {@link Annotation} with the given type. Otherwise
	 *         <code>false</code>.
	 */
	public static boolean containsClass(List<? extends Annotation> as, Class<? extends Annotation> type) {
		assertNotNull(as);
		assertNotNull(type);

		for (Annotation a : as) {
			if (a.annotationType().equals(type)) {
				return true;
			}
		}
		return false;
	}
}
