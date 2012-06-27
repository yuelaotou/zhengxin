package org.xpup.hafmis.syscollection.paymng.monthpay.action;

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
import org.xpup.hafmis.syscollection.paymng.monthpay.bsinterface.IMonthpayBS;
import org.xpup.hafmis.syscollection.paymng.monthpay.dto.MonthpayTbWindowDto;

public class MonthpayTbWindowMXShowAC extends Action{
public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.paymng.monthpay.action.MonthpayTbWindowMXShowAC";
  
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionMessages messages =null;
    try{
      String paymentid =(String) request.getAttribute("paymentid");
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);  
      if(paymentid != null){
        pagination.getQueryCriterions().put("paymentid", paymentid);
      }else{
        paymentid=(String)pagination.getQueryCriterions().get("paymentid");
      }
      saveToken(request);
      IMonthpayBS monthpayBS = (IMonthpayBS) BSUtils
      .getBusinessService("monthpayBS", this, mapping.getModuleConfig());
      List list = monthpayBS.findTaillistMX(pagination);
      List listTotal = monthpayBS.findTailTotal(paymentid);
      MonthpayTbWindowDto dto = new MonthpayTbWindowDto();
      if(listTotal.size() > 0){
         dto = (MonthpayTbWindowDto)listTotal.get(0);
      }
      request.setAttribute("dto",dto);
      pagination.getQueryCriterions().put("monthpayList", list);
      request.setAttribute("list",list);
    }catch(BusinessException bex){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage(bex.getMessage(),
          false));
      saveErrors(request, messages);
    }catch(Exception ex){
      ex.printStackTrace();
    }
    return mapping.findForward("show_tail");
  }
  
  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination();
      request.getSession().setAttribute(paginationKey, pagination);
    }   
 
    return pagination;
  }
}