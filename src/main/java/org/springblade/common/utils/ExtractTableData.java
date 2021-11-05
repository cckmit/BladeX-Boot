package org.springblade.common.utils;


import liquibase.pro.packaged.S;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;


import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.awt.Rectangle;

public class ExtractTableData {



//	public static void main(String[] args) throws Exception {
//		readFdf("D:\\resource\\tttw.pdf");
//	}
//
//
//
//		public static void readFdf(String file) throws Exception {
//			// 是否排序
//			boolean sort = false;
//			// pdf文件名
//			String pdfFile = file;
//			// 输入文本文件名称
//			String textFile = null;
//			// 编码方式
//			String encoding = "UTF-8";
//			// 开始提取页数
//			int startPage = 1;
//			// 结束提取页数
//			int endPage = Integer.MAX_VALUE;
//			// 文件输入流，生成文本文件
//			Writer output = null;
//			// 内存中存储的PDF Document
//			PDDocument document = null;
//			try {
//				try {
//					// 首先当作一个URL来装载文件，如果得到异常再从本地文件系统//去装载文件
//					URL url = new URL(pdfFile);
//					//注意参数已不是以前版本中的URL.而是File。
//					document = PDDocument.load(new File(pdfFile));
//					// 获取PDF的文件名
//					String fileName = url.getFile();
//					// 以原来PDF的名称来命名新产生的txt文件
//					if (fileName.length() > 4) {
//						File outputFile = new File(fileName.substring(0, fileName
//							.length() - 4)
//							+ ".txt");
//						textFile = outputFile.getName();
//					}
//				} catch (MalformedURLException e) {
//					// 如果作为URL装载得到异常则从文件系统装载
//					//注意参数已不是以前版本中的URL.而是File。
//					document = PDDocument.load(new File(pdfFile));
//					if (pdfFile.length() > 4) {
//						textFile = pdfFile.substring(0, pdfFile.length() - 4)
//							+ ".txt";
//					}
//				}
//				// 文件输入流，写入文件到textFile
//				output = new OutputStreamWriter(new FileOutputStream(textFile), encoding);
//				// PDFTextStripper来提取文本
//				PDFTextStripper stripper = null;
//				stripper = new PDFTextStripper();
//				// 设置是否排序
//				stripper.setSortByPosition(sort);
//				// 设置起始页
//				stripper.setStartPage(startPage);
//				// 设置结束页
//				stripper.setEndPage(endPage);
//				// 调用PDFTextStripper的writeText提取并输出文本
//				stripper.writeText(document, output);
//				output.close();
//			} finally {
//				if (output != null) {
//					// 关闭输出流
//					output.close();
//				}
//				if (document != null) {
//					// 关闭PDF Document
//					document.close();
//				}
//			}
//		}



	public static void main(String[] args) throws IOException {
		PDDocument document = PDDocument.load(new File("D:\\resource\\qqw.pdf"));
		if (!document.isEncrypted()) {
			PDFTextStripperByArea stripper = new PDFTextStripperByArea();
			stripper.setSortByPosition(true);
			PDFTextStripper tStripper = new PDFTextStripper();
			String pdfFileInText = tStripper.getText(document);
// System.out.println("Text:" + st);
// split by whitespace
			String lines[] = pdfFileInText.split("\\r?\\n");
			for (String line : lines) {
				System.out.println(line);
			}
		}
	}






//	public static void main(String[] args) throws IOException {
//			getContent("D:\\resource\\Sample.pdf");
//	}
//
//	/**
//	 * 取得PDF文本
//	 *
//	 * @param path
//	 * @return
//	 */
//	public static String getContent(String path) {
//		String result = "";
//		PDDocument document = null;
//		try {
//			InputStream is = new FileInputStream(path);
//			document = PDDocument.load(is);
//			PDFTextStripper stripper = new PDFTextStripper();
//			result = stripper.getText(document).trim();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			if (null != document) {
//				try {
//					document.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//		return result.toLowerCase();
//	}



//		public static void main(String[] args) throws IOException {
//			ForCharacter(0,0,1000,1000);
//	}
//	public static String ForCharacter(int x, int y, int width, int height) throws IOException {
//		String templetePath = "D:\\resource\\Sample.pdf";
//		//新建区域，坐标：x,y；宽高：width，height
//		 Rectangle rectangle = new Rectangle(x,y,width,height);
//		 PDDocument document=PDDocument.load(new File(templetePath));
//		// 按区域读取文本剥离器
//		 PDFTextStripperByArea stripper = new PDFTextStripperByArea();
//		// 设置区域
//		 stripper.addRegion("regionName",rectangle);
//		// 按位置进行排序
//		 stripper.setSortByPosition(true);
//		// 获取目录
//		 PDDocumentCatalog catalog = document.getDocumentCatalog();
//		// 获取页码树
//		 PDPageTree tree = catalog.getPages();
//		// 获取指定页，从0开始
//		PDPage page = tree.get(0);
//		// 提取页面信息 s
//		stripper.extractRegions(page);
//		// 获取指定区域名称对应区域的文本
//		String regionText = stripper.getTextForRegion( "regionName" );
//		return regionText;
//	}




//	public static void main(String[] args) {
//		fillTemplete();
//	}
//	//pdf模板处理
//	private static void fillTemplete(){
//		String templetePath = "D:\\resource\\Sample.pdf";
//		String data = "";
//		try {
//			PDDocument document = PDDocument.load(new File(templetePath));
//			if(document.isEncrypted()){
//				try{
//					document.decrypt("");
//				} catch (Exception e){
//				}
//			}
//			PDFTextStripperByArea stripper = new PDFTextStripperByArea();
//			stripper.setSortByPosition(true);
//			stripper.setWordSeparator("|");
////	      stripper.setLineSeparator("#");
//			//划定区域
//			Rectangle rect= new Rectangle(0, 0, 10000, 10000);
//			stripper.addRegion("area", rect);
//			List<PDPage> allPages = document.getDocumentCatalog().getAllPages();
//			int i = 0;
//			for(PDPage page : allPages){
//				stripper.extractRegions(page);
//				i++;
//				//获取区域的text
//				data = stripper.getTextForRegion("area");
////	         data = data.trim();
//				String[] datas = data.split("\r\n");
//				//对文本进行分行处理
//				for( i = 0; i<datas.length; ++i){
//					String[] str = datas[i].split(" ");
//					System.out.println(JsonUtils.toString(str));
//				}
//			}
//			document.close();
//		} catch (Exception e){
//			e.printStackTrace();
//		}
//	}












//	public static void main(String[] args) throws IOException {
//		System.out.print(getPdfFileText("D:\\resource\\Sample.pdf"));
//	}
//
//	public static String getPdfFileText(String fileName) throws IOException {
//		PdfReader reader = new PdfReader(fileName);
//		PdfReaderContentParser parser = new PdfReaderContentParser(reader);
//		StringBuffer buff = new StringBuffer();
//		TextExtractionStrategy strategy;
//		for (int i = 1; i <= reader.getNumberOfPages(); i++) {
//			strategy = parser.processContent(i,
//				new SimpleTextExtractionStrategy());
//			buff.append(strategy.getResultantText());
//		}
//		return buff.toString();
//	}
//










//	public void readPDF() {
//		     PDDocument helloDocument = null;
//		     try {
//			         helloDocument = PDDocument.load(new File(
//				                 "D:\\resource\\Sample.pdf"));
//			          PDFTextStripper textStripper = new PDFTextStripper();
//			        System.out.println(textStripper.getText(helloDocument));
//			          helloDocument.close();
//			        } catch (IOException e) {
//			           // TODO Auto-generated catch block
//			          e.printStackTrace();
//			       }
//	}
//	public static void main(String[] args) throws IOException {
//
//		File pdfFile = new File("D:\\resource\\Sample.pdf");
//		PDDocument document = null;
//		try
//		{
//			// 方式一：
////
////			 InputStream input = null;
////			 input = new FileInputStream( pdfFile );
////			 //加载 pdf 文档
////			 PDFParser parser = new PDFParser(new RandomAccessBuffer(input));
////			 parser.parse();
////			 document = parser.getPDDocument();
//
//			// 方式二：
//			document=PDDocument.load(pdfFile);
//
//			// 获取页码
//			int pages = document.getNumberOfPages();
//
//			// 读文本内容
//			PDFTextStripper stripper=new PDFTextStripper();
//			// 设置按顺序输出
//			stripper.setSortByPosition(true);
//			stripper.setStartPage(1);
//			stripper.setEndPage(pages);
//			System.out.println(stripper);
//			String content = stripper.getText(document);
//			System.out.println(content);
//		}
//		catch(Exception e)
//		{
//		}
//
//	}



//	public static void main(String []args) throws Exception {
//		String aa="D:\\resource\\Sample.pdf";
//		//Load a sample PDF document
//		PdfDocument pdf = new PdfDocument(aa);
//
//		//Create a StringBuilder instance
//		StringBuilder builder = new StringBuilder();
//		//Create a PdfTableExtractor instance
//		PdfTableExtractor extractor = new PdfTableExtractor(pdf);
//
//		//Loop through the pages in the PDF
//		for (int pageIndex = 0; pageIndex < pdf.getPages().getCount(); pageIndex++) {
//			//Extract tables from the current page into a PdfTable array
//			PdfTable[] tableLists = extractor.extractTable(pageIndex);
//
//			//If any tables are found
//			if (tableLists != null && tableLists.length > 0) {
//				//Loop through the tables in the array
//				for (PdfTable table : tableLists) {
//					//Loop through the rows in the current table
//					for (int i = 0; i < table.getRowCount(); i++) {
//						//Loop through the columns in the current table
//						for (int j = 0; j < table.getColumnCount(); j++) {
//							//Extract data from the current table cell and append to the StringBuilder
//							String text = table.getText(i, j);
//							builder.append(text + " | ");
//						}
//						builder.append("\r\n");
//					}
//				}
//			}
//		}
//		//Write data into a .txt document
//		FileWriter fw = new FileWriter("D:\\resource\\ExtractTable.txt");
//		fw.write(builder.toString());
//		fw.flush();
//		fw.close();
//	}

}
