package com.spider.test;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.htmlparser.tags.*;
import org.htmlparser.tags.LinkTag;

public class Url {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try{
			HttpClient http=new DefaultHttpClient();
			//http.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, new HttpHost("172.17.18.84",8080));
			
			HttpGet hg=new HttpGet("http://www.git-scm.com/book/zh/起步-关于版本控制");
			HttpResponse hr=http.execute(hg);
			HttpEntity he=hr.getEntity();//鍝堝搱
			if(he!=null){
				InputStream is=he.getContent();
				BufferedReader br=new BufferedReader(new InputStreamReader(is,"utf-8"));
				String line=null;
			while((line=br.readLine())!=null){
					/*List<LinkTag > link=Attrbuite.getText(line, LinkTag .class);
					for(LinkTag  l:link){
						System.out.println(l.getStringText());
						System.out.println("link："+l.getLink());
						System.out.println("linkText:"+l.getLinkText());
					}*/
				Attrbuite.getContent(line);
					
				}
				is.close();
			}
			http.getConnectionManager().shutdown();
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	
}

