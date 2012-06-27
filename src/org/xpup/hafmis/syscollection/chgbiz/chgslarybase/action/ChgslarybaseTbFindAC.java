package org.xpup.hafmis.syscollection.chgbiz.chgslarybase.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.syscollection.chgbiz.chgslarybase.form.ChgslarybaseListAF;

public class ChgslarybaseTbFindAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ChgslarybaseListAF chgslarybaseListAF = (ChgslarybaseListAF) form;
    try {
      HashMap criterions = makeCriterionsMap(chgslarybaseListAF);
      Pagination pagination = new Pagination(0, 10, 1, " a2.chg_status asc, a2.id desc ", "", criterions);
      String paginationKey = getPaginationKey();
      request.getSession().setAttribute(paginationKey, pagination);
      pagination.getQueryCriterions().put("typetb","typetb");
      chgslarybaseListAF.reset(mapping, request);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("chgslarybaseTbShowAC.do");
  }

  protected String getPaginationKey() {
    return ChgslarybaseTbShowAC.PAGINATION_KEY;
  }

  protected HashMap makeCriterionsMap(ChgslarybaseListAF form) {
    HashMap m = new HashMap();
    String orgId = (String) form.getOrg().getId();
    if (orgId != null && !"".equals(orgId)) {
      m.put("orgId", orgId.trim());
    }
    String orgName = form.getOrg().getOrgInfo().getName();
    if (orgName != null && !"".equals(orgName)) {
      m.put("orgName", orgName.trim());
    }
    String chgMonth = form.getChgMonth();
    if (chgMonth != null && !"".equals(chgMonth)) {
      m.put("chgMonth", chgMonth.trim());
    }
    String endChgMonth = form.getEndChgMonth();
    if (endChgMonth != null && !"".equals(endChgMonth)) {
      m.put("endChgMonth", endChgMonth.trim());
    }
    return m;
  }
}
