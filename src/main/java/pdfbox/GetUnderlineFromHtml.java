package pdfbox;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**  
 * Description： 
 * Author:lucktyy@gmail.com
 * Date:2014-10-20上午10:57:10
 */
public class GetUnderlineFromHtml {

	public  static void  getWord(String file,String resFile){
		File f = new File(file);
		String line = null;
		StringBuilder sb = new StringBuilder("");
		String header = "";
		boolean flag = false;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader((new FileInputStream(f))));
			long t1 = System.currentTimeMillis();
			System.out.println("read start:"+t1);
			while( (line = br.readLine() ) != null){
				line = line.toLowerCase();
				sb.append(line);
			}
			
			long t2 = System.currentTimeMillis();
			System.out.println("read end:"+t2);
			System.out.println("spend :"+(t2-t1));
			int fromIndex = 0;
			int firstStart = 0;
			int endStart = 0;
			StringBuilder res = new StringBuilder();
			do{
				firstStart = sb.indexOf("underline", fromIndex);
				endStart =  sb.indexOf("</span>",firstStart);
				
//				if(sb.substring(firstStart, firstStart+11+2).contains("<")){
//					System.out.println("start = "+firstStart);
//					System.out.println("end = "+endStart);
//					System.out.println("str from start to end = "+sb.substring(firstStart, endStart));
//					System.out.println("realStart = "+sb.indexOf(">",firstStart+11+2));
//					System.out.println("escape = "+sb.substring(sb.indexOf(">",firstStart+11), 50));
//					firstStart = sb.indexOf(">",firstStart+11+5);
//				}
				
				if(firstStart !=-1 && endStart !=-1){
					String hit = sb.substring(firstStart+11,endStart);
					res.append(hit).append("<br/><br/>\n\n");
					fromIndex = endStart;
				}
			}while(firstStart != -1 && endStart !=-1 );
			
			outputFile(resFile,res.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void outputFile(String resFile ,String str){
		try{
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(resFile)));
			bw.write("<html><head><meta http-equiv=\"Content-Type\"content=\"text/html;charset=utf8\" /> </head><body>");
			bw.write(str);
			bw.write("</body></html>");
			bw.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		getWord("D:\\c.html","D:\\c1.html");
	}
}
