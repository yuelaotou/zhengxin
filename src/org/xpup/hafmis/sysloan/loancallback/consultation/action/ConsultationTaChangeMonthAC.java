package org.xpup.hafmis.sysloan.loancallback.consultation.action; 



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
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loancallback.loancallback.bsinterface.ILoancallbackBS;
import org.xpup.hafmis.sysloan.loancallback.loancallback.form.LoancallbackTaAF;

public class ConsultationTaChangeMonthAC extends Action {
public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.loancallback.consultation.action.ConsultationTaShowAC";
  
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionMessages messages =null;
    try{
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      String month = request.getParameter("month");
      String ovaerLoanRepay = request.getParameter("ovaerLoanRepay");
//      String pldebit = request.getParameter("pldebit");
      String contractId = request.getParameter("contractId");
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      if(month != null){
        pagination.getQueryCriterions().put("callbackMonth",month);
      }
      if(ovaerLoanRepay!= null){
        pagination.getQueryCriterions().put("ovaerLoanRepay", ovaerLoanRepay);
      }
      if(contractId!= null){
        pagination.getQueryCriterions().put("contractId", contractId);
      }
//      if(pldebit!= null){
//        pagination.getQueryCriterions().put("pldebit", pldebit);
//      }
      LoancallbackTaAF af = new LoancallbackTaAF(); 
      ILoancallbackBS loancallbackBS = (ILoancallbackBS) BSUtils
      .getBusinessService("loancallbackBS", this, mapping.getModuleConfig());
      af = loancallbackBS.findShouldLoancallbackInfo(pagination, securityInfo);
      pagination.getQueryCriterions().put("shouldBackList",af.getShouldBackList());
      request.setAttribute("loancallbackTaAF", af);
    }catch(BusinessException bex){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage(bex.getMessage(),
          false));
      saveErrors(request, messages);
    }catch(Exception ex){
      ex.printStackTrace();
    }
    return mapping.findForward("consultation_jy");
  }
  
  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination();
      pagination.getQueryCriterions().put("bizType", "2");
      request.getSession().setAttribute(paginationKey, pagination);
    }   
    return pagination;
  }
}