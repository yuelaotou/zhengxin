package org.xpup.hafmis.sysloan.common.biz.loankouaccpop.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysloan.common.biz.loankouaccpop.form.LoanKouAccpopAF;
/**
 * @author 郭婧平
 */
public class LoanKouAccpopFindAC extends Action{
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    LoanKouAccpopAF loanKouAccpopAF = (LoanKouAccpopAF) form;
    HashMap criterions = makeCriterionsMap(loanKouAccpopAF);
    Pagination pagination = new Pagination(0, loanKouAccpopAF.getPageSize(), 1,
        "p110.contract_id", "DESC", criterions);
   
    String paginationKey = LoanKouAccpopShowAC.PAGINATION_KEY;

    request.getSession().setAttribute(paginationKey, pagination);

    loanKouAccpopAF.reset(mapping, request);
    return mapping.findForward("loankouaccpop_show");
    
  }
  protected HashMap makeCriterionsMap(LoanKouAccpopAF form) {
    HashMap m = new HashMap();

    String loankouacc = form.getLoankouacc().trim();// 贷款账号
    if (loankouacc != null && !"".equals(loankouacc)) {
      m.put("loankouacc", loankouacc);
    }
    
    String contractId = form.getContractId().trim();// 合同ID
    if (contractId != null && !"".equals(contractId)) {
      m.put("contractId", contractId);
    }

    String borrowerName = form.getBorrowerName().trim();// 借款人姓名
    if (borrowerName != null && !"".equals(borrowerName)) {
      m.put("borrowerName", borrowerName);
    }

    String cardNum = form.getCardNum().trim();// 证件号
    if (cardNum != null && !"".equals(cardNum)) {
      m.put("cardNum", cardNum);
    }

    String empId = form.getEmpId().trim();// 职工ID
    if (empId != null && !"".equals(empId)) {
      m.put("empId", empId);
    }

    return m;
  }
}
