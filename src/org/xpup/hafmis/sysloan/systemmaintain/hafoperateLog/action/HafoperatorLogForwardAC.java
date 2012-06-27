package org.xpup.hafmis.sysloan.systemmaintain.hafoperateLog.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class HafoperatorLogForwardAC extends Action{
  
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.systemmaintain.hafoperateLog.action.HafoperatorLogShowAC";
  
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    request.getSession().setAttribute(PAGINATION_KEY, null);
    return mapping.findForward("goto");
  }

}
