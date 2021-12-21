package org.springblade.common.utils;


import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class UploadZip {


	/**
	 * zip解压
	 *
	 * @param srcFile     zip源文件
	 * @param destDirPath 解压后的目标文件夹
	 * @throws RuntimeException 解压失败会抛出运行时异常
	 */
	public static List<String> unZip(File srcFile, String destDirPath) throws RuntimeException {
		//记录解压出来的所有文件名
		List<String> filesName = new ArrayList<>();
		long start = System.currentTimeMillis();
		// 判断源文件是否存在
		if (!srcFile.exists()) {
			throw new RuntimeException(srcFile.getPath() + "所指文件不存在");
		}
		// 开始解压
		ZipFile zipFile = null;
		try {
			zipFile = new ZipFile(srcFile, Charset.forName("GBK"));
			Enumeration<?> entries = zipFile.entries();

			while (entries.hasMoreElements()) {
				ZipEntry entry = (ZipEntry) entries.nextElement();
				//添加进filesName
				filesName.add(entry.getName());
				System.out.println("解压文件:" + entry.getName());
				// 如果是文件夹，就创建个文件夹
				if (entry.isDirectory()) {
					String dirPath = destDirPath + "/" + entry.getName();
					File dir = new File(dirPath);
					dir.mkdirs();
				} else {
					// 如果是文件，就先创建一个文件，然后用io流把内容copy过去
					File targetFile = new File(destDirPath + "/" + entry.getName());
					// 保证这个文件的父文件夹必须要存在
					if (!targetFile.getParentFile().exists()) {
						targetFile.getParentFile().mkdirs();
					}
					targetFile.createNewFile();
					// 将压缩文件内容写入到这个文件中
					InputStream is = zipFile.getInputStream(entry);
					FileOutputStream fos = new FileOutputStream(targetFile);
					int len;
					byte[] buf = new byte[1024];
					while ((len = is.read(buf)) != -1) {
						fos.write(buf, 0, len);
					}
					// 关流顺序，先打开的后关闭
					fos.close();
					is.close();
				}
			}
			long end = System.currentTimeMillis();
			System.out.println("解压完成，耗时：" + (end - start) + " ms");
		} catch (Exception e) {
			throw new RuntimeException("unzip error from ZipUtils", e);
		} finally {
			if (zipFile != null) {
				try {
					zipFile.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return filesName;
	}




	/**
	 * 删除文件
	 *
	 * @param filePath
	 * @return
	 */
	public static boolean deleteFile(String filePath) {
		boolean flag = false;
		File file = new File(filePath);
		if (!file.exists()) {
			return flag;
		}
		if (!file.isDirectory()) {
			return flag;
		}
		String[] tempList = file.list();
		File temp;
		for (int i = 0; i < tempList.length; i++) {
			if (filePath.endsWith(File.separator)) {
				temp = new File(filePath + tempList[i]);
			} else {
				temp = new File(filePath + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				// 先删除文件夹里面的文件
				deleteFile(filePath + "/" + tempList[i]);
				// 再删除空文件夹
				deleteFile(filePath + "/" + tempList[i]);
				flag = true;
			}
		}
		return flag;
	}





















































//	private static final int BUFFER_SIZE = 1024;

	/**
	 * zip解压
	 * @param srcFile        zip源文件
	 * @param destDirPath	  解压后的目标文件夹
	 * @throws RuntimeException 解压失败会抛出运行时异常
	 */
//	public static void unZip(File srcFile, String destDirPath) throws RuntimeException {
//		long start = System.currentTimeMillis();
//		// 判断源文件是否存在
//		if (!srcFile.exists()) {
//			throw new RuntimeException(srcFile.getPath() + "所指文件不存在");
//		}
//		// 开始解压
//		ZipFile zipFile = null;
//		try {
//			zipFile = new ZipFile(srcFile);
//			Enumeration<?> entries = zipFile.entries();
//			while (entries.hasMoreElements()) {
//				ZipEntry entry = (ZipEntry) entries.nextElement();
//				System.out.println("解压" + entry.getName());
//				// 如果是文件夹，就创建个文件夹
//				if (entry.isDirectory()) {
//					String dirPath = destDirPath + "/" + entry.getName();
//					File dir = new File(dirPath);
//					dir.mkdirs();
//				} else {
//					// 如果是文件，就先创建一个文件，然后用io流把内容copy过去
//					File targetFile = new File(destDirPath + "/" + entry.getName());
//					// 保证这个文件的父文件夹必须要存在
//					if(!targetFile.getParentFile().exists()){
//						targetFile.getParentFile().mkdirs();
//					}
//					targetFile.createNewFile();
//					// 将压缩文件内容写入到这个文件中
//					InputStream is = zipFile.getInputStream(entry);
//					FileOutputStream fos = new FileOutputStream(targetFile);
//					int len;
//					byte[] buf = new byte[BUFFER_SIZE];
//					while ((len = is.read(buf)) != -1) {
//						fos.write(buf, 0, len);
//					}
//					// 关流顺序，先打开的后关闭
//					fos.close();
//					is.close();
//				}
//			}
//			long end = System.currentTimeMillis();
//			System.out.println("解压完成，耗时：" + (end - start) +" ms");
//		} catch (Exception e) {
//			throw new RuntimeException("unzip error from ZipUtils", e);
//		} finally {
//			if(zipFile != null){
//				try {
//					zipFile.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	}

}
