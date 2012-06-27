package org.xpup.hafmis.syscollection.querystatistics.accountinfo.empaccountinfo.business;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.domain.OrganizationUnit;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accounthandle.ratemng.bsinterface.IRatemngBS;
import org.xpup.hafmis.syscollection.common.dao.ChangeRateBizActivityLogDAO;
import org.xpup.hafmis.syscollection.common.dao.EmpHAFAccountFlowDAO;
import org.xpup.hafmis.syscollection.common.dao.HafInterestRateDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgHAFAccountFlowDAO;
import org.xpup.hafmis.syscollection.common.domain.entity.ChangeRateBizActivityLog;
import org.xpup.hafmis.syscollection.common.domain.entity.EmpHAFAccountFlow;
import org.xpup.hafmis.syscollection.common.domain.entity.HafInterestRate;
import org.xpup.hafmis.syscollection.common.domain.entity.TranInOrg;
import org.xpup.hafmis.syscollection.common.domain.entity.TranOutOrg;
import org.xpup.hafmis.syscollection.querystatistics.accountinfo.empaccountinfo.bsinterface.IEmpAccountBS;
import org.xpup.hafmis.syscollection.querystatistics.accountinfo.empaccountinfo.dto.EmpaccountinfoDTO;
import org.xpup.hafmis.syscommon.dao.HafOperateLogDAO;
import org.xpup.hafmis.syscommon.domain.entity.HafOperateLog;



public class EmpAccountBS implements IEmpAccountBS {

  private EmpHAFAccountFlowDAO empHAFAccountFlowDAO = null;

  public void setEmpHAFAccountFlowDAO(EmpHAFAccountFlowDAO empHAFAccountFlowDAO) {
    this.empHAFAccountFlowDAO = empHAFAccountFlowDAO;
  }
  /**
   * @param 没有条件
   * @return 
   * @throws BusinessException
   */
  public List findEmpAccountList_sy(Pagination pagination,SecurityInfo securityInfo)throws Exception, BusinessException{
    BusinessException be = null;
    List list=new ArrayList();
    List returnList=new ArrayList();
    List printList=new ArrayList();
    try{
      String orderBy = (String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int page = pagination.getPage();
      String startDate=(String) pagination.getQueryCriterions().get("startDate");
      String lastDate=(String) pagination.getQueryCriterions().get("lastDate");
      String orgIdaa101=(String) pagination.getQueryCriterions().get("orgIdaa101");
      String nameba001=(String)pagination.getQueryCriterions().get("nameba001");
      String empIdaa102=(String)pagination.getQueryCriterions().get("empIdaa102");
      String nameba002=(String)pagination.getQueryCriterions().get("nameba002");
      BigDecimal temp_prebalance=new BigDecimal(0.00);
      BigDecimal empBalance=new BigDecimal(0.00);
      //查找返回的是数组,数组中存放empid和orgid
      list=empHAFAccountFlowDAO.queryEmpAccountList_sy(orderBy, order, start, pageSize,page,startDate,lastDate,orgIdaa101,nameba001,empIdaa102,nameba002,securityInfo);
       if(!list.isEmpty()){
       for(int i=0;i<list.size();i++){
         Object[] object = (Object[])list.get(i);
         BigDecimal empid =(BigDecimal) object[0];
         BigDecimal orgid=(BigDecimal) object[1];
         EmpHAFAccountFlow empHAFAccountFlow=new EmpHAFAccountFlow();
         List temp_list=empHAFAccountFlowDAO.queryEmpHAFAccount_sy(startDate, empid.toString(), orgid.toString(), lastDate);
         empHAFAccountFlow=(EmpHAFAccountFlow) temp_list.get(0);
       // 增加账户余额
         empBalance=empHAFAccountFlowDAO.queryEmpBalance(empHAFAccountFlow.getEmpId(),new Integer(empHAFAccountFlow.getOrg().getId().toString()));
         empHAFAccountFlow.getEmp().setBalance(empBalance);
         
         returnList.add(empHAFAccountFlow);

       }
     }
       //查找所有的,主要用于计算发生金额,打印.方法判断查找同上.
     List  temp_list= empHAFAccountFlowDAO.queryEmpAccountCountList_sy(startDate, lastDate, orgIdaa101, nameba001, empIdaa102, nameba002, securityInfo);
     //以下是为打印准备数据
     if(!temp_list.isEmpty()){
       EmpaccountinfoDTO empaccountinfoDTO=new EmpaccountinfoDTO();
       for(int i=0;i<temp_list.size();i++){
         Object[] object = (Object[])temp_list.get(i);
         BigDecimal empid =(BigDecimal) object[0];
         BigDecimal orgid=(BigDecimal) object[1];
         //页面显示基本数据
         EmpHAFAccountFlow empHAFAccountFlow=new EmpHAFAccountFlow();
         List temp_list2=empHAFAccountFlowDAO.queryEmpHAFAccount_sy(startDate,empid.toString(),orgid.toString(),lastDate);
         empHAFAccountFlow=(EmpHAFAccountFlow) temp_list2.get(0);
         // 增加帐户余额
         empBalance=empHAFAccountFlowDAO.queryEmpBalance(empHAFAccountFlow.getEmpId(),new Integer(empHAFAccountFlow.getOrg().getId().toString()));
         empHAFAccountFlow.getEmp().setBalance(empBalance);
        
         empaccountinfoDTO.setTemp_credit(empaccountinfoDTO.getTemp_credit().add(empHAFAccountFlow.getTemp_credit()));
         empaccountinfoDTO.setTemp_debit(empaccountinfoDTO.getTemp_debit().add(empHAFAccountFlow.getTemp_debit()));
         empaccountinfoDTO.setTemp_interest(empaccountinfoDTO.getTemp_interest().add(empHAFAccountFlow.getTemp_interest()));
         printList.add(empHAFAccountFlow);
       }
       pagination.getQueryCriterions().put("temp1_interest", empaccountinfoDTO.getTemp_interest().toString());
       pagination.getQueryCriterions().put("temp_credit",empaccountinfoDTO.getTemp_credit().toString());
       pagination.getQueryCriterions().put("temp_debit",empaccountinfoDTO.getTemp_debit().toString());
     }
     pagination.getQueryCriterions().put("printList", printList);
     pagination.setNrOfElements(temp_list.size());
    }catch(Exception e){
      e.printStackTrace();
      throw be;
    }
    return returnList;
  }
  /**
   * 深入月查询
   * 
  */
  public List findEmpAccountMonthList_sy(Pagination pagination,SecurityInfo securityInfo){
    List list=new ArrayList();
    List returnList=new ArrayList();
    List printList=new ArrayList();
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    String startDate=(String) pagination.getQueryCriterions().get("startDate");
    String lastDate=(String) pagination.getQueryCriterions().get("lastDate");
    String orgIdaa101=(String) pagination.getQueryCriterions().get("orgIdaa101");
    String empIdaa102=(String)pagination.getQueryCriterions().get("empIdaa102");
    BigDecimal temp_prebalance=new BigDecimal(0.00);
   try{
         //因为有了empid所以对象唯一，只取一个emp做为基础信息
     EmpHAFAccountFlow empHAFAccountFlow=new EmpHAFAccountFlow();
     List temp_list=empHAFAccountFlowDAO.queryEmpHAFAccountFlowList(empIdaa102,orgIdaa101);
     empHAFAccountFlow=(EmpHAFAccountFlow) temp_list.get(0);
     //为前台显示相关内容准备数据.
     //显示单位名称
     pagination.getQueryCriterions().put("nameba001", empHAFAccountFlow.getOrg().getOrgInfo().getName());
     //显示职工名称
     pagination.getQueryCriterions().put("nameba002", empHAFAccountFlow.getEmpName());
         //利用group by分组查找记录把每个月分离出来,查找返回数组有三项内容,0是分段时间他是按月取的,1是本期贷方发生额,2是本期借方发生额
         List temp_empHAFAccountFlow2=empHAFAccountFlowDAO.empAccountcurbalance1(orderBy, order, start, pageSize,page,startDate, empIdaa102, orgIdaa101, lastDate);
         if(!temp_empHAFAccountFlow2.isEmpty()){
           for(int j=0;j<temp_empHAFAccountFlow2.size();j++){
              EmpHAFAccountFlow empHAFAccountFlow1=new EmpHAFAccountFlow();
              empHAFAccountFlow1.setEmp(empHAFAccountFlow.getEmp());
              empHAFAccountFlow1.setId(empHAFAccountFlow.getId());
              empHAFAccountFlow1.setOrg(empHAFAccountFlow.getOrg());
              empHAFAccountFlow1.setEmpId(empHAFAccountFlow.getEmpId());
              empHAFAccountFlow1.setEmpName(empHAFAccountFlow.getEmpName());
             Object[] empHAFAccount = (Object[]) temp_empHAFAccountFlow2.get(j);
           //本期贷方发生额
           BigDecimal  temp_credit=(BigDecimal) empHAFAccount[1];
           //本期借方发生额
           BigDecimal  temp_debit=(BigDecimal) empHAFAccount[2];
           
           String tempdate=(String) empHAFAccount[0];
           String tempStart=startDate.substring(0,6);
           String tempLast=lastDate.substring(0,6);
           //期初时间
           String tempStartDate="";
           //期末时间
           String tempLastDate="";
           if(Integer.parseInt(tempdate)>Integer.parseInt(tempStart)){
             tempStartDate=tempdate+"01";
             if(Integer.parseInt(tempdate)>=Integer.parseInt(tempLast)){
               tempLastDate=lastDate;
             }else{
               tempLastDate=tempdate+"31";
             }
           }
           if(Integer.parseInt(tempdate)==Integer.parseInt(tempStart)){
             tempStartDate=startDate;
             if(Integer.parseInt(tempdate)>=Integer.parseInt(tempLast)){
               tempLastDate=lastDate;
             }else{
               tempLastDate=tempdate+"31";
             }
           } 
           //本期贷方笔数
           List countCreditlist=empHAFAccountFlowDAO.countEmpHAFAccountCredit(tempStartDate, empIdaa102, orgIdaa101, tempLastDate);
           Integer countCredit=(Integer) countCreditlist.get(0);
           //本期借方笔数
           List countDebitlist=empHAFAccountFlowDAO.countEmpHAFAccountDebit(tempStartDate, empIdaa102, orgIdaa101, tempLastDate);
           Integer countDebit=(Integer) countDebitlist.get(0);
           empHAFAccountFlow1.setCountCredit(countCredit+"");
           empHAFAccountFlow1.setCountDebit(countDebit+"");
           empHAFAccountFlow1.setTemp_credit(temp_credit);
           empHAFAccountFlow1.setTemp_debit(temp_debit);
           //期初余额
           List temp_empHAFAccountFlow=empHAFAccountFlowDAO.empAccountPrebalance(tempStartDate, empIdaa102, orgIdaa101);
           if(!temp_empHAFAccountFlow.isEmpty()&&temp_empHAFAccountFlow!=null){
             if(temp_empHAFAccountFlow.get(0)!=null&&!temp_empHAFAccountFlow.equals("")){
               
             temp_prebalance=(BigDecimal)temp_empHAFAccountFlow.get(0);
             empHAFAccountFlow1.setPrebalance(temp_prebalance);
             }else{
               empHAFAccountFlow1.setPrebalance(new BigDecimal(0.00));
             }     
             }
           //期末余额
           List temp_empHAFAccountFlow3=empHAFAccountFlowDAO.empAccountCurbalance(tempLastDate,empIdaa102, orgIdaa101);
           if(!temp_empHAFAccountFlow3.isEmpty()){
             if(temp_empHAFAccountFlow3.get(0)!=null&&!temp_empHAFAccountFlow3.equals("")){
             BigDecimal temp_curbalance=(BigDecimal)temp_empHAFAccountFlow3.get(0);
             empHAFAccountFlow1.setCurbalance(temp_curbalance);
             }else{
               empHAFAccountFlow1.setCurbalance(new BigDecimal(0.00));
             }
           }
//         时间截取到月
           String temp_settDate=tempdate;
           empHAFAccountFlow1.setDisplayTme(temp_settDate);
           returnList.add(empHAFAccountFlow1);
           
         }
         } 
         //查询所有记录,统计合计发生额,打印准备数据.调用方法同上.
         List temp_count_list=empHAFAccountFlowDAO.empAccountcurCountBalance1(startDate, empIdaa102, orgIdaa101, lastDate);
         //用于合计显示发生额
         EmpaccountinfoDTO empaccountinfoDTO=new EmpaccountinfoDTO();
         for(int j=0;j<temp_count_list.size();j++){
           Object[] empHAFAccount = (Object[]) temp_count_list.get(j);
           
           //本期贷方发生额
           BigDecimal  temp_credit=(BigDecimal) empHAFAccount[1];
           //本期借方发生额
           BigDecimal  temp_debit=(BigDecimal) empHAFAccount[2];
           //本期利息
           BigDecimal  temp_interest=(BigDecimal) empHAFAccount[3];
           empaccountinfoDTO.setTemp_credit(empaccountinfoDTO.getTemp_credit().add(temp_credit));
           empaccountinfoDTO.setTemp_debit(empaccountinfoDTO.getTemp_debit().add(temp_debit));
           empaccountinfoDTO.setTemp_interest(empaccountinfoDTO.getTemp_interest().add(temp_interest));
         }
         pagination.getQueryCriterions().put("temp1_interest", empaccountinfoDTO.getTemp_interest().toString());
         pagination.getQueryCriterions().put("temp_credit",empaccountinfoDTO.getTemp_credit().toString());
         pagination.getQueryCriterions().put("temp_debit",empaccountinfoDTO.getTemp_debit().toString());
         //以下是为打印准备数据.
         if(!temp_count_list.isEmpty()){
           for(int j=0;j<temp_count_list.size();j++){
              EmpHAFAccountFlow empHAFAccountFlow1=new EmpHAFAccountFlow();
              empHAFAccountFlow1.setEmp(empHAFAccountFlow.getEmp());
              empHAFAccountFlow1.setId(empHAFAccountFlow.getId());
              empHAFAccountFlow1.setOrg(empHAFAccountFlow.getOrg());
              empHAFAccountFlow1.setEmpId(empHAFAccountFlow.getEmpId());
              empHAFAccountFlow1.setEmpName(empHAFAccountFlow.getEmpName());
             Object[] empHAFAccount = (Object[]) temp_count_list.get(j);
           //本期贷方发生额
           BigDecimal  temp_credit=(BigDecimal) empHAFAccount[1];
           //本期借方发生额
           BigDecimal  temp_debit=(BigDecimal) empHAFAccount[2];
           
           String tempdate=(String) empHAFAccount[0];
           String tempStart=startDate.substring(0,6);
           String tempLast=lastDate.substring(0,6);
           //期初时间
           String tempStartDate="";
           //期末时间
           String tempLastDate="";
           if(Integer.parseInt(tempdate)>Integer.parseInt(tempStart)){
             tempStartDate=tempdate+"01";
             if(Integer.parseInt(tempdate)>=Integer.parseInt(tempLast)){
               tempLastDate=lastDate;
             }else{
               tempLastDate=tempdate+"31";
             }
           }
           if(Integer.parseInt(tempdate)==Integer.parseInt(tempStart)){
             tempStartDate=startDate;
             if(Integer.parseInt(tempdate)>=Integer.parseInt(tempLast)){
               tempLastDate=lastDate;
             }else{
               tempLastDate=tempdate+"31";
             }
           } 
           //本期贷方笔数
           List countCreditlist=empHAFAccountFlowDAO.countEmpHAFAccountCredit(tempStartDate, empIdaa102, orgIdaa101, tempLastDate);
           Integer countCredit=(Integer) countCreditlist.get(0);
           //本期借方笔数
           List countDebitlist=empHAFAccountFlowDAO.countEmpHAFAccountDebit(tempStartDate, empIdaa102, orgIdaa101, tempLastDate);
           Integer countDebit=(Integer) countDebitlist.get(0);
           empHAFAccountFlow1.setCountCredit(countCredit+"");
           empHAFAccountFlow1.setCountDebit(countDebit+"");
           empHAFAccountFlow1.setTemp_credit(temp_credit);
           empHAFAccountFlow1.setTemp_debit(temp_debit);
           //期初余额
           List temp_empHAFAccountFlow=empHAFAccountFlowDAO.empAccountPrebalance(tempStartDate, empIdaa102, orgIdaa101);
           if(!temp_empHAFAccountFlow.isEmpty()&&temp_empHAFAccountFlow!=null){
             if(temp_empHAFAccountFlow.get(0)!=null&&!temp_empHAFAccountFlow.equals("")){
               
             temp_prebalance=(BigDecimal)temp_empHAFAccountFlow.get(0);
             empHAFAccountFlow1.setPrebalance(temp_prebalance);
             }else{
               empHAFAccountFlow1.setPrebalance(new BigDecimal(0.00));
             }     
             }
           //期末余额
           List temp_empHAFAccountFlow3=empHAFAccountFlowDAO.empAccountCurbalance(tempLastDate,empIdaa102, orgIdaa101);
           if(!temp_empHAFAccountFlow3.isEmpty()){
             if(temp_empHAFAccountFlow3.get(0)!=null&&!temp_empHAFAccountFlow3.equals("")){
             BigDecimal temp_curbalance=(BigDecimal)temp_empHAFAccountFlow3.get(0);
             empHAFAccountFlow1.setCurbalance(temp_curbalance);
             }else{
               empHAFAccountFlow1.setCurbalance(new BigDecimal(0.00));
             }
           }
//         时间截取到月
           String temp_settDate=tempdate;
           empHAFAccountFlow1.setDisplayTme(temp_settDate);
           printList.add(empHAFAccountFlow1);
           
         }
         } 
     pagination.getQueryCriterions().put("printList", printList);
     pagination.setNrOfElements(temp_count_list.size()); 
   }catch(Exception e){
     e.printStackTrace();
   } 
    return returnList;
  }
  /**
   * 深入日查询
   * temp_time为传过来的年月份
  */
  public List findEmpAccountDayList_sy(Pagination pagination,SecurityInfo securityInfo){
    List list=new ArrayList();
    List returnList=new ArrayList();
    List printList=new ArrayList();
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    String startDate=(String) pagination.getQueryCriterions().get("startDate");
    String lastDate=(String) pagination.getQueryCriterions().get("lastDate");
    String orgIdaa101=(String) pagination.getQueryCriterions().get("orgIdaa101");
    String empIdaa102=(String)pagination.getQueryCriterions().get("empIdaa102");
    String temp_time=(String)pagination.getQueryCriterions().get("temp_time");
    BigDecimal temp_prebalance=new BigDecimal(0.00);
   try{
         //因为有了empid所以对象唯一，只取一个emp做为基础信息
     EmpHAFAccountFlow empHAFAccountFlow=new EmpHAFAccountFlow();
     List temp_list=empHAFAccountFlowDAO.queryEmpHAFAccountFlowList(empIdaa102,orgIdaa101);
     empHAFAccountFlow=(EmpHAFAccountFlow) temp_list.get(0);
     //页面显示在查询条件准备数据.
     //显示单位名称
     pagination.getQueryCriterions().put("nameba001", empHAFAccountFlow.getOrg().getOrgInfo().getName());
     //显示职工名称
     pagination.getQueryCriterions().put("nameba002", empHAFAccountFlow.getEmpName());
     String nowStartDate="";
     String nowLastDate="";
     if(Integer.parseInt(temp_time+"01")>Integer.parseInt(startDate)){
       nowStartDate=temp_time+"01";
       if(Integer.parseInt(temp_time+"31")>Integer.parseInt(lastDate)){
         nowLastDate=lastDate;
       }else{
         nowLastDate=temp_time+"31";
       }
     }else{
       nowStartDate=startDate;
       if(Integer.parseInt(temp_time+"31")>Integer.parseInt(lastDate)){
         nowLastDate=lastDate;
       }else{
         nowLastDate=temp_time+"31";
       }
     }
         //利用group by分组查找记录把每个月中的天分离出来,返回值有六个,0是本期贷方发生额,1是本期借方发生额,2,3是分组的时间.
         List temp_empHAFAccountFlow2=empHAFAccountFlowDAO.empAccountcurbalance2(orderBy, order, start, pageSize,page,nowStartDate,empIdaa102,orgIdaa101, nowLastDate);
         if(!temp_empHAFAccountFlow2.isEmpty()){
           for(int j=0;j<temp_empHAFAccountFlow2.size();j++){
              EmpHAFAccountFlow empHAFAccountFlow1=new EmpHAFAccountFlow();
              empHAFAccountFlow1.setEmp(empHAFAccountFlow.getEmp());
              empHAFAccountFlow1.setId(empHAFAccountFlow.getId());
              empHAFAccountFlow1.setOrg(empHAFAccountFlow.getOrg());
              empHAFAccountFlow1.setEmpId(empHAFAccountFlow.getEmpId());
              empHAFAccountFlow1.setEmpName(empHAFAccountFlow.getEmpName());
             Object[] empHAFAccount = (Object[]) temp_empHAFAccountFlow2.get(j);
           //本期贷方发生额
           BigDecimal  temp_credit=(BigDecimal) empHAFAccount[0];
           //本期借方发生额
           BigDecimal  temp_debit=(BigDecimal) empHAFAccount[1];
          
           //获取截取时间以天数排序
           String tempdate=(String) empHAFAccount[2];
           //本期贷方笔数
           List countCreditlist=empHAFAccountFlowDAO.countEmpHAFAccountCredit(tempdate, empIdaa102, orgIdaa101, tempdate);
           Integer countCredit=(Integer) countCreditlist.get(0);
           //本期借方笔数
           List countDebitlist=empHAFAccountFlowDAO.countEmpHAFAccountDebit(tempdate, empIdaa102, orgIdaa101, tempdate);
           Integer countDebit=(Integer) countDebitlist.get(0);
           empHAFAccountFlow1.setCountCredit(countCredit+"");
           empHAFAccountFlow1.setCountDebit(countDebit+"");
           empHAFAccountFlow1.setTemp_credit(temp_credit);
           empHAFAccountFlow1.setTemp_debit(temp_debit);
           //获取时间所在月
           String settDate=tempdate;
           //期初余额
           List temp_empHAFAccountFlow=empHAFAccountFlowDAO.empAccountPrebalance(settDate, empIdaa102, orgIdaa101);
           if(!temp_empHAFAccountFlow.isEmpty()){
             if(temp_empHAFAccountFlow.get(0)!=null&&!temp_empHAFAccountFlow.equals("")){
             temp_prebalance=(BigDecimal)temp_empHAFAccountFlow.get(0);
             empHAFAccountFlow1.setPrebalance(temp_prebalance);
           }else{
             empHAFAccountFlow1.setPrebalance(new BigDecimal(0.00));
           }
           }
           //期末余额
           List temp_empHAFAccountFlow3=empHAFAccountFlowDAO.empAccountCurbalance(settDate, empIdaa102, orgIdaa101);
           if(!temp_empHAFAccountFlow3.isEmpty()){
             if(temp_empHAFAccountFlow3.get(0)!=null&&!temp_empHAFAccountFlow3.equals("")){
             BigDecimal temp_curbalance=(BigDecimal)temp_empHAFAccountFlow3.get(0);
             empHAFAccountFlow1.setCurbalance(temp_curbalance);
           }else{
             empHAFAccountFlow1.setCurbalance(new BigDecimal(0.00));
           }
           }
           empHAFAccountFlow1.setDisplayTme(settDate);
           returnList.add(empHAFAccountFlow1);
         }
         } 
         //查找所有,返回计算发生额,打印准备数据
         List temp_count_list=empHAFAccountFlowDAO.empAccountcurCountBalance2(nowStartDate, empIdaa102, orgIdaa101, nowLastDate);
         EmpaccountinfoDTO empaccountinfoDTO=new EmpaccountinfoDTO();
         //用于合计显示发生额
         for(int j=0;j<temp_count_list.size();j++){
           Object[] empHAFAccount = (Object[]) temp_count_list.get(j);
           //本期贷方发生额
           BigDecimal  temp_credit=(BigDecimal) empHAFAccount[0];
           //本期借方发生额
           BigDecimal  temp_debit=(BigDecimal) empHAFAccount[1];
           //本期利息
           BigDecimal  temp_interest=(BigDecimal) empHAFAccount[2];
           empaccountinfoDTO.setTemp_credit(empaccountinfoDTO.getTemp_credit().add(temp_credit));
           empaccountinfoDTO.setTemp_debit(empaccountinfoDTO.getTemp_debit().add(temp_debit));
           empaccountinfoDTO.setTemp_interest(empaccountinfoDTO.getTemp_interest().add(temp_interest));
         }
         pagination.getQueryCriterions().put("temp1_interest", empaccountinfoDTO.getTemp_interest().toString());
         pagination.getQueryCriterions().put("temp_credit",empaccountinfoDTO.getTemp_credit().toString());
         pagination.getQueryCriterions().put("temp_debit",empaccountinfoDTO.getTemp_debit().toString());
         //以下是为打印准备数据
         if(!temp_count_list.isEmpty()){
           for(int j=0;j<temp_count_list.size();j++){
              EmpHAFAccountFlow empHAFAccountFlow1=new EmpHAFAccountFlow();
              empHAFAccountFlow1.setEmp(empHAFAccountFlow.getEmp());
              empHAFAccountFlow1.setId(empHAFAccountFlow.getId());
              empHAFAccountFlow1.setOrg(empHAFAccountFlow.getOrg());
              empHAFAccountFlow1.setEmpId(empHAFAccountFlow.getEmpId());
              empHAFAccountFlow1.setEmpName(empHAFAccountFlow.getEmpName());
             Object[] empHAFAccount = (Object[]) temp_count_list.get(j);
           //本期贷方发生额
           BigDecimal  temp_credit=(BigDecimal) empHAFAccount[0];
           //本期借方发生额
           BigDecimal  temp_debit=(BigDecimal) empHAFAccount[1];
          
           //获取截取时间以天数排序
           String tempdate=(String) empHAFAccount[3];
           //本期贷方笔数
           List countCreditlist=empHAFAccountFlowDAO.countEmpHAFAccountCredit(tempdate, empIdaa102, orgIdaa101, tempdate);
           Integer countCredit=(Integer) countCreditlist.get(0);
           //本期借方笔数
           List countDebitlist=empHAFAccountFlowDAO.countEmpHAFAccountDebit(tempdate, empIdaa102, orgIdaa101, tempdate);
           Integer countDebit=(Integer) countDebitlist.get(0);
           empHAFAccountFlow1.setCountCredit(countCredit+"");
           empHAFAccountFlow1.setCountDebit(countDebit+"");
           empHAFAccountFlow1.setTemp_credit(temp_credit);
           empHAFAccountFlow1.setTemp_debit(temp_debit);
           //获取时间所在月
           String settDate=tempdate;
           //期初余额
           List temp_empHAFAccountFlow=empHAFAccountFlowDAO.empAccountPrebalance(settDate, empIdaa102, orgIdaa101);
           if(!temp_empHAFAccountFlow.isEmpty()){
             if(temp_empHAFAccountFlow.get(0)!=null&&!temp_empHAFAccountFlow.equals("")){
             temp_prebalance=(BigDecimal)temp_empHAFAccountFlow.get(0);
             empHAFAccountFlow1.setPrebalance(temp_prebalance);
           }else{
             empHAFAccountFlow1.setPrebalance(new BigDecimal(0.00));
           }
           }
           //期末余额
           List temp_empHAFAccountFlow3=empHAFAccountFlowDAO.empAccountCurbalance(settDate, empIdaa102, orgIdaa101);
           if(!temp_empHAFAccountFlow3.isEmpty()){
             if(temp_empHAFAccountFlow3.get(0)!=null&&!temp_empHAFAccountFlow3.equals("")){
             BigDecimal temp_curbalance=(BigDecimal)temp_empHAFAccountFlow3.get(0);
             empHAFAccountFlow1.setCurbalance(temp_curbalance);
           }else{
             empHAFAccountFlow1.setCurbalance(new BigDecimal(0.00));
           }
           }
           empHAFAccountFlow1.setDisplayTme(settDate);
           printList.add(empHAFAccountFlow1);
         }
         } 
         pagination.getQueryCriterions().put("printList", printList);
         pagination.setNrOfElements(temp_count_list.size());    
   }catch(Exception e){
     e.printStackTrace();
   } 
    return returnList;
  }
}