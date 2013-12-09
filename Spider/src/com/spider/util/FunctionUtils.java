package com.spider.util;
	import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

/**
 * 提供不同的静态方法，
 * 包括：页面链接正则表达式匹配,
 * 获取URL链接的元素,
 * 判断是否创建文件,
 * 获取页面的Url并将其转换为规范的Url,
 * 截取网页网页源文件的目标内容。
 * @author DMD
 *
 */
public class FunctionUtils {

	/**
	     * 匹配超链接的正则表达式
		     *///www.oschina.net/code/explore"
		     //http://www\\.oschina\\.net/code/explore/.*/\\w+\\.[a-zA-Z]+
		    // private static String pat = "http://www\\.git-scm\\.com/.*/\\w+\\.[a-zA-Z]+";
		     private static String pat = "http://www\\.git-scm\\.com/book/zh/.+[a-zA-Z 0-9]+";
		    public static Pattern pattern = Pattern.compile(pat);
		    
		    private static BufferedWriter writer = null;
		   /* @Test
		    public void test(){
		    	String str="http://www.git-scm.com/book/zh/%E8%B5%B7%E6%AD%A5";
		    	Matcher matcher=pattern.matcher(str);
		    	System.out.println(matcher.find());
		    }*/
	    /**
		     * 爬虫搜索深度
		     */
		    public static int depth = 0;
		
		    /**
		     * 以"/"来分割URL,获得超链接的元素
		     * 
		     * @param url
	     * @return
		     */
	    public static String[] divUrl(String url)
	    {
		        return url.split("/");
		    }
		
		    /**
		     * 判断是否创建文件
		     * 
		     * @param url
		     * @return
		     */
		    public static boolean isCreateFile(String url)
		    {
		        Matcher matcher = pattern.matcher(url);
		
		        return matcher.matches();
		    }
		
		    /**
		     * 创建对应文件
		     * 
		     * @param content
		     * @param urlPath
		     */
		    public static void createFile(String content, String urlPath)

		    {
		        /* 分割url */
		        String[] elems = divUrl(urlPath);
		        System.out.println("elems=="+elems);
		        StringBuffer path = new StringBuffer();
		
		        File file = null;
		        for (int i = 1; i < elems.length; i++)
	        {
		            if (i != elems.length - 1)
		            {
		
		                path.append(elems[i]);
		                path.append(File.separator);
		                file = new File("D:" + File.separator + path.toString());
		            
		            }
		
		            if (i == elems.length - 1)
		            {
		                Pattern pattern = Pattern.compile("\\w+\\.[a-zA-Z]+");
		                Matcher matcher = pattern.matcher(elems[i]);
		                if ((matcher.matches()))
		                {
		                    if (!file.exists())
		                    {
		                        file.mkdirs();
		                    }
		                    String[] fileName = elems[i].split("\\.");
		                    file = new File("D:" + File.separator + path.toString()
		                            + File.separator + fileName[0] + ".txt");
		                    try
		                    {
		                        file.createNewFile();
		                        writer = new BufferedWriter(new OutputStreamWriter(
		                                new FileOutputStream(file)));
		                        writer.write(content);
		                        writer.flush();
		                        writer.close();
		                        System.out.println("创建文件成功");
		                    } catch (IOException e)
		                    {
	                      e.printStackTrace();
		                    }
		
	                }
		            }
		
		        }
		    }
		
		    /**
		     * 获取页面的超链接并将其转换为正式的A标签
		     * 
		     * @param href
		     * @return
		     */
		    public static String getHrefOfInOut(String href)
		    {
	        /* 内外部链接最终转化为完整的链接格式 */
		        String resultHref = null;
		
		        /* 判断是否为外部链接 */
		        if (href.startsWith("http://"))
		        {
		            resultHref = href;
		        } else
		        {
		            /* 如果是内部链接,则补充完整的链接地址,其他的格式忽略不处理,如：a href="#" */
		            if (href.startsWith("/"))
		            {
		                resultHref = "http://www.git-scm.com" + href;
		            }
		        }
		        return resultHref;
		    }
		
		    /**
		     * 截取网页网页源文件的目标内容
		     * 
		     * @param content
		     * @return
		     */
		    public static String getGoalContent(String content)
		    {
		        int sign = content.indexOf("<pre class=\"");
		        String signContent = content.substring(sign);
		
		        int start = signContent.indexOf(">");
		        int end = signContent.indexOf("</pre>");
		        System.out.println("signContent="+signContent.substring(start + 1, end));
		        return signContent.substring(start + 1, end);
		    }
		
		    /**
		     * 检查网页源文件中是否有目标文件
		     * 
		     * @param content
		     * @return
		     */
		    public static int isHasGoalContent(String content)
		    {
		    	System.out.println("[FunctionUtils] content="+content);
		        return content.indexOf("<p ");
		    }
		

}
