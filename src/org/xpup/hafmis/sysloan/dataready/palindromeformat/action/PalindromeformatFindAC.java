package org.xpup.hafmis.sysloan.dataready.palindromeformat.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysloan.dataready.bankpalindrome.form.BankpalindromeAF;
import org.xpup.hafmis.sysloan.dataready.palindromeformat.form.PalindromeformatAF;

/**
 * 
 * @author yuqf
 *2007-12-20
 */
public class PalindromeformatFindAC extends Action{
  private HashMap criterions = null;
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    // TODO Auto-generated method stub
    PalindromeformatAF palindromeformatAF = (PalindromeformatAF)form;
    criterions = makeCriterionsMap(palindromeformatAF,request);
    String paginationKey = getPaginationKey();
    Pagination pagination = new Pagination(0, 10, 1, "null", "ASC",
        criterions);
    request.getSession().setAttribute(paginationKey, pagination);
    modifyPagination(pagination);
    return mapping.findForward("to_palindromeformatShowAC");
  }
  private String getPaginationKey() {
    // TODO Auto-generated method stub
    return PalindromeformatShowAC.PAGINATION_KEY;
  }
  protected HashMap makeCriterionsMap(PalindromeformatAF form,HttpServletRequest request) {
    HashMap m = new HashMap();
    String bankId = form.getBankId();
    if (bankId != null && !"".equals(bankId)) {
      m.put("bankId", bankId.trim());
      request.getSession().setAttribute("bankId", bankId);
    }
    m.put("key", "value");
    return m;
  }
  protected void modifyPagination(Pagination pagination) {
  }
}
