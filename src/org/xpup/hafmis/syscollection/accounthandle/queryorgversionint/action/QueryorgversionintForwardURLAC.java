package org.xpup.hafmis.syscollection.accounthandle.queryorgversionint.action;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class QueryorgversionintForwardURLAC extends Action{

  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    // TODO Auto-generated method stub
    request.getSession().setAttribute(QueryorgversionintShowAC.PAGINATION_KEY, null);

    return mapping.findForward("to_queryorgversionintShowAC");
  }

}
