package org.xpup.hafmis.sysfinance.treasurermng.depositcheckacc.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class DepositCheckAccForwardAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    request.getSession().removeAttribute("pagination_gjp");
    request.getSession().removeAttribute("bdcList");
    request.getSession().removeAttribute("bcaList");
    request.getSession().removeAttribute("bankList_gjp");
    request.getSession().removeAttribute("officeList_gjp");
    request.getSession().removeAttribute("bankOutList_gjp");
    request.getSession().removeAttribute("officeOutList_gjp");
    request.getSession().removeAttribute("depositCheckAccWindowDTO");
    resetToken(request);   
    return mapping.findForward("depositcheckacc_show");
  }
}
