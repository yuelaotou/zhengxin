package org.xpup.hafmis.sysfinance.bookmng.subjectrelation.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.sysfinance.bookmng.subjectrelation.action.SubjectRelationTaPop1AC;
import org.xpup.hafmis.sysfinance.bookmng.subjectrelation.action.SubjectRelationTaPop2AC;
import org.xpup.hafmis.sysfinance.bookmng.subjectrelation.action.SubjectRelationTaPop3AC;
import org.xpup.hafmis.sysfinance.bookmng.subjectrelation.action.SubjectRelationTaPop4AC;
import org.xpup.hafmis.sysfinance.bookmng.subjectrelation.action.SubjectrelationTaShowAC;

public class SubjectrelationTaForwardURLAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    resetToken(request);
    request.setAttribute("type", "1");
    request.getSession()
        .removeAttribute(SubjectRelationTaPop1AC.PAGINATION_KEY);
    request.getSession()
        .removeAttribute(SubjectRelationTaPop2AC.PAGINATION_KEY);
    request.getSession()
        .removeAttribute(SubjectRelationTaPop3AC.PAGINATION_KEY);
    request.getSession()
        .removeAttribute(SubjectRelationTaPop4AC.PAGINATION_KEY);
    request.getSession()
        .removeAttribute(SubjectrelationTaShowAC.PAGINATION_KEY);
    return mapping.findForward("to_show_subjectrelationta");
  }
}
