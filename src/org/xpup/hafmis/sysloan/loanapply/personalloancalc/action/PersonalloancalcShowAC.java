package org.xpup.hafmis.sysloan.loanapply.personalloancalc.action;

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
import org.xpup.hafmis.sysloan.loanapply.personalloancalc.bsinterface.IPersonalloancalcBS;
import org.xpup.hafmis.sysloan.loanapply.personalloancalc.form.PersonalloancalcFindAF;

public class PersonalloancalcShowAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.loanapply.personalloancalc.action.PersonalloancalcShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    PersonalloancalcFindAF af = new PersonalloancalcFindAF();
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    try {
      Pagination pagination = (Pagination) request.getSession().getAttribute(
          PAGINATION_KEY);
      if (pagination == null) {
        pagination = getPagination(PAGINATION_KEY, request);
      } else {
        PaginationUtils.updatePagination(pagination, request);
        IPersonalloancalcBS personalloancalcBS = (IPersonalloancalcBS) BSUtils
            .getBusinessService("personalloancalcBS", this, mapping
                .getModuleConfig());
        List list = personalloancalcBS.queryCorpusInterestList(pagination,
            securityInfo);
        request.getSession().setAttribute("list", list);
      }
      request.setAttribute("af", af);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_personalloancalc_show");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      HashMap m = new HashMap();
      pagination = new Pagination(0, 12, 1, "", "", m);
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
}
