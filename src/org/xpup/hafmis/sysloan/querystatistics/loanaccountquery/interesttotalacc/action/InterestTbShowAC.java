package org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.interesttotalacc.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.interesttotalacc.bsinterface.IInterestBS;
import org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.interesttotalacc.form.InterestTaAF;

public class InterestTbShowAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.interesttotalacc.action.InterestTbShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    try {
      InterestTaAF af = null;
      String loanBankId = request.getParameter("loanBankId");
      String year = request.getParameter("year");
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);
      pagination.getQueryCriterions().put("loanBankId", loanBankId);
      pagination.getQueryCriterions().put("year", year);
      IInterestBS interestBS = (IInterestBS) BSUtils.getBusinessService(
          "interestBS", this, mapping.getModuleConfig());
      af = interestBS.queryMonthAccList(pagination, securityInfo);
      request.setAttribute("interestTaAF", af);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_interesttb_show");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      HashMap m = new HashMap();
      pagination = new Pagination(0, 12, 1, "t1.flow_head_id", "ASC", m);
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
}
