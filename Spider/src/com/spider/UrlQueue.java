package com.spider;

import java.util.LinkedList;
/**
 * 未访问Url队列。
 * @author DMD
 *
 */
public class UrlQueue {
	 /**超链接队列*/
		    public static LinkedList<String> urlQueue = new LinkedList<String>();
		    
		    /**队列中对应最多的超链接数量*/
		    public static final int MAX_SIZE = 10000;
		    
		    public synchronized static void addElem(String url)
		    {
		        urlQueue.add(url);
		    }
		    
		    public synchronized static String outElem()
		    {
		        return urlQueue.removeFirst();
		    }
		    
		    public synchronized static boolean isEmpty()
		    {
		        return urlQueue.isEmpty();
		    }
	   
		    public  static int size()
		    {
		        return urlQueue.size();
		    }
		    
		    public  static boolean isContains(String url)
		    {
		        return urlQueue.contains(url);
		    }
		

}
