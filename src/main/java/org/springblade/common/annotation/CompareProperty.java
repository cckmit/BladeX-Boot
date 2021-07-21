package org.springblade.common.annotation;

import java.lang.annotation.*;

@Target({ElementType.FIELD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CompareProperty {
	/**
	 * 是否忽略
	 * @return
	 */
	boolean isIgnore() default false;
}
