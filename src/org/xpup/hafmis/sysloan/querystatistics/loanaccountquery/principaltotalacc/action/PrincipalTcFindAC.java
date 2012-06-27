package org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.principaltotalacc.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.interestgl.action.InterestglTaShowAC;
import org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.principaltotalacc.form.PrincipalTaAF;

public class PrincipalTcFindAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    PrincipalTaAF af = (PrincipalTaAF) form;
    Map criterions = makeCriterionsMap(af);
    Pagination pagination = new Pagination(0, 10, 1, "", "ASC", criterions);
    String paginationKey = getPaginationKey();
    request.getSession().setAttribute(paginationKey, pagination);
    af.reset(mapping, request);
    return mapping.findForward("principalTcShowAC");
  }

  protected String getPaginationKey() {

    return PrincipalTcShowAC.PAGINATION_KEY;
  }

  protected HashMap makeCriterionsMap(PrincipalTaAF form) {
    HashMap m = new HashMap();

    String loanBankId = form.getLoanBankId();
    if (loanBankId != null && !"".equals(loanBankId))
      m.put("loanBankId", loanBankId.trim());
    String startYear = form.getStartYear();
    if (startYear != null && !"".equals(startYear))
      m.put("year", startYear.trim());

    String month = form.getMonth();
    if (month != null && !"".equals(month))
      m.put("month", month.trim());
    return m;
  }
}
