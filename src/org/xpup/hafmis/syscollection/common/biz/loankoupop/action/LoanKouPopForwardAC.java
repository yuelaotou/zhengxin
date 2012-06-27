package org.xpup.hafmis.syscollection.common.biz.loankoupop.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class LoanKouPopForwardAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    request.getSession().removeAttribute(LoanKouPopShowAC.PAGINATION_KEY);
    //request.getSession().removeAttribute("batchNum");
    //request.getSession().removeAttribute("loanBankId");
    request.setAttribute("type", "1");
    return mapping.findForward("loankoupop_show");
  }
}
