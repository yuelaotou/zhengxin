package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.orgpaymentstatistics.business;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.dao.EmpDAO;
import org.xpup.hafmis.syscollection.common.dao.MonthPaymentHeadDAO;
import org.xpup.hafmis.syscollection.common.dao.MonthPaymentTailDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgDAO;
import org.xpup.hafmis.syscollection.common.dao.PaymentHeadDAO;
import org.xpup.hafmis.syscollection.common.dao.SearchLackInfoDAO;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.syscollection.common.domain.entity.MonthPaymentHead;
import org.xpup.hafmis.syscollection.common.domain.entity.MonthPaymentTail;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.common.domain.entity.PaymentHead;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.orgpaymentstatistics.bsinterface.IOrgpaymentstatisticsBS;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.orgpaymentstatistics.dto.OrgpaymentstatisticsDTO;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.orgpaymentstatistics.form.OrgpaymentAF;

public class OrgpaymentstatisticsBS implements IOrgpaymentstatisticsBS {
           
  private OrgDAO orgDAO = null;
  private PaymentHeadDAO paymentHeadDAO = null;//缴存头表 301
  private MonthPaymentHeadDAO monthPaymentHeadDAO = null;//汇缴 302
  private MonthPaymentTailDAO monthPaymentTailDAO = null;//尾表
  private SearchLackInfoDAO searchLackInfoDAO = null;
  private EmpDAO empDAO = null;
 
  
  public void setSearchLackInfoDAO(SearchLackInfoDAO searchLackInfoDAO) {
    this.searchLackInfoDAO = searchLackInfoDAO;
  }


  public void setEmpDAO(EmpDAO empDAO) {
    this.empDAO = empDAO;
  }


  public void setMonthPaymentTailDAO(MonthPaymentTailDAO monthPaymentTailDAO) {
    this.monthPaymentTailDAO = monthPaymentTailDAO;
  }


  public void setPaymentHeadDAO(PaymentHeadDAO paymentHeadDAO) {
    this.paymentHeadDAO = paymentHeadDAO;
  }
  
  
  public void setOrgDAO(OrgDAO orgDAO) {
    this.orgDAO = orgDAO;
  }
  
  public void setMonthPaymentHeadDAO(MonthPaymentHeadDAO monthPaymentHeadDAO) {
    this.monthPaymentHeadDAO = monthPaymentHeadDAO;
  }
  
  //根据单位ID查询单位信息
  public Org findOrgInfo(Serializable id,String status,SecurityInfo securityInfo) throws BusinessException {
    // TODO Auto-generated method stub
    Org org = null;
    String orgid="";
    if(id!=null){
      orgid=id.toString();
    }
    org = orgDAO.queryByCriterions(orgid,null,null,securityInfo);
    return org;
  }
  public Emp findEmpInfo(String empid,SecurityInfo securityInfo) throws BusinessException {
    // TODO Auto-generated method stub
    Emp emp = null;
    List emplist = null;
    emplist = empDAO.queryAllEmpList(empid, securityInfo);
    if(!emplist.isEmpty()){
      emp=(Emp)emplist.get(0);
    }
    return emp;
  }
  public List findEmpList(Pagination pagination,SecurityInfo securityInfo) throws Exception {
    // TODO Auto-generated method stub
    List emplist = null;
    int count=0;
    String empid=(String)pagination.getQueryCriterions().get("empid");
    String empname=(String)pagination.getQueryCriterions().get("empname");
    String orderBy=(String) pagination.getOrderBy();;
    String order = (String) pagination.getOrderother(); 
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize(); 
    emplist = empDAO.queryAllEmpListBySecurityInfo(empid, empname, orderBy, order, start, pageSize, securityInfo);
    if(!emplist.isEmpty()){
      for (int i = 0; i < emplist.size(); i++) {
        Emp emp = (Emp) emplist.get(i);
        emp.setPayStatusStr(BusiTools.getBusiValue(emp.getPayStatus().intValue(), BusiConst.OLDPAYMENTSTATE));
      }
      
    }
    count = empDAO.queryCountEmpListBySecurityInfo(empid, empname, securityInfo);
    pagination.setNrOfElements(count);
    return emplist;
  }
  public OrgpaymentAF queryOrgpayInfoList(Pagination pagination,SecurityInfo securityInfo)throws Exception,BusinessException{

    OrgpaymentAF orgpaymentAF = new OrgpaymentAF();
      String orgId = (String) pagination.getQueryCriterions().get("orgId");   
      String payMonth = (String)pagination.getQueryCriterions().get("payMonth");
      List yearlist=new ArrayList();
      List infolist=new ArrayList();
      List totallist=new ArrayList();
      Org org = null;
      if(orgId!=null){
         org=findOrgInfo(orgId,null,securityInfo);
        if(org==null){
          throw new BusinessException("查无此单位或无查看此单位的权限！");
        }
      }
      try{ 
          if(org!=null){
            yearlist=searchLackInfoDAO.querySearchLackInfoYear(orgId);
            orgpaymentAF.setOrgId(orgId);
            orgpaymentAF.setOrgName(org.getOrgInfo().getName());
            orgpaymentAF.setPay_month(payMonth);
          }
          if(payMonth!=null){
            infolist=searchLackInfoDAO.querySearchLackInfoByYear(orgId, payMonth);
            totallist=searchLackInfoDAO.querySearchLackInfoTotalByYear(orgId, payMonth);
          }
          orgpaymentAF.setYearlist(yearlist);
          orgpaymentAF.setTotallist(totallist);
          orgpaymentAF.setList(infolist);
//        List list  = new ArrayList();
//        List totallist = new ArrayList();
//        BigDecimal orgPay = new BigDecimal(0.00);
//        BigDecimal empPay = new BigDecimal(0.00);
//        List temp_list =  new ArrayList();
//        list  = paymentHeadDAO.queryMonthEmpRealPayOrgRealPay(orgId, orgName, payMonth, start, pageSize, orderBy, order, securityInfo);
//
//        if(list.size()!=0){
//          for(int i=0;i<list.size();i++){
//            OrgpaymentstatisticsDTO orgpaymentstatisticsDTO = new OrgpaymentstatisticsDTO();
//            Object[] obj = (Object[])list.get(i);
//            orgpaymentstatisticsDTO.setPay_month(obj[0].toString());
//            orgpaymentstatisticsDTO.setOrgPay(obj[1].toString());
//            orgpaymentstatisticsDTO.setEmpPay(obj[2].toString());
//            temp_list.add(orgpaymentstatisticsDTO);
//          }
//        }
//       totallist = paymentHeadDAO.queryCountMonthEmpRealPayOrgRealPay(orgId, orgName, payMonth, start, pageSize, orderBy, order, securityInfo);
//
//        if(totallist.size()!=0){
//           for(int j=0;j<totallist.size();j++){
//             Object[] obj = (Object[])totallist.get(j);
//             orgPay = orgPay.add(new BigDecimal(obj[1].toString()));
//             empPay = empPay.add(new BigDecimal(obj[2].toString()));
//           }    
//        }
//        int count = totallist.size();
//        pagination.setNrOfElements(count);
//        orgpaymentAF.setOrgPayment(orgPay.toString());
//        orgpaymentAF.setEmpPayment(empPay.toString());
//        orgpaymentAF.setList(temp_list);
//        orgpaymentAF.setTotallist(totallist);
      }catch(Exception e){
        e.printStackTrace();
      }
    return orgpaymentAF;
  }  
/**
 * 职工情况列表
 */
  
  public OrgpaymentAF queryEmppayInfoList(Pagination pagination,SecurityInfo securityInfo)throws Exception,BusinessException{

      OrgpaymentAF orgpaymentAF = new OrgpaymentAF();
      String empId = (String) pagination.getQueryCriterions().get("empId");   
      String payMonth = (String)pagination.getQueryCriterions().get("payMonth");
      List yearlist=new ArrayList();
      List infolist=new ArrayList();
      List totallist=new ArrayList();
      Emp emp = null;
      if(empId!=null){
        emp=findEmpInfo(empId,securityInfo);
        if(emp==null){
          throw new BusinessException("查无此职工或无查看此职工的权限！");
        }
      }
      try{ 
          if(emp!=null){
            yearlist=paymentHeadDAO.queryEmppaymentstatisticsYear(empId);
            orgpaymentAF.setEmpId(empId);
            orgpaymentAF.setEmpName(emp.getEmpInfo().getName());
            orgpaymentAF.setPay_month(payMonth);
          }
          if(payMonth!=null){
            infolist=paymentHeadDAO.queryEmppaymentstatisticsList(empId, payMonth);
            totallist=paymentHeadDAO.queryEmppaymentstatisticsTotalList(empId, payMonth);
          }
          orgpaymentAF.setYearlist(yearlist);
          orgpaymentAF.setTotallist(totallist);
          orgpaymentAF.setList(infolist);
      }catch(Exception e){
        e.printStackTrace();
      }
    return orgpaymentAF;
  }
//    OrgpaymentAF orgpaymentAF = new OrgpaymentAF();
//    if(!pagination.getQueryCriterions().isEmpty()){
//      String empId = (String) pagination.getQueryCriterions().get("empId");   
//      String empName = (String)pagination.getQueryCriterions().get("empName");
//      String payMonth = (String)pagination.getQueryCriterions().get("pay_month");
//      
//      String orderBy = (String) pagination.getOrderBy();
//      String order = (String) pagination.getOrderother();
//      int start = pagination.getFirstElementOnPage() - 1;
//      int pageSize = pagination.getPageSize();      
//      
//      try{    
//        List list  = new ArrayList();
//        List totallist = new ArrayList();
//        BigDecimal orgPay = new BigDecimal(0.00);
//        BigDecimal empPay = new BigDecimal(0.00);
//        List temp_list =  new ArrayList();
//        list  = paymentHeadDAO.queryMonthEmpRealPay(empId, empName, payMonth, start, pageSize, orderBy, order, securityInfo);
//        if(list.size()!=0){
//          for(int i=0;i<list.size();i++){
//            OrgpaymentstatisticsDTO orgpaymentstatisticsDTO = new OrgpaymentstatisticsDTO();
//            Object[] obj = (Object[])list.get(i);
//      //      orgpaymentstatisticsDTO.setPay_month(obj[0].toString());
////            orgpaymentstatisticsDTO.setOrgPay(obj[1].toString());
////            orgpaymentstatisticsDTO.setEmpPay(obj[2].toString());
//            temp_list.add(orgpaymentstatisticsDTO);
//          }
//        }
//       totallist = paymentHeadDAO.queryCountMonthEmpRealPay(empId, empName, payMonth, start, pageSize, orderBy, order, securityInfo);
//        if(totallist.size()!=0){
//           for(int j=0;j<totallist.size();j++){
//             Object[] obj = (Object[])totallist.get(j);
//             orgPay = orgPay.add(new BigDecimal(obj[1].toString()));
//             empPay = empPay.add(new BigDecimal(obj[2].toString()));
//           }    
//        }
//        int count = totallist.size();
//        pagination.setNrOfElements(count);
//        orgpaymentAF.setOrgPayment(orgPay.toString());
//        orgpaymentAF.setEmpPayment(empPay.toString());
//        orgpaymentAF.setList(temp_list);
//        orgpaymentAF.setTotallist(totallist);
//      }catch(Exception e){
//        e.printStackTrace();
//      }
//      
//    }
//   
//    
//    return orgpaymentAF;
//  }  

}












