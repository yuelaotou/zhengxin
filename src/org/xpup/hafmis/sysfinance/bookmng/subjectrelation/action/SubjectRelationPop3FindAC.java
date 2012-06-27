package org.xpup.hafmis.sysfinance.bookmng.subjectrelation.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysfinance.bookmng.subjectrelation.form.SubjectrelationTaPop3AF;

public class SubjectRelationPop3FindAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      SubjectrelationTaPop3AF subjectrelationTaPop3AF = (SubjectrelationTaPop3AF) form;
      HashMap criterions = makeCriterionsMap(subjectrelationTaPop3AF);
      Pagination pagination = new Pagination(0, 10, 1, "",
          "DESC", criterions);
      String paginationKey = getPaginationKey();
      request.getSession().setAttribute(paginationKey, pagination);     
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_pop_subjectrelationta3");
  }

  protected String getPaginationKey() {
    return SubjectRelationTaPop3AC.PAGINATION_KEY;
  }

  protected HashMap makeCriterionsMap(SubjectrelationTaPop3AF form) {
    HashMap m = new HashMap();
    String office = form.getOffice();
    if (!(office == null || office.trim().length() == 0))
      m.put("office", form.getOffice().trim());
    String loanBankId = form.getLoanBankId();
    if (!(loanBankId == null || loanBankId.trim().length() == 0)) {
      m.put("loanBankId", form.getLoanBankId().trim());
    }
   
    return m;
  }
}
