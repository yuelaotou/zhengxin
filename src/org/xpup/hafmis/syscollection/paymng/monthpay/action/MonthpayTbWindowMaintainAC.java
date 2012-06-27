package org.xpup.hafmis.syscollection.paymng.monthpay.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlow;
import org.xpup.hafmis.syscollection.paymng.monthpay.bsinterface.IMonthpayBS;
import org.xpup.hafmis.syscollection.paymng.monthpay.form.MonthpayJYAF;
import org.xpup.hafmis.syscollection.paymng.orgaddpay.bsinterface.IOrgaddpayBS;

/**
 * 
 * 
 *@author Àî¾ê
 *2007-6-27
 */
public class MonthpayTbWindowMaintainAC extends Action {
  public static final String PAGINATION_KEY1 = "org.xpup.hafmis.syscollection.accounthandle.bizcheck.action.BizcheckTaWindowAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages =null;
    String type_month_pay=(String)request.getSession().getAttribute("type_month_pay");
    try{
      
      MonthpayJYAF f = (MonthpayJYAF)form;
      IMonthpayBS monthpayBS = (IMonthpayBS) BSUtils
      .getBusinessService("monthpayBS", this, mapping.getModuleConfig());

      String url="mx";
      String headid=request.getParameter("headid");
      if(headid != null){
        f.setPaymentid(headid);
        url=request.getParameter("url");
      }
      f=monthpayBS.findPringInfo(f.getPaymentid());
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      Org org=monthpayBS.findOrgInfo(f.getOrgid(), "2", securityInfo);
      String office="";
      String collbankname="";
      String bankid="";
      String bankname="";
      String str[]=new String[2];
      str= monthpayBS.queryOfficeBankNames(f.getOrgid(), "2", f.getPaymentid(), "A", securityInfo);
      if(str[0]!=null){
        office=str[0];
      }
      if(str[1]!=null){
        collbankname=str[1];
      }
//      List officlist=securityInfo.getAllCenterList();
//      String collBankid=org.getOrgInfo().getCollectionBankId();
//      if(officlist != null){
//        OfficeDto dto=(OfficeDto)officlist.get(0);
//        office=dto.getOfficeName();
//      }
      IOrgaddpayBS orgaddpayBS = (IOrgaddpayBS) BSUtils
      .getBusinessService("orgaddpayBS", this, mapping.getModuleConfig());
     
      if(request.getSession().getAttribute("print")!=null){
        Pagination pagination = getPagination(PAGINATION_KEY1, request);
        String flowId=(String)pagination.getQueryCriterions().get("headid");
        OrgHAFAccountFlow orgHAFAccountFlow=null;
        orgHAFAccountFlow=orgaddpayBS.queryOrgHAFAccountFlow(flowId);
        
        if(orgHAFAccountFlow.getOfficeCode()!= null){
          String officeCode=orgaddpayBS.queryOfficeName(orgHAFAccountFlow.getOfficeCode());
          office=officeCode;
        }
        
        if(orgHAFAccountFlow.getMoneyBank() != null){
          collbankname=orgaddpayBS.findCollBank(orgHAFAccountFlow.getMoneyBank());
       }
        request.getSession().removeAttribute("print");
      }
      List list = f.getList();
      f.setOffice(office);
      if(org.getOrgInfo().getPayBank()!=null){
        bankid=org.getOrgInfo().getPayBank().getAccountNum();
        bankname=org.getOrgInfo().getPayBank().getName();
      }
      f.setBankid(bankid);
      f.setBankname(bankname);
      f.setCollbankname(collbankname);
      f.setPrintDate(securityInfo.getUserInfo().getBizDate());
      request.setAttribute("monthpayURL", url);
      request.setAttribute("monthpayJYAF",f);
      request.setAttribute("cellList", list);
    }catch(BusinessException b){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(b.getMessage(),
          false));
      saveErrors(request, messages);
    }
    request.getSession().setAttribute("type_month_pay", null);
    if("1".equals(type_month_pay)){
      return mapping.findForward("show_print_1");
    }else{
      return mapping.findForward("show_print");
    }
    
  }
  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "empHAFAccountFlow.id", "ASC",
          new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
}


