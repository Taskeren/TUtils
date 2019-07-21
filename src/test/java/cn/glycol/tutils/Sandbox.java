package cn.glycol.tutils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.TYPE })
/**
 * Only used for auto scanner to scan the method
 * @author Taskeren
 *
 */
public @interface Sandbox {

}