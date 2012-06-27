package org.xpup.hafmis.sysloan.specialbiz.bailenrol.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysloan.specialbiz.bailenrol.form.BailenRolTbAF;

/**
 * @author ÍõÒ° 2007-10-03
 */
public class BailenRolTbFindAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    BailenRolTbAF bailenRolTbAF = (BailenRolTbAF) form;
    HashMap criterions = makeCriterionsMap(bailenRolTbAF);
    Pagination pagination = new Pagination(0, 10, 1, "p202.flow_head_id", "DESC", criterions);
    String paginationKey = getPaginationKey();
    request.getSession().setAttribute(paginationKey, pagination);
    return mapping.findForward("bailenRolTbShowAC");
  }

  protected HashMap makeCriterionsMap(BailenRolTbAF form) {
    HashMap m = new HashMap();

    String contractId = form.getContractId().trim();
    if (contractId != null && !"".equals(contractId)) {
      m.put("contractId", contractId);
    }

    String borrowerName = form.getBorrowerName().trim();
    if (borrowerName != null && borrowerName.length() > 0) {
      m.put("borrowerName", borrowerName);
    }

    String cardNum = form.getCardNum().trim();
    if (cardNum != null && cardNum.length() > 0) {
      m.put("cardNum", cardNum);
    }
    String loanBankName = form.getLoanBankName().trim();
    if (loanBankName != null && loanBankName.length() > 0) {
      m.put("loanBankName", loanBankName);
    }

    String bizSt = form.getBizSt().trim();
    if (bizSt != null && bizSt.length() > 0) {
      m.put("bizSt", bizSt);
    }
    m.put("findType", "find");
    return m;
  }

  protected String getPaginationKey() {
    return BailenRolTbShowAC.PAGINATION_KEY;
  }
}
