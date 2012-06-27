package org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.interesttotalacc.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.interesttotalacc.form.InterestTaAF;

public class InterestTaFindAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    InterestTaAF af = (InterestTaAF) form;
    Map criterions = makeCriterionsMap(af);
    Pagination pagination = new Pagination(0, 10, 1, "t1.flow_head_id", "ASC",
        criterions);
    String paginationKey = getPaginationKey();
    request.getSession().setAttribute(paginationKey, pagination);
    af.reset(mapping, request);
    return mapping.findForward("interestTaShowAC");
  }

  protected String getPaginationKey() {

    return InterestTaShowAC.PAGINATION_KEY;
  }

  protected HashMap makeCriterionsMap(InterestTaAF form) {
    HashMap m = new HashMap();
    
    String loanBankId = form.getLoanBankId();
    if (loanBankId != null && !"".equals(loanBankId))
      m.put("loanBankId", loanBankId.trim());
    String startYear = form.getStartYear();
    if (startYear != null && !"".equals(startYear))
      m.put("startYear", startYear.trim());
    
    String endYear = form.getEndYear();
    if (endYear != null && !"".equals(endYear))
      m.put("endYear", endYear.trim());
    return m;
  }
}
