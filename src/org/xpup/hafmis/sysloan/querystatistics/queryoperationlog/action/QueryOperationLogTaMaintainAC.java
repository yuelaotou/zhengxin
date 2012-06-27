package org.xpup.hafmis.sysloan.querystatistics.queryoperationlog.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;

public class QueryOperationLogTaMaintainAC extends LookupDispatchAction{


  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.print", "print");
    return map;
  }
  
  public ActionForward print(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try {
      String paginationKey=QueryOperationLogTaShow.PAGINATION_KEY;
      Pagination pagination = (Pagination) request.getSession().getAttribute(
          paginationKey);
      List printList =(List)pagination.getQueryCriterions().get("printlist");
//      List printList = (List) request.getSession().getAttribute("printLoanBusiFlowQueryList");
//      request.setAttribute("URL", "loanBusiFlowQueryShowAC.do");
      request.setAttribute("cellList", printList);
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      String userName = securityInfo.getRealName();
      String plbizDate = securityInfo.getUserInfo().getPlbizDate();
      request.setAttribute("userName", userName);
      request.setAttribute("plbizDate", plbizDate);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_print");
  }
  
  
}
