package org.springblade.common.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class DownloadZip {


	public void download( HttpServletResponse response,String path, String fileName) {
		try {
			// path是指想要下载的文件的路径
			File file = new File(path);
			// 获取文件名
			String filename = fileName != null ? fileName : file.getName();
			// 获取文件后缀名
			String ext = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();

			// 将文件写入输入流
			FileInputStream fileInputStream = new FileInputStream(file);
			InputStream fis = new BufferedInputStream(fileInputStream);
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();

			// 清空response
			response.reset();
			// 设置response的Header
			response.setCharacterEncoding("UTF-8");
			//Content-Disposition的作用：告知浏览器以何种方式显示响应返回的文件，用浏览器打开还是以附件的形式下载到本地保存
			//attachment表示以附件方式下载   inline表示在线打开   "Content-Disposition: inline; filename=文件名.mp3"
			// filename表示文件的默认名称，因为网络传输只支持URL编码的相关支付，因此需要将文件名URL编码后进行传输,前端收到后需要反编码才能获取到真正的名称
			response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
			// 告知浏览器文件的大小
			response.addHeader("Content-Length", "" + file.length());
			OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("application/octet-stream");
			outputStream.write(buffer);
			outputStream.flush();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}


	//获取文件夹下面的文件
	public static List<File> getAllFileName(String path) {
		List<File> list=new ArrayList<>();
		File f=new File(path);
		String[] names=f.list();
		for(String name: names){
			File file=new File(path+"\\"+name);
			list.add(file);
		}
		return list;
	}

	public static void main(String[] args) throws Exception {
		String path="D:\\BladeXFile\\wj";
		List<File> s =	getAllFileName(path);
		String path1="D:\\BladeXFile\\aa.zip";
		OutputStream out = new FileOutputStream(path1);
			generateZip(out,s);
	}


	/**
	 * 打包成zip包
	 */
	public static void generateZip(OutputStream os, List<File> files) throws Exception {

		ZipOutputStream out = null;
		try {
			byte[] buffer = new byte[1024];
			//生成的ZIP文件名为Demo.zip
			out = new ZipOutputStream(os);
			//需要同时下载的两个文件result.txt ，source.txt
			for (File file : files) {
				System.out.println(file.getName());
				System.out.println(file.getPath());
				FileInputStream fis = new FileInputStream(file);
				out.putNextEntry(new ZipEntry(file.getName()));
				int len;
				//读入需要下载的文件的内容，打包到zip文件
				while ((len = fis.read(buffer)) != -1) {
					out.write(buffer, 0, len);
				}
				out.flush();
				out.closeEntry();
				fis.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}




}
