package cn.glycol.tutils.natives;

import static java.util.Objects.requireNonNull;

import java.util.function.Consumer;

import javax.annotation.Nullable;

/**
 * This class contains various methods
 * not found in {@link java.util.Arrays}.
 * 
 * <p>The methods in this class all throw a {@code NullPointerException},
 * if the specified argument is null, except where noted.
 * 
 * @author Taskeren
 * @since 1.0
 */
public class TArray {
	private TArray() {}

	/**
	 * Perform the given action for each element of the given elements
	 * until all elements have been processed or the action throws an exception.
	 * 
	 * @param elements the specified action
	 * @param action the specified elements
	 * @throws NullPointerException if the specified action or elements is null
	 */
	public static <T> void forEach(T[] elements, Consumer<T> action) {
		requireNonNull(elements);
		requireNonNull(action);
		
		for(T element : elements) {
			action.accept(element);
		}
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
		return TObject.findNonNull(elements);
	}

}
