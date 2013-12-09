package com.spider;

import java.util.List;

import org.htmlparser.util.ParserException;

import com.spider.util.FunctionUtils;

/**
 * 整合各个给类，实现url到获取数据到数据处理类
 * @author DMD
 *
 */
public class UrlDataHanding implements Runnable {

	   /**
		     * 下载对应页面并分析出页面对应的URL放在未访问队列中。
		     * @param url
	 * @throws Exception 
		     */
		    public void dataHanding(String url) throws Exception
		    {
		         DownloadPage.getContentFormUrl(url);
		    }
		        
		    public void run()
		    {
		        while(!UrlQueue.isEmpty())
		        {
		           try {
					dataHanding(UrlQueue.outElem());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        }
		    }


}
