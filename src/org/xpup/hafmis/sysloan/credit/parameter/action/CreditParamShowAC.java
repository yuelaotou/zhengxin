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
public class CreditParamShowAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    CreditParamAF creditParamAF = new CreditParamAF();

    ICreditParamBS creditParamBS = (ICreditParamBS) BSUtils.getBusinessService(
        "creditParamBS", this, mapping.getModuleConfig());
    try {
      creditParamAF = creditParamBS.getCreditParam();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    request.setAttribute("creditParamAF", creditParamAF);
    return mapping.findForward("creditparam");
  }

}
