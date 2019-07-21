package cn.glycol.tutils.natives;

import static java.util.Objects.requireNonNull;

import java.io.File;
import java.io.IOException;

/**
 * This class contains various methods not found in {@link java.nio.file.Files}.
 * 
 * <p>
 * The methods in this class all throw a {@code NullPointerException}, if the
 * specified argument is null, except where noted.
 * 
 * @author Taskeren
 * @since 1.0
 */
public class TFile {
	private TFile() {
	}

	/**
	 * Create the specified file forcedly, no matter how, like destroying other
	 * files and folders. Usually be used by create data or cache files.
	 * 
	 * @param f the specified file
	 * @throws NullPointerException if the specified file is null
	 * @throws SecurityException
	 * @throws IOException
	 */
	public static void newFile(File f) throws SecurityException, IOException {
		requireNonNull(f);

		// To create the parent folder
		newFolder(f.getParentFile());
		
		// To create the file
		if (!f.isFile()) {
			f.delete();
		}
		if (!f.exists()) {
			f.createNewFile();
		}
	}

	/**
	 * Create the specified folder forcedly, no matter how, like destroy other files
	 * and folders. Usually be used by creating data or cache files.
	 * 
	 * @param f the specified folder
	 * @throws NullPointerException if the specified folder is null
	 * @throws SecurityException
	 * @throws IOException
	 */
	public static void newFolder(File f) throws SecurityException, IOException {
		requireNonNull(f);

		if (!f.isDirectory()) {
			f.delete();
		}

		if (!f.exists()) {
			f.mkdirs();
		}

	}

}
