package org.xpup.hafmis.syscollection.collloanback.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class CollLoanbackTbForwardAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    request.getSession().removeAttribute(CollLoanbackTbShowAC.PAGINATION_KEY);
    request.getSession().removeAttribute("countlist_gjp");
    request.getSession().removeAttribute("sun");
    return mapping.findForward("collloanbacktb_show"); 
  }
}
