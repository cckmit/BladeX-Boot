package org.springblade.common.utils;

import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import lombok.SneakyThrows;
import org.springblade.modules.EnterpriseResource.entity.AllFile;
import org.springblade.modules.resource.entity.Oss;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.*;
import java.util.List;

public class DownloadFile {

	public static String aa="D:\\BladeXFile";
	/**
	 * 以流的形式下载一个对象。
	 *
	 * @param objectName
	 * @return String
	 */
	@SneakyThrows
	public static InputStream getObject1(@RequestParam String objectName,@RequestParam String bucketName) {
		// 创建客户端
		Oss oss = new Oss();
		MinioClient minioClient = MinioClient.builder()
			.endpoint(oss.getEndpoint())
			.credentials(oss.getAccessKey(), oss.getSecretKey())
			.build();
		// 获取指定offset和length的"myobject"的输入流。
		InputStream stream = minioClient.getObject(GetObjectArgs.builder().object(objectName).bucket(bucketName).build());
		// 读取输入流直到EOF并打印到控制台。
		byte[] buf = new byte[16384];
		int bytesRead;
		while ((bytesRead = stream.read(buf, 0, buf.length)) >= 0) {
			System.out.println(new String(buf, 0, bytesRead));
		}
		return stream;
	}

	//从oss下载到本地
	public static File saveUrlAs(List<AllFile> files) throws IOException {
		String filePath = "D:\\BladeXFile";
		//创建不同的文件夹目录
		File file = new File(filePath);
		//判断文件夹是否存在
		if (!file.exists()) {//如果文件夹不存在，则创建新的的文件夹
			file.mkdirs();
		}
		for (AllFile fileAll : files) {
			//获取文件输入流
			InputStream in = getObject1(fileAll.getFileName(),"gdtec");
			//输出流
			//OutputStream out = new FileOutputStream(file);
			BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file)) ;
			//输出文件
			int len = 0;
			byte[] buffer = new byte[1024];
			while ((len = in.read(buffer)) > 0) {
				//将缓冲区的数据输出到客户端浏览器
				out.write(buffer, 0, len);
			}
			in.close();

		}

		return file;
	}



}
