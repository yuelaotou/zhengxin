package org.xpup.hafmis.syscollection.chgbiz.chgslarybase.action;


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
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.chgbiz.chgslarybase.bsinterface.IChgslarybaseBS;
import org.xpup.hafmis.syscollection.chgbiz.chgslarybase.form.ChgslarybaseListAF;


public class ChgslarybaseTbWindowShowAC extends Action{
public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.chgbiz.chgslarybase.action.ChgslarybaseTbWindowShowAC";
  
public ActionForward execute(ActionMapping mapping, ActionForm form,
    HttpServletRequest request, HttpServletResponse response)
    throws Exception {
    String paymentid = request.getParameter("paymentid");
    String sun = request.getParameter("sun");
    ActionMessages messages = null;
    ChgslarybaseListAF chgslarybaseListAF =new ChgslarybaseListAF();
    List list=null;
    if(sun!=null && sun.equals("sun")){
      request.getSession().setAttribute(ChgslarybaseTbWindowShowAC.PAGINATION_KEY, null);
    }
    Pagination pagination = getPagination(PAGINATION_KEY, request);
    PaginationUtils.updatePagination(pagination, request);
    
    if (request.getParameter("paymentid")!=null) {
      pagination.getQueryCriterions().put("paymentid", paymentid);
    }
    
    SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
    pagination.getQueryCriterions().put("SecurityInfo", securityInfo);
    try {
      IChgslarybaseBS chgslarybaseBS = (IChgslarybaseBS) BSUtils.getBusinessService("chgslarybaseBS", this,
          mapping.getModuleConfig());
       chgslarybaseListAF = chgslarybaseBS.findChgslarybaseWindow(pagination);
       
      if(chgslarybaseListAF==null){
        chgslarybaseListAF=new ChgslarybaseListAF();
      }
      request.setAttribute("chgslarybaseListAF", chgslarybaseListAF);
      list=chgslarybaseListAF.getList();
      pagination.getQueryCriterions().put("pageList", list);
    }  catch(BusinessException e) {
      chgslarybaseListAF=new ChgslarybaseListAF();
     request.setAttribute("chgslarybaseListAF", chgslarybaseListAF);
      messages = new ActionMessages();
     messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(e
         .getMessage(), false));
     saveErrors(request, messages);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  return mapping.findForward("chgslarybasetbwindow");
}

private Pagination getPagination(String paginationKey,
    HttpServletRequest request) {
  Pagination pagination = (Pagination) request.getSession().getAttribute(
      paginationKey);
  if (pagination == null) {
    pagination = new Pagination(0, 10, 1, "chgPaymentTail.empId", "ASC", new HashMap(0));
    request.getSession().setAttribute(paginationKey, pagination);
  }

  return pagination;
}

}
