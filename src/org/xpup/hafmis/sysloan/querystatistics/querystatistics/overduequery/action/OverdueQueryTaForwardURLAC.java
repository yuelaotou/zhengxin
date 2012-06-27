package org.xpup.hafmis.sysloan.querystatistics.querystatistics.overduequery.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class OverdueQueryTaForwardURLAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.querystatistics.querystatistics.overduequery.action.OverdueQueryTaShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    request.getSession()
        .setAttribute(OverdueQueryTaShowAC.PAGINATION_KEY, null);
    return mapping.findForward("overdueQueryTaShowAC");
  }
}
