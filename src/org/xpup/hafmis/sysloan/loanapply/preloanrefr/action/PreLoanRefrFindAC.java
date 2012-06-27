/**
 * Copy Right Information   : Goldsoft 
 * Project                  : PreLoanRefrFindAC
 * @Version                 : 1.0
 * @author                  : wangy
 * 生成日期                   :2008-05-19
 **/
package org.xpup.hafmis.sysloan.loanapply.preloanrefr.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysloan.loanapply.preloanrefr.form.PreLoanRefrShowAF;

public class PreLoanRefrFindAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    String findType = "find";
    PreLoanRefrShowAF preLoanRefrShowAF = (PreLoanRefrShowAF) form;
    HashMap criterions = makeCriterionsMap(preLoanRefrShowAF, findType);
    Pagination pagination = new Pagination(0, 10, 1, "",
        "DESC", criterions);
    String paginationKey = getPaginationKey();
    request.getSession().setAttribute(paginationKey, pagination);
    return mapping.findForward("preLoanRefrShowAC");
  }

  protected HashMap makeCriterionsMap(PreLoanRefrShowAF form, String findType) {
    HashMap m = new HashMap();
    String loanMoney = form.getLoanMoney().trim();
    if (loanMoney != null && loanMoney.length() > 0) {
      m.put("loanMoney", loanMoney);
    }
    return m;
  }

  protected String getPaginationKey() {
    return PreLoanRefrShowAC.PAGINATION_KEY;
  }
}