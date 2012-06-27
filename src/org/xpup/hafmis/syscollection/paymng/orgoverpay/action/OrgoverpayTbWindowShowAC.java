package org.xpup.hafmis.syscollection.paymng.orgoverpay.action;

import java.util.HashMap;
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
import org.xpup.hafmis.syscollection.paymng.orgaddpay.action.OrgaddpayTbWindowShowAC;
import org.xpup.hafmis.syscollection.paymng.orgoverpay.bsinterface.IOrgoverpayBS;
import org.xpup.hafmis.syscollection.paymng.orgoverpay.form.OrgoverpayAF;


public class OrgoverpayTbWindowShowAC extends Action{
  
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.paymng.orgoverpay.action.OrgoverpayTbWindowShowAC";
  
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionMessages messages =null;
    try{
      Pagination pagination = getPagination(OrgaddpayTbWindowShowAC.PAGINATION_KEY, request); 
      PaginationUtils.updatePagination(pagination, request);  
      saveToken(request);
      IOrgoverpayBS orgoverpayBS = (IOrgoverpayBS) BSUtils
      .getBusinessService("orgoverpayBS", this, mapping.getModuleConfig());   
      OrgoverpayAF orgoverpayAF = new OrgoverpayAF();
      String paymentid = request.getParameter("paymentid");
      if(paymentid!=null)
      pagination.getQueryCriterions().put("paymentid", paymentid);
      orgoverpayAF=orgoverpayBS.findOrgoverpayMX(pagination);
      request.setAttribute("orgoverpayAF", orgoverpayAF);
         
    }catch(BusinessException b){
      b.printStackTrace();
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(b.getMessage(), false));   
      saveErrors(request, messages);
    }catch(Exception ex){
      ex.printStackTrace();
    }
    return mapping.findForward("to_orgoverpay_lb_detail.jsp");
  }
  
  private Pagination getPagination(String paginationKey, HttpServletRequest request) {
     
    Pagination pagination = (Pagination) request.getSession().getAttribute(paginationKey);
     
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "orgExcessPayment.id", "ASC",new HashMap(0));      
      request.getSession().setAttribute(paginationKey, pagination);
    }   
    return pagination;
  }
}
