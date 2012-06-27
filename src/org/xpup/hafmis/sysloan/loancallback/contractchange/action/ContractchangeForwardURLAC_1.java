package org.xpup.hafmis.sysloan.loancallback.contractchange.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ContractchangeForwardURLAC_1  extends Action{

  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    // TODO Auto-generated method stub
    request.getSession().setAttribute("type", "2");
    request.getSession().removeAttribute(ContractchangeShowAC.PAGINATION_KEY);
    return mapping.findForward("to_contractchangeShowAC");
  }

}
