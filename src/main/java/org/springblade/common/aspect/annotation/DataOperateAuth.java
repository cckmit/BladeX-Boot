package org.springblade.common.aspect.annotation;

import java.lang.annotation.*;

/**
 * @author zhuyilong
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface DataOperateAuth {

}
