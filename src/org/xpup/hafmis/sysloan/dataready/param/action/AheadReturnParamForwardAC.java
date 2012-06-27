package org.xpup.hafmis.sysloan.dataready.param.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AheadReturnParamForwardAC extends Action{
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    // TODO Auto-generated method stub
    request.setAttribute("aheadReturnParamAF", null);
    return mapping.findForward("aheadreturnparam_show");
  }
}
