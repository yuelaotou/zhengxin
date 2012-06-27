package org.xpup.hafmis.sysloan.querystatistics.checkqueryplfn.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class CheckQueryPlFnForwardAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    // TODO Auto-generated method stub
    request.getSession().setAttribute(CheckQueryPlFnShowAC.PAGINATION_KEY, null);
    
    
    String a="";
    a=(String)request.getParameter("cardNum");
    a=(String)request.getParameter("contractid");
    a=(String)request.getParameter("loankouacc");
                                             
    return mapping.findForward("checkQueryPlFn_show");
  }
}
