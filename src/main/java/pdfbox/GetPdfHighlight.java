package pdfbox;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URL;
import java.util.List;
import java.util.Properties;

import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.cos.COSObject;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.fdf.FDFDocument;
import org.apache.pdfbox.pdmodel.fdf.FDFField;
import org.apache.pdfbox.util.PDFHighlighter;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.pdfbox.util.operator.SetTextFont;

import com.alibaba.fastjson.JSON;

/**  
 * Description： 
 * Author:lucktyy@gmail.com
 * Date:2014-10-14下午03:35:16
 */
public class GetPdfHighlight {

	public static void getText2(String file) throws Exception{
		  PDFHighlighter lighter = new PDFHighlighter();
//          lighter.generateXMLHighlight(document, "的", output);
//          String s = lighter.getText(document);
//          System.out.println("s = "+s);
          
          
//          PDFMarkedContentExtractor lighter = new PDFMarkedContentExtractor();
//          List<PDMarkedContent>  contents = lighter.getMarkedContents();
//          System.out.println(contents.size());
          
          FDFDocument fdfDoc = new FDFDocument();
          fdfDoc.load(file);
          
          COSDocument cosDoc = fdfDoc.getDocument();
          System.out.println(cosDoc.getHeaderString());
          System.out.println(cosDoc.getVersion());
          System.out.println(cosDoc.isEncrypted() );
          try{
        	  System.out.println(JSON.toJSONString(cosDoc.getXrefTable(),true));
        	  
//        	  System.out.println(JSON.toJSONString(co ,true));
//        	  System.out.println(JSON.toJSONString(cosDoc.getXrefTable(),true));
//        	  System.out.println(JSON.toJSONString(cosDoc.getXrefTable(),true));
        	  
          }catch(Exception e){
        	  e.printStackTrace();
          }
          
          cosDoc.print();
          
          List<COSObject> list = cosDoc.getObjects();
          System.out.println(list.size());
          
          COSDictionary cosD = cosDoc.getTrailer();
          FDFField dffield =  new FDFField(cosD);
          System.out.println(JSON.toJSONString(dffield,true));
          
//          System.out.println("cosD = "+cosD);
//          FDFAnnotationHighlight fdfa = new FDFAnnotationHighlight(cosD);
////          System.out.println(JSON.toJSONString(fdfa,true));
//          System.out.println(fdfa.getDate());
//          System.out.println(fdfa.getSubject());
//          System.out.println(fdfa.getTitle());
//          System.out.println(fdfa.getPage());
//          
//          cosDoc.close();
		
	}
	
	   public static void getText(String file) throws Exception{
	       //是否排序
	       boolean sort = false;
	       //pdf文件名
	       String pdfFile = file;
	       //输入文本文件名称
	       String textFile = null;
	       //编码方式
	       String encoding = "UTF-8";
	       //开始提取页数
	       int startPage = 1;
	       //结束提取页数
	       int endPage = 50;  // Integer.MAX_VALUE;
	       //文件输入流，输入文本文件
	       Writer output = null; 
	       //内存中存储的PDF Document
	       PDDocument document = null;

	       try{
	           try{
	               //首先当作一个URL来加载文件，如果得到异常再从本地系统装载文件
	               URL url = new URL(pdfFile);
	               document = PDDocument.load(url);
	               String fileName = url.getFile();
	           
	               if(fileName.length() > 4){
	                   //以原来pdf名称来命名新产生的txt文件
	                   File outputFile = new File(fileName.substring(0, fileName.length()-4) + ".txt");
	                   textFile = outputFile.getName();
	               }            
	           }catch(Exception e){
	               //如果作为URL装载得到异常则从文件系统装载
	               document = PDDocument.load(pdfFile);
	               if(pdfFile.length() > 4){
	                   textFile = pdfFile.substring(0, pdfFile.length() - 4) + ".txt";
	               }
	           }
	           //文件输出流，写入文件到textFile
	           output = new OutputStreamWriter(new FileOutputStream(textFile),encoding);
	           //PDFTextStripper来提取文本
	           Properties p = new Properties();
	           
	           
	           SetTextFont stf = new SetTextFont();
//	           p.setProperty("Tf", stf);
	           PDFTextStripper stripper =  new PDFTextStripper(p);
	           
//	           //设置是否排序
//	           stripper.setSortByPosition(sort);
//	           //设置起始页
//	           stripper.setStartPage(startPage);
//	           //设置结束页
//	           stripper.setEndPage(endPage);
//	           //调用PDFTextStripper的writeText提取并输出文本
	           stripper.writeText(document, output);
	           
//	           FDFDocument fdfDoc = new FDFDocument();
//	           fdfDoc.load("D:\\ab.fdf");
//	           ImportFDF ipdf = new ImportFDF();
//	           ipdf.importFDF(document,fdfDoc); 
//	           document.save(new File("D:\\test12.pdf"));
//	           document.close();
	           
//	           COSDocument cosDoc = fdfDoc.getDocument();
//	           cosDoc.print();
//	           
//	           List<COSObject> list = cosDoc.getObjects();
//	           System.out.println(list.size());
//	           
//	           COSDictionary cosD = cosDoc.getTrailer();
//	           
//	           FDFDictionary fdfD = new FDFDictionary(cosD);
//	           List annotations = fdfD.getAnnotations();
//	           
////	           FDFField dffield =  new FDFField(cosD);
//	           System.out.println(JSON.toJSONString(document,true));
	           document.getDocument().print();
//	           System.out.println(document.getDocument().getObjects());
	           COSObject s  = document.getDocument().getObjectByType("4");
	           System.out.println("s = "+s);
	       }finally{
	           if(output != null){
	               output.close();                
	           }
	           if(document != null){
	               document.close();
	           }
	       }        
	   }

	   
	   
	public static void main(String[] args) throws Exception {
		   
		//是否排序
	       boolean sort = false;
	       //输入文本文件名称
	       String textFile = null;
	       //编码方式
	       String encoding = "UTF-8";
	       //开始提取页数
	       int startPage = 1;
	       //结束提取页数
	       int endPage = Integer.MAX_VALUE;
	       //文件输入流，输入文本文件
	       Writer output = null; 
	       //内存中存储的PDF Document
//	       PDDocument document = null;
		
	       String filename = "D:\\a.pdf";
	       getText(filename);
	       
	       
//	       filename = "D:\\bc.xfdf";
//	       getText2(filename);
	       
//	       FileInputStream   fis   =   new   FileInputStream(filename); 
//           BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\a.txt"));
//           PDFParser   p   =   new   PDFParser(fis); 
//           p.parse();         
//           PDFTextStripper   stripper   =   new   PDFHighlighter();      
//           
//         //设置是否排序
//           stripper.setSortByPosition(sort);
//           //设置起始页
//           stripper.setStartPage(startPage);
//           //设置结束页
//           stripper.setEndPage(endPage);
//           String   s   =   stripper.getText(p.getPDDocument()); 
//           writer.write(s);
//           System.out.println(s); 
//           fis.close(); 
//           writer.close();
		
	}
}
