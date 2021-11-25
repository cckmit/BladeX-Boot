package org.springblade.test;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;
import org.springblade.common.utils.CompareUtil;
import org.springblade.common.utils.StringCompare.IStringSimilarityService;
import org.springblade.common.utils.StringCompare.StringSimilarityFactory;
import org.springblade.core.tool.support.Kv;
import org.springblade.modules.auth.utils.LDAPAuthentication;
import org.springblade.modules.project.entity.Business;
import org.springblade.modules.project.entity.ChangeDetail;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


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
		entity.setProCompany(Long.valueOf("1"));

		Business entity2 = new Business();
		entity2.setClientName("12");
		entity2.setRecordCode("98");
		entity.setProCompany(Long.valueOf("2"));

		List<Kv> diff = CompareUtil.compareEntityFields(entity, entity2);

		List<ChangeDetail> detail = JSON.parseArray(JSON.toJSONString(diff), ChangeDetail.class);

		//System.out.println(diff);
		System.out.println(detail);

	}

	private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMM");
	private static final AtomicInteger atomicInteger = new AtomicInteger(1000000);

	@Test
	public void testSnowId() {
		String year = String.valueOf(DateTime.now().getYear());
		String month = String.format("%02d", DateTime.now().getMonthOfYear());


		String resultCode = "SJ" + year.substring(year.length() - 2) + month;
		System.out.println(resultCode);
		System.out.println(RandomStringUtils.randomNumeric(8));


		List<String> tmpList = new ArrayList<>();

		for (int i = 0; i < 10000; i++) {
			synchronized (this) {
				String randNum2 = RandomStringUtils.randomNumeric(8) + System.currentTimeMillis();

				if (!tmpList.contains(randNum2)) {
					tmpList.add(randNum2);
				}
			}


		}
//String.valueOf(100000+tmp)

		System.out.println(tmpList.stream().count());


	}


	public static String getOrderIdByUUId() {
		int machineId = 1;//最大支持1-9个集群机器部署
		int hashCodeV = UUID.randomUUID().toString().hashCode();
		if (hashCodeV < 0) {//有可能是负数
			hashCodeV = -hashCodeV;
		}

		String date = simpleDateFormat.format(new Date());
		return date + hashCodeV;
	}

	@Test
	public void testADUser() {
		String account = "18122120828";
		String pwd = "Gdtec@2021*";
		LDAPAuthentication ad = new LDAPAuthentication(account, pwd);
		ad.authenticate();
	}

}
