package cn.glycol.tutils.test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.TYPE, ElementType.FIELD })
/**
 * Only used for auto scanner to scan the method
 * @author Taskeren
 *
 */
public @interface Sandbox {

	String value() default "#Sandbox#";
	
}
