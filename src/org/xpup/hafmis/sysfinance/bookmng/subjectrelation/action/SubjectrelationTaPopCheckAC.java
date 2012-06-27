package org.xpup.hafmis.sysfinance.bookmng.subjectrelation.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class SubjectrelationTaPopCheckAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    // TODO Auto-generated method stub
    String calculRelaType = (String) request.getParameter("calculRelaType");
    request.getSession().setAttribute("calculRelaType", calculRelaType);
    request.getSession().removeAttribute(SubjectRelationTaPop3AC.PAGINATION_KEY);
    request.getSession().removeAttribute(SubjectRelationTaPop1AC.PAGINATION_KEY);
    request.getSession().removeAttribute(SubjectRelationTaPop2AC.PAGINATION_KEY);
    request.getSession().removeAttribute(SubjectRelationTaPop4AC.PAGINATION_KEY);
    String firstSubjectCode=(String)request.getParameter("firstSubjectCode");
    request.getSession().setAttribute("firstSubjectCode", firstSubjectCode.trim());
    if ("0".equals(calculRelaType)) {
      return mapping.findForward("to_pop_subjecttapop1");
    }
    if ("1".equals(calculRelaType)) {
      return mapping.findForward("to_pop_subjecttapop2");
    }
    if ("2".equals(calculRelaType)) {
      return mapping.findForward("to_pop_subjecttapop3");
    }
    if ("3".equals(calculRelaType)) {
      return mapping.findForward("to_pop_subjecttapop4");
    }
    return null;
  }
}
