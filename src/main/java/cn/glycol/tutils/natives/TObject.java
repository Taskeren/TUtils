package cn.glycol.tutils.natives;

import static java.util.Objects.requireNonNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * This class contains various methods
 * not found in {@link java.util.Collections}.
 * 
 * <p>The methods in this class all throw a {@code NullPointerException},
 * if the specified argument is null, except where noted.
 * 
 * @author Taskeren
 * @since 1.0
 */
public class TObject {
	private TObject() {}

	/**
	 * Return the default, if the given object is null.
	 * Else return the given object.
	 * 
	 * @param object the specified object
	 * @param _default the default value
	 * @throws NullPointerException if the specified default is null
	 * @return the default if the given object is null, else the given object.
	 */
	@Nonnull
	public static <T> T defaultIfNull(T object, T _default) {
		requireNonNull(_default);
		
		return object == null ? _default : object;
	}
	
	/**
	 * Return the first non-null element in the given
	 * elements. Return {@code null}, if all elements are null.
	 * 
	 * @param elements the specified elements
	 * @throws NullPointerException if the specified argument(elements) is null
	 * @return the first non-null element in the given elements.
	 */
	@Nullable
	public static <T> T findNonNull(T[] elements) {
		requireNonNull(elements);
		
		for(T element : elements) {
			if(null != element) {
				return element;
			}
		}
		return null;
	}
	
}
