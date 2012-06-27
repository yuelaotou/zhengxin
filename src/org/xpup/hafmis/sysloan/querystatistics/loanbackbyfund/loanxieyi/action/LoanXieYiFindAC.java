package org.xpup.hafmis.sysloan.querystatistics.loanbackbyfund.loanxieyi.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysloan.querystatistics.loanbackbyfund.loanxieyi.form.LoanXieYiAF;

/**
 * @author Õı“∞ 2007-10-15
 */
public class LoanXieYiFindAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try {
      LoanXieYiAF loanXieYiAF = (LoanXieYiAF) form;
      HashMap criterions = makeCriterionsMap(loanXieYiAF);
      Pagination pagination = new Pagination(0, 10, 1, "p110.contract_id", "DESC",
          criterions);
      pagination.getQueryCriterions().put("type", "1");
      String paginationKey = getPaginationKey();
      request.getSession().setAttribute(paginationKey, pagination);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("loanXieYiShowAC");
  }

  protected HashMap makeCriterionsMap(LoanXieYiAF form) {
    HashMap m = new HashMap();

    String office = form.getOffice();
    if (office != null && !"".equals(office.trim())) {
      m.put("office", office.trim());
    }

    String loanBank = form.getLoanBankName();
    if (loanBank != null && !"".equals(loanBank.trim())) {
      m.put("loanBank", loanBank);
    }
    
    String contractId = form.getContractId();
    if (contractId != null && !"".equals(contractId.trim())) {
      m.put("contractId", contractId.trim());
    }
    
    String borrowerName = form.getBorrowerName();
    if (borrowerName != null && !"".equals(borrowerName.trim())) {
      m.put("borrowerName", borrowerName.trim());
    }
    String beginBizDate = form.getBeginBizDate();
    if (beginBizDate != null && !"".equals(beginBizDate.trim())) {
      m.put("beginBizDate", beginBizDate);
    }

    String endBizDate = form.getEndBizDate().trim();
    if (endBizDate != null && !"".equals(endBizDate.trim())) {
      m.put("endBizDate", endBizDate);
    }

    String beginDelDate = form.getBeginDelDate();
    if (beginDelDate != null && !"".equals(beginDelDate.trim())) {
      m.put("beginDelDate", beginDelDate);
    }

    String endDelDate = form.getEndDelDate();
    if (endDelDate != null && !"".equals(endDelDate.trim())) {
      m.put("endDelDate", endDelDate);
    }
    String isDelete = form.getIsDelete();
    if (isDelete != null && !"".equals(isDelete.trim())) {
      m.put("isDelete", isDelete);
    }
    return m;
  }

  protected String getPaginationKey() {
    return LoanXieYiShowAC.PAGINATION_KEY;
  }
}