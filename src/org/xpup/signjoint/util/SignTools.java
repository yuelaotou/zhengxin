package org.xpup.signjoint.util;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.io.*;

import org.xpup.signjoint.dto.ConfigDTO;
/**
 * 工具类
 * @author yinchao
 *
 */
public class SignTools {

  public static XmlOutput xo=new XmlOutput();//XML输出类
  
  /**
   * 转换数据格式
   * @param 带有分隔符的字符串
   * @return 从字符中提取的List
   */
  public static List Compart(String temp)
  {

    List list=new ArrayList();
    String mark=null;
    int pointer=0;
    for(int i=0;i<temp.length();i++)
    {
      mark=temp.substring(i,i+1);
      if(mark.equals(xo.getConfig().getMark()))
      {
       list.add(temp.substring(pointer,i));
       pointer=i+1;
      }
    }
    //list.add(temp.substring(pointer,temp.length()));//末尾不加分隔符
    return list;
  }
  
  /**
   * 将传进来的List中的数据合并成一个String
   * @param 存着数据项的List
   * @return 将其合并成一个特定格式的字符串
   */
  public static String Combination(List temp)
  {
    Iterator iter=temp.iterator();
    StringBuffer sb= new StringBuffer();
    String mark=xo.getConfig().getMark();
    while(iter.hasNext())
    {
      sb.append(((String)iter.next()+mark));
      
    }
    return sb.toString();
    //return sb.toString().substring(1);//如果末尾不加分隔符
    
  }
  
  /**
   * 将含有两位小数的金额转换成银行格式
   * @param money保留两为小数的金额
   * @return 银行接收的格式（去掉小数点）
   */
  public static long DoubletoLong(double money)
  {
    money=money*100;
    return (long)money;
  }
  
  
  /**
   * 将流中数据去掉分隔符
   * @param   输入流
   * @return 存储着从输入流中提取数据的List
   */
  public static List Compart(InputStream in)
  {
    InputStreamReader isr=new InputStreamReader(in);
    BufferedReader bin=new BufferedReader(isr);
    int ch;
    StringBuffer sb= new StringBuffer();
    try {
      while((ch=bin.read())!=-1)
      {
        sb.append(ch);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return Compart(sb.toString());
    
  }
}
