package org.xpup.hafmis.sysloan.querystatistics.datareadyquery.aheadreturnparamquery.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AheadReturnParamQueryForwardAC extends Action{
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    // TODO Auto-generated method stub
    request.setAttribute("aheadReturnParamQueryAF", null);
    request.getSession().setAttribute("aheadReturnParamQueryAF", null);
    return mapping.findForward("aheadreturnparamquery_show");
  }
}
