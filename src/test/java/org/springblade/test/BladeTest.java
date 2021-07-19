package org.springblade.test;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiModelProperty;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springblade.common.utils.CompareUtil;
import org.springblade.common.utils.StringCompare.IStringSimilarityService;
import org.springblade.common.utils.StringCompare.StringSimilarityFactory;
import org.springblade.common.utils.StringUtil;
import org.springblade.core.test.BladeBootTest;
import org.springblade.core.test.BladeSpringRunner;
import org.springblade.core.tool.support.Kv;
import org.springblade.modules.project.entity.Business;
import org.springblade.modules.project.entity.ChangeDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Blade单元测试
 *
 * @author Chill
 */
//@RunWith(BladeSpringRunner.class)
//@BladeBootTest(appName = "blade-runner")
public class BladeTest {

	@Autowired
	private StringSimilarityFactory stringCompareFactory;

	@Test
	public void contextLoads() {
		System.out.println("测试～～～～～～");
	}

	@Test
	public void testString() {

		String a = "茂名市荔枝优势产区产业园荔枝产业大数据中心建设一体化项目";
		String b = "茂名市荔枝产业园大数据平台项目";
		//double c = StringUtil.getSimilarityRatio(a, b);
		//double d = StringUtil.stringOccurrenceRate(a, b);

		//构建渠道类型对应的服务类
		IStringSimilarityService compareService = stringCompareFactory.buildService("CXL");

		IStringSimilarityService compareService2 = stringCompareFactory.buildService("XSD");
		//发送短信
		double c = compareService.stringCompare(a, b);
		double d = compareService2.stringCompare(a, b);

		System.out.println(c + "               " + d);
	}

	@Test
	public void testGetEntiyValue() {
		Business entity = new Business();
		entity.setClientName("1");
		entity.setRecordCode("99");

		Business entity2 = new Business();
		entity2.setClientName("12");
		entity2.setRecordCode("98");

		List<Kv> diff = CompareUtil.compareEntityFields(entity, entity2);

		List<ChangeDetail> detail= JSON.parseObject(JSON.toJSONString(diff), List.class);

		//System.out.println(diff);
		System.out.println(detail);
		System.out.println(entity.getId());
	}
}
