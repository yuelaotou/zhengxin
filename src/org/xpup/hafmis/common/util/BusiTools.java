package org.xpup.hafmis.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * @author 刘洋 2007-6-2
 */
public abstract class BusiTools {
  /**
   * 根据参数返回业务属性的列表 例如：得到单位属性（"直属单位","三省公司","系统外单位"）
   * 使用方法如下：BusiTools.listBusiProperty(BusiConst.ORGUNIT_PROPERTY);
   * 
   * @return Map 可以用Iterator entries = map.entrySet().iterator()取出当中的数据。
   */
  public static Map listBusiProperty(String busiPro) throws Exception {
    Map busiProList = null;
    try {
      busiProList = (Map) Class.forName(busiPro).newInstance();
    } catch (Exception e) {
      throw e;
    }
    return busiProList;
  }

  /**
   * 转换单位编号的为6位...从数据库读出来的单位编号是int类型 前面没有0 单位编号在导出的时候 必须是6位数字 边不够补0
   * 参数:从数据库读出来的编号 return 6位的标准职工和单位编号
   */
  public static String convertSixNumber(String number) {
    if (number == null || number.equals(""))
      return "";
    StringBuffer buffer = new StringBuffer("");
    if (number.length() < 6) {
      int length = number.length();
      for (int i = 0; i < 6 - length; i++)
        buffer.append("0");
      number = buffer.toString() + number;
    }
    return number;
  }
  public static String convertTwoNumber(String number) {
    if (number == null || number.equals(""))
      return "";
    if (number.length() < 2) {
      return "0"+number;
    }
    return number;
  }
  public static String convertFourNumber(String number) {
    if (number == null || number.equals(""))
      return "";
    StringBuffer buffer = new StringBuffer("");
    if (number.length() < 4) {
      int length = number.length();
      for (int i = 0; i < 4 - length; i++)
        buffer.append("0");
      number = buffer.toString() + number;
    }
    return number;
  }
  public static String convertTenNumber(String number) {
    if (number == null || number.equals(""))
      return "";
    StringBuffer buffer = new StringBuffer("");
    if (number.length() < 10) {
      int length = number.length();
      for (int i = 0; i < 10 - length; i++)
        buffer.append("0");
      number = buffer.toString() + number;
    }
    return number;
  }
  public static String removePreZero(String number){
    int i=0;
    for (i = 0; i < number.length(); i++) {
      if(!number.substring(i,i+1).equals("0")){
        break;
      }
    }
    return number.substring(i,number.length());
  }
  /**
   * 根据参数返回业务属性的列表 例如：得到单位属性的值（"直属单位","三省公司","系统外单位"）
   * 使用方法如下：BusiTools.getBusiValue(1,BusiConst.ORGUNIT_PROPERTY);
   * 
   * @return String
   */
  public static String getBusiValue(int key, String busiPro) throws Exception {
    Map busiProList = null;
    String value = "";
    try {
      busiProList = (Map) Class.forName(busiPro).newInstance();
    } catch (Exception e) {
      throw e;
    }
    value = (String) busiProList.get(new Integer(key));
    return value;

  }

  /**
   * 根据value取回key值，使用时要判断是否返回值为999，如果为999则取key失败 注意value要trim，否则可能失败。
   * 
   * @param value
   * @param busiPro
   * @return
   * @throws Exception
   */
  public static int getBusiKey(String value, String busiPro) throws Exception {
    Map busiProList = null;
    int key = 999;
    try {
      busiProList = (Map) Class.forName(busiPro).newInstance();
      Iterator entries = busiProList.entrySet().iterator();
      List list = new ArrayList();
      while (entries.hasNext()) {
        java.util.Map.Entry en = (java.util.Map.Entry) entries.next();
        if (en.getValue().equals(value)) {
          key = Integer.parseInt(en.getKey().toString());
        }
      }
    } catch (Exception e) {
      throw e;
    }
    return key;

  }

  /**
   * 根据参数返回业务属性的列表 例如：得到单位属性的值（"直属单位","三省公司","系统外单位"）
   * 使用方法如下：BusiTools.getBusiValue(1,BusiConst.ORGUNIT_PROPERTY);
   * 
   * @return String
   */
  public static String getBusiValue_WL(String key, String busiPro)
      throws Exception {
    Map busiProList = null;
    String value = "";
    try {
      busiProList = (Map) Class.forName(busiPro).newInstance();
    } catch (Exception e) {
      throw e;
    }
    value = (String) busiProList.get(key);
    return value;

  }

  /**
   * 根据value取回key值，使用时要判断是否返回值为999，如果为999则取key失败 注意value要trim，否则可能失败。
   * 
   * @param value
   * @param busiPro
   * @return
   * @throws Exception
   */
  public static String getBusiKey_WL(String value, String busiPro)
      throws Exception {
    Map busiProList = null;
    String key = "999";
    try {
      busiProList = (Map) Class.forName(busiPro).newInstance();
      Iterator entries = busiProList.entrySet().iterator();
      List list = new ArrayList();
      while (entries.hasNext()) {
        java.util.Map.Entry en = (java.util.Map.Entry) entries.next();
        if (en.getValue().equals(value)) {
          key = (String) en.getKey();
        }
      }
    } catch (Exception e) {
      throw e;
    }
    return key;

  }

  /**
   * 根据给定的日期和时间格式, 将日期类型格式化成字符串. 常用的格式有:
   * <li>yyyy-MM-dd 长日期格式</li>
   * <li>HH:dd:ss 长时间格式</li>
   * <li>yyyy-MM-dd HH:dd 长日期时间格式</li>
   * 这些格式已经在Constant类里定义了.
   * 
   * @param date 日期时间型
   * @param format 日期格式
   * @return String 字符串形式的日期(时间)
   */
  public static String dateToString(java.util.Date date, String format) {
    String pattern = format;
    java.text.SimpleDateFormat formatter = new SimpleDateFormat();
    formatter.applyPattern(pattern);
    return formatter.format(date);

  }

  public static String dateToString(java.sql.Timestamp date, String format) {
    String pattern = format;
    java.text.SimpleDateFormat formatter = new SimpleDateFormat();
    formatter.applyPattern(pattern);
    return formatter.format(date);
  }

  /**
   * 把一个字符串形式的日期转换成java.util.Date类型.
   * 
   * @param strDate String 字符串形式的日期
   * @return java.util.Date
   */
  public static java.util.Date stringToUDate(String s, String pattern)
      throws ParseException {
    try {
      java.util.Date date;
      SimpleDateFormat formatter = new SimpleDateFormat(pattern);
      date = formatter.parse(s);
      return date;
    } catch (ParseException e) {
      throw e;
    }

  }

  /**
   * 对日期字符串（必须为yyyyMM形式） 进行增加月份的操作
   * 
   * @param ym
   * @param monthNum
   * @return
   */
  public static String addMonth(String ym, int monthNum) {

    Calendar d = Calendar.getInstance();
    d.set(Calendar.YEAR, Integer.parseInt(ym.substring(0, 4)));
    d.set(Calendar.MONTH, Integer.parseInt(ym.substring(4, 6)));
    d.set(Calendar.DATE, 0);
    d.add(Calendar.MONTH, monthNum);
    return BusiTools.dateToString(d.getTime(), "yyyyMM");

  }

  public static String addMonths(String ym, int monthNum) {

    Calendar d = Calendar.getInstance();
    d.set(Calendar.YEAR, Integer.parseInt(ym.substring(0, 4)));
    d.set(Calendar.MONTH, Integer.parseInt(ym.substring(4, 6)));
    d.set(Calendar.DATE, 0);
    d.add(Calendar.MONTH, monthNum);
    return BusiTools.dateToString(d.getTime(), "yyyyMM");

  }

  /**
   * 对日期字符串（必须为yyyyMMdd形式） 进行增加日的操作
   * 
   * @param yyyymmdd
   * @param dayNum
   * @return
   * @throws ParseException
   */
  public static String addDay(String yyyymmdd, int dayNum)
      throws ParseException {
    Date dt = stringToUDate(yyyymmdd + "000000",
        BusiConst.PUB_LONG_DATE_TIME_SECOND_PATTERN_yyyyMMddHHmmss);
    long now = dt.getTime();
    long next = now + dayNum * 24 * 3600 * 1000; // 一天24小时*3600秒*1000毫秒
    Date dtNext = new Date(next);
    return dateToString(dtNext, BusiConst.PUB_LONG_YMD_PATTERN);

  }

  /**
   * 计算两个日期之间相差多少天
   * 
   * @param strDate1 String
   * @param strDate2 String
   * @return int
   */
  public static int minusDate(String strDate1, String strDate2) {
    int longReturn = 999;
    java.sql.Date date1 = null;
    java.sql.Date date2 = null;
    try {
      date1 = java.sql.Date.valueOf(strDate1);
      date2 = java.sql.Date.valueOf(strDate2);
      int thevalue = (int) ((date1.getTime() - date2.getTime())
          / (1000 * 60 * 60 * 24) + 0.5);
      longReturn = thevalue;
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return longReturn;
  }

  /**
   * 计算这个月有多少天 int month /1-12/
   * 
   * @param year
   * @param month
   * @return int
   */
  public static int daysOfMonth(int year, int month) {
    int days[] = { 31, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
    if (2 == month && 0 == (year % 4)
        && (0 != (year % 100) || 0 == (year % 400))) { // 判断闰年，当
      // month
      // = 2
      // 时才判断，以减少计算量
      days[2] = 29;
    }
    return (days[month]);
  }

  /**
   * 判断两个时间的间隔月份
   * 
   * @param sDate 字符串复合"yyyyMMdd"格式,应该为较大日期
   * @param eDate 字符串复合"yyyyMMdd"格式,应该为较小日期
   * @return 如果后面日期比前面日期小,那么返回-1,如果一样返回0,如果后面日期比前面的大,返回相差的月份,
   * @throws ParseException
   */
  public static int monthInterval(String sDate, String eDate)
      throws ParseException {

    try {

      Date sDateD = BusiTools.stringToUDate(sDate,
          BusiConst.PUB_LONG_DATE_PATTERNS);
      Date eDateD = BusiTools.stringToUDate(eDate,
          BusiConst.PUB_LONG_DATE_PATTERNS);

      if (sDateD.after(eDateD)) {
        return -1;
      } else if (sDateD.equals(eDateD)) {
        return 0;
      } else {
        if (eDateD.getMonth() - sDateD.getMonth() >= 0) {
          return (eDateD.getYear() - sDateD.getYear()) * 12
              + (eDateD.getMonth() - sDateD.getMonth());
        } else {
          return (eDateD.getYear() - sDateD.getYear() - 1) * 12
              + (eDateD.getMonth() - sDateD.getMonth() + 12);
        }
      }
    } catch (ParseException e) {
      throw e;
    }

  }
  
  /**
   * 判断两个时间的间隔月份
   * 
   * @param sDate 字符串复合"yyyy-MM"格式,应该为较大日期
   * @param eDate 字符串复合"yyyy-MM"格式,应该为较小日期
   * @return 如果后面日期比前面日期小,那么返回-1,如果一样返回0,如果后面日期比前面的大,返回相差的月份,
   * @throws ParseException 
   * @throws ParseException
   */
  public static int getDisMonths(String sDate, String eDate)
      throws ParseException {

    Date value1 = BusiTools.stringToUDate(eDate,
        BusiConst.PUB_LONG_DATE_PATTERN);
    Date value2 = BusiTools.stringToUDate(sDate,
        BusiConst.PUB_LONG_DATE_PATTERN);

    Calendar cal = Calendar.getInstance();
    cal.setTime(value1);
    int year1 = cal.get(Calendar.YEAR);
    int month1 = cal.get(Calendar.MONTH);
    cal.setTime(value2);
    int year2 = cal.get(Calendar.YEAR);
    int month2 = cal.get(Calendar.MONTH);
    return (year2 - year1) * 12 + month2 - month1;
  }

  /**
   * 刘洋
   * 
   * @param sDate 最小年月
   * @param eDate 最大年月
   * @return 最小年月开始到最大年月结束的list
   * @throws ParseException
   */
  public static List getYearList(String sDate, String eDate)
      throws ParseException {
    List yearList = new ArrayList();
    try {
      if (!sDate.equals("") && !eDate.equals("")) {
        Date sDateD = BusiTools.stringToUDate(sDate,
            BusiConst.PUB_LONG_DATE_PATTERNS);
        Date eDateD = BusiTools.stringToUDate(eDate,
            BusiConst.PUB_LONG_DATE_PATTERNS);
        sDate = BusiTools.dateToString(sDateD, BusiConst.PUB_DATE_YM_PATTERN);
        eDate = BusiTools.dateToString(eDateD, BusiConst.PUB_DATE_YM_PATTERN);
        int monthCount = BusiTools.monthInterval(sDate + "-01", eDate + "-01");
        for (int i = 0; i <= monthCount; i++) {
          // YearListInfor yearListInfor=new YearListInfor();
          String key = BusiTools.addMonth(sDate, i);
          // yearListInfor.setKey(key);
          // yearListInfor.setValue(key);
          yearList.add(key);
        }
      }// else{
      // YearListInfor yearListInfor=new YearListInfor();
      // yearListInfor.setKey("");
      // yearListInfor.setValue("");
      // yearList.add(yearListInfor);
      // }
    } catch (ParseException e) {
      e.printStackTrace();
      throw e;
    }
    return yearList;
  }
  
  /**
   * 得到每月里面日的下拉菜单
   * @return
   */
 public static List getDayList(String yearMonth){
   List dayList=new ArrayList();
   try{
      if(yearMonth!=null&&!yearMonth.equals("")){
//      String sDate=dateToString(stringToUDate(yearMonth+"01",BusiConst.PUB_LONG_DATE_PATTERN),BusiConst.PUB_LONG_DATE_PATTERN);
//      String eDate=dateToString(stringToUDate(addMonth(yearMonth,1)+"01",BusiConst.PUB_LONG_DATE_PATTERN),BusiConst.PUB_LONG_DATE_PATTERN);
//      int days=minusDate(eDate,sDate);
        int days=daysOfMonth(Integer.parseInt(yearMonth.substring(0, 4)),Integer.parseInt(yearMonth.substring(4, 6)));
        for(int i=1;i<=days;i++){
        dayList.add(i+"");
      }
     }
   }catch(Exception e){
     e.printStackTrace();
   }
   return dayList;
 }
  /** ************************************************************ */
  public static void main(String[] arg) {
    try {

      // Map map = BusiTools.listBusiProperty(BusiConst.PROVINCE);
      // Iterator entries = map.entrySet().iterator();
      // List list = new ArrayList();
      // while(entries.hasNext())
      // {
      // java.util.Map.Entry en = (java.util.Map.Entry)entries.next();
      // System.out.println(" key : "+en.getKey() + " value : " +
      // en.getValue());
      // }
      // System.out.println("分中心编码 : " + BusiTools.generateSCCode("01"));
      // System.out.println("单位编码 ： " +
      // BusiTools.generateOUCode("000101000000","1"));

      //Date date = new Date();
      List list=getDayList("200710");
      for(int i=0;i<list.size();i++){
        System.out.println(list.get(i).toString());
      }
      // System.out.println(monthInterval("200706","200709"));
      // System.out.println(addMonths("200705",1));
      // System.out.println(BusiTools.generateEmpCode());
      // System.out.println("二级单位编码 ： " +
      // BusiTools.generateSOUCode("000101100100"));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // **********************财务：对公式进行转化，例如A201
  // 转化成201年初借方金额*****************************//

  /**
   * 传进来公式的数字表达式,转换成汉字
   * 
   * @param s String
   * @return String
   */
  public static String getExpressName(String s) {
    String name = "";
    StringTokenizer substr = new StringTokenizer(s, "+");
    while (substr.hasMoreTokens()) {
      String expressBig = substr.nextToken();
      StringTokenizer substr2 = new StringTokenizer(expressBig, "-");
      int num = substr2.countTokens();
      if (num > 1) {
        int j = 0;
        while (substr2.hasMoreTokens()) {
          String express = substr2.nextToken();
          j++;
          if (j == num) {
            if (express.length() > 2) {
              name += express.substring(2, express.length());
            }
            name += getName(express.substring(0, 2));
            name += "+";
          } else {
            if (express.length() > 2) {
              name += express.substring(2, express.length());
            }
            name += getName(express.substring(0, 2));
            name += "-";
          }
        }
      } else {
        if (expressBig.length() > 2) {
          name += expressBig.substring(2, expressBig.length());
        }
        name += getName(expressBig.substring(0, 2));
        name += "+";
      }
    }
    return name.substring(0, name.length() - 1);
  }

  /**
   * 通过公式类型的代码,转化成公式类型名称
   * 
   * @param express String  
   * @return String
   */
  public static String getName(String express) {
    String name = "";   
    try {
      if (express.substring(0, 2).equals(BusiConst.REPORTLOGO_BEGBALANCE_DEBIT)) {
        name += getBusiValue_WL(BusiConst.REPORTLOGO_BEGBALANCE_DEBIT, BusiConst.REPORTLOGO);
      } else if (express.substring(0, 2).equals(BusiConst.REPORTLOGO_BEGBALANCE_CREDIT)) {
        name += getBusiValue_WL(BusiConst.REPORTLOGO_BEGBALANCE_CREDIT, BusiConst.REPORTLOGO);
      } else if (express.substring(0, 2).equals(BusiConst.REPORTLOGO_ENDBALANCE_DEBIT)) {
        name += getBusiValue_WL(BusiConst.REPORTLOGO_ENDBALANCE_DEBIT, BusiConst.REPORTLOGO);
      } else if (express.substring(0, 2).equals(BusiConst.REPORTLOGO_ENDBALANCE_CREDIT)) {
        name += getBusiValue_WL(BusiConst.REPORTLOGO_ENDBALANCE_CREDIT, BusiConst.REPORTLOGO);
        
      } else if (express.substring(0, 2).equals(BusiConst.REPORTLOGO_CURFIGURES_DEBIT)) {
        name += getBusiValue_WL(BusiConst.REPORTLOGO_CURFIGURES_DEBIT, BusiConst.REPORTLOGO);
      } else if (express.substring(0, 2).equals(BusiConst.REPORTLOGO_CURFIGURES_CREDIT)) {
        name += getBusiValue_WL(BusiConst.REPORTLOGO_CURFIGURES_CREDIT, BusiConst.REPORTLOGO);
      } else if (express.substring(0, 2).equals(BusiConst.REPORTLOGO_CURCUMULATIVEFIGURES_DEBIT)) {
        name += getBusiValue_WL(BusiConst.REPORTLOGO_CURCUMULATIVEFIGURES_DEBIT, BusiConst.REPORTLOGO);
      } else if (express.substring(0, 2).equals(BusiConst.REPORTLOGO_CURCUMULATIVEFIGURES_CREDIT)) {
        name += getBusiValue_WL(BusiConst.REPORTLOGO_CURCUMULATIVEFIGURES_CREDIT, BusiConst.REPORTLOGO);
      } else if (express.substring(0, 2).equals(BusiConst.REPORTLOGO_LASTATIVEFIGURES_DEBIT)) {
        name += getBusiValue_WL(BusiConst.REPORTLOGO_LASTATIVEFIGURES_DEBIT, BusiConst.REPORTLOGO);
      } else if (express.substring(0, 2).equals(BusiConst.REPORTLOGO_LASTATIVEFIGURES_CREDIT)) {
        name += getBusiValue_WL(BusiConst.REPORTLOGO_LASTATIVEFIGURES_CREDIT, BusiConst.REPORTLOGO);
        
      } else if (express.substring(0, 2).equals(BusiConst.REPORTLOGO_CURFIGURES_SUMDEBIT)) {
        name += getBusiValue_WL(BusiConst.REPORTLOGO_CURFIGURES_SUMDEBIT, BusiConst.REPORTLOGO);
      } else if (express.substring(0, 2).equals(BusiConst.REPORTLOGO_CURFIGURES_SUMCREDIT)) {
        name += getBusiValue_WL(BusiConst.REPORTLOGO_CURFIGURES_SUMCREDIT, BusiConst.REPORTLOGO);
      } else if (express.substring(0, 2).equals(BusiConst.REPORTLOGO_CURCUMULATIVEFIGURES_SUMDEBIT)) {
        name += getBusiValue_WL(BusiConst.REPORTLOGO_CURCUMULATIVEFIGURES_SUMDEBIT, BusiConst.REPORTLOGO);
      } else if (express.substring(0, 2).equals(BusiConst.REPORTLOGO_CURCUMULATIVEFIGURES_SUMCREDIT)) {
        name += getBusiValue_WL(BusiConst.REPORTLOGO_CURCUMULATIVEFIGURES_SUMCREDIT, BusiConst.REPORTLOGO);
      } else if (express.substring(0, 2).equals(BusiConst.REPORTLOGO_LASTATIVEFIGURES_SUMDEBIT)) {
        name += getBusiValue_WL(BusiConst.REPORTLOGO_LASTATIVEFIGURES_SUMDEBIT, BusiConst.REPORTLOGO);
      } else if (express.substring(0, 2).equals(BusiConst.REPORTLOGO_LASTATIVEFIGURES_SUMCREDIT)) {
        name += getBusiValue_WL(BusiConst.REPORTLOGO_LASTATIVEFIGURES_SUMCREDIT, BusiConst.REPORTLOGO);
        
      } else if(express.substring(0, 2).equals(BusiConst.REPORTLOGO_COL)){
        name += getBusiValue_WL(BusiConst.REPORTLOGO_COL, BusiConst.REPORTLOGO);
        
      } else if (express.substring(0, 2).equals(BusiConst.REPORTLOGO_REPAIR_CURTERMAMOUNT)) {
        name += getBusiValue_WL(BusiConst.REPORTLOGO_REPAIR_CURTERMAMOUNT, BusiConst.REPORTLOGO);
      } else if (express.substring(0, 2).equals(BusiConst.REPORTLOGO_REPAIR_CURYEARAMOUNT)) {
        name += getBusiValue_WL(BusiConst.REPORTLOGO_REPAIR_CURYEARAMOUNT, BusiConst.REPORTLOGO);
      } else if (express.substring(0, 2).equals(BusiConst.REPORTLOGO_REPAIR_CURYEARSUMAMOUNT)) {
        name += getBusiValue_WL(BusiConst.REPORTLOGO_REPAIR_CURYEARSUMAMOUNT, BusiConst.REPORTLOGO);
        
      } else if (express.substring(0, 2).equals(BusiConst.REPORTLOGO_RETIREMENT_CURTERMAMOUNT)) {
        name += getBusiValue_WL(BusiConst.REPORTLOGO_RETIREMENT_CURTERMAMOUNT, BusiConst.REPORTLOGO);
      } else if (express.substring(0, 2).equals(BusiConst.REPORTLOGO_RETIREMENT_CURYEARAMOUNT)) {
        name += getBusiValue_WL(BusiConst.REPORTLOGO_RETIREMENT_CURYEARAMOUNT, BusiConst.REPORTLOGO);
      } else if (express.substring(0, 2).equals(BusiConst.REPORTLOGO_RETIREMENT_CURYEARSUMAMOUNT)) {
        name += getBusiValue_WL(BusiConst.REPORTLOGO_RETIREMENT_CURYEARSUMAMOUNT, BusiConst.REPORTLOGO);
        
      } else if (express.substring(0, 2).equals(BusiConst.REPORTLOGO_LOSEABILITY_CURTERMAMOUNT)) {
        name += getBusiValue_WL(BusiConst.REPORTLOGO_LOSEABILITY_CURTERMAMOUNT, BusiConst.REPORTLOGO);
      } else if (express.substring(0, 2).equals(BusiConst.REPORTLOGO_LOSEABILITY_CURYEARAMOUNT)) {
        name += getBusiValue_WL(BusiConst.REPORTLOGO_LOSEABILITY_CURYEARAMOUNT, BusiConst.REPORTLOGO);
      } else if (express.substring(0, 2).equals(BusiConst.REPORTLOGO_LOSEABILITY_CURYEARSUMAMOUNT)) {
        name += getBusiValue_WL(BusiConst.REPORTLOGO_LOSEABILITY_CURYEARSUMAMOUNT, BusiConst.REPORTLOGO);
        
      } else if (express.substring(0, 2).equals(BusiConst.REPORTLOGO_ABROAD_CURTERMAMOUNT)) {
        name += getBusiValue_WL(BusiConst.REPORTLOGO_ABROAD_CURTERMAMOUNT, BusiConst.REPORTLOGO);
      } else if (express.substring(0, 2).equals(BusiConst.REPORTLOGO_ABROAD_CURYEARAMOUNT)) {
        name += getBusiValue_WL(BusiConst.REPORTLOGO_ABROAD_CURYEARAMOUNT, BusiConst.REPORTLOGO);
      } else if (express.substring(0, 2).equals(BusiConst.REPORTLOGO_ABROAD_CURYEARSUMAMOUNT)) {
        name += getBusiValue_WL(BusiConst.REPORTLOGO_ABROAD_CURYEARSUMAMOUNT, BusiConst.REPORTLOGO);
        
      } else if (express.substring(0, 2).equals(BusiConst.REPORTLOGO_REIMBURSEMENT_CURTERMAMOUNT)) {
        name += getBusiValue_WL(BusiConst.REPORTLOGO_REIMBURSEMENT_CURTERMAMOUNT, BusiConst.REPORTLOGO);
      } else if (express.substring(0, 2).equals(BusiConst.REPORTLOGO_REIMBURSEMENT_CURYEARAMOUNT)) {
        name += getBusiValue_WL(BusiConst.REPORTLOGO_REIMBURSEMENT_CURYEARAMOUNT, BusiConst.REPORTLOGO);
      } else if (express.substring(0, 2).equals(BusiConst.REPORTLOGO_REIMBURSEMENT_CURYEARSUMAMOUNT)) {
        name += getBusiValue_WL(BusiConst.REPORTLOGO_REIMBURSEMENT_CURYEARSUMAMOUNT, BusiConst.REPORTLOGO);
        
      } else if (express.substring(0, 2).equals(BusiConst.REPORTLOGO_PAYACCOMMODATION_CURTERMAMOUNT)) {
        name += getBusiValue_WL(BusiConst.REPORTLOGO_PAYACCOMMODATION_CURTERMAMOUNT, BusiConst.REPORTLOGO);
      } else if (express.substring(0, 2).equals(BusiConst.REPORTLOGO_PAYACCOMMODATION_CURYEARAMOUNT)) {
        name += getBusiValue_WL(BusiConst.REPORTLOGO_PAYACCOMMODATION_CURYEARAMOUNT, BusiConst.REPORTLOGO);
      } else if (express.substring(0, 2).equals(BusiConst.REPORTLOGO_PAYACCOMMODATION_CURYEARSUMAMOUNT)) {
        name += getBusiValue_WL(BusiConst.REPORTLOGO_PAYACCOMMODATION_CURYEARSUMAMOUNT, BusiConst.REPORTLOGO);
        
      } else if (express.substring(0, 2).equals(BusiConst.REPORTLOGO_OTHERS_CURTERMAMOUNT)) {
        name += getBusiValue_WL(BusiConst.REPORTLOGO_OTHERS_CURTERMAMOUNT, BusiConst.REPORTLOGO);
      } else if (express.substring(0, 2).equals(BusiConst.REPORTLOGO_OTHERS_CURYEARAMOUNT)) {
        name += getBusiValue_WL(BusiConst.REPORTLOGO_OTHERS_CURYEARAMOUNT, BusiConst.REPORTLOGO);
      } else if (express.substring(0, 2).equals(BusiConst.REPORTLOGO_OTHERS_CURYEARSUMAMOUNT)) {
        name += getBusiValue_WL(BusiConst.REPORTLOGO_OTHERS_CURYEARSUMAMOUNT, BusiConst.REPORTLOGO);
        
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return name;
  }
  
}