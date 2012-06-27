package org.xpup.hafmis.sysloan.querystatistics.loanbackbyfund.loanback.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysloan.querystatistics.loanbackbyfund.loanback.form.LoanBackAF;
/**
 * @author ั๎นโ 20090512
 */
public class LoanBackFindAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try {
      LoanBackAF loanBackAF = (LoanBackAF) form;
      HashMap criterions = makeCriterionsMap(loanBackAF);
      Pagination pagination = new Pagination(0, 10, 1, "p203.contract_id", "DESC",
          criterions);
      pagination.getQueryCriterions().put("type", "1");
      String paginationKey = getPaginationKey();
      request.getSession().setAttribute(paginationKey, pagination);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("loanBackShowAC");
  }

  protected HashMap makeCriterionsMap(LoanBackAF form) {
    HashMap m = new HashMap();

    String office = form.getOffice();
    if (office != null && !"".equals(office.trim())) {
      m.put("office", office.trim());
    }
    String loanBank = form.getLoanBankName();
    if (loanBank != null && !"".equals(loanBank.trim())) {
      m.put("loanBank", loanBank.trim());
    }
    String contractId = form.getContractId();
    if (contractId != null && !"".equals(contractId.trim())) {
      m.put("contractId", contractId.trim());
    }
    String borrowerName = form.getBorrowerName().trim();
    if (borrowerName != null && !"".equals(borrowerName.trim())) {
      m.put("borrowerName", borrowerName.trim());
    }
    String borrowerCardNum = form.getBorrowerCardNum();
    if (borrowerCardNum != null && !"".equals(borrowerCardNum.trim())) {
      m.put("borrowerCardNum", borrowerCardNum.trim());
    }
    String beginBizDate = form.getBeginBizDate();
    if (beginBizDate != null && !"".equals(beginBizDate.trim())) {
      m.put("beginBizDate", beginBizDate.trim());
    }
    String endBizDate = form.getEndBizDate();
    if (endBizDate != null && !"".equals(endBizDate.trim())) {
      m.put("endBizDate", endBizDate.trim());
    }
    String orgId=form.getOrgId();
    if (orgId != null && !"".equals(orgId.trim())) {
      m.put("orgId", orgId.trim());
    }
    String orgName=form.getOrgName();
    if (orgName != null && !"".equals(orgName.trim())) {
      m.put("orgName", orgName.trim());
    }
   String empId=form.getEmpId();
   if (empId != null && !"".equals(empId.trim())) {
     m.put("empId", empId.trim());
   }
   String noteNum=form.getNoteNum();
   if (noteNum != null && !"".equals(noteNum.trim())) {
     m.put("noteNum", noteNum.trim());
   }
    return m;
  }

  protected String getPaginationKey() {
    return LoanBackShowAC.PAGINATION_KEY;
  }
}