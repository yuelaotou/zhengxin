package org.xpup.hafmis.sysloan.loancallback.advancepayloan.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.sysloan.loancallback.advancepayloan.form.AdvancepayloanTaAF;

public class AdvancepayloanTaForwardURLAC extends Action{

  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse rsponse) throws Exception {
    // TODO Auto-generated method stub
    request.getSession().setAttribute(AdvancepayloanTaShowAC.PAGINATION_KEY, null);
    return mapping.findForward("to_advancepayloanTaShowAC");
  }
}
