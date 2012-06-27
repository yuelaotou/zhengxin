/**
 * Copy Right Information   : Goldsoft 
 * Project                  : LoanBusiFlowQueryClearForwardURLWindowAC
 * @Version                 : 1.0
 * @author                  : wangy
 * 生成日期                   :2007-10-18
 **/
package org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.loanbusiflowquery.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class LoanBusiFlowQueryClearForwardURLWindowAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.loanbusiflowquery.action.LoanBusiFlowQueryClearShowWindowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try {
      String flowHeadId = request.getParameter("flowHeadId");
      request.setAttribute("flowHeadId", flowHeadId);
      request.getSession().setAttribute(PAGINATION_KEY, null);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("loanBusiFlowQueryClearShowWindowAC");
  }
}