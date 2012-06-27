package org.xpup.hafmis.syscollection.paymng.personaddpay.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.EmpAddPay;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.paymng.monthpay.bsinterface.IMonthpayBS;
import org.xpup.hafmis.syscollection.paymng.personaddpay.bsinterface.IPersonAddPayBS;
import org.xpup.hafmis.syscollection.paymng.personaddpay.form.PersonAddPayAF;

public class PersonaddpayTbMXPrintAC  extends Action{
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.paymng.personaddpay.action.PersonaddpayTbWindowShowAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionMessages messages =null;
        String type_month_pay=(String)request.getSession().getAttribute("type_month_pay");
    try{
      Pagination pagination = getPagination(PersonaddpayTbWindowShowAC.PAGINATION_KEY, request); 
      SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
      PaginationUtils.updatePagination(pagination, request);  
      saveToken(request);
      IPersonAddPayBS personAddPayBS = (IPersonAddPayBS) BSUtils
      .getBusinessService("personAddPayBS", this, mapping.getModuleConfig());
      
      IMonthpayBS monthpayBS = (IMonthpayBS) BSUtils.getBusinessService(
          "monthpayBS", this, mapping.getModuleConfig());
      
      String paymentid=(String)pagination.getQueryCriterions().get("paymentid"); 
      if(paymentid==null){
        paymentid=(String)request.getParameter("paymentid").trim();
      }
      EmpAddPay empAddPay = personAddPayBS.findEmpAddPay(paymentid.trim());
  //    List list = personAddPayBS.findEmpaddpayListPring(paymentid);  
      pagination.getQueryCriterions().put("paymentHeadId", paymentid.trim()); 
      pagination.getQueryCriterions().put("orgId",empAddPay.getOrg().getId().toString());
      pagination.getQueryCriterions().put("unitName",empAddPay.getOrg().getOrgInfo().getName());
      List list2=personAddPayBS.findPersonAddPayPrintList(pagination); 
      PersonAddPayAF personAddPayAF=new PersonAddPayAF();
      Org org=personAddPayBS.findOrgInfo(empAddPay.getOrg().getId().toString(), "2", securityInfo);
      String office="";
      String collbankname="";
      String bankid="";
      String bankname="";
      String str[]=new String [2];
      
      String receivables_orgname="";
      String receivables_bank_acc="";
      String receivables_bank_name="";
      String userName="";
      
//      List officlist=securityInfo.getAllCenterList();
//      String collBankid=org.getOrgInfo().getCollectionBankId();
//      if(officlist != null){
//        OfficeDto dto1=(OfficeDto)officlist.get(0);
//        office=dto1.getOfficeName();
//      }
//      if(collBankid != null){
//        collbankname=personAddPayBS.findCollBank(collBankid);
//     }
    str= personAddPayBS.queryOfficeBankNames(empAddPay.getOrg().getId().toString(), "2",paymentid.trim(), "B", securityInfo);
    if(str[0]!=null){
      office=str[0];
    }
    if(str[1]!=null){
      collbankname=str[1];
    }
     if(org.getOrgInfo().getPayBank()!=null){
       bankid=org.getOrgInfo().getPayBank().getAccountNum();
       bankname=org.getOrgInfo().getPayBank().getName();
     }
     
     personAddPayAF.setReceivables_bank_acc(empAddPay.getReceivables_bank_acc());
     personAddPayAF.setReceivables_bank_name(empAddPay.getReceivables_bank_name());
     personAddPayAF.setReceivables_orgname(receivables_orgname);
     personAddPayAF.setPayment_bank_acc(empAddPay.getPayment_bank_acc());
     personAddPayAF.setPayment_bank_name(empAddPay.getPayment_bank_name());
     if(org.getOrgInfo().getReserveaB()!=null && !org.getOrgInfo().getReserveaB().equals("")){
       personAddPayAF.setPayment_orgname(org.getOrgInfo().getReserveaB());
     }else{
       personAddPayAF.setPayment_orgname(org.getOrgInfo().getName());
     }
     personAddPayAF.setPayWay(empAddPay.getPay_way());
     personAddPayAF.setPayKind(empAddPay.getPay_kind());
     String name = monthpayBS.queryMakerPara();
     personAddPayAF.setBizDate(securityInfo.getUserInfo().getBizDate());
     if (name.equals("1")) {
       userName = securityInfo.getUserName();
     } else {
       userName = securityInfo.getRealName();
     }
     
     personAddPayAF.setOffice(office);
     personAddPayAF.setCollbankname(collbankname);
     personAddPayAF.setBankid(bankid);
     personAddPayAF.setBankname(bankname);
      personAddPayAF.setPersonAddPayList(list2);
      if(org.getOrgInfo().getTransactor()!= null){
        personAddPayAF.setTransactorName(org.getOrgInfo().getTransactor().getName());      
        personAddPayAF.setTransactorTel(org.getOrgInfo().getTransactor().getTelephone());
      }
      request.getSession().setAttribute("f", personAddPayAF);
      request.setAttribute("empaddpURL", "javascript:history.back();");
 //     request.setAttribute("cellList", list);
      
      
      pagination.getQueryCriterions().put("paymentHeadId",paymentid.trim());
    
      
    
      EmpAddPay empAddPay_1=personAddPayBS.findEmpAddPayInfo(paymentid.trim());
      pagination.getQueryCriterions().put("orgId",empAddPay_1.getOrg().getId().toString());
      pagination.getQueryCriterions().put("unitName",empAddPay_1.getOrg().getOrgInfo().getName());
      pagination.getQueryCriterions().put("docNumber",empAddPay_1.getNoteNum());
      List list2_1=personAddPayBS.findPersonAddPayPrintList(pagination); 
      PersonAddPayAF personAddPayAF_1=new PersonAddPayAF();
      Org org_1=personAddPayBS.findOrgInfo(empAddPay_1.getOrg().getId().toString(), "2", securityInfo);
      BigDecimal payMoney = personAddPayBS.getOverPay(empAddPay_1.getNoteNum());
//      String office="";
//      String collbankname="";
//      String bankid="";
//      String bankname="";
//      String str[]=new String[2];
//      String receivables_orgname="";
//      String receivables_bank_acc="";
//      String receivables_bank_name="";
//      String userName="";
//    List officlist=securityInfo.getAllCenterList();
//    String collBankid=org.getOrgInfo().getCollectionBankId();
//    if(officlist != null){
//      OfficeDto dto=(OfficeDto)officlist.get(0);
//      office=dto.getOfficeName();
//    }
      List banklist = new ArrayList();
      banklist = monthpayBS.queryCollBankInfo(org_1.getOrgInfo()
          .getOfficecode(), org_1.getOrgInfo().getCollectionBankId());
      if (banklist.size() > 0) {
        for (int i = 0; i < banklist.size(); i++) {
          Object obj[] = null;
          obj = (Object[]) banklist.get(0);
          if(obj[0] != null){
            receivables_orgname = obj[0].toString();
          }
          if(obj[2] != null){
            receivables_bank_acc = obj[2].toString();
          }
          if(obj[1] != null){
            receivables_bank_name = obj[1].toString();
          }
        }
      }
    str= personAddPayBS.queryOfficeBankNames(empAddPay_1.getOrg().getId().toString(), "2", paymentid.trim(), "B", securityInfo);
    if(str[0]!=null){
      office=str[0];
    }
    if(str[1]!=null){
      collbankname=str[1];
    }
     if(org_1.getOrgInfo().getPayBank()!=null){
       bankid=org_1.getOrgInfo().getPayBank().getAccountNum();
       bankname=org_1.getOrgInfo().getPayBank().getName();
     }
     personAddPayAF_1.setOffice(office);
     personAddPayAF_1.setCollbankname(collbankname);
     personAddPayAF_1.setBankid(bankid);
     personAddPayAF_1.setBankname(bankname);
     personAddPayAF_1.setPersonAddPayList(list2_1);
      
     personAddPayAF_1.setReceivables_bank_acc(empAddPay_1.getReceivables_bank_acc());
     personAddPayAF_1.setReceivables_bank_name(empAddPay_1.getReceivables_bank_name());
     personAddPayAF_1.setReceivables_orgname(receivables_orgname);
     personAddPayAF_1.setPayment_bank_acc(empAddPay_1.getPayment_bank_acc());
     personAddPayAF_1.setPayment_bank_name(empAddPay_1.getPayment_bank_name());
     if(org_1.getOrgInfo().getReserveaB()!=null && !org_1.getOrgInfo().getReserveaB().equals("")){
       personAddPayAF_1.setPayment_orgname(org_1.getOrgInfo().getReserveaB());
     }else{
       personAddPayAF_1.setPayment_orgname(org_1.getOrgInfo().getName());
     }
     personAddPayAF_1.setPayWay(empAddPay_1.getPay_way());
     personAddPayAF_1.setPayKind(empAddPay_1.getPay_kind());
     personAddPayAF_1.setNoteNum(empAddPay_1.getDocNum());
      String name_1 = monthpayBS.queryMakerPara();
      personAddPayAF_1.setBizDate(securityInfo.getUserInfo().getBizDate());
      if (name_1.equals("1")) {
        userName = securityInfo.getUserName();
      } else {
        userName = securityInfo.getRealName();
      }
      personAddPayAF_1.setPaySum(empAddPay_1.getPayMoney().add(payMoney));
      request.setAttribute("monthpayURL", "javascript:history.back();");
      request.getSession().setAttribute("userName", userName);
      request.getSession().setAttribute("f", personAddPayAF_1);
      
      
      
      
      
      
      
      
    }catch(BusinessException bex){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage(bex.getMessage(), false));        
      saveErrors(request, messages);
    }catch(Exception ex){
      ex.printStackTrace();
    }
    request.getSession().setAttribute("type_month_pay", null);
    if("1".equals(type_month_pay)){
      return mapping.findForward("empaddpay_qc_cell_1");
    }else{
      return mapping.findForward("empaddpay_qc_cell");
    }
    
  }
  private Pagination getPagination(String paginationKey,HttpServletRequest request) { 
    Pagination pagination = (Pagination) request.getSession().getAttribute( paginationKey);      
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "", "ASC", new HashMap(0));       
      request.getSession().setAttribute(paginationKey, pagination);
    }    
    return pagination;
  }
}