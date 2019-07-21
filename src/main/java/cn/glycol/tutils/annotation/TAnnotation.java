package cn.glycol.tutils.annotation;

import static java.util.Objects.requireNonNull;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import cn.glycol.tutils.reflections.TReflection;

public class TAnnotation {
	private TAnnotation() {}

	/**
	 * Return the list of the method with specified annotation of the class with
	 * specified annotation.
	 * 
	 * @param packageName the package name
	 * @param annoClass   the specified class annotation
	 * @param annoMethod  the specified method annotation
	 * @throws NullPointerException if the param is null
	 * @return the list of the method with specified annotation of the class with
	 *         specified annotation.
	 */
	public static List<Method> scan(String packageName, Class<? extends Annotation> annoClass,
			Class<? extends Annotation> annoMethod) {
		requireNonNull(packageName);
		requireNonNull(annoClass);
		requireNonNull(annoMethod);

		ArrayList<Method> list = new ArrayList<>();
		List<Class<?>> clses = TClassScanner.getClassFromPackage(packageName);
		for (Class<?> cls : clses) {
			if (TReflection.getAnnotationClass(cls, annoClass) != null) {
				// Found class
				Method[] methods = cls.getDeclaredMethods();
				for (Method method : methods) {
					if (method.getAnnotation(annoMethod) != null) {
						// Found method
						list.add(method);
					}
				}
			}
		}

		return list;
	}

}
