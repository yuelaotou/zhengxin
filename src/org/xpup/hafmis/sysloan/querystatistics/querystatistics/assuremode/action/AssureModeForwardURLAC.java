package org.xpup.hafmis.sysloan.querystatistics.querystatistics.assuremode.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author ��Ұ 2007-10-11
 */
public class AssureModeForwardURLAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try {
      request.getSession().setAttribute(AssureModeShowAC.PAGINATION_KEY, null);
      request.getSession().setAttribute("printList", null);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("assureModeShowAC");
  }
}
