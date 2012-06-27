/**
 * Copy Right Information   : Goldsoft 
 * Project                  : FiveLevelQueryForwardURLAC
 * @Version                 : 1.0
 * @author                  : wangy
 * 生成日期                   :2007-10-19
 **/
package org.xpup.hafmis.sysloan.querystatistics.querystatistics.fivelevelquery.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class FiveLevelQueryForwardURLAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try {
      request.getSession().setAttribute(FiveLevelQueryShowAC.PAGINATION_KEY, null);
      request.getSession().setAttribute("printList", null);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("fiveLevelQueryShowAC");
  }
}
