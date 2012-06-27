package org.xpup.hafmis.syscollection.querystatistics.operationflow.orgoperationflow.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class OrgbusinessflowForwardURLAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    request.getSession().setAttribute(ShowOrgbusinessflowListAC.PAGINATION_KEY,
        null);
    return mapping.findForward("to_orgbusinessflow_list");

  }
}
