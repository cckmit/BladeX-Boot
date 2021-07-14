package org.springblade.test;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * Blade单元测试
 *
 * @author Chill
 */
@RunWith(BladeSpringRunner.class)
@BladeBootTest(appName = "Application")
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

		Map<String, Kv> diff = CompareUtil.compareEntityFields(entity, entity2);
		System.out.println(diff);

//		//获取实体类 返回的是一个数组 数组的数据就是实体类中的字段
		Field[] fields = entity.getClass().getDeclaredFields();
//		for (int i = 0; i < fields.length; i++) {
//			//有的字段是用private修饰的 将他设置为可读
//			fields[i].setAccessible(true);
//			ApiModelProperty property = fields[i].getAnnotation(ApiModelProperty.class);
//			String temp="";
//			if(property!=null)temp= property.value();
//
//
//
//			try {
//				// 输出属性名和属性值
//				System.out.println("字段名:" + fields[i].getName() + "-----属性值:" + fields[i].get(entity)+"------------标签值："+temp);
//			} catch (IllegalAccessException e) {
//				e.printStackTrace();
//			}
//
//		}

	}
}
