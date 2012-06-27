package org.xpup.hafmis.signjoint.util;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.io.*;
import java.math.BigDecimal;

import javax.servlet.ServletContext;

import org.xpup.hafmis.signjoint.dto.ConfigDTO;
/**
 * 工具类
 * 需要什么就到这里来找吧
 * @author yinchao
 */
public class SignTools {
  private static ServletContext sc=null;
  public static XmlIO xo=null;//XML输出类

  /**
   * 获取当前Servlet上下文
   * @return 当前Servlet上下文
   */
  public static ServletContext getServletContext() {
    return sc;
  }
  /**
   * 设置当前Servlet上下文
   * @param sc Servlet上下文
   */
  public static void setServletContext(ServletContext sc) {
    SignTools.sc = sc;
    xo=new XmlIO(sc);
  }  
//以下为常用工具方法
//------------------------------------------------------------------  
  /**
   * 转换数据格式
   * @param 带有分隔符的字符串
   * @return 从字符中提取的List
   */
  public static List Compart(String temp)
  {
    List list=new ArrayList();
    String str=null;
    int pointer=0;
    int length=temp.length();
    String mark=xo.getConfig().getMark();
    for(int i=0;i<length;i++)
    {
      str=temp.substring(i,i+1);
      if(str.equals(mark))
      {
       list.add(temp.substring(pointer,i));
       pointer=i+1;
      }
    }
    if(list.size()!=0){
      return list;
    }
    else{
      list.add(temp);
      return list;
    }
    //list.add(temp.substring(pointer,temp.length()));//末尾不加分隔符
    
  }
  
  /**
   * 将传进来的List中的数据以逗号分隔符合并成一个String(末尾加'|')
   * @param 存着数据项的List
   * @return 将其合并成一个特定格式的字符串
   */
  public static String combinationWithComma(List temp)
  {
    Iterator iter=temp.iterator();
    StringBuffer sb= new StringBuffer();
    String mark=",";
    String temp_str=null;
    while(iter.hasNext())
    {
      sb.append(((String)iter.next()+mark));
    }
    temp_str=sb.toString();
    temp_str=temp_str.substring(0,temp_str.length()-1)+xo.getConfig().getMark();
    return temp_str;
    //return sb.toString().substring(1);//如果末尾不加分隔符
    
  }
  
  /**
   * 将传进来的List中的数据以逗号分隔符合并成一个String(末尾加',')
   * @param 存着数据项的List
   * @return 将其合并成一个特定格式的字符串
   */
  public static String combinationWithAllComma(List temp)
  {
    Iterator iter=temp.iterator();
    StringBuffer sb= new StringBuffer();
    String mark=",";
    String temp_str=null;
    while(iter.hasNext())
    {
      sb.append(((String)iter.next()+mark));
    }
    return sb.toString();
    //return sb.toString().substring(1);//如果末尾不加分隔符
    
  }
  /**
   * 将传进来的List中的数据以配置分隔符合并成一个String
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
  public static String BigDecimaltoLang(BigDecimal money)
  {
    long tolang=(long)(money.doubleValue()*100);
    if(tolang==0){
      return "0";
    }
    String str=Long.toString(tolang);
    return str.substring(0,str.length()-2)+"."+str.substring(str.length()-2);
  }
  
//------------------------------------------------------------------
//以下为返回操作结果的方法
//------------------------------------------------------------------
  /**
   * 返回操作失败的数据
   * @param disposalnum 操作号
   * @return 失败数据
   */
  public static String getFailedInfo(String disposalnum)
  {
    List list=new ArrayList();
    list.add("01");
    list.add(disposalnum);
    return SignTools.Combination(list);
  }

  /**
   * 返回操作成功的数据
   * @param disposalnum 操作号,data 查询结果数据,remark 备注数据
   * @return 成功数据
   */
  public static String getSuccessInfo(String disposalnum,List data,List remark){
    List list=new ArrayList();
    list.add("00");
    list.add(disposalnum);
    Iterator it=data.iterator();
    while(it.hasNext())
    {
      list.add(it.next());
    }
    return SignTools.Combination(list)+SignTools.combinationWithComma(remark);
  }
  /**
   * 返回操作成功的数据
   * @param disposalnum 操作号,data 查询结果数据,remark 备注数据
   * @return 成功数据
   */
  public static String getSuccessInfo(String disposalnum,List data){
    List list=new ArrayList();
    list.add("00");
    list.add(disposalnum);
    Iterator it=data.iterator();
    while(it.hasNext())
    {
      list.add(it.next());
    }
    return SignTools.Combination(list);
  }
  /**
   * 获得02返回码操作的数据
   * 在贷款余额查询、贷款明细查询时
   * 如果该用户没贷款,则返回：此码+操作码
   * @param disposalnum操作码
   * @return 02返回码操作的数据
   */
  public static String getInfo_02(String disposalnum){
    List list=new ArrayList();
    list.add("02");
    list.add(disposalnum);
    return SignTools.Combination(list);
  }
  /**
   * 获得03返回码操作的数据
   * 在余额明细查询、贷款明细查询时，查询的时间范围如果大于6个月，
   * 将拒绝查询，返回：此码+操作码
   * @param disposalnum操作码
   * @return 03返回码操作的数据
   */
  public static String getInfo_03(String disposalnum){
    List list=new ArrayList();
    list.add("03");
    list.add(disposalnum);
    return SignTools.Combination(list);
  }
  /**
   * 获得04返回码操作的数据
   * 如果在余额查询、余额明细查询、贷款查询、贷款明细查询
   * 时，中心未签约该用户,则返回:此码+操作码 
   * @param disposalnum操作码
   * @return 04返回码操作的数据
   */
  public static String getInfo_04(String disposalnum){
    List list=new ArrayList();
    list.add("04");
    list.add(disposalnum);
    return SignTools.Combination(list);
  }
  /**
   * 获得05返回码操作的数据
   * 中心信息系统如发生IO错误或其他业务无关错误，将返回：此码
   * @return 05返回码操作的数据
   */
  public static String getInfo_05(){
    List list=new ArrayList();
    list.add("05");
    return SignTools.Combination(list);
  }
  /**
   * 获得06返回码操作的数据
   * 银行传来的数据为非法数据格式或非法数据，返回：此码+操作码
   * @param disposalnum操作码
   * @return 06返回码操作的数据
   */
  public static String getInfo_06(String disposalnum){
    List list=new ArrayList();
    list.add("06");
    list.add(disposalnum);
    return SignTools.Combination(list);
  }

  /**
   * 获得07返回码操作的数据
   * 在贷款查询、贷款明细查询时，如果用户已经签约，但职工姓名，
   * 和身份证等信息在公基金信息系统中不存在，返回：此码+操作码
   * @param disposalnum操作码
   * @return 07返回码操作的数据
   */
  public static String getInfo_07(String disposalnum){
    List list=new ArrayList();
    list.add("07");
    list.add(disposalnum);
    return SignTools.Combination(list);
  }
  /**
   * 获得08返回码操作的数据
   * 在余额查询、余额明细查询时，如果用户已经签约，
   * 但职工编号信息在公基金信息系统中不存在，返回：此码+操作码
   * @param disposalnum操作码
   * @return 08返回码操作的数据
   */
  public static String getInfo_08(String disposalnum){
    List list=new ArrayList();
    list.add("08");
    list.add(disposalnum);
    return SignTools.Combination(list);
  }
  
  
//------------------------------------------------------------------
  /**
   * 判断List里元素的个数是否等于num
   * @param list要判断个数的List
   * @param num 要判断的个数
   * @return 是否相等
   */
  public static boolean isRightNum(List list,int num){
    if(list.size()==num){
      return true;
    }
    else{
      return false;
    }
  }

//以下为空值转换方法
//------------------------------------------------------------------
  /**
   * 判断是否是空值，并转换
   * @param money 浮点数对象
   * @return 转换后的结果
   */
  public static BigDecimal parseNull(BigDecimal money){
    if(money!=null){
      return money;
    }
    return new BigDecimal(0);  
  }
  /**
   * 判断是否是空值，并转换
   * @param num 整数对象
   * @return 转换后的结果
   */
  public static Integer parseNull(Integer num){
    if(num!=null){
      return num;
    }
    return new Integer(0);
    
  }  
  /**
   * 判断是否是空值，并转换
   * @param str 字符串
   * @return 转换后的结果
   */
  public static String parseNull(String str){
    if(str!=null){
      return str;
    }
    return "";
  }
  /**
   * 判断是否是空值，并转换
   * @param str 字符串
   * @return 转换后的结果
   */
  public static Long parseNull(Long lon){
    if(lon!=null){
      return lon;
    }
    return new Long(0);
  }
//------------------------------------------------------------------
//以下为备用工具方法
//------------------------------------------------------------------  
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

  /**
   * 从缓存流中读取数据，转换成String
   * @param in 字符流
   * @return 转换后的结果
   * @throws IOException
   */
  public static String ReaderToString(BufferedReader in) throws IOException{
    
    char [] ch=new char[300];
    int number=0;
    char mark=xo.getConfig().getMark().charAt(0);
    number=in.read(ch);
    String temp=new String(ch).trim();
    if(number!=-1){
      return temp;
    }
    else{
      return "";
    } 
  }
  /**
   * 计算结息年度
   * @return 开始年月和结束年月
   */
  public static String [] getCheckOutAccrual(){
    String curdate=null;
    String startdate=null;
    String enddate=null;
    Date d = new Date();
    String year = Integer.toString(d.getYear()+1900);
    String month = Integer.toString(d.getMonth()+1);
    String day = Integer.toString(d.getDate());
    if(month.length()==1) month="0"+month;
    if(day.length()==1) day="0"+day;  
    StringBuffer sb=new StringBuffer();
    if((d.getMonth()+1)>6){
      startdate=Integer.toString(d.getYear()+1900)+"07";
      enddate=Integer.toString(d.getYear()+1900+1)+"06";
      String [] str={startdate,enddate};
      return str;
    }else{
      startdate=Integer.toString(d.getYear()+1900-1)+"07";
      enddate=Integer.toString(d.getYear()+1900)+"06";
      String [] str={startdate,enddate};
      return str;
    }
  }
  /**
   * 给定年月的字符串加若干月
   * @param yyyymm指定年月
   * @param xm加几月
   * @return 运算后的年月字符串
   */
  public static String getNextXMonth(String yyyymm,int xm)
  { 
    if("".equalsIgnoreCase(yyyymm)){
      return yyyymm;
    }
    int year=0;
    int month=0;
    year=Integer.parseInt(yyyymm.trim().substring(0,4));
    month=Integer.parseInt(yyyymm.trim().substring(4));
    month=month+xm;
    if(month<=12){
      return Integer.toString(year*100+month);
    }else{
      year=year+month/12;
      month=month%12;
      return Integer.toString(year*100+month);
    } 
  }
  
  /**
   * 给定年月的字符串减若干月
   * @param yyyymm指定年月
   * @param xm减几月
   * @return 运算后的年月字符串
   */
  public static String getPreviousXMonth(String yyyymm,int xm)
  { 
    if("".equalsIgnoreCase(yyyymm)){
      return yyyymm;
    }
    int year=0;
    int month=0;
    year=Integer.parseInt(yyyymm.trim().substring(0,4));
    month=Integer.parseInt(yyyymm.trim().substring(4));
    month=month-xm;
    if(month>0){
      return Integer.toString(year*100+month);
    }else{
      year=year-((-(month-1)/12)+1);
      month=12+(month)%12;
      return Integer.toString(year*100+month);
    } 
  }
  
 /**
   * 将List中的记录置i个空字串
   * @param list 要置空串的list
   * @param i 置空串的数量
   * @return 置i个空字串后的list
   */
  public static List rowIsNull(List list,int i){
    for(int j=0;j<i;j++){
      list.add("");
    }
    return list;
  }

  
  /**
   * 根据扣款日获得当前逾期期数月份
   * @param repayday 扣款日
   * @return 逾期期数月份
   */
  public static String getLoanRepayYearMonth(String repayday){
    String curdate=null;
    String startdate=null;
    String enddate=null;
    Date d = new Date();
    String year = Integer.toString(d.getYear()+1900);
    String month = Integer.toString(d.getMonth()+1);
    String day = Integer.toString(d.getDate());
    int m=0;
    int y=0;
    if(month.length()==1) month="0"+month;
    if(day.length()==1) day="0"+day;
    if(Integer.parseInt(day)>Integer.parseInt(repayday.trim())){
      return year+month;
    }else{
      m=Integer.parseInt(month)-1;
      y=Integer.parseInt(year)-1;
      if(m!=0){
        return year+Integer.toString(m);  
      }else{
        return Integer.toString(y)+"12";
      }
    }
  }
  /**
   * 第一个年月日比第二个年月日大多少个月
   * @param yyyymmdd1 大的年月日
   * @param yyyymmdd2 小的年月日
   * @return 相差的年月,如果运算失败返回-1
   */
  public static int dateBiggerThanDateXMonth(String yyyymmdd1,String yyyymmdd2){
    if("".equalsIgnoreCase(yyyymmdd1)||"".equalsIgnoreCase(yyyymmdd2)){
      return -1;
    }
    if((Integer.parseInt(yyyymmdd1)-Integer.parseInt(yyyymmdd2))<0){
      return -1;
    }

    int dd1=Integer.parseInt(yyyymmdd1.substring(6));
    int dd2=Integer.parseInt(yyyymmdd2.substring(6));   
    yyyymmdd1=yyyymmdd1.substring(0,6);
    yyyymmdd2=yyyymmdd2.substring(0,6);
    int year1=0;
    int month1=0;
    int year2=0;
    int month2=0;
    year1=Integer.parseInt(yyyymmdd1.trim().substring(0,4));
    month1=Integer.parseInt(yyyymmdd1.trim().substring(4));
    year2=Integer.parseInt(yyyymmdd2.trim().substring(0,4));
    month2=Integer.parseInt(yyyymmdd2.trim().substring(4));
    int sumy=year1-year2;
    int summ=month1-month2;
    //System.out.println((dd1-dd2)>=0?(sumy*12+summ):(sumy*12+summ-1));
    return (dd1-dd2)<=0?(sumy*12+summ):(sumy*12+summ+1);    
  }
  /**
   * 校验是否为正确的日期
   * @param date 日期
   * @return 是否为合法日期 合法返回true,非法返回false
   * @throws ParseException 
   */
  
  public static boolean isRightDate(String date) {
    if(date.length()!=8){
      return false;
    }
    String year=date.substring(0,4);
    String month=date.substring(4,6);
    String day=date.substring(6);
    if((!year.matches("[0-9]{4}"))||(!month.matches("[0-9]{2}"))||(!day.matches("[0-9]{2}"))){
      return false; 
    }
    int y=Integer.parseInt(year);
    int m=Integer.parseInt(month);
    int d=Integer.parseInt(day);
    boolean run=(y%4)==0;//是否是润年
    if(!(m>0&&m<=12)){
      return false;
    }
    if(m==2){//如果查询月份为2月
      if(!run){//是否为润年
        if(d>0&&d<=28)
          return true;
        else
          return false;
      }else{
        if(d>0&&d<=29)
          return true;
        else
          return false;
      }
    }
    int [] small={4,6,9,11};//小月
    for(int i=0;i<4;i++){//判断是否为小月
      if(m==small[i]){
         if(d>0&&d<=30){
           return true;
         }else{
           return false;
         }
      }
    }
    return true; 
  }
  
  /**
   * 是否为正确的签约号
   * @param empid 签约号
   * @return true为正确的职工号 false为非法的职工号
   */
  public static boolean isRigntSignid(String empid){
    if(empid.matches("\\d+")){
      return true;
    }else{
      return false;
    }
  }
  

  /**
   * 将18位身份证号转为15位
   * @param cardnum 身份证号
   * @return 转换后的身份证号
   */
  public static String conversionTo15DigitCard_num(String cardnum){
    return cardnum.substring(0,6)+cardnum.substring(8, cardnum.length()-1); 
  }
  
  
  
  public void readParam(){
    ResourceBundle bundle; 
  }
  

  
  
  
  
  
  
  
  
  
}
