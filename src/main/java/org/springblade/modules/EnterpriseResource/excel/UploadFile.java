package org.springblade.modules.EnterpriseResource.excel;


import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import liquibase.pro.packaged.L;
import lombok.SneakyThrows;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.springblade.common.constant.CommonConstant;
import org.springblade.common.constant.OssConstant;
import org.springblade.common.enums.RescoreEnum;
import org.springblade.core.oss.model.BladeFile;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.DateUtil;
import org.springblade.core.tool.utils.FileUtil;
import org.springblade.core.tool.utils.StringUtil;
import org.springblade.modules.EnterpriseResource.entity.AllFile;
import org.springblade.modules.EnterpriseResource.service.IFileService;
import org.springblade.modules.system.mapper.DeptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;

@Component
public class UploadFile {

	private static IFileService fileService;

	//文件上传工具类
	private static MinioClient client;
	private static DeptMapper deptMapper;
	@Autowired
	public  void setClient(MinioClient client) {
		UploadFile.client = client;
	}
	@Autowired
	public  void setDeptMapper(DeptMapper deptMapper) {
		UploadFile.deptMapper = deptMapper;
	}

	@Autowired
	public void setFileService(IFileService fileService) {
		UploadFile.fileService = fileService;
	}

	//文件上传到mini io服务器上
	@SneakyThrows
	public static R<BladeFile> put(@RequestParam MultipartFile file,String imgsName,Long gitId) {
		String filename = fileName(file.getOriginalFilename());
		client.putObject((PutObjectArgs) ((io.minio.PutObjectArgs.Builder) ((io.minio.PutObjectArgs.Builder) PutObjectArgs.builder().bucket(OssConstant.MINIO_Bucket)).object(filename)).stream(file.getInputStream(), (long) file.getSize(), -1L).contentType("application/octet-stream").build());
		BladeFile files = new BladeFile();
		files.setOriginalName(file.getOriginalFilename());
		files.setName(filename);
		files.setDomain(UploadFile.getOssHost());
		files.setLink(UploadFile.fileLinkr(filename));
		Long A = UploadFile.buildAttach(filename,(file.getSize()),files,imgsName,gitId);
		files.setAttachId(A);
		return R.data(files);
	}

	/**
	 * 构建附件表
	 *
	 * @param fileName  文件名
	 * @param fileSize  文件大小
	 * @param bladeFile 对象存储文件
	 * @return attachId
	 */
	private static Long buildAttach(String fileName, Long fileSize, BladeFile bladeFile, String imgsName, Long gitId) {
		String fileExtension = FileUtil.getFileExtension(fileName);
		AllFile allFile = new AllFile();
		allFile.setDomain(bladeFile.getDomain());
		allFile.setLink(bladeFile.getLink());
		allFile.setFileName(imgsName);
		//原本文件名称
		allFile.setOriginalName(imgsName);
		allFile.setName(imgsName);
		int b=1024;
		double c;
		DecimalFormat df=new DecimalFormat("0.00");
		String a= df.format((float)fileSize/b);
		allFile.setFileSize(a+"Kb");
		allFile.setFileSuffix(fileExtension);
		allFile.setObjectValue(RescoreEnum.RESCORE_APTITUDE.getValue());
		//附件存入相关联表的主键ID
		allFile.setObjectId(gitId);
		fileService.save(allFile);
		return allFile.getId();
	}




	public static String getOssHost(){
		return OssConstant.MINIO_address + OssConstant.MINIO_Ossendpoint + OssConstant.MINIO_Bucket;
	}

	public static String fileLinkr(String filename){
		return UploadFile.getOssHost() +"/"+ filename;
	}


	public static String fileName(String originalFilename) {
		BladeUser User = AuthUtil.getUser();
		//获取需要进行匹对判断冲突的列表
		String Com = User.getAccount().equals("admin")?"admin":deptMapper.selectById(User.getDetail().getStr(CommonConstant.PROF_COM_ID)).getDeptName();
		return "upload/business/"+ Com + "/" + DateUtil.today() + "/" + StringUtil.randomUUID() + "." + FileUtil.getFileExtension(originalFilename);
	}


	public static FileItem createFileItem(String filePath) {
		FileItemFactory factory = new DiskFileItemFactory(16, null);
		String textFieldName = "textField";
		int num = filePath.lastIndexOf(".");
		String extFile = filePath.substring(num);
		FileItem item = factory.createItem(textFieldName, "text/plain", true,
			"MyFileName" + extFile);
		File newfile = new File(filePath);
		int bytesRead = 0;
		byte[] buffer = new byte[8192];
		try {
			FileInputStream fis = new FileInputStream(newfile);
			OutputStream os = item.getOutputStream();
			while ((bytesRead = fis.read(buffer, 0, 8192))
				!= -1) {
				os.write(buffer, 0, bytesRead);
			}
			os.close();
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return item;

	}


	/**
	 * 随机生成由数字、字母组成的N位验证码
	 *
	 * @return 返回一个字符串
	 */
	public static String getCode(int n) {
		char arr[] = new char[n];
		int i = 0;
		while (i < n) {
			char ch = (char) (int) (Math.random() * 124);
			if (ch >= 'A' && ch <= 'Z' || ch >= 'a' && ch <= 'z' || ch >= '0' && ch <= '9') {
				arr[i++] = ch;
			}
		}
		//将数组转为字符串
		return new String(arr);
	}


}
