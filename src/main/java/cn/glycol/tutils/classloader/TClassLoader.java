package cn.glycol.tutils.classloader;

import static java.util.Objects.requireNonNull;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;

import cn.glycol.tutils.reflections.TReflection;

/**
 * This class contains various methods for the classloader
 * 
 * <p>
 * This class is merged from
 * <a href="https://github.com/nitu2003/TClassLoader">TClassLoader</a>
 * 
 * <p>
 * The methods in this class all throw a {@code NullPointerException}, if the
 * specified argument is null, except where noted.
 * 
 * @author Taskeren
 * @since 1.0
 */
public class TClassLoader {
	private TClassLoader() {}

	/**
	 * Load a jar file.
	 * 
	 * @param jar the URL of the specified jar
	 * @throws NullPointerException      if the param is null
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public static void loadJar(URL jar) throws NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		loadJar((URLClassLoader) ClassLoader.getSystemClassLoader(), jar);
	}

	/**
	 * Load a jar file
	 * 
	 * @param loader the specified loader
	 * @param jar    the specified jar
	 * @throws NullPointerException      if the param is null
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public static void loadJar(URLClassLoader loader, URL jar) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		requireNonNull(loader);
		requireNonNull(jar);

		TReflection.invokeMethod(loader, "addURL", new Object[] { jar });
	}

}
