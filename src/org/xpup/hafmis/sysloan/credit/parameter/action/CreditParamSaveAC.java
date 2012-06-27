package org.xpup.hafmis.sysloan.credit.parameter.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.sysloan.credit.parameter.bsinterface.ICreditParamBS;
import org.xpup.hafmis.sysloan.credit.parameter.form.CreditParamAF;

/**
 * @author ั๎นโ
 */
public class CreditParamSaveAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    CreditParamAF creditParamAF = (CreditParamAF) form;
    ICreditParamBS creditParamBS = (ICreditParamBS) BSUtils.getBusinessService(
        "creditParamBS", this, mapping.getModuleConfig());
    try {
      creditParamBS.insertCreditParam(creditParamAF);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    request.setAttribute("creditParamAF", creditParamAF);
    return mapping.findForward("creditParamShowAC");
  }

}
