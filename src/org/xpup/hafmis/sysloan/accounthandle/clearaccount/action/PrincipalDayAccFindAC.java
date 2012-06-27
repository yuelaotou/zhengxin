package org.xpup.hafmis.sysloan.accounthandle.clearaccount.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysloan.accounthandle.clearaccount.form.ClearaccountAF;
import org.xpup.hafmis.sysloan.accounthandle.clearaccount.form.PrincipalDayAccAF;

/**
 * @author yg
 */
public class PrincipalDayAccFindAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    PrincipalDayAccAF f = (PrincipalDayAccAF) form;
    HashMap criterions = makeCriterionsMap(f);
    Pagination pagination = new Pagination(0, 10, 1, null, "", criterions);
    String paginationKey = PrincipalDayAccShowAC.PAGINATION_KEY;
    request.getSession().setAttribute(paginationKey, pagination);
    f.reset(mapping, request);
    return mapping.findForward(getForword());
  }

  protected String getForword() {
    return "principalDayAccShowAC";
  }

  protected HashMap makeCriterionsMap(PrincipalDayAccAF form) {
    HashMap m = new HashMap();

    String searchDate = form.getSearchDate().trim();// 查询日期
    if (searchDate != null && searchDate.length() > 0) {
      m.put("bizType", searchDate);
    }

    String loanBankName = form.getLoanBankName().trim();// 放款银行
    if (loanBankName != null && loanBankName.length() > 0) {
      m.put("loanBankName", loanBankName);
    }

    return m;
  }

}
