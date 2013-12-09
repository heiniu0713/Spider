package com.spider;

import java.sql.SQLException;
import java.util.Scanner;

public class Test {
	 public static void main(String[] args) throws SQLException
		  {
		 Scanner in=new Scanner(System.in);
		 	//String url="http://www.git-scm.com";
		 //	String url1="http://www.git-scm.com/book/zh";
		 	String url1=in.next();
		 	UrlQueue.addElem(url1);
	      UrlDataHanding[] url_Handings = new UrlDataHanding[10];
		      
		          for(int i = 0 ; i < url_Handings.length ; i++)
		          {
		              url_Handings[i] = new UrlDataHanding();
		              new Thread(url_Handings[i]).start();
		          }
		        
		
		  }

}
