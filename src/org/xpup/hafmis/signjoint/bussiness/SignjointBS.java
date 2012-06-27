package org.xpup.hafmis.signjoint.bussiness;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.xpup.hafmis.signjoint.util.SignTools;
import org.apache.commons.beanutils.BeanUtils;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.imp.rule.UtilRule;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.demo.domain.entity.Demo;
import org.xpup.hafmis.signjoint.dao.SignDAO;
import org.xpup.hafmis.signjoint.bsinterface.ISignjointBS;
import org.xpup.hafmis.signjoint.dto.BaseEmpInfoDTO;

import org.xpup.hafmis.signjoint.dto.HistoryDTO;
import org.xpup.hafmis.signjoint.dto.LogDTO;
import org.xpup.hafmis.signjoint.dto.RecieveFileDTO;
import org.xpup.hafmis.signjoint.dto.RequestSignDTO;
import org.xpup.hafmis.signjoint.dto.SignImportBodyDTO;
import org.xpup.hafmis.signjoint.dto.SignImportHeadDTO;
import org.xpup.hafmis.signjoint.dto.BaseInfoDTO;
import org.xpup.hafmis.signjoint.dto.TempDTO;
import org.xpup.hafmis.signjoint.entity.Sign;
import org.xpup.hafmis.signjoint.util.SignTools;

public class SignjointBS implements ISignjointBS{

  private static final String SIGN_UP_NUMBER="1001";//签约操作号
  private static final String QUERY_BALANCE_NUMBER="1003";//公基金帐户余额操作号
  private static final String QUERY_LIST_BALANCE_NUBBER="1004";//公基金帐户余额明细操作号
  private static final String QUERY_BORROW_BALANCE_NUMBER="1005";//贷款余额操作号
  private static final String QUERY_BORROW_LIST_BALANCE="1006";//贷款余额明细操作号
  private SignDAO signdao=null;
  public SignDAO getSigndao() {
    return signdao;
  }
  public void setSigndao(SignDAO signdao) {
    this.signdao = signdao;
  }
  /**
   * 插入Sign数据
   * @param dto 存放着签约信息的SignDTO
   * @return 返回签约情况
   */
  public String saveSign(RequestSignDTO dto)
  {
   try{
    if(dto.getCardnum().length()==18){//如果为18位身份证号
      String card15=SignTools.conversionTo15DigitCard_num(dto.getCardnum());//转换为15位身份证号
      if(signdao.isHaveUserInfo(dto.getEmpid(), dto.getName(), dto.getCardnum())){
        return sign(dto);
      }else if(signdao.isHaveUserInfo(dto.getEmpid(), dto.getName(),card15)){
        dto.setCardnum(card15);
        return sign(dto);
      }else{
        return SignTools.getInfo_07(SIGN_UP_NUMBER);
      }
    }else {//如果为15位身份证号
      String card18=signdao.to18Card_num(dto.getCardnum());//转换为18位身份证号
      if(signdao.isHaveUserInfo(dto.getEmpid(), dto.getName(), dto.getCardnum())){
        return sign(dto);
      }else if(signdao.isHaveUserInfo(dto.getEmpid(), dto.getName(),card18)){
        dto.setCardnum(card18);
        return sign(dto);
      }else{
        return SignTools.getInfo_07(SIGN_UP_NUMBER);
      }
    }
   }//try catch
   catch(Exception e){
     e.printStackTrace();
     return SignTools.getFailedInfo(SIGN_UP_NUMBER);
   }
 }
  
  /**
   * 向数据库插入数据 给saveSign方法使用
   * @param dto 存放着签约信息的SignDTO
   * @return 返回签约情况
   */
  private String sign(RequestSignDTO dto){
    List list_sign=new ArrayList();
    //提取数据
    Sign sign=new Sign();
    sign.setBank_card(dto.getBanknum());
    sign.setCard_num(dto.getCardnum());
    sign.setEmpid(dto.getEmpid());
    sign.setName(dto.getName());
    Integer i=signdao.save(sign);//插入数据
    if(i.intValue()!=0){//是否插入成功
      list_sign.add(i.toString());
      return SignTools.getSuccessInfo(SIGN_UP_NUMBER,list_sign);
    }
    else{//失败
      return SignTools.getFailedInfo(SIGN_UP_NUMBER);
    }
  }
  
  
  
  
  
  
  /**
   * 根据唯一标识查其他签约信息
   * @param sign 唯一标识
   * @return 返回签约对象
   */
  public Sign querySignBySignNum(String sign) {
    Sign s=null;
    List list=signdao.getBySign(sign);
    if((list.size()!=0)&&(list!=null))
     return s=(Sign)list.get(0);
    return s;
  }
  /**
   * 查询公基金帐户余额
   * @param dto 存放着基本信息的DTO
   * @return 返回余额信息
   */
  public String queryBalance(BaseInfoDTO dto) {
    List list_1=new ArrayList();
    List list_2=new ArrayList();
    List list_3=new ArrayList();
    List list_4=new ArrayList();
    List list_5=new ArrayList();
    List list_main=new ArrayList();
    List list_subordination=new ArrayList();
    String empid=null;
    empid=dto.getEmpid();
    String startdate=SignTools.getCheckOutAccrual()[0].trim();
    String enddate=SignTools.getCheckOutAccrual()[1].trim();
    if(empid!=null)//如果唯一标识没有查询失败
    {
      if(!signdao.isHaveEmp_id(empid)){//数据库中是否存有职工编号
        return SignTools.getInfo_08(QUERY_BALANCE_NUMBER);
      }
      list_1=signdao.getBalancePartOne(empid);
      list_2=signdao.getBalancePartTwo(empid);
      list_3=signdao.getBalancePartThree(empid, startdate, enddate);
      list_4=signdao.getBalancePartFour(empid);
      list_5=signdao.getBalancePartFive(empid);
      //添加主要信息
      list_main.add(list_5.get(0));//余额
      list_main.add(list_5.get(1));//姓名
      list_main.add(list_5.get(2));//证件号码
      list_main.add(list_5.get(3));//单位名称
      list_main.add(list_2.get(0));//月缴存额
      //添加附加信息
      list_subordination.add(list_5.get(4));//个人帐号
      list_subordination.add(list_5.get(5));//单位帐号
      list_subordination.add(list_5.get(6));//证件类型
      list_subordination.add(list_5.get(7));//帐户状态
      list_subordination.add(list_5.get(8));//定期余额
      list_subordination.add(list_5.get(9));//活期余额
      list_subordination.add(list_1.get(0));//上年余额
      list_subordination.add(list_1.get(1));//上年利息
      list_subordination.add(list_2.get(0));//月缴存额
      list_subordination.add(list_2.get(1));//其中个人部分
      list_subordination.add(list_4.get(0));//上次缴存年月
      list_subordination.add(list_5.get(10));//下次月缴存额
      list_subordination.add(list_5.get(11));//其中个人部分
      list_subordination.add(list_3.get(0));//本年汇缴合计
      list_subordination.add(list_3.get(1));//本年补缴合计                                     
      list_subordination.add(list_3.get(2));//本年支取合计
      list_subordination.add(list_5.get(12));//开户日期
      list_subordination.add(SignTools.getNextXMonth((String)list_4.get(0), 1));//下次应缴月份
      return SignTools.getSuccessInfo(QUERY_BALANCE_NUMBER,list_main,list_subordination);
    }//empid!=null
    else{//唯一标识查询失败
      return SignTools.getFailedInfo(QUERY_BALANCE_NUMBER);
    }
  }
  /**
   * 查询余额明细
   * @param dto 存放着基本信息的DTO,startdate开始时间,enddate结束时间
   * @return 返回余额明细信息
   */
  public String queryListBalance(BaseInfoDTO dto,String startdate,String enddate){
    /*转换日期的方法
    try {
      BusiTools.dateToString(BusiTools.stringToUDate("2007-02-29", BusiConst.PUB_LONG_YMD_PATTERN),BusiConst.PUB_LONG_YMD_PATTERN);
    } catch (ParseException e) {
      e.printStackTrace();
    }*/
    try {
     //String start=BusiTools.dateToString(BusiTools.stringToUDate(startdate, BusiConst.PUB_LONG_DATE_PATTERN),BusiConst.PUB_LONG_YMD_PATTERN);
     //String end=BusiTools.dateToString(BusiTools.stringToUDate(enddate, BusiConst.PUB_LONG_DATE_PATTERN),BusiConst.PUB_LONG_YMD_PATTERN);
     String start=startdate;
     String end=enddate;
     int startint=Integer.parseInt(start);
     int endint=Integer.parseInt(end);
     int isbigger=endint-startint;
     start=Integer.toString(startint);
     end=Integer.toString(endint);
     //int isbigger=Integer.getInteger(end).intValue()-Integer.getInteger(start).intValue();//开始日期是否小于结束日期
     List temp=new ArrayList();
     List list_balance=new ArrayList();
     StringBuffer buf=new StringBuffer();
     String empid=null;
     
     List list_main=new ArrayList();//存放以'|'分隔的主要信息
     List list_subordination=new ArrayList();//存放以','分隔的附加信息
     
     empid=dto.getEmpid();
     if((empid!=null)&&(isbigger>=0))//如果唯一标识没有查询失败
     {
       if(SignTools.dateBiggerThanDateXMonth(enddate,startdate)>6){//如果查询范围大于6个月
         return SignTools.getInfo_03(QUERY_LIST_BALANCE_NUBBER);
       }
       if(!signdao.isHaveEmp_id(empid)){//数据库中是否存有职工编号
         return SignTools.getInfo_08(QUERY_LIST_BALANCE_NUBBER);
       }

       list_balance=signdao.getListBalance(empid, start, end);
       
       String empinfo=SignTools.combinationWithAllComma(signdao.getEmpInfoByEmpID(empid));//先获得单位帐号，单位名称，个人帐号，姓名
       empinfo=empinfo.substring(0, empinfo.length()-1);
       
       temp.add(empinfo);
       temp.add(list_balance.get(0));
       
       buf.append(SignTools.getSuccessInfo(QUERY_LIST_BALANCE_NUBBER,temp));
       if(Integer.parseInt(((String)list_balance.get(0)).trim())!=0){
          temp.clear();
       int size=list_balance.size();
        for(int i=1;i<size;i++){
          temp=(List)list_balance.get(i);
          list_subordination.add(temp.get(0));//日期
          list_subordination.add(temp.get(1));//摘要
          list_subordination.add(temp.get(2));//发生额
          list_subordination.add(temp.get(3));//余额
          //下面为附加信息
          //list_subordination.add(temp.get(4));//单位帐号
          //list_subordination.add(temp.get(5));//单位名称
          //list_subordination.add(temp.get(6));//个人帐号
          //list_subordination.add(temp.get(7));//姓名        
          list_subordination.add(temp.get(8));//借贷标识
          list_subordination.add(temp.get(9));//余额其中定期
          list_subordination.add(temp.get(10));//汇缴年月
          buf.append(SignTools.Combination(list_main));
          buf.append(SignTools.combinationWithAllComma(list_subordination));
          temp.clear();
          //list_main.clear();
          list_subordination.clear();
         }
        String s=buf.toString();
        return s.substring(0, s.length()-1)+"|";
       }//如果明细条目为0
       else{
        buf.append("|");
        return buf.toString();
       }
     }//empid!=null isbigger>=0
     else{//查询失败
       return SignTools.getFailedInfo(QUERY_LIST_BALANCE_NUBBER);
     }
    } catch (Exception e) {
      e.printStackTrace();
      return SignTools.getFailedInfo(QUERY_LIST_BALANCE_NUBBER);
    }
  }


  /**
   * 查询贷款余额
   * @param dto 存放着基本信息的DTO
   * @return 返回贷款余额信息
   */
  public String queryBorrowBalance(BaseInfoDTO dto){
   try{
    List list_balance=new ArrayList();
    String card_num=dto.getCard_num();
    String name=dto.getName();
    List list_main=new ArrayList();
    List list_subordination=new ArrayList();
    
    if((card_num!=null)&&(name!=null))//如果唯一标识没有查询失败
    {
      if(!signdao.isHaveNameAndCard_num(name,card_num)){//职工姓名和身份证号是否存在
        return SignTools.getInfo_02(QUERY_BORROW_BALANCE_NUMBER);
      }
      String date=SignTools.getLoanRepayYearMonth(signdao.getLoanRepayDay(name, card_num));
      if(date.equalsIgnoreCase("")){//如果用户没贷款
        return SignTools.getInfo_02(QUERY_BORROW_BALANCE_NUMBER);
      }
      int corb=signdao.getBorrowBalanceByBankORCenter();
      if(corb==0){
        return SignTools.getFailedInfo(QUERY_BORROW_BALANCE_NUMBER);
      }
      else if(corb==1){//以中心为主
        list_balance=signdao.getBorrowBalanceByCenter(name, card_num, date);
      }
      else {//以银行为主
        //获得当前年月
        Date d = new Date();
        String year = Integer.toString(d.getYear()+1900);
        String month = Integer.toString(d.getMonth()+1);
        if(month.length()==1) month="0"+month;
        
        list_balance=signdao.getBorrowBalanceByBank(name, card_num, SignTools.getPreviousXMonth(year+month, 1));
      }
      //贷款余额|姓名|身份证|单位名称|月还金额|贷款帐号,逾期余额,贷款本金,累计还款额,逾期期数,剩余期限,下期扣款日,下期还款金额|
      
      
      if((list_balance.size()!=0)&&(list_balance!=null)){//如果余额没有查询失败
        if(Integer.parseInt((String)list_balance.get(0))==0){//如果贷款已还清
      //还款金额、逾期余额，逾期期数、下期扣款日、下期扣款金额置为0
          list_main.add(list_balance.get(0));//余额
          list_main.add(list_balance.get(1));//姓名
          list_main.add(list_balance.get(2));//身份证
          list_main.add(list_balance.get(3));//单位名称
          list_main.add("0");//月还金额
          
          list_subordination.add(list_balance.get(5));//贷款帐号
          list_subordination.add("0");//逾期余额
          list_subordination.add(list_balance.get(7));//贷款本金
          list_subordination.add(list_balance.get(8));//累计还款额
          list_subordination.add("0");//逾期期数
          list_subordination.add(list_balance.get(10));//剩余期限
          list_subordination.add("0");//下期扣款日
          list_subordination.add("0");//下期还款金额
          return SignTools.getSuccessInfo(QUERY_BORROW_BALANCE_NUMBER, list_main, list_subordination);
        }
        list_main.add(list_balance.get(0));//余额
        list_main.add(list_balance.get(1));//姓名
        list_main.add(list_balance.get(2));//身份证
        list_main.add(list_balance.get(3));//单位名称
        list_main.add(list_balance.get(4));//月还金额

        list_subordination.add(list_balance.get(5));//贷款帐号
        list_subordination.add(list_balance.get(6));//逾期余额
        list_subordination.add(list_balance.get(7));//贷款本金
        list_subordination.add(list_balance.get(8));//累计还款额
        list_subordination.add(list_balance.get(9));//逾期期数
        list_subordination.add(list_balance.get(10));//剩余期限
        list_subordination.add(list_balance.get(11));//下期扣款日
        list_subordination.add(list_balance.get(12));//下期还款金额
        return SignTools.getSuccessInfo(QUERY_BORROW_BALANCE_NUMBER, list_main, list_subordination);       
      }
      else{//余额查询失败
        return SignTools.getFailedInfo(QUERY_BORROW_BALANCE_NUMBER);
      }
    }//card_num!=null name!=null
    else{//查询失败
      return SignTools.getFailedInfo(QUERY_BORROW_BALANCE_NUMBER);
    }
   }catch(Exception e)
   {
     e.printStackTrace();
     return SignTools.getFailedInfo(QUERY_BORROW_BALANCE_NUMBER);
   }
  }
  
   /** 查询贷款余额明细
    * @param dto 存放着基本信息的DTO,startdate开始时间,enddate结束时间
    * @return 返回贷款余额明细信息
    */
  public String queryBorrowListBalance(BaseInfoDTO dto,String startdate,String enddate){
    try {
      String start=startdate;
      String end=enddate;
      String card_num=dto.getCard_num();
      String name=dto.getName();
      int startint=Integer.parseInt(start);
      int endint=Integer.parseInt(end);
      int isbigger=endint-startint;
      StringBuffer buf=new StringBuffer();
      start=Integer.toString(startint);
      end=Integer.toString(endint);
      //int isbigger=Integer.getInteger(end).intValue()-Integer.getInteger(start).intValue();//开始日期是否小于结束日期
      List list_balance=new ArrayList();
      if((name!=null)&&(card_num!=null)&&(isbigger>=0))//如果唯一标识没有查询失败
      {
        
        if(!signdao.isHaveNameAndCard_num(name,card_num)){//职工姓名和身份证号是否存在
          return SignTools.getInfo_02(QUERY_BORROW_LIST_BALANCE);
        }
        String date=SignTools.getLoanRepayYearMonth(signdao.getLoanRepayDay(name, card_num));
        if(date.equalsIgnoreCase("")){//如果用户没贷款
          return SignTools.getInfo_02(QUERY_BORROW_LIST_BALANCE);
        }
        if(SignTools.dateBiggerThanDateXMonth(enddate,startdate)>6){//如果查询范围大于6个月
          return SignTools.getInfo_03(QUERY_BORROW_LIST_BALANCE);
        }
        

        list_balance=signdao.getBorrowListBalance(name,card_num,start,end);
        
        List list_main=new ArrayList();//存放以'|'分隔的主要信息
        List list_subordination=new ArrayList();//存放以','分隔的附加信息
        List temp=new ArrayList();//暂时存放操作时的数据
        
        temp.add(signdao.getLoanIDByNameAndCard_num(name, card_num).get(0));//获得贷款帐号
        
        //返回码|交易码|明细条数|日期,摘要,回收本金,回收利息,贷款帐号,还款金额,时点贷款余额|

        temp.add(list_balance.get(0));
        buf.append(SignTools.getSuccessInfo(QUERY_BORROW_LIST_BALANCE,temp));
        if(Integer.parseInt(((String)list_balance.get(0)).trim())!=0){
        temp.clear();
        int size=list_balance.size();
         for(int i=1;i<size;i++){
           temp=(List)list_balance.get(i);
           list_subordination.add(temp.get(0));//日期
           list_subordination.add(temp.get(1));//摘要
           list_subordination.add(temp.get(2));//回收本金
           list_subordination.add(temp.get(3));//回收利息
           //list_subordination.add(temp.get(4));//贷款帐号
           list_subordination.add(temp.get(5));//还款金额
           list_subordination.add(temp.get(6));//时点贷款余额
           buf.append(SignTools.Combination(list_main));
           buf.append(SignTools.combinationWithAllComma(list_subordination));
           temp.clear();
           list_subordination.clear();
          }
         String s=buf.toString();
         return s.substring(0, s.length()-1)+"|";
        }//如果明细条目为0
        else{
          buf.append("|");
          return buf.toString();
        }
      }//empid!=null isbigger>=0
      else{//查询失败
        return SignTools.getFailedInfo(QUERY_BORROW_LIST_BALANCE);
      }
     } catch (Exception e) {
       e.printStackTrace();
       return SignTools.getFailedInfo(QUERY_BORROW_LIST_BALANCE);
     }
  }
  
//--------------------------------------------------------------------
//新业务逻辑 START
//--------------------------------------------------------------------


  /**
   * 查询公基金帐户余额
   * @param dto 存放着基本信息的DTO
   * @return 返回余额信息
   */
  public String queryNewBalance(BaseInfoDTO dto){
    String empid=dto.getEmpid();
    
    if(empid!=null&&(!"".equalsIgnoreCase(empid)))//如果唯一标识没有查询失败
    {
      //    执行数据同步，将数据同步到临时表
      signdao.execSynPartProcdural(dto.getEmpid(), dto.getName(), dto.getCard_num(), 1);
      if(!signdao.isNewHaveEmp_id(empid)){//数据库中是否存有职工编号
        return SignTools.getInfo_08(QUERY_BALANCE_NUMBER);
      }
      List list=signdao.getNewBalance(empid);
      List main=new ArrayList();//存放以'|'分隔的主要信息
      List subsidiary=new ArrayList();//存放以','分隔的附加信息
      main.add(list.get(0));
      main.add(list.get(1));
      main.add(list.get(2));
      main.add(list.get(3));
      main.add(list.get(4));
      
      subsidiary.add(list.get(5));
      subsidiary.add(list.get(6));
      subsidiary.add(list.get(7));
      subsidiary.add(list.get(8));
      subsidiary.add(list.get(9));
      subsidiary.add(list.get(10));
      subsidiary.add(list.get(11));
      subsidiary.add(list.get(12));
      subsidiary.add(list.get(13));
      subsidiary.add(list.get(14));
      subsidiary.add(list.get(15));
      subsidiary.add(list.get(16));
      subsidiary.add(list.get(17));
      subsidiary.add(list.get(18));
      subsidiary.add(list.get(19));
      subsidiary.add(list.get(20));
      subsidiary.add(list.get(21));
      subsidiary.add(list.get(22));
      return SignTools.getSuccessInfo(QUERY_BALANCE_NUMBER,main,subsidiary);
    }//empid!=null
    else{//唯一标识查询失败
      return SignTools.getFailedInfo(QUERY_BALANCE_NUMBER);
    }
  }
  /**
   * 查询余额明细
   * @param dto 存放着基本信息的DTO,startdate开始时间,enddate结束时间
   * @return 返回余额明细信息
   */
  public String queryNewListBalance(BaseInfoDTO dto,String startdate,String enddate){
    try {
      String start=startdate;
      String end=enddate;
      int startint=Integer.parseInt(start);
      int endint=Integer.parseInt(end);
      int isbigger=endint-startint;
      start=Integer.toString(startint);
      end=Integer.toString(endint);

      List temp=new ArrayList();
      List list_balance=new ArrayList();
      StringBuffer buf=new StringBuffer();
      String empid=null;
      
      List list_main=new ArrayList();//存放以'|'分隔的主要信息
      List list_subordination=new ArrayList();//存放以','分隔的附加信息
      
      empid=dto.getEmpid();
      if((empid!=null)&&(isbigger>=0))//如果唯一标识没有查询失败
      {
        if(SignTools.dateBiggerThanDateXMonth(enddate,startdate)>6){//如果查询范围大于6个月
          return SignTools.getInfo_03(QUERY_LIST_BALANCE_NUBBER);
        }
        //执行数据同步，将数据同步到临时表
        signdao.execSynPartProcdural(dto.getEmpid(), dto.getName(), dto.getCard_num(), 1);
        signdao.execSynPartProcdural(dto.getEmpid(), dto.getName(), dto.getCard_num(), 2);
        if(!signdao.isNewHaveEmp_id(empid)){//数据库中是否存有职工编号
          return SignTools.getInfo_08(QUERY_LIST_BALANCE_NUBBER);
        }
        list_balance=signdao.getNewListBalance(empid, start, end);
        
        String empinfo=SignTools.combinationWithAllComma(signdao.getNewListBalanceInfo(empid));//先获得单位帐号，单位名称，个人帐号，姓名
        empinfo=empinfo.substring(0, empinfo.length()-1);
        
        temp.add(empinfo);
        temp.add(list_balance.get(0));
        
        buf.append(SignTools.getSuccessInfo(QUERY_LIST_BALANCE_NUBBER,temp));
        if(Integer.parseInt(((String)list_balance.get(0)).trim())!=0){
           temp.clear();
        int size=list_balance.size();
         for(int i=1;i<size;i++){
           temp=(List)list_balance.get(i);
           list_subordination.add(temp.get(0));//日期
           list_subordination.add(temp.get(1));//摘要
           list_subordination.add(temp.get(2));//发生额
           list_subordination.add(temp.get(3));//余额      
           list_subordination.add(temp.get(4));//借贷标识
           list_subordination.add(temp.get(5));//余额其中定期
           list_subordination.add(temp.get(6));//汇缴年月
           buf.append(SignTools.Combination(list_main));
           buf.append(SignTools.combinationWithAllComma(list_subordination));
           temp.clear();
           //list_main.clear();
           list_subordination.clear();
          }
         String s=buf.toString();
         return s.substring(0, s.length()-1)+"|";
        }//如果明细条目为0
        else{
         buf.append("|");
         return buf.toString();
        }
      }//empid!=null isbigger>=0
      else{//查询失败
        return SignTools.getFailedInfo(QUERY_LIST_BALANCE_NUBBER);
      }
     } catch (Exception e) {
       e.printStackTrace();
       return SignTools.getFailedInfo(QUERY_LIST_BALANCE_NUBBER);
     }
    
  }
  /**
   * 查询贷款余额
   * @param dto 存放着基本信息的DTO
   * @return 返回贷款余额信息
   */
  public String queryNewBorrowBalance(BaseInfoDTO dto){
    try{
      List list_balance=new ArrayList();
      String card_num=dto.getCard_num();
      String name=dto.getName();
      List list_main=new ArrayList();
      List list_subordination=new ArrayList();
      
      if((card_num!=null)&&(name!=null))//如果唯一标识没有查询失败
      {
        //      执行数据同步，将数据同步到临时表
        signdao.execSynPartProcdural(dto.getEmpid(), dto.getName(), dto.getCard_num(), 3);
        if(!signdao.isNewHaveNameAndCard_num(name,card_num)){//职工姓名和身份证号是否存在
          return SignTools.getInfo_02(QUERY_BORROW_BALANCE_NUMBER);
        }
        list_balance=signdao.getNewBorrowBalance(name, card_num);      
        if((list_balance.size()!=0)&&(list_balance!=null)){//如果余额没有查询失败
          if(((String)list_balance.get(0)).trim().equalsIgnoreCase("0")){//如果贷款已还清
        //还款金额、逾期余额，逾期期数、下期扣款日、下期扣款金额置为0
            list_main.add(list_balance.get(0));//余额
            list_main.add(list_balance.get(1));//姓名
            list_main.add(list_balance.get(2));//身份证
            list_main.add(list_balance.get(3));//单位名称
            list_main.add("0");//月还金额
            
            list_subordination.add(list_balance.get(5));//贷款帐号
            list_subordination.add("0");//逾期余额
            list_subordination.add(list_balance.get(7));//贷款本金
            list_subordination.add(list_balance.get(8));//累计还款额
            list_subordination.add("0");//逾期期数
            list_subordination.add(list_balance.get(10));//剩余期限
            list_subordination.add("0");//下期扣款日
            list_subordination.add("0");//下期还款金额
            return SignTools.getSuccessInfo(QUERY_BORROW_BALANCE_NUMBER, list_main, list_subordination);
          }
          list_main.add(list_balance.get(0));//余额
          list_main.add(list_balance.get(1));//姓名
          list_main.add(list_balance.get(2));//身份证
          list_main.add(list_balance.get(3));//单位名称
          list_main.add(list_balance.get(4));//月还金额

          list_subordination.add(list_balance.get(5));//贷款帐号
          list_subordination.add(list_balance.get(6));//逾期余额
          list_subordination.add(list_balance.get(7));//贷款本金
          list_subordination.add(list_balance.get(8));//累计还款额
          list_subordination.add(list_balance.get(9));//逾期期数
          list_subordination.add(list_balance.get(10));//剩余期限
          list_subordination.add(list_balance.get(11));//下期扣款日
          list_subordination.add(list_balance.get(12));//下期还款金额
          return SignTools.getSuccessInfo(QUERY_BORROW_BALANCE_NUMBER, list_main, list_subordination);       
        }
        else{//余额查询失败
          return SignTools.getFailedInfo(QUERY_BORROW_BALANCE_NUMBER);
        }
      }//card_num!=null name!=null
      else{//查询失败
        return SignTools.getFailedInfo(QUERY_BORROW_BALANCE_NUMBER);
      }
     }catch(Exception e)
     {
       e.printStackTrace();
       return SignTools.getFailedInfo(QUERY_BORROW_BALANCE_NUMBER);
     }
    
    
  }
  /** 查询贷款余额明细
   * @param dto 存放着基本信息的DTO,startdate开始时间,enddate结束时间
   * @return 返回贷款余额明细信息
   */
  public String queryNewBorrowListBalance(BaseInfoDTO dto,String startdate,String enddate){
    try {
      String start=startdate;
      String end=enddate;
      String card_num=dto.getCard_num();
      String name=dto.getName();
      int startint=Integer.parseInt(start);
      int endint=Integer.parseInt(end);
      int isbigger=endint-startint;
      StringBuffer buf=new StringBuffer();
      start=Integer.toString(startint);
      end=Integer.toString(endint);
      //int isbigger=Integer.getInteger(end).intValue()-Integer.getInteger(start).intValue();//开始日期是否小于结束日期
      List list_balance=new ArrayList();
      if((name!=null)&&(card_num!=null)&&(isbigger>=0))//如果唯一标识没有查询失败
      {
        if(SignTools.dateBiggerThanDateXMonth(enddate,startdate)>6){//如果查询范围大于6个月
          return SignTools.getInfo_03(QUERY_BORROW_LIST_BALANCE);
        }
        //执行数据同步，将数据同步到临时表
        signdao.execSynPartProcdural(dto.getEmpid(), dto.getName(), dto.getCard_num(), 3);
        signdao.execSynPartProcdural(dto.getEmpid(), dto.getName(), dto.getCard_num(), 4);
        if(!signdao.isNewHaveNameAndCard_num(name,card_num)){//职工姓名和身份证号是否存在
          return SignTools.getInfo_02(QUERY_BORROW_LIST_BALANCE);
        }
        list_balance=signdao.getNewBorrowListBalance(name,card_num,start,end);
        List list_main=new ArrayList();//存放以'|'分隔的主要信息
        List list_subordination=new ArrayList();//存放以','分隔的附加信息
        List temp=new ArrayList();//暂时存放操作时的数据
        List account=signdao.getNewLoanAccount(name, card_num);
        temp.add(account.get(0));//获得贷款帐号
        //返回码|交易码|明细条数|日期,摘要,回收本金,回收利息,贷款帐号,还款金额,时点贷款余额|
        temp.add(list_balance.get(0));
        buf.append(SignTools.getSuccessInfo(QUERY_BORROW_LIST_BALANCE,temp));
        if(Integer.parseInt(((String)list_balance.get(0)).trim())!=0){
        temp.clear();
        int size=list_balance.size();
         for(int i=1;i<size;i++){
           temp=(List)list_balance.get(i);
           list_subordination.add(temp.get(0));//日期
           list_subordination.add(temp.get(1));//摘要
           list_subordination.add(temp.get(2));//回收本金
           list_subordination.add(temp.get(3));//回收利息
           list_subordination.add(temp.get(4));//还款金额
           list_subordination.add(temp.get(5));//时点贷款余额
           buf.append(SignTools.Combination(list_main));
           buf.append(SignTools.combinationWithAllComma(list_subordination));
           temp.clear();
           list_subordination.clear();
          }
         String s=buf.toString();
         return s.substring(0, s.length()-1)+"|";
        }//如果明细条目为0
        else{
          buf.append("|");
          return buf.toString();
        }
      }//empid!=null isbigger>=0
      else{//查询失败
        return SignTools.getFailedInfo(QUERY_BORROW_LIST_BALANCE);
      }
     } catch (Exception e) {
       e.printStackTrace();
       return SignTools.getFailedInfo(QUERY_BORROW_LIST_BALANCE);
     }
  }
  
  /**
   * 插入Sign数据
   * @param dto 存放着签约信息的SignDTO
   * @return 返回签约情况
   */
  public String saveNewSign(RequestSignDTO dto)
  {

    if(dto.getCardnum().length()==18){//如果为18位身份证号
      String card15=SignTools.conversionTo15DigitCard_num(dto.getCardnum());//转换为15位身份证号
      if(signdao.isNewHaveUserInfo(dto.getEmpid(), dto.getName(), dto.getCardnum())){
        return newSign(dto);
      }else if(signdao.isNewHaveUserInfo(dto.getEmpid(), dto.getName(),card15)){
        dto.setCardnum(card15);
        return newSign(dto);
      }else{
        return SignTools.getFailedInfo(SIGN_UP_NUMBER);
      }
    }else {//如果为15位身份证号
      String card18=signdao.to18Card_num(dto.getCardnum());//转换为18位身份证号
      if(signdao.isNewHaveUserInfo(dto.getEmpid(), dto.getName(), dto.getCardnum())){
        return newSign(dto);
      }else if(signdao.isNewHaveUserInfo(dto.getEmpid(), dto.getName(),card18)){
        dto.setCardnum(card18);
        return newSign(dto);
      }else{
        return SignTools.getFailedInfo(SIGN_UP_NUMBER);
      }
    }
  }
  
  /**
   * 向数据库插入数据 给saveSign方法使用
   * @param dto 存放着签约信息的SignDTO
   * @return 返回签约情况
   */
  private String newSign(RequestSignDTO dto){
    List list_sign=new ArrayList();
    //提取数据
    Sign sign=new Sign();
    sign.setBank_card(dto.getBanknum());
    sign.setCard_num(dto.getCardnum());
    sign.setEmpid(dto.getEmpid());
    sign.setName(dto.getName());
    Integer i=signdao.save(sign);//插入数据
    if(i.intValue()!=0){//是否插入成功
      list_sign.add(i.toString());
      return SignTools.getSuccessInfo(SIGN_UP_NUMBER,list_sign);
    }
    else{//失败
      return SignTools.getFailedInfo(SIGN_UP_NUMBER);
    }
  }

//--------------------------------------------------------------------
//新业务逻辑 END
//--------------------------------------------------------------------
  /**
   * 执行老表与新表数据同步
   */
  public void execSynProcdure(){
    signdao.execSynProcdural();
  }
//--------------------------------------------------------------------
//批量签约 START
//--------------------------------------------------------------------

  
   /**
   * 根据单位编号查询该单位分页后的信息
   */
  public List queryAllEmpInfo(Pagination pagination) {
    List list=new ArrayList();
    
    String orgid=(String) pagination.getQueryCriterions().get("orgid");
    String empid=(String) pagination.getQueryCriterions().get("empid");  
    String orderBy=(String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother(); 
    int start = pagination.getFirstElementOnPage()-1;
    int pageSize = pagination.getPageSize(); 
    int page = pagination.getPage();
    list=signdao.queryEmpInfoByOrgIdFromTemp(orgid, empid, orderBy, order, start, pageSize, page);
    int count = signdao.queryEmpCountByOrgid(orgid);
    pagination.setNrOfElements(count);
//    if(list.size()==0){
//      throw new BusinessException("不存在记录");
//    }
    return list;
  }
  
  /**
   * 导入文本批量插入数据库
   * @param imp
   * @return
   * @throws BusinessException 
   */
  public void signImpBatch(List headlist,List bodylist) throws BusinessException{
    try{
      SignImportHeadDTO headdto=(SignImportHeadDTO)headlist.get(1);
      String orgid=headdto.getOrgid().trim();
      String orgname=headdto.getOrgname().trim();
      int impsize=bodylist.size();

      for(int i=1;i<impsize;i++){
        SignImportBodyDTO bodydto=(SignImportBodyDTO)bodylist.get(i);
        try{
          TempDTO temp=new TempDTO();
          temp.setOrgid(orgid.trim());
          temp.setOrgname(orgname.trim());
          temp.setEmpid(bodydto.getEmpid().trim());
          
          temp.setEmpname(bodydto.getEmpname().trim());
          temp.setCardnum(bodydto.getCardnum().trim());
          temp.setBankcardid(bodydto.getBankcardid().trim());
          if(signdao.isHaveSignInTemp(temp.getEmpid(), temp.getBankcardid())){
            signdao.deleteTemp(temp.getEmpid(),temp.getBankcardid());
          }
          signdao.insertEmpInfo(temp);
        }
        catch(SQLException e){
          e.printStackTrace();
          throw new BusinessException("导入失败！");
        }
      }
    }catch(Exception e){
      e.printStackTrace();
      throw new BusinessException("导入失败！");
    }
  }
  
  /**
   * 根据员工编号和银行卡号获得暂存表中的员工信息
   * @param empid 员工编号
   * @param bankcardid 银行卡号
   * @return
   */
  public RequestSignDTO getSingleEmpInfoByEmpidAndCardnum(String empid,String bankcardid){
    RequestSignDTO dto=new RequestSignDTO();
    List list=signdao.queryEmpInfoByEmpidAndBank(empid, bankcardid);
    if(list.size()!=0){
      Object [] obj=(Object [])list.get(0);
      dto.setEmpid(SignTools.parseNull((BigDecimal)obj[0]).toString().trim());
      dto.setName(SignTools.parseNull(((String)obj[1]).trim()));
      dto.setCardnum(SignTools.parseNull(((String)obj[2]).trim()));
      dto.setBanknum(SignTools.parseNull(((String)obj[3]).trim()));
      return dto;
    }else{
      return null;
    }
  }
  
  /**
   * 根据职工编号和银行卡号删除该员工信息
   * @param empid 员工编号
   * @param bankcardid 银行卡号
   * @return
   */
  public void deleteUserInfo(String orgid,String empid,String bankcardid) throws BusinessException{
    try{
      signdao.deleteByEmpidAndBank(orgid, empid, bankcardid);
    }catch(Exception e){
      throw new BusinessException("删除失败");
    }
  }
  /**
   * 删除该单位下的咱暂存表中的所有员工信息
   * @param orgid 单位编号
   * @return
   */
  public void deleteAll(String orgid) throws BusinessException {
    try{
      signdao.deleteAllByOrgId(orgid);
    }catch(Exception e){
      throw new BusinessException("批量删除失败");
    }
  }
  /**
   * 查询在暂存表中该单位下的所有员工信息,导出用
   * @param orgid 单位编号
   * @return
   */
  public List queryEmpListAll(Pagination pagination) throws BusinessException {
    String orgid=(String)pagination.getQueryCriterions().get("orgid");
    String orgname=(String)pagination.getQueryCriterions().get("orgname");
    if(orgid==null||orgid.equalsIgnoreCase("")){
      throw new BusinessException("导出失败");
    }
    if(orgname==null){
      orgname="";
    }  
    List list=signdao.queryEmpInfoByOrgID(orgid.trim(),orgname.trim());
    if(list==null){
      throw new BusinessException("导出失败");
    }
    return list;
  }
  
  /**
   * 根据单位编号查询单位名称
   */
  public String getOrgnameByOrgID(String orgid){
    return signdao.queryOrgnameByOrgID(orgid);
  }
  
  /**
   * 加入用户信息
   * @param dto 用户信息
   * @throws BusinessException
   */
  public void addUserInfo(TempDTO dto) throws BusinessException {
    try{
      if(signdao.isHaveSignInTemp(dto.getEmpid(), dto.getBankcardid())){
        signdao.deleteTemp(dto.getEmpid(), dto.getBankcardid());
      }
      signdao.insertEmpInfo(dto);
    }catch(Exception e){
      e.printStackTrace();
      throw new BusinessException("插入失败");
    }
  }

  /**
   * 修改用户信息
   * @param dto 用户信息
   * @throws BusinessException
   */
  public void modifyUserInfo(TempDTO newdto,TempDTO olddto) throws BusinessException {
    try{
      signdao.updateEmpInfo(newdto,olddto);
    }catch(SQLException e){
      e.printStackTrace();
      throw new BusinessException("修改失败");
    }
  }
  
  /**
   * 根据单位编号，职工编号，银行卡号，从暂存表中查询员工信息
   */
  public TempDTO queryUserInfo(String orgid, String empid, String bankcardid) throws BusinessException {
    TempDTO dto=new TempDTO();
    List list=signdao.queryUserInfo(empid,orgid,bankcardid);
    try{
      //empid,empname,cardnum,bankcardid
      dto.setEmpid((String)list.get(0));
      dto.setEmpname((String)list.get(1));
      dto.setCardnum((String)list.get(2));
      dto.setBankcardid((String)list.get(3));
      
    }catch(Exception e){
      e.printStackTrace();
      throw new BusinessException("修改用户失败");
    }
    return dto;
  }
  
  /**
   * 根据条件分页查询批量签约历史记录
   * @param pagination
   * @return
   * @throws BusinessException
   */
  public List queryHistoryList(Pagination pagination) throws BusinessException {
    List list=new ArrayList();
    String orgid=(String) pagination.getQueryCriterions().get("orgid");
    String empid=(String) pagination.getQueryCriterions().get("empid");  
    String transactdatastart=(String)pagination.getQueryCriterions().get("transactdatastart");
    String transactdataend=(String)pagination.getQueryCriterions().get("transactdataend");
    String affirmdatastart=(String)pagination.getQueryCriterions().get("affirmdatastart");
    String affirmdataend=(String)pagination.getQueryCriterions().get("affirmdataend");
    String issccueed=(String)pagination.getQueryCriterions().get("issccueed");
    String orderby=(String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother(); 
    int start = pagination.getFirstElementOnPage()-1;
    int pageSize = pagination.getPageSize(); 
    int page = pagination.getPage();
    try{
    list=signdao.queryHistoryInfo(orgid, empid, transactdatastart,transactdataend,affirmdatastart,affirmdataend,issccueed,orderby, order, start, pageSize, page);
    int count = signdao.queryEmpHistoryCount(orgid, empid, transactdatastart,transactdataend,affirmdatastart,affirmdataend,issccueed);
    pagination.setNrOfElements(count);
//    if(list.size()==0){
//      throw new BusinessException("不存在记录");
//    }
    }catch(Exception e){
      e.printStackTrace();
      throw new BusinessException("查询失败");
    }
    return list;
  }
  
  
  
  
  public List queryLogList(Pagination pagination) throws BusinessException {
    List list=new ArrayList();
    String orgid=(String) pagination.getQueryCriterions().get("orgid");
    String empid=(String) pagination.getQueryCriterions().get("empid");  
    String transactdatastart=(String)pagination.getQueryCriterions().get("transactdatastart");
    String transactdataend=(String)pagination.getQueryCriterions().get("transactdataend");
    String affirmdatastart=(String)pagination.getQueryCriterions().get("affirmdatastart");
    String affirmdataend=(String)pagination.getQueryCriterions().get("affirmdataend");
    String issccueed=(String)pagination.getQueryCriterions().get("isucceed");
    String orderby=(String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother(); 
    int start = pagination.getFirstElementOnPage()-1;
    int pageSize = pagination.getPageSize(); 
    int page = pagination.getPage();
    try{
    list=signdao.queryHistoryInfo(orgid, empid, transactdatastart,transactdataend,affirmdatastart,affirmdataend,issccueed,orderby, order, start, pageSize, page) ;
    int count = signdao.queryHistoryCount(orgid, empid, transactdatastart,transactdataend,affirmdatastart,affirmdataend,issccueed);
    pagination.setNrOfElements(count);
    return list;
    }catch(Exception e){
      e.printStackTrace();
      throw new BusinessException("查询失败！");
    }
//    if(list.size()==0){
//      throw new BusinessException("不存在记录");
//    }
    
  }
  
  
  
  /**
   * 获得日志文件表信息
   */
  public List queryLog(Pagination pagination) throws BusinessException {
    List list=new ArrayList();
    //  ID FILE_NAME OPERATION_TYPE OP_DATE
    String file_type=(String) pagination.getQueryCriterions().get("filetype");
    String timestart=(String) pagination.getQueryCriterions().get("starttime");  
    String timeend=(String)pagination.getQueryCriterions().get("endtime");
    String orderby=(String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother(); 
    int start = pagination.getFirstElementOnPage()-1;
    int pageSize = pagination.getPageSize(); 
    int page = pagination.getPage();
    try {
      list=signdao.queryLogInfo(file_type, timestart, timeend, orderby, order, start, pageSize, page);
      int count = signdao.queryLogCount(file_type, timestart, timeend);
      pagination.setNrOfElements(count);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      throw new BusinessException("查询失败！");
    }
    
    return list;
  }
  
  
  /**
   * 从缓存表中读List
   */
  public List getAllUserInfo() {
    return signdao.readFromTemp();
  }
  
  
  /**
   * 为签约准备
   */
  public List prepareSendFile() {
    List templist=signdao.readFromTemp();
    signdao.deleteHistory();
    List returnlist=new ArrayList();
    int size=templist.size();
    for(int i=0;i<size;i++){
      TempDTO dto=(TempDTO)templist.get(i);
      Sign sign=new Sign();
      sign.setName(dto.getEmpname());
      sign.setEmpid(dto.getEmpid());
      sign.setCard_num(dto.getCardnum());
      sign.setBank_card(dto.getBankcardid());
      
      Integer alone=signdao.save(sign);//向签约表插入数据，获得唯一标识
      
      HistoryDTO history=new HistoryDTO();
      history.setOrgid(dto.getOrgid());
      history.setOrgname(dto.getOrgname());
      history.setEmpid(dto.getEmpid());
      history.setEmpname(dto.getEmpname());
      history.setCardnum(dto.getCardnum());
      history.setBankcardid(dto.getBankcardid());
      history.setBiz_date(dto.getBiz_date());
      history.setOperater(dto.getOperater());
      history.setSign(alone.toString());
      history.setSucc_fail("2");
      try{
        signdao.insertHistory(history);
      }
      catch(Exception e){
        e.printStackTrace();
        return new ArrayList();
      }
      returnlist.add(history);
     }
     return returnlist;
  }
  
  
  /**
   * 记录文件信息
   */
  public void logFile(LogDTO dto)throws Exception{
    signdao.insertLog(dto);
  }
  
  
  /**
   * 准备接收文件
   */
  public void prepareReceiveFile(List list) throws Exception{
    int size=list.size();
    for(int i=0;i<size;i++){
      RecieveFileDTO dto=(RecieveFileDTO)list.get(i);
      String sign=dto.getSign().trim();
      signdao.updateHistory(sign,dto.getS_f().trim());
      if(!dto.getS_f().trim().equalsIgnoreCase("00")){
        if(signdao.isHaveSignInHistory(sign))
          signdao.deleteSignBySign(sign);
      }  
    }
  }
  
  /**
   * 清空缓存表
   */
  public void clearTemp(){
    signdao.deleteAllTemp();
  }
  
//--------------------------------------------------------------------
//批量签约 END
//--------------------------------------------------------------------



}


