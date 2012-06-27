package org.xpup.hafmis.sysfinance.bookmng.subjectrelation.action;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysfinance.bookmng.subjectrelation.form.SubjectrelationTbAF;

public class SubjectRelationTbFindAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      SubjectrelationTbAF subjectrelationTbAF = (SubjectrelationTbAF) form;
      HashMap criterions = makeCriterionsMap(subjectrelationTbAF);
      Pagination pagination = new Pagination(0, 10, 1, "b.subject_rele_id ",
          "DESC", criterions);
      String paginationKey = getPaginationKey();
      request.getSession().setAttribute(paginationKey, pagination);
      subjectrelationTbAF.reset(mapping, request);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_show_subjectrelationtb");
  }

  protected String getPaginationKey() {
    return SubjectrelationTbShowAC.PAGINATION_KEY;
  }

  protected HashMap makeCriterionsMap(SubjectrelationTbAF form) {
    HashMap m = new HashMap();
    String subjectCode = form.getSubjectCode();
    if (!(subjectCode == null || subjectCode.trim().length() == 0))
      m.put("subjectCode", form.getSubjectCode().trim());
    String subjectName = form.getSubjectName();
    if (!(subjectName == null || subjectName.trim().length() == 0)) {
      m.put("subjectName", form.getSubjectName().trim());
    }
    String calculRelaType = form.getCalculRelaType();
    if (!(calculRelaType == null || calculRelaType.trim().length() == 0)) {
      if ("3".equals(calculRelaType)) {
        m.put("calculRelaType", "3");
      } else {
        m.put("calculRelaType", form.getCalculRelaType().trim());
      }
    }
    String orgid = form.getOrgid();
    String office = form.getOffice();
    String bankid = form.getBankid();
    if (!(orgid == null || orgid.trim().length() == 0)) {
      orgid=String.valueOf(new Integer(orgid));
      m.put("calculRelaValue", orgid.trim());
    } else if (!(office == null || office.trim().length() == 0)) {
      m.put("calculRelaValue", form.getOffice().trim());
    } else {
      if (!(bankid == null || bankid.trim().length() == 0)) {
        m.put("calculRelaValue", form.getBankid().trim());
      }
    }
    return m;
  }
}
