package cn.glycol.tutils.natives;

import static java.util.Objects.requireNonNull;

import java.util.Collection;

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
public class TCollection {
	private TCollection() {}

	/**
	 * Returns a string representation of the contents of the specified array.
     * If the array contains other arrays as elements, they are converted to
     * strings by the {@link Object#toString} method inherited from
     * <tt>Object</tt>, which describes their <i>identities</i> rather than
     * their contents.
     * 
	 * @param collection the specified collection
	 * @throws NullPointerException if the specified collection is null
	 * @return the string representation of the given element.
	 */
	public static String toString(Collection<?> collection) {
		requireNonNull(collection);
		
		return java.util.Arrays.toString(collection.toArray(new Object[collection.size()]));
	}
	
	/**
	 * Return the first non-null element in the given
	 * elements. Return {@code null}, if all elements are null.
	 * 
	 * @param elements the specified elements
	 * @throws NullPointerException if the specified argument(elements) is null
	 * @return the first non-null element in the given elements.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T findNonNull(Collection<T> elements) {
		return (T) TObject.findNonNull(elements.toArray());
	}
	
}
