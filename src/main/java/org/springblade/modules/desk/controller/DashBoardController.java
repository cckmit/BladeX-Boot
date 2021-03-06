package org.springblade.modules.desk.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springblade.core.launch.constant.AppConstant;
import org.springblade.core.tenant.annotation.NonDS;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.support.Kv;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springblade.modules.resource.endpoint.OssEndpoint;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springblade.core.launch.constant.AppConstant;
import org.springblade.core.oss.model.BladeFile;
import org.springblade.core.oss.model.OssFile;
import org.springblade.core.secure.annotation.PreAuth;
import org.springblade.core.tenant.annotation.NonDS;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.constant.RoleConstant;
import org.springblade.core.tool.utils.FileUtil;
import org.springblade.core.tool.utils.Func;
import org.springblade.modules.resource.builder.oss.OssBuilder;
import org.springblade.modules.resource.entity.Attach;
import org.springblade.modules.resource.service.IAttachService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springblade.core.oss.MinioTemplate;
import org.springblade.modules.resource.endpoint.OssEndpoint;

import io.minio.BucketExistsArgs;
import io.minio.CopyObjectArgs;
import io.minio.CopySource;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveBucketArgs;
import io.minio.RemoveObjectArgs;
import io.minio.RemoveObjectsArgs;
import io.minio.SetBucketPolicyArgs;
import io.minio.StatObjectArgs;
import io.minio.StatObjectResponse;
import io.minio.BucketExistsArgs.Builder;
import io.minio.http.Method;
import io.minio.messages.Bucket;
import io.minio.messages.DeleteObject;


import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.utils.DateUtil;
import org.springblade.core.tool.utils.FileUtil;
import org.springblade.core.tool.utils.StringUtil;

/**
 * ??????
 *
 * @author Chill
 */
@NonDS
@ApiIgnore
@RestController
@RequestMapping(AppConstant.APPLICATION_DESK_NAME)
@AllArgsConstructor
@Api(value = "??????", tags = "??????")
public class DashBoardController {


	//minio
	private OssEndpoint ossEndpoint;
	@SneakyThrows
	@PostMapping("/dashboard/putt")
	public R<BladeFile> test(@RequestParam MultipartFile file,String controller){
		R<BladeFile> a = ossEndpoint.put(file,controller);
		return a;
	}
	private final MinioClient client;
	private final MinioTemplate minioTemplate;

	/**
	 * ????????????
	 */
	@GetMapping("/dashboard/activities")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "????????????", notes = "????????????")
	public R activities() {
		List<Map<String, Object>> list = new ArrayList<>();

		Map<String, Object> map1 = new HashMap<>(16);
		map1.put("id", "trend-1");
		map1.put("updatedAt", "2019-01-01");
		map1.put("user", Kv.create().set("name", "?????????").set("avatar", "https://gw.alipayobjects.com/zos/rmsportal/ThXAXghbEsBCCSDihZxY.png"));
		map1.put("group", Kv.create().set("name", "?????????????????????").set("link", "http://github.com/"));
		map1.put("project", Kv.create().set("name", "????????????").set("link", "http://github.com/"));
		map1.put("template", "??? @{group} ???????????? @{project}");
		list.add(map1);

		Map<String, Object> map2 = new HashMap<>(16);
		map2.put("id", "trend-2");
		map2.put("updatedAt", "2019-01-01");
		map2.put("user", Kv.create().set("name", "?????????").set("avatar", "https://gw.alipayobjects.com/zos/rmsportal/ThXAXghbEsBCCSDihZxY.png"));
		map2.put("group", Kv.create().set("name", "?????????????????????").set("link", "http://github.com/"));
		map2.put("project", Kv.create().set("name", "???????????????").set("link", "http://github.com/"));
		map2.put("template", "???  @{group} ???????????? @{project}");
		list.add(map2);

		return R.data(list);
	}

	/**
	 * ????????????
	 */
	@GetMapping("/dashboard/info")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "????????????", notes = "????????????")
	public R info() {
		Map<String, Object> map = new HashMap<>(16);
		map.put("id", "trend-1");
		map.put("updatedAt", "2019-01-01");
		map.put("user", Kv.create().set("name", "?????????").set("avatar", "https://gw.alipayobjects.com/zos/rmsportal/ThXAXghbEsBCCSDihZxY.png"));
		map.put("group", Kv.create().set("name", "?????????????????????").set("link", "http://github.com/"));
		map.put("project", Kv.create().set("name", "????????????").set("link", "http://github.com/"));
		map.put("template", "??? @{group} ???????????? @{project}");
		return R.data(map);
	}

	/**
	 * ????????????
	 */
	@PostMapping("/dashboard/sign")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "????????????", notes = "????????????")
	public R sign() {
		Map<String, Object> map = new HashMap<>(16);
		map.put("user", Kv.create().set("name", "?????????").set("avatar", "https://gw.alipayobjects.com/zos/rmsportal/ThXAXghbEsBCCSDihZxY.png"));
		return R.data(map);
	}

	/**
	 * ????????????
	 */
	@GetMapping("/notice/notices")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "??????", notes = "??????")
	public R notices() {
		List<Map<String, String>> list = new ArrayList<>();
		Map<String, String> map1 = new HashMap<>(16);
		map1.put("logo", "https://spring.io/img/homepage/icon-spring-framework.svg");
		map1.put("title", "SpringBoot");
		map1.put("description", "?????????web????????????????????????spring?????????????????????spring???????????????????????????xml?????????????????? springboot????????????   ???????????????????????????????????????????????????????????????????????????????????????springboot????????????just run??????");
		map1.put("member", "Chill");
		map1.put("href", "http://spring.io/projects/spring-boot");
		list.add(map1);

		Map<String, String> map2 = new HashMap<>(16);
		map2.put("logo", "https://spring.io/img/homepage/icon-spring-cloud.svg");
		map2.put("title", "SpringCloud");
		map2.put("description", "SpringCloud?????????SpringBoot??????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????");
		map2.put("member", "Chill");
		map2.put("href", "http://spring.io/projects/spring-cloud");
		list.add(map2);

		Map<String, String> map3 = new HashMap<>(16);
		map3.put("logo", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1546359961068&di=05ff9406e6675ca9a58a525a7e7950b9&imgtype=jpg&src=http%3A%2F%2Fimg0.imgtn.bdimg.com%2Fit%2Fu%3D575314515%2C4268715674%26fm%3D214%26gp%3D0.jpg");
		map3.put("title", "Mybatis");
		map3.put("description", "MyBatis ?????????????????????????????????????????????????????? SQL????????????????????????????????????MyBatis ???????????????????????? JDBC ???????????????????????????????????????????????????MyBatis ????????????????????? XML ?????????????????????????????????????????????????????? Java ??? POJOs(Plain Old Java Objects,????????? Java??????)?????????????????????????????????");
		map3.put("member", "Chill");
		map3.put("href", "http://www.mybatis.org/mybatis-3/getting-started.html");
		list.add(map3);

		Map<String, String> map4 = new HashMap<>(16);
		map4.put("logo", "https://gw.alipayobjects.com/zos/rmsportal/kZzEzemZyKLKFsojXItE.png");
		map4.put("title", "React");
		map4.put("description", "React ????????? Facebook ??????????????????????????????????????????????????? JavaScript MVC ???????????????????????????????????????????????????????????????Instagram ??????????????????????????????????????????????????????????????????2013???5???????????????");
		map4.put("member", "Chill");
		map4.put("href", "https://reactjs.org/");
		list.add(map4);

		Map<String, String> map5 = new HashMap<>(16);
		map5.put("logo", "https://gw.alipayobjects.com/zos/rmsportal/dURIMkkrRFpPgTuzkwnB.png");
		map5.put("title", "Ant Design");
		map5.put("description", "??????????????????????????????????????????????????????????????????????????????????????? Ant Design??????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????Ant Design??????UI??????????????????????????????????????????????????????????????????");
		map5.put("member", "Chill");
		map5.put("href", "https://ant.design/docs/spec/introduce-cn");
		list.add(map5);

		Map<String, String> map6 = new HashMap<>(16);
		map6.put("logo", "https://gw.alipayobjects.com/zos/rmsportal/sfjbOqnsXXJgNCjCzDBL.png");
		map6.put("title", "Ant Design Pro");
		map6.put("description", "Ant Design Pro ????????????????????????????????????????????????/??????????????????????????????????????????'???????????????+???????????????'????????????");
		map6.put("member", "Chill");
		map6.put("href", "https://pro.ant.design");
		list.add(map6);

		return R.data(list);
	}

	/**
	 * ??????????????????
	 */
	@GetMapping("/notice/my-notices")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "??????", notes = "??????")
	public R myNotices() {
		List<Map<String, String>> list = new ArrayList<>();
		Map<String, String> map1 = new HashMap<>(16);
		map1.put("id", "000000001");
		map1.put("avatar", "https://gw.alipayobjects.com/zos/rmsportal/ThXAXghbEsBCCSDihZxY.png");
		map1.put("title", "???????????? 14 ????????????");
		map1.put("datetime", "2018-08-09");
		map1.put("type", "notification");
		list.add(map1);

		Map<String, String> map2 = new HashMap<>(16);
		map2.put("id", "000000002");
		map2.put("avatar", "https://gw.alipayobjects.com/zos/rmsportal/OKJXDXrmkNshAMvwtvhu.png");
		map2.put("title", "???????????? ????????? ????????????????????????");
		map2.put("datetime", "2018-08-08");
		map2.put("type", "notification");
		list.add(map2);


		Map<String, String> map3 = new HashMap<>(16);
		map3.put("id", "000000003");
		map3.put("avatar", "https://gw.alipayobjects.com/zos/rmsportal/fcHMVNCjPOsbUGdEduuv.jpeg");
		map3.put("title", "????????? ????????????");
		map3.put("description", "????????????????????????????????????");
		map3.put("datetime", "2018-08-07");
		map3.put("type", "message");
		map3.put("clickClose", "true");
		list.add(map3);


		Map<String, String> map4 = new HashMap<>(16);
		map4.put("id", "000000004");
		map4.put("avatar", "https://gw.alipayobjects.com/zos/rmsportal/fcHMVNCjPOsbUGdEduuv.jpeg");
		map4.put("title", "????????? ????????????");
		map4.put("description", "??????????????????????????????????????????????????????????????????????????????");
		map4.put("type", "message");
		map4.put("datetime", "2018-08-07");
		map4.put("clickClose", "true");
		list.add(map4);


		Map<String, String> map5 = new HashMap<>(16);
		map5.put("id", "000000005");
		map5.put("title", "????????????");
		map5.put("description", "??????????????? 2018-01-12 20:00 ?????????");
		map5.put("extra", "?????????");
		map5.put("status", "todo");
		map5.put("type", "event");
		list.add(map5);

		return R.data(list);
	}

}
