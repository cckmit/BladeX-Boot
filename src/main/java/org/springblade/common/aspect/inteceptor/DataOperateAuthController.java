package org.springblade.common.aspect.inteceptor;

import org.aspectj.lang.JoinPoint;

/**
 * @author zhuyilong
 */
public interface DataOperateAuthController {

	boolean isPermit(String ids, String methodName, JoinPoint point);
}
