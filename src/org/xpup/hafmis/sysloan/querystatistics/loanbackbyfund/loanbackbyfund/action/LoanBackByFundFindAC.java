package org.xpup.hafmis.sysloan.querystatistics.loanbackbyfund.loanbackbyfund.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysloan.querystatistics.loanbackbyfund.loanbackbyfund.form.LoanBackByFundAF;
/**
 * @author ั๎นโ
 * 20090303
 */
public class LoanBackByFundFindAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try {
      LoanBackByFundAF loanBackByFundAF = (LoanBackByFundAF) form;
      HashMap criterions = makeCriterionsMap(loanBackByFundAF);
      Pagination pagination = new Pagination(0, 10, 1, "p1.contract_id", "DESC",
          criterions);
      String paginationKey = getPaginationKey();
      request.getSession().setAttribute(paginationKey, pagination);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("loanBackByFundShowAC");
  }

  protected HashMap makeCriterionsMap(LoanBackByFundAF form) {
    HashMap m = new HashMap();

    String loanBank = form.getLoanBankName();
    if (loanBank != null && !"".equals(loanBank.trim())) {
      m.put("loanBank", loanBank);
    }
    String beginBizDate = form.getBeginBizDate();
    if (beginBizDate != null && !"".equals(beginBizDate.trim())) {
      m.put("beginBizDate", beginBizDate.trim());
    }
    String endBizDate = form.getEndBizDate();
    if (endBizDate != null && !"".equals(endBizDate.trim())) {
      m.put("endBizDate", endBizDate.trim());
    }
    return m;
  }

  protected String getPaginationKey() {
    return LoanBackByFundShowAC.PAGINATION_KEY;
  }
}