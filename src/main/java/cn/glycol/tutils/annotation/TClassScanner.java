package cn.glycol.tutils.annotation;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;

public class TClassScanner {

	/**
	 * Return the list of the classes in the specified package.
	 * @param packageName the path of specified package
	 * @return the list of the classes in the specified package
	 * @throws IOException if the specified packageName is null
	 */
	public static List<Class<?>> getClassFromPackage(String packageName) {
		Objects.requireNonNull(packageName);
		
		List<Class<?>> ret = new ArrayList<>();
		// If scan sub directory
		boolean recursive = true;
		String packageDirName = packageName.replace(".", "/");
		Enumeration<URL> dirs;
		
		try {
			dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
		} catch (IOException e) {
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
	
}
