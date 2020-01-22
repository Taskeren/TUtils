package cn.glycol.tutils.natives;

import static java.util.Objects.requireNonNull;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * This class contains various methods for the reflection.
 * 
 * <p>
 * The methods in this class all throw a {@code NullPointerException}, if the
 * specified argument is null, except where noted.
 * 
 * @author Taskeren
 * @since 1.0
 */
public class TReflection {
	private TReflection() {}

	/**
	 * Return the array of the class of the given objects.
	 * 
	 * @param objects the specified objects
	 * @throws NullPointerException if the specified array of the objects is null
	 * @throws NullPointerException if the element in the given array is null
	 * @return the array of the class of the given objects.
	 */
	public static Class<?>[] objectsToClass(Object[] objects) {
		requireNonNull(objects);

		Class<?>[] clazz = new Class<?>[objects.length];
		for (int i = 0; i < objects.length; i++) {
			requireNonNull(objects[i]);
			clazz[i] = objects[i].getClass();
		}
		return clazz;
	}

	/**
	 * Invoke a non-static method with given params, then return the result.
	 * 
	 * @param instance   the specified instance. For static method
	 * @param name       the name of specified method
	 * @param parameters the specified params
	 * @return the result of invoking the specified method.
	 * @throws NullPointerException      if the param is null, except instance
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public static Object invokeMethod(Object instance, String name, Object[] parameters) throws NoSuchMethodException,
			SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		return invokeMethod(instance, instance.getClass(), name, parameters);
	}

	/**
	 * Invoke a static method with given params, then return the result.
	 * 
	 * @param clazz      the specified class
	 * @param name       the name of specified method
	 * @param parameters the specified params
	 * @return the result of invoking the specified method.
	 * @throws NullPointerException      if the param is null, except instance
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public static Object invokeMethod(Class<?> clazz, String name, Object[] parameters) throws NoSuchMethodException,
			SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		return invokeMethod(null, clazz, name, parameters);
	}

	/**
	 * Invoke a method with given params, then return the result.
	 * 
	 * @param instance   the specified instance. For static method, just
	 *                   {@code null}.
	 * @param clazz      the specified class
	 * @param name       the name of specified method
	 * @param parameters the specified params
	 * @return the result of invoking the specified method.
	 * @throws NullPointerException      if the param is null, except instance
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @see #invokeMethod(Object, String, Object[])
	 * @see #invokeMethod(Class, String, Object[])
	 */
	private static Object invokeMethod(Object instance, Class<?> clazz, String name, Object[] parameters)
			throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		requireNonNull(clazz);
		requireNonNull(name);
		requireNonNull(parameters);

		Method method = clazz.getDeclaredMethod(name, objectsToClass(parameters));
		method.setAccessible(true);
		return method.invoke(instance, parameters);
	}

	/**
	 * Remove the final modifier of the given field.
	 * 
	 * @param field the specified field
	 * @throws NullPointerException     if the specified field is null
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static void breakFinal(Field field)
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		requireNonNull(field);

		Field modifiers = Field.class.getDeclaredField("modifiers");
		modifiers.setAccessible(true);
		if((field.getModifiers() & Modifier.FINAL) == Modifier.FINAL) {
			modifiers.setInt(field, field.getModifiers() & ~Modifier.FINAL);
		}
	}

	/**
	 * Return the specified non-static field.
	 * 
	 * @param instance the specified instance.
	 * @param name     the name of specified field
	 * @return the specified field
	 * @throws NullPointerException     if the param is null, except instance
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static Object getField(Object instance, String name)
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		return getField(instance, instance.getClass(), name);
	}

	/**
	 * Return the specified static field.
	 * 
	 * @param clazz the specified class
	 * @param name  the name of specified field
	 * @return the specified field
	 * @throws NullPointerException     if the param is null, except instance
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static Object getField(Class<?> clazz, String name)
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		return getField(null, clazz, name);
	}

	/**
	 * Return the specified field.
	 * 
	 * @param instance the specified instance. For static field, just {@code null}.
	 * @param clazz    the specified class
	 * @param name     the name of specified field
	 * @return the specified field
	 * @throws NullPointerException     if the param is null, except instance
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	private static Object getField(Object instance, Class<?> clazz, String name)
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		requireNonNull(clazz);
		requireNonNull(name);

		Field field = clazz.getDeclaredField(name);
		field.setAccessible(true);
		breakFinal(field);
		return field.get(instance);
	}

	/**
	 * Set the value of specified non-static field
	 * 
	 * @param instance the specified instance
	 * @param value    the specified value
	 * @param name     the name of specified field
	 * @throws NullPointerException     if the param is null
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static void setField(Object instance, String name, Object value)
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		setField(instance, value, instance.getClass(), name);
	}

	/**
	 * Set the value of specified static field
	 * 
	 * @param value the specified value
	 * @param clazz the specified class
	 * @param name  the name of specified field
	 * @throws NullPointerException     if the param is null
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static void setField(Class<?> clazz, String name, Object value)
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		setField(null, value, clazz, name);
	}

	/**
	 * Set the value of specified field
	 * 
	 * @param instance the specified instance. For static, just {@code null}.
	 * @param value    the specified value
	 * @param clazz    the specified class
	 * @param name     the name of specified field
	 * @throws NullPointerException     if the param is null
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	private static void setField(Object instance, Object value, Class<?> clazz, String name)
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		requireNonNull(clazz);
		requireNonNull(name);

		Field field = clazz.getDeclaredField(name);
		field.setAccessible(true);
		breakFinal(field);
		field.set(instance, value);
	}

	/**
	 * Return a new instance of specified class with specified parameters.
	 * 
	 * @param clazz      the specified class
	 * @param parameters the specified parameters
	 * @return the new instance of specified class
	 * @throws NullPointerException      if the param is null
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public static <T> T newInstance(Class<T> clazz, Object[] parameters)
			throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		requireNonNull(clazz);
		requireNonNull(parameters);

		Constructor<T> constructor = clazz.getConstructor(objectsToClass(parameters));
		constructor.setAccessible(true);
		return constructor.newInstance(parameters);
	}

	/**
	 * Return the specified annotation instance, if could found, else null.
	 * 
	 * @param clazz         the specified class
	 * @param annotationCls the class of specified annotation
	 * @throws NullPointerException if the param is null
	 * @return the instance of the specified annotation, if the given class
	 *         contains, else null.
	 */
	public static <T extends Annotation> T getAnnotationClass(Class<?> clazz, Class<T> annotationCls) {
		requireNonNull(clazz);
		requireNonNull(annotationCls);

		return clazz.getAnnotation(annotationCls);
	}

	/**
	 * Return the specified annotation instance on the specified method, if could
	 * found, else null.
	 * 
	 * @param clazz         the class of the specified method
	 * @param name          the name of the specified method
	 * @param paramTypes    the types of the params of the specified method
	 * @param annotationCls the class of the specified annotation
	 * @return the instance of the specified annotation, if the given method
	 *         contains, else null.
	 * @throws NullPointerException  if the param is null
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	public static <T extends Annotation> T getAnnotationMethod(Class<?> clazz, String name, Class<?>[] paramTypes,
			Class<T> annotationCls) throws NoSuchMethodException, SecurityException {
		requireNonNull(clazz);
		requireNonNull(name);
		requireNonNull(annotationCls);

		Method method = clazz.getDeclaredMethod(name, paramTypes);
		return method.getAnnotation(annotationCls);
	}

	/**
	 * Return this specified annotation instance on the specified field, if could
	 * found, else null.
	 * 
	 * @param clazz         the class of specified field
	 * @param name          the name of the specified field
	 * @param annotationCls the class of specified annotation
	 * @return the instance of the specified annotation, if the given field
	 *         contains, else null.
	 * @throws NullPointerException if the param is null
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 */
	public static <T extends Annotation> T getAnnotationField(Class<?> clazz, String name, Class<T> annotationCls)
			throws NoSuchFieldException, SecurityException {
		requireNonNull(clazz);
		requireNonNull(name);
		requireNonNull(annotationCls);

		Field field = clazz.getDeclaredField(name);
		return field.getAnnotation(annotationCls);
	}

}
