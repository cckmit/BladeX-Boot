package org.springblade.common.utils;

import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.*;
import com.spire.pdf.PdfDocument;
import com.spire.pdf.utilities.PdfTable;
import com.spire.pdf.utilities.PdfTableExtractor;
import org.apache.pdfbox.io.RandomAccessBuffer;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.*;


public class ExtractTableData {

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

	public void readPDF() {
		     PDDocument helloDocument = null;
		     try {
			         helloDocument = PDDocument.load(new File(
				                 "D:\\resource\\Sample.pdf"));
			          PDFTextStripper textStripper = new PDFTextStripper();
			        System.out.println(textStripper.getText(helloDocument));
			          helloDocument.close();
			        } catch (IOException e) {
			           // TODO Auto-generated catch block
			          e.printStackTrace();
			       }
	}
	public static void main(String[] args){

		File pdfFile = new File("D:\\resource\\Sample.pdf");
		PDDocument document = null;
		try
		{
			// 方式一：

			 InputStream input = null;
			 input = new FileInputStream( pdfFile );
			 //加载 pdf 文档
			 PDFParser parser = new PDFParser(new RandomAccessBuffer(input));
			 parser.parse();
			 document = parser.getPDDocument();


			// 方式二：
			//document=PDDocument.load(pdfFile);

			// 获取页码
			int pages = document.getNumberOfPages();

			// 读文本内容
			PDFTextStripper stripper=new PDFTextStripper();
			// 设置按顺序输出
			stripper.setSortByPosition(true);
			stripper.setStartPage(1);
			stripper.setEndPage(pages);
			String content = stripper.getText(document);
			System.out.println(content);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

























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
