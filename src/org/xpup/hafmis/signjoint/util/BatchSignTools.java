package org.xpup.hafmis.signjoint.util;
import java.io.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
/**
 * 批量签约工具类
 * @author yinchao
 */
public class BatchSignTools {

  public static String requestFile="gjjkqy_"+getTodayDateString()+".txt";//请求文件名 
  public static String responseFile="gjjkqyhp_"+getTodayDateString()+".txt";//银行返回文件名
  public static String requestpath="";
  public static String responsepath="";
  
  
  /**
   * 是否有回文文件
   * @return
   */
  public static boolean isHaveResponseFile(){
    File f=new File(responsepath,responseFile); 
    return f.exists();
  }
  
  
  
  /**
   * 获得今天日期的字符串 
   * @return 格式为：YYYYMMDD
   */
  public static String getTodayDateString(){
    Date d = new Date();
    String year = Integer.toString(d.getYear()+1900);
    String month = Integer.toString(d.getMonth()+1);
    String day = Integer.toString(d.getDate());
    if(month.length()==1) month="0"+month;
    if(day.length()==1) day="0"+day;
    return year+month+day;
  }
  /**
   * 判断文件是否已创建
   * @param path 路径
   * @return
   */
  public static boolean isHaveTodayFile(String path){
    File f=new File(path,requestFile); 
    return f.exists();
  }
  /**
   * 向文件尾追加文本
   * @param path 文件路径
   * @param file 文件名
   * @param content 要追加的文本
   * @throws IOException 
   */
  public static void superAdditiontoTodayFile(String path,String file,String content) throws IOException{
    FileWriter fw=new FileWriter(path+file,true);   
    PrintWriter pw=new PrintWriter(fw);   
    pw.print(content);   
    pw.close();   
    fw.close();
  }
  /**
   * 创建请求文件
   * @param path 路径
   * @throws IOException
   */
  public static void mkRequestTodayFile() throws IOException{
    File f=new File(requestpath,requestFile); 
    f.createNewFile();
  }
  
  /**
   * 读取回文
   * @param path 文件路径
   * @return 回文
   * @throws IOException 
   */
  public static String readResponseFile(String name) throws IOException{
    StringBuffer sb=new StringBuffer();
    File response=new File(responsepath,name);
    FileReader in=new FileReader(response);
    BufferedReader bin=new BufferedReader(in);
    int c;
    while((c=bin.read())!=-1)
      sb.append((char)c);
    bin.close();
    return sb.toString();
  }
  
  /**
   * 分析回文
   * @param content 要分析的文本
   * @param num 每条的段数
   * @return List每项存放着每条记录
   */
  public static List Analyse(String content,int num){
    List list=SignTools.Compart(content);
    List returnlist=new ArrayList();
    List temp=new ArrayList();
    int length=list.size();
    for(int i=0;i<length;i++){
      temp.add(list.get(i));
      if(((i+1)%num)==0){
         returnlist.add(temp);
         temp.clear();
      }
    }
    return returnlist;
  }

  /**
   * 回文是否是正确格式
   * @param content 回文
   * @param num 每条的数目
   * @return true是正确格式 false是错误格式
   */
  public static boolean isRightFormat(String content,int num){
    List list=SignTools.Compart(content);
    return list.size()%num==0?true:false;
  }
  /**
   * 将List内容包装后写入文件请求
   * @param path 文件路径
   * @param file 文件名
   * @param list 请求信息内容
   * @throws IOException
   */
  public static void writeRequestInfo(String path,String file,List list) throws IOException{
    superAdditiontoTodayFile(path,file,SignTools.Combination(list));
  }
  /**
   * 获得办理人员
   * @return
   */
  public static String getOperater(){
    
    
    return null;
  }
  /**
   * 将左边'0'去掉
   * @param id 
   * @return
   */
  public static String trimID(String id){
    String str;
    int i;
    id=id.trim();
    String temp=id;
    int length=id.length();
    for(i=0;i<length;i++)
    {
      str=temp.substring(i,i+1);
      if(!str.equalsIgnoreCase("0"))
         break;        
    }    
    return id.substring(i);
  }
  
  /**
   * 将'~'分隔的串拆分为两个字符串
   * @param str
   * @return
   */
  public static String [] Compart(String str){
    
    String mark="~";
    str=str.trim();
    int length=str.length();
    String [] temp=new String[2];
    int i=0;
    for(i=0;i<length;i++){
      if(str.substring(i,i+1).equalsIgnoreCase(mark))
      {
         temp[0]=str.substring(0,i);
         break;
      }
    }
    temp[1]=str.substring(i+1);
    return temp;
  }
  
  /**
   * 转换正确与错误的标识
   * @param str
   * @return
   */
  public static String transformSucc_Fail(String str){
    if(str.equalsIgnoreCase("0")){
      return "成功";
    }else if(str.equalsIgnoreCase("1")){
      return "失败";
    }else{
      return "未知";
    }
  }
  
  /**
   * 获得当前时间的Timestamp类型
   * @return
   */
  public  static Timestamp   getTimestamp()   
  { Calendar   calendar   =   new   GregorianCalendar();   
    String   str="";   
    int   year;   
    int   month;   
    int   date;   
    int   hour;   
    int   minute;   
    int   second;   
    int   million;   
    year   =   calendar.get(Calendar.YEAR);   
    month   =   calendar.get(Calendar.MONTH);   
    date   =   calendar.get(Calendar.DAY_OF_MONTH);   
    hour   =   calendar.get(Calendar.HOUR);   
    minute   =   calendar.get(Calendar.MINUTE);   
    second   =   calendar.get(Calendar.SECOND);     
    str   =Integer.toString(year)+"-"+Integer.toString(month+1)   +   "-"   
    +   Integer.toString(date)   +   " "   +   Integer.toString(hour)   +   ":"   +   Integer.toString(minute)   +   ":"     
    +   Integer.toString(second);    
    return   Timestamp.valueOf(str);   
  }
  
  
  /**
   * 转换文件类型
   * @param str
   * @return
   */
  public static String transformFile_Type(String str){
    if(str.equalsIgnoreCase("0")){
      return "生成文件";
    }else if(str.equalsIgnoreCase("1")){
      return "回文文件";
    }else{
      return "未知";
    }
  }
  
  
  
  

  
  
  public static String getRequestFile() {
    return requestFile;
  }
  public static void setRequestFile(String requestFile) {
    BatchSignTools.requestFile = requestFile;
  }
  public static String getResponseFile() {
    return responseFile;
  }
  public static void setResponseFile(String responseFile) {
    BatchSignTools.responseFile = responseFile;
  }



  public static String getRequestpath() {
    return requestpath;
  }



  public static void setRequestpath(String requestpath) {
    BatchSignTools.requestpath = requestpath;
  }



  public static String getResponsepath() {
    return responsepath;
  }



  public static void setResponsepath(String responsepath) {
    BatchSignTools.responsepath = responsepath;
  }
  
  


}
