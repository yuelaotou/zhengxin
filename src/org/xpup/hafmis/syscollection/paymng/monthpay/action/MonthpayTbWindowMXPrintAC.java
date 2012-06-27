package org.xpup.hafmis.syscollection.paymng.monthpay.action;

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
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.paymng.monthpay.bsinterface.IMonthpayBS;
import org.xpup.hafmis.syscollection.systemmaintain.loanpara.bsinterface.ILoanDocNumDesignBS;

public class MonthpayTbWindowMXPrintAC extends Action{
  
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionMessages messages =null;
    try{
      Pagination pagination = getPagination(MonthpayTbWindowMXShowAC.PAGINATION_KEY, request);
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      String bizDate=securityInfo.getUserInfo().getBizDate();
      String userName="";
      IMonthpayBS monthpayBS = (IMonthpayBS) BSUtils
      .getBusinessService("monthpayBS", this, mapping.getModuleConfig());
     
      String name = monthpayBS.queryMakerPara();

      if (name.equals("1")) {
        userName = securityInfo.getUserName();
      } else {
        userName = securityInfo.getRealName();
      }
      List list = monthpayBS.findTaillistMXPrint(pagination);
//      List list = (List)pagination.getQueryCriterions().get("monthpayList");
      request.getSession().setAttribute("bizDate",bizDate );
      request.getSession().setAttribute("cellList", list);
      request.getSession().setAttribute("userName",userName );
    }catch(Exception bex){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("²Ù×÷Ê§°Ü£¡",
          false));
      saveErrors(request, messages);
    }
    return mapping.findForward("show_print");
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