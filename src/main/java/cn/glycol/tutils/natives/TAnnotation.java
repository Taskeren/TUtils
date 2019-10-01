package cn.glycol.tutils.natives;

import static java.util.Objects.requireNonNull;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TAnnotation {
	private TAnnotation() {
	}

	/**
	 * Return the list of the method with the given annotation and the annotation on
	 * the method.
	 * 
	 * @param packageName the package name
	 * @param annoClass   the specified class annotation, nullable
	 * @param annoMethod  the specified method annotation
	 * @throws NullPointerException if the param is null
	 * @return the list of ScanResult that contains the method and the annotation.
	 */
	public static <T extends Annotation> List<ScanResult<Method, T>> scanMethod(String packageName,
			Class<? extends Annotation> annoClass, Class<T> annoMethod) {
		requireNonNull(packageName);
		requireNonNull(annoMethod);

		ArrayList<ScanResult<Method, T>> list = new ArrayList<>();
		List<Class<?>> clses = TClass.findAllClass(packageName);
		for (Class<?> cls : clses) {
			if (annoClass == null || TReflection.getAnnotationClass(cls, annoClass) != null) {
				// Found class
				Method[] methods = cls.getDeclaredMethods();
				for (Method method : methods) {
					T annotation;
					if ((annotation = method.getAnnotation(annoMethod)) != null) {
						// Found method
						list.add(new ScanResult<Method, T>(method, annotation));
					}
				}
			}
		}

		return list;
	}

	/**
	 * Return the list of the class with the given annotation and the annotation on
	 * the class.
	 * 
	 * @param packageName the package name
	 * @param annoClass   the specified annotation
	 * @throws NullPointerException if the param is null
	 * @return the list of ScanResult that contains the class and the annotation.
	 */
	public static <T extends Annotation> List<ScanResult<Class<?>, T>> scanClass(String packageName,
			Class<T> annoClass) {
		requireNonNull(packageName);
		requireNonNull(annoClass);

		ArrayList<ScanResult<Class<?>, T>> list = new ArrayList<>();
		List<Class<?>> clses = TClass.findAllClass(packageName);
		for (Class<?> cls : clses) {
			T annotation;
			if ((annotation = TReflection.getAnnotationClass(cls, annoClass)) != null) {
				list.add(new ScanResult<Class<?>, T>(cls, annotation));
			}
		}

		return list;
	}

	/**
	 * Return the list of the field with the given annotation and the annotation on
	 * the field
	 * 
	 * @param packageName the package name
	 * @param annoClass   the annotation on the specified class, nullable
	 * @param annoField   the annotation on the specified field
	 * @return the list of result that contains the field and the annotation.
	 */
	public static <T extends Annotation> List<ScanResult<Field, T>> scanField(String packageName, Class<T> annoClass,
			Class<T> annoField) {
		requireNonNull(packageName);
		requireNonNull(annoField);

		ArrayList<ScanResult<Field, T>> list = new ArrayList<>();
		List<Class<?>> clses = TClass.findAllClass(packageName);
		for (Class<?> cls : clses) {
			if (annoClass == null || cls.getAnnotation(annoClass) != null) {
				// Found class
				Field[] fields = cls.getDeclaredFields();
				for (Field field : fields) {
					T annotation;
					if ((annotation = field.getAnnotation(annoField)) != null) {
						list.add(new ScanResult<Field, T>(field, annotation));
					}
				}
			}
		}

		return list;
	}

	/**
	 * This class is used to store the result of Annotation Scanning.
	 * 
	 * @author Taskeren
	 *
	 * @param <R> the result like Method, Class and Field
	 * @param <A> the Annotation
	 */
	public static class ScanResult<R, A extends Annotation> {

		final R result;
		final A annotation;

		public ScanResult(R result, A annotation) {
			this.result = result;
			this.annotation = annotation;
		}

		public R getResult() {
			return result;
		}

		public A getAnnotation() {
			return annotation;
		}

	}

}
