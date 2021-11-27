package org.springblade.common.aspect.inteceptor;

import com.github.xiaoymin.knife4j.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springblade.core.tool.api.R;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 数据操作鉴权
 *
 * @author zhuyilong
 */
@Aspect
@Component
@RequiredArgsConstructor
public class DataOperateInterceptor {

	private final ApplicationContext context;

	@Around(value = "execution(* org.springblade.modules.*.controller.*.*(..)) && @annotation(org.springblade.common.aspect.annotation.DataOperateAuth)")
	public Object permitJudge(ProceedingJoinPoint point) throws Throwable {
		Object target = point.getTarget();
		if (target instanceof DataOperateAuthController) {
			DataOperateAuthController controller = (DataOperateAuthController) target;
			// 默认第一个参数为 ids
			Object ids = point.getArgs()[0];
			if (ids == null || StrUtil.isBlank(ids.toString())) {
				return R.fail("操作数据 ID 不能为空");
			}
			boolean isPermit = controller.isPermit(ids.toString(), point.getSignature().getName(), point);
			if (!isPermit) {
				return R.fail("数据越权");
			}
		}
		return point.proceed(point.getArgs());
	}
}
