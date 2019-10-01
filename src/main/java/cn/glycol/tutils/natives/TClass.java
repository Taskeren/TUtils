package cn.glycol.tutils.natives;

import static java.util.Objects.requireNonNull;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;

/**
 * This class contains various methods for the classloader
 * 
 * <p>
 * The jar loading methods is merged from
 * <a href="https://github.com/nitu2003/TClassLoader">TClassLoader</a>
 * 
 * <p>
 * The methods in this class all throw a {@code NullPointerException}, if the
 * specified argument is null, except where noted.
 * 
 * @author Taskeren
 * @since 1.0
 */
public class TClass {
	private TClass() {
	}
	
	public static List<Class<?>> findAllClass(String packageName) {
		return findClass(packageName, true);
	}

	/**
	 * Return the list of the classes in the specified package.
	 * 
	 * @param packageName the path of specified package
	 * @return the list of the classes in the specified package
	 * @throws IOException if the specified packageName is null
	 */
	public static List<Class<?>> findClass(String packageName, boolean deep) {
		Objects.requireNonNull(packageName);

		List<Class<?>> ret = new ArrayList<>();
		// If scan sub directory
		boolean recursive = true;
		String packageDirName = packageName.replace(".", "/");
		Enumeration<URL> dirs;

		try {
			dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
		} catch(IOException e) {
			e.printStackTrace();
			return ret;
		}
		while(dirs.hasMoreElements()) {

			URL url = dirs.nextElement();
			String protocol = url.getProtocol();

			if("file".contentEquals(protocol)) {
				try {
					String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
					findClassInPackageByFile(packageName, filePath, recursive, ret);
				} catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		}

		return ret;
	}

	private static void findClassInPackageByFile(String packageName, String filePath, boolean recursive, List<Class<?>> list) throws ClassNotFoundException {
		File dir = new File(filePath);
		if(!dir.exists() || !dir.isDirectory()) {
			return;
		}

		File[] dirFiles = dir.listFiles(new FileFilter() {
			@Override
			public boolean accept(File file) {
				return (recursive && file.isDirectory()) || file.getName().endsWith("class");
			}
		});

		for(File file : dirFiles) {
			if(file.isDirectory()) {
				findClassInPackageByFile(packageName + "." + file.getName(), file.getAbsolutePath(), recursive, list);
			} else {
				String className = file.getName().substring(0, file.getName().length() - 6);
				Class<?> cls = Thread.currentThread().getContextClassLoader().loadClass(packageName + "." + className);
				list.add(cls);
			}
		}
	}

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
	public static void loadExtJar(URL jar) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		loadExtJar((URLClassLoader) ClassLoader.getSystemClassLoader(), jar);
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
	public static void loadExtJar(URLClassLoader loader, URL jar)
			throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		requireNonNull(loader);
		requireNonNull(jar);

		TReflection.invokeMethod(loader, "addURL", new Object[] { jar });
	}

}
