package org.xpup.hafmis.sysfinance.bookmng.subject.action;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.bookmng.subject.bsinterface.ISubjectBS;
import org.xpup.hafmis.sysfinance.bookmng.subject.form.SubjectShowAF;
/**
 * 
 * @author ЭѕСт
 * 2007-10-13
 */
public class SubjectTeShowAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysfinance.bookmng.subject.action.SubjectTeShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    SubjectShowAF subjectShowAF = new SubjectShowAF();
    try {
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);
      SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
      ISubjectBS subjectBS = (ISubjectBS) BSUtils.getBusinessService("subjectBS", this, mapping.getModuleConfig());
      subjectShowAF.setSortcodeflag("4");
      List subjectList=subjectBS.findSubjectTree(subjectShowAF,securityInfo);
      request.setAttribute("subjectList", subjectList);
    } catch (Exception e) {
      e.printStackTrace();
    }
    request.setAttribute("subjectShowAF", subjectShowAF);
    return mapping.findForward("to_subject_show");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "",
          "DESC", new HashMap(0));
      request.getSession().setAttribute(PAGINATION_KEY, pagination);
    }

    return pagination;
  }
}
