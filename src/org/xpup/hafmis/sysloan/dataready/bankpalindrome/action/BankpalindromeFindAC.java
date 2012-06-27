package org.xpup.hafmis.sysloan.dataready.bankpalindrome.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysloan.dataready.bankpalindrome.form.BankpalindromeAF;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.action.EndorsecontractTeShowAC;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.form.EndorsecontractTeAF;
/**
 * 
 * @author yuqf
 *2007-12-20
 */
public class BankpalindromeFindAC extends Action{
  private HashMap criterions = null;
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    // TODO Auto-generated method stub
    BankpalindromeAF bankpalindromeAF = (BankpalindromeAF)form;
    criterions = makeCriterionsMap(bankpalindromeAF);
    Pagination pagination = new Pagination(0, 10, 1, "null", "ASC",
        criterions);
    String paginationKey = getPaginationKey();
    request.getSession().setAttribute(paginationKey, pagination);
    modifyPagination(pagination);
    return mapping.findForward("to_bankpalindromeShowAC");
  }
  protected String getPaginationKey() {

    return BankpalindromeShowAC.PAGINATION_KEY;
  }
  protected HashMap makeCriterionsMap(BankpalindromeAF form) {
    HashMap m = new HashMap();
    String bankId = form.getBankId();
    if (bankId != null && !"".equals(bankId)) {
      m.put("bankId", bankId.trim());
    }

    String rowNum = form.getRowNum();
    if (rowNum != null && !"".equals(rowNum)) {
      m.put("rowNum", rowNum.trim());
    }
    m.put("key", "value");
    return m;
  }
  protected void modifyPagination(Pagination pagination) {
  }
}
