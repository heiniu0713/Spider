package com.spider;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.ParserException;

import com.spider.test.Attrbuite;
import com.spider.util.FunctionUtils;
/**
 * 下载此超链接的页面源代码
 * @author DMD
 *
 */
public class DownloadPage {

	/**
		      * 根据URL抓取网页内容
		      * 
	      * @param url
	      * @return
	 * @throws ParserException 
		      */
	     public static String getContentFormUrl(String url) throws ParserException
	     {
	    	 LinkedList<String> queue=new LinkedList<String>();
	         /* 实例化一个HttpClient客户端 */
	         HttpClient client = new DefaultHttpClient();
	         //调用http的HttpGet(url)构造 声明HttpGet实例
	         HttpGet getHttp = new HttpGet(url);
		     String content = null;
		     HttpResponse response;
	         try
		         {
		             /*获得信息载体*/
	        	 //创建一个httpResponse,HttpGet继承了HttpRequest
	             response = client.execute(getHttp);
	             HttpEntity entity = response.getEntity();
	 			//新添加的代码！
	             if(entity!=null){
	            	 /* 转化为文本信息 */
	 				InputStream is=entity.getContent();//输入流
	 				BufferedReader br=new BufferedReader(new InputStreamReader(is,"utf-8"));
	 				String line=null;
	 			
	 			while((line=br.readLine())!=null){
	 				List<LinkTag> link=Attrbuite.getText(line, LinkTag .class);
					for(LinkTag  l:link){
						queue.add(FunctionUtils.getHrefOfInOut(l.getLink()));
						
					}
					  
	 				}
	 			for (int i = 0; i < queue.size(); i++) {   
	 				//System.out.println(queue.get(i));
	 				if(queue.get(i)!=null && queue.get(i)!=""){
		 				Matcher matcher=FunctionUtils.pattern.matcher(queue.get(i));
		 				//System.out.println(matcher.find());
		 				Boolean flag=matcher.find();
		 				if(flag){
			 				System.out.println("[DownloadPage] url="+queue.get(i));
			 				witer(Attrbuite.getText(queue.get(i)),"git_"+i);
			 				//witer(getPContent(queue.get(i)),"git_"+i);
			 				//System.out.println("[DownloadPage] content="+Attrbuite.getText(queue.get(i)));
			 				
		 				}
	 				}	
				}
		                 is.close();
	 			}
	             //新添加的代码结束
		
		         } catch (ClientProtocolException e)
		         {
		             e.printStackTrace();
		         } catch (IOException e)
		         {
		             e.printStackTrace();
	         } finally{
		             client.getConnectionManager().shutdown();
	         }
		         
	         return content;
	     }
		public static String  getPContent(String url) throws ParserException, ClientProtocolException, IOException{
			HttpClient http=new DefaultHttpClient();
			//http.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, new HttpHost("172.17.18.84",8080));
			String str="";
			HttpGet hg=new HttpGet(url);
			HttpResponse hr=http.execute(hg);
			HttpEntity he=hr.getEntity();//鍝堝搱
			if(he!=null){
				InputStream is=he.getContent();
				BufferedReader br=new BufferedReader(new InputStreamReader(is,"utf-8"));
				String line=null;
			while((line=br.readLine())!=null){
				System.out.println(Attrbuite.getContent(line));
			str+=Attrbuite.getContent(line);	
				}
				is.close();
			}
			http.getConnectionManager().shutdown();
			System.out.println("str=="+str);
			return str;
		}
	     public static Boolean witer(String str,String fileName){
	    	 System.out.println("witer str="+str);
	    	boolean blg = false;
	    	try {
	    	BufferedWriter bw = new BufferedWriter(new FileWriter("E:\\"+fileName+".txt"));
	    	bw.write(str);
	    	bw.close();
	    	} catch (IOException e) {
	    	e.printStackTrace();
	    	}
			return blg;
	     }
}
