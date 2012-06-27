/**
 * Copy Right Information   : Goldsoft 
 * Project                  : LoanBusiFlowQueryBailClearForwardURLWindowAC
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

public class LoanBusiFlowQueryBailClearForwardURLWindowAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try {
      request.getSession().setAttribute(LoanBusiFlowQueryBailClearShowWindowAC.PAGINATION_KEY, null);
      request.getSession().setAttribute("printList", null);
      String flowHeadId = request.getParameter("flowHeadId");
      request.setAttribute("flowHeadId", flowHeadId);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("loanBusiFlowQueryBailClearShowWindowAC");
  }
}
