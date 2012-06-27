package org.xpup.hafmis.syscollection.chgbiz.chgorgrate.action;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.syscollection.chgbiz.chgorgrate.form.ChgOrgRateMaintainListAF;

public class FindChgOrgRateMaintainAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    ChgOrgRateMaintainListAF f = (ChgOrgRateMaintainListAF) form;

    HashMap criterions = makeCriterionsMap(f);

    String orgID = request.getParameter("id");
    String orgName = request.getParameter("name");
    String chgDate = request.getParameter("date");
    String endDate = request.getParameter("endDate");

    HttpSession session = request.getSession();
    session.setAttribute("firstsearch", "2");

    if (!(orgID == null || "".equals(orgID))) {

      String org = "";
      if (orgID.length() == 10) {
        org = orgID.substring(1, orgID.length());
        criterions.put("orgID", org);
      } else {
        criterions.put("orgID", orgID);
      }
    }

    if (!(orgName == null || orgName.length() == 0))
      criterions.put("orgName", orgName);

    if (!(chgDate == null || chgDate.length() == 0))
      criterions.put("chgDate", chgDate);
    if (!(endDate == null || endDate.length() == 0))
      criterions.put("endDate", endDate);

    Pagination pagination = new Pagination(0, 10, 1, "chgOrgRate.chgStatus ASC,chgOrgRate.id DESC", "",
        criterions);
    String paginationKey = getPaginationKey();
    request.getSession().setAttribute(paginationKey, pagination);
    modifyPagination(pagination);

    f.reset(mapping, request);

    return mapping.findForward(getForword());
  }

  protected String getForword() {
    return "to_chgorgrateMaintain_list";
  }

  protected String getPaginationKey() {
    return "org.xpup.hafmis.syscollection.chgbiz.chgorgrate.action.ShowChgorgrateMaintainListAC";
  }

  protected HashMap makeCriterionsMap(ChgOrgRateMaintainListAF form) {
    HashMap m = new HashMap();

    return m;
  }

  protected void modifyPagination(Pagination pagination) {
  }
}
