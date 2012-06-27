package org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgperson.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgperson.from.ChgpersonQueryAF;

public class ChgpersonQueryFindAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ChgpersonQueryAF af = (ChgpersonQueryAF) form;
    HashMap criterions = makeCriterionsMap(af);
    Pagination pagination = new Pagination(0, 10, 1, "", "ASC", criterions);
    String paginationKey = getPaginationKey();
    request.getSession().setAttribute(paginationKey, pagination);
    return mapping.findForward("chgpersonQueryShowAC");
  }

  private String getPaginationKey() {
    return ChgpersonQueryShowAC.PAGINATION_KEY;
  }

  private HashMap makeCriterionsMap(ChgpersonQueryAF form) {
    HashMap m = new HashMap();

    String office = form.getOffice();
    if (office != null && !"".equals(office.trim())) {
      m.put("office", office.trim());
    }
    String bankid = form.getBankid();
    if (bankid != null && !"".equals(bankid.trim())) {
      m.put("bankid", bankid.trim());
    }
    String begDate = form.getBegDate();
    if (begDate != null && !"".equals(begDate.trim())) {
      m.put("begDate", begDate.trim());
    }
    String endDate = form.getEndDate();
    if (endDate != null && !"".equals(endDate.trim())) {
      m.put("endDate", endDate.trim());
    }
    String type = form.getType();
    if (type != null && !"".equals(type.trim())) {
      m.put("type", type.trim());
    }
    String orgid = form.getOrgid();
    if (orgid != null && !"".equals(orgid.trim())) {
      m.put("orgid", orgid.trim());
    }
    return m;
  }
}
