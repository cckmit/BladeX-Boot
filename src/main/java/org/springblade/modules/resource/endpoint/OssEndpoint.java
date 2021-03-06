/*
 *      Copyright (c) 2018-2028, Chill Zhuang All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 *  Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the dreamlu.net developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: Chill 庄骞 (smallchill@163.com)
 */
package org.springblade.modules.resource.endpoint;

import io.minio.*;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.springblade.common.constant.CommonConstant;
import org.springblade.common.constant.OssConstant;
import org.springblade.core.launch.constant.AppConstant;
import org.springblade.core.oss.MinioTemplate;
import org.springblade.core.oss.model.BladeFile;
import org.springblade.core.oss.model.OssFile;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.secure.annotation.PreAuth;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tenant.annotation.NonDS;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.constant.RoleConstant;
import org.springblade.core.tool.utils.DateUtil;
import org.springblade.core.tool.utils.FileUtil;
import org.springblade.core.tool.utils.Func;
import org.springblade.core.tool.utils.StringUtil;
import org.springblade.modules.resource.builder.oss.OssBuilder;
import org.springblade.modules.resource.entity.Attach;
import org.springblade.modules.resource.service.IAttachService;
import org.springblade.modules.system.mapper.DeptMapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * 对象存储端点
 *
 * @author Chill
 */
@NonDS
@RestController
@AllArgsConstructor
@Api(value = "对象存储端点", tags = "对象存储端点")
@RequestMapping(AppConstant.APPLICATION_RESOURCE_NAME + "/oss/endpoint")
public class OssEndpoint {

	/**
	 * 对象存储构建类
	 */
	private final OssBuilder ossBuilder;

	/**
	 * 附件表服务
	 */
	private final IAttachService attachService;

	/**
	 * 创建存储桶
	 *
	 * @param bucketName 存储桶名称
	 * @return Bucket
	 */
	@SneakyThrows
	@PostMapping("/make-bucket")
	@PreAuth(RoleConstant.HAS_ROLE_ADMIN)
	public R makeBucket(@RequestParam String bucketName) {
		ossBuilder.template().makeBucket(bucketName);
		return R.success("创建成功");
	}

	/**
	 * 创建存储桶
	 *
	 * @param bucketName 存储桶名称
	 * @return R
	 */
	@SneakyThrows
	@PostMapping("/remove-bucket")
	@PreAuth(RoleConstant.HAS_ROLE_ADMIN)
	public R removeBucket(@RequestParam String bucketName) {
		ossBuilder.template().removeBucket(bucketName);
		return R.success("删除成功");
	}

	/**
	 * 拷贝文件
	 *
	 * @param fileName       存储桶对象名称
	 * @param destBucketName 目标存储桶名称
	 * @param destFileName   目标存储桶对象名称
	 * @return R
	 */
	@SneakyThrows
	@PostMapping("/copy-file")
	public R copyFile(@RequestParam String fileName, @RequestParam String destBucketName, String destFileName) {
		ossBuilder.template().copyFile(fileName, destBucketName, destFileName);
		return R.success("操作成功");
	}



	/**
	 * 获取文件信息
	 *
	 * @param fileName 存储桶对象名称
	 * @return InputStream
	 */
	@SneakyThrows
	@GetMapping("/stat-file")
	public R<OssFile> statFile(@RequestParam String fileName) {
		return R.data(ossBuilder.template().statFile(fileName));
	}

	/**
	 * 获取文件相对路径
	 *
	 * @param fileName 存储桶对象名称
	 * @return String
	 */
	@SneakyThrows
	@GetMapping("/file-path")
	public R<String> filePath(@RequestParam String fileName) {
		return R.data(ossBuilder.template().filePath(fileName));
	}




	/**
	 * 上传文件
	 *
	 * @param file 文件
	 * @return ObjectStat
	 */
	@SneakyThrows
	@PostMapping("/put-file")
	public R<BladeFile> putFile(@RequestParam MultipartFile file) {
		BladeFile bladeFile = ossBuilder.template().putFile(file.getOriginalFilename(), file.getInputStream());
//		BladeFile bladeFile = ossBuilder.template("minioo").putFile(file.getOriginalFilename(), file.getInputStream());
		return R.data(bladeFile);
	}

	/**
	 * 上传文件
	 *
	 * @param fileName 存储桶对象名称
	 * @param file     文件
	 * @return ObjectStat
	 */
	@SneakyThrows
	@PostMapping("/put-file-by-name")
	public R<BladeFile> putFile(@RequestParam String fileName, @RequestParam MultipartFile file) {
		BladeFile bladeFile = ossBuilder.template().putFile(fileName, file.getInputStream());
		return R.data(bladeFile);
	}

	/**
	 * 上传文件并保存至附件表
	 *
	 * @param file 文件
	 * @return ObjectStat
	 */
	@SneakyThrows
	@PostMapping("/put-file-attach")
	public R<BladeFile> putFileAttach(@RequestParam MultipartFile file) {
		String fileName = file.getOriginalFilename();
		BladeFile bladeFile = ossBuilder.template().putFile(fileName, file.getInputStream());
		Long attachId = buildAttach(fileName, file.getSize(), bladeFile);
		bladeFile.setAttachId(attachId);
		return R.data(bladeFile);
	}

	/**
	 * 上传文件并保存至附件表
	 *
	 * @param fileName 存储桶对象名称
	 * @param file     文件
	 * @return ObjectStat
	 */
	@SneakyThrows
	@PostMapping("/put-file-attach-by-name")
	public R<BladeFile> putFileAttach(@RequestParam String fileName, @RequestParam MultipartFile file) {
		BladeFile bladeFile = ossBuilder.template().putFile(fileName, file.getInputStream());
		Long attachId = buildAttach(fileName, file.getSize(), bladeFile);
		bladeFile.setAttachId(attachId);
		return R.data(bladeFile);
	}

	/**
	 * 构建附件表
	 *
	 * @param fileName  文件名
	 * @param fileSize  文件大小
	 * @param bladeFile 对象存储文件
	 * @return attachId
	 */
	private Long buildAttach(String fileName, Long fileSize, BladeFile bladeFile) {
		String fileExtension = FileUtil.getFileExtension(fileName);
		Attach attach = new Attach();
		attach.setDomain(bladeFile.getDomain());
		attach.setLink(bladeFile.getLink());
		attach.setName(bladeFile.getName());
		attach.setOriginalName(bladeFile.getOriginalName());
		attach.setAttachSize(fileSize);
		attach.setExtension(fileExtension);
		attachService.save(attach);
		return attach.getId();
	}

	/**
	 * 删除文件
	 *
	 * @param fileName 存储桶对象名称
	 * @return R
	 */
	@SneakyThrows
	@PostMapping("/remove-file")
	@PreAuth(RoleConstant.HAS_ROLE_ADMIN)
	public R removeFile(@RequestParam String fileName) {
		ossBuilder.template().removeFile(fileName);
		return R.success("操作成功");
	}

	/**
	 * 批量删除文件
	 *
	 * @param fileNames 存储桶对象名称集合
	 * @return R
	 */
	@SneakyThrows
	@PostMapping("/remove-files")
	@PreAuth(RoleConstant.HAS_ROLE_ADMIN)
	public R removeFiles(@RequestParam String fileNames) {
		ossBuilder.template().removeFiles(Func.toStrList(fileNames));
		return R.success("操作成功");
	}

	//=============业务上传===========================
	private final MinioClient client;
	private final MinioTemplate minioTemplate;
	private final DeptMapper deptMapper;
	@SneakyThrows
	@PostMapping("/put-object")
	public R<BladeFile> put(@RequestParam(name = "file") MultipartFile file,@RequestParam(name = "controller") String controller) {
		String filename = fileName(file.getOriginalFilename(),controller);
		client.putObject((PutObjectArgs) ((io.minio.PutObjectArgs.Builder) ((io.minio.PutObjectArgs.Builder) PutObjectArgs.builder().bucket(OssConstant.MINIO_Bucket)).object(filename)).stream(file.getInputStream(), (long) file.getSize(), -1L).contentType("application/octet-stream").build());
		InputStream stream = client.getObject((GetObjectArgs) ((io.minio.GetObjectArgs.Builder) ((io.minio.GetObjectArgs.Builder) GetObjectArgs.builder().bucket(OssConstant.MINIO_Bucket)).object(filename)).build());
		BladeFile files = new BladeFile();
		files.setOriginalName(file.getOriginalFilename());
		files.setName(filename);
		files.setDomain(this.getOssHost());
		files.setLink(this.fileLinkr(filename));
		Long attachId = buildAttach(filename, file.getSize(), files);
		files.setAttachId(attachId);
		return R.data(files);
	}
	/**
	 * 通过文件流下载
	 *
	 * @param fileName 存储桶对象名称
	 * @return void
	 */
	@SneakyThrows
	@GetMapping("/file-link")
	public void fileLink(HttpServletResponse response,@RequestParam String fileName) {
		InputStream stream = client.getObject(GetObjectArgs.builder().bucket("gdtec").object(fileName).build());
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int length;
		while ((length = stream.read(buffer)) != -1) {
			os.write(buffer, 0, length);
		}
		byte[] data = os.toByteArray();
		response.resetBuffer();
		response.resetBuffer();
		response.setHeader("Content-Disposition", "attachment");
		response.addHeader("file-name", URLEncoder.encode(fileName, "UTF-8"));
		response.addHeader("Content-Length", "" + data.length);
		response.setContentType("application/octet-stream; charset=UTF-8");
		IOUtils.write(data,response.getOutputStream());
	}
	/**
	 * 获得外链地址，已弃用
	 *
	 * @param fileName 存储桶对象名称
	 * @return void
	 */
	@SneakyThrows
	@GetMapping("/file-link2")
	public R<String> fileLink2(@RequestParam String fileName) {
		return R.data(minioTemplate.getPresignedObjectUrl("gdtec",fileName,500));
	}

	public String getOssHost(){
		return OssConstant.MINIO_address  + OssConstant.MINIO_Bucket;
	}

	public String fileLinkr(String filename){
		return this.getOssHost() +"/"+ filename;
	}


	public String fileName(String originalFilename,String controller){
		BladeUser User = AuthUtil.getUser();
		String  t = User.getDetail().getStr(CommonConstant.PROF_COM_ID);
		//获取需要进行匹对判断冲突的列表
		String Com = User.getAccount().equals("admin")||Func.isEmpty(t)?"ccscc":deptMapper.selectById(User.getDetail().getStr(CommonConstant.PROF_COM_ID)).getDeptName();
		if(Func.isEmpty(controller)){controller = "other";}
		return "upload/"+controller+"/"+ Com + "/" + DateUtil.today() + "/" + StringUtil.randomUUID() + "." + FileUtil.getFileExtension(originalFilename);
	}


}
