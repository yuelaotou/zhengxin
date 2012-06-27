package org.xpup.hafmis.sysloan.loanapply.personalloancalc.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysloan.loanapply.personalloancalc.form.PersonalloancalcFindAF;
import org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.principaltotalacc.form.PrincipalTaAF;

public class PersonalloancalcFindAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    PersonalloancalcFindAF af = (PersonalloancalcFindAF) form;
    Map criterions = makeCriterionsMap(af);
    Pagination pagination = new Pagination(0, 10, 1, "", "", criterions);
    String paginationKey = getPaginationKey();
    request.getSession().setAttribute(paginationKey, pagination);
    af.reset(mapping, request);
    return mapping.findForward("personalloancalcShowAC");
  }

  protected String getPaginationKey() {

    return PersonalloancalcShowAC.PAGINATION_KEY;
  }

  protected HashMap makeCriterionsMap(PersonalloancalcFindAF form) {
    HashMap m = new HashMap();
    String loanMoney = form.getLoanMoney();
    if (loanMoney != null && !"".equals(loanMoney))
      m.put("loanMoney", loanMoney.trim());
    String loanTimeLimit = form.getLoanTimeLimit();
    if (loanTimeLimit != null && !"".equals(loanTimeLimit))
      m.put("loanTimeLimit", loanTimeLimit.trim());
    String loanRate = form.getLoanRate();
    if (loanRate != null && !"".equals(loanRate))
      m.put("loanRate", loanRate.trim());
    return m;
  }
}
