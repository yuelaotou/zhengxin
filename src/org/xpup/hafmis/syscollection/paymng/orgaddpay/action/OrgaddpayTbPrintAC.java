
package org.xpup.hafmis.syscollection.paymng.orgaddpay.action;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlow;
import org.xpup.hafmis.syscollection.paymng.orgaddpay.bsinterface.IOrgaddpayBS;
import org.xpup.hafmis.syscollection.paymng.orgaddpay.dto.OrgaddpayHeadPrintDto;


public class OrgaddpayTbPrintAC extends Action{
  public static final String PAGINATION_KEY1 = "org.xpup.hafmis.syscollection.accounthandle.bizcheck.action.BizcheckTaWindowAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    String paymentid = (String)request.getAttribute("paymentid");
    if(paymentid==null){
      paymentid=request.getParameter("paymentid");
    }
    SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
    IOrgaddpayBS orgaddpayBS = (IOrgaddpayBS) BSUtils
    .getBusinessService("orgaddpayBS", this, mapping.getModuleConfig());
    OrgaddpayHeadPrintDto dto = orgaddpayBS.findOrgaddpayPring(paymentid);
    Org org=orgaddpayBS.findOrgInfo(dto.getOrgId(), "2", securityInfo);
    String office="";
    String collbankname="";
    String bankid="";
    String bankname="";
    
//    List officlist=securityInfo.getAllCenterList();
    String collBankid=org.getOrgInfo().getCollectionBankId();
//    if(officlist != null){
//      OfficeDto dto1=(OfficeDto)officlist.get(0);
//      office=dto1.getOfficeName();
//    }
    if(org.getOrgInfo().getOfficecode() != null){
      String officeCode=orgaddpayBS.queryOfficeName(org.getOrgInfo().getOfficecode());
      office=officeCode;
    }
    
    if(collBankid != null){
      collbankname=orgaddpayBS.findCollBank(collBankid);
   }
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
    
    
    
   if(org.getOrgInfo().getPayBank()!=null){
     bankid=org.getOrgInfo().getPayBank().getAccountNum();
     bankname=org.getOrgInfo().getPayBank().getName();
   }
    dto.setBankid(bankid);
    dto.setBankname(bankname);
    dto.setOffice(office);
    dto.setCollbankname(collbankname);
    request.setAttribute("orgaddpay",dto);
    return mapping.findForward("show_print");
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
