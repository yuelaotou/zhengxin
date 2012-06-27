package org.xpup.hafmis.sysfinance.bookmng.credencemodle.action;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysfinance.bookmng.credencemodle.form.CredencemodleAF;


public class CredencemodleFindAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      CredencemodleAF credencemodleAF = (CredencemodleAF) form;
      HashMap criterions = makeCriterionsMap(credencemodleAF);
      Pagination pagination = new Pagination(0, 10, 1, "b.modle_id ",
          "DESC", criterions);
      String paginationKey = getPaginationKey();
      request.getSession().setAttribute(paginationKey, pagination);
      credencemodleAF.reset(mapping, request);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_show_credencemodle");
  }

  protected String getPaginationKey() {
    return CredencemodleShowAC.PAGINATION_KEY;
  }

  protected HashMap makeCriterionsMap(CredencemodleAF form) {
    HashMap m = new HashMap();
    String subjectCode = form.getSubjectCode1();
    if (!(subjectCode == null || subjectCode.length() == 0))
      m.put("subjectCode1", form.getSubjectCode1().trim());
    String subjectName = form.getSubjectName1();
    if (!(subjectName == null || subjectName.length() == 0)) {
      m.put("subjectName1", form.getSubjectName1().trim());
    }
    String subjectDirection1 = form.getSubjectDirection4();
    if (!(subjectDirection1 == null || subjectDirection1.length() == 0)) {
      m.put("subjectDirection1", form.getSubjectDirection4().trim());
    }
    String bizType1 = form.getBizType1();
    if (!(bizType1 == null || bizType1.length() == 0)) {
      m.put("bizType1", form.getBizType1().trim());
    }
    return m;
  }
}
