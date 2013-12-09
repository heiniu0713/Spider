package com.spider.test;

import java.util.ArrayList;
import java.util.List;


import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;

import org.htmlparser.beans.StringBean;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.nodes.TagNode;
import org.htmlparser.nodes.TextNode;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.ParagraphTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

/**
 * 提取具有某个属性值的标签列表
 * @author Administrator
 *
 */

public class Attrbuite {
private static NodeList nodeList;
public static String  getContent(String html) throws ParserException{
	Parser p=new Parser();
	p.setInputHTML(html);
	 String line="";
	NodeFilter textFilter=new NodeClassFilter(ParagraphTag.class);
	OrFilter lastFilter = new OrFilter();
	lastFilter.setPredicates(new NodeFilter[] { textFilter });
	 nodeList = p.parse(lastFilter);
	 Node[] nodes = nodeList.toNodeArray(); 
	 for (int i = 0; i < nodes.length; i++) {
		 Node node=nodes[i];
	
		 if(node instanceof TagNode){
			 TagNode tn=(TagNode) node;
			 line+=tn.toPlainTextString().trim();
		 }
		 if(line.isEmpty()){
			 continue;
		 }
		
	}

	return line;
}
public static void getContentP(String html) throws ParserException{

}

public static <T extends TagNode> List<T> getText(String html,final Class<?> tagType,final String attrName,final String attrValue){
	try{
		Parser p=new Parser();
		p.setInputHTML(html);	
		NodeList list=p.parse(new NodeFilter(){

			public boolean accept(Node node) {
				if(node.getClass()==tagType){
					T t=(T)node;
					
					if(attrName==null){
						//System.out.println("niah:===="+attrName);
						return true;
					}
					String attrValue=t.getAttribute("luanma:"+attrName);
					System.out.println("attrValue=="+attrValue);
					if(attrValue!=null&&attrValue.equals(attrValue)){
						return true;
					}
				}
				return false;
			}
		}
		);
	List<T> tags=new ArrayList<T>();
	for(int i=0;i<list.size();i++){
		T tt=(T)list.elementAt(i);
		tags.add(tt);
	}
	return tags;
	}catch(Exception e){
		e.printStackTrace();
	}return null;
}
public static <T extends TagNode> List<T> getText(String html,final Class<?> tagType){
return getText( html,  tagType,null,null);
}

/* 根据提供的URL，获取此URL对应网页的纯文本信息  
* @param url 提供的URL链接  
* @return RL对应网页的纯文本信息  
* @throws ParserException  
*/  
public static String getText(String url)throws ParserException{   
StringBean sb = new StringBean(); 
//设置不需要得到页面所包含的链接信息   
sb.setLinks(false);

//设置将不间断空格由正规空格所替代   
sb.setReplaceNonBreakingSpaces(true);

//设置将一序列空格由一个单一空格所代替   
sb.setCollapse(true);  

//传入要解析的URL   
sb.setURL(url);     
   return sb.getStrings();   
} 
}
