package org.xpup.hafmis.syscollection.paymng.orgaddpay.action;
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
import org.xpup.hafmis.syscollection.paymng.orgaddpay.bsinterface.IOrgaddpayBS;
import org.xpup.hafmis.syscollection.paymng.orgaddpay.form.OrgaddpayTaAF;

public class OrgaddpayTbWindowShowAC extends Action{
  
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.paymng.orgaddpay.action.OrgaddpayTbWindowShowAC";
  
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionMessages messages =null;
    try{
      /**
       * ·ÖÒ³
       */
      Pagination pagination = getPagination(OrgaddpayTbWindowShowAC.PAGINATION_KEY, request); 
      PaginationUtils.updatePagination(pagination, request);  
      saveToken(request);
      IOrgaddpayBS orgaddpayBS = (IOrgaddpayBS) BSUtils
      .getBusinessService("orgaddpayBS", this, mapping.getModuleConfig());
      OrgaddpayTaAF orgaddpayTaAF = new OrgaddpayTaAF();
      String paymentid = (String)request.getAttribute("paymentid");
      if(paymentid!=null)
      pagination.getQueryCriterions().put("paymentid", paymentid);
      orgaddpayTaAF=orgaddpayBS.findOrgaddpayMX(pagination);
      List list=orgaddpayTaAF.getList();
      
      if(list.size()>0 && list!=null){
        orgaddpayTaAF.setListCount("1");
      }
      request.setAttribute("orgaddpayTaAF", orgaddpayTaAF);
     
      pagination.getQueryCriterions().put("pageList", orgaddpayTaAF.getList());
    }catch(BusinessException b){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(b.getMessage(),
          false));
      saveErrors(request, messages);
    }catch(Exception ex){
      ex.printStackTrace();
    }
    return mapping.findForward("show_orgaddpay");
  }
  
  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "monthPaymentTail.id", "ASC",
          new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }   
 
    return pagination;
  }
}
