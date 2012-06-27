package org.xpup.hafmis.syscollection.pickupmng.pickup.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.syscollection.pickupmng.pickup.form.PickCheckAF;

public class PickCheckFindAC extends Action {

  private HashMap criterions = null;

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    PickCheckAF pickCheckAF = (PickCheckAF) form;
    criterions = makeCriterionsMap(pickCheckAF);
    Pagination pagination = new Pagination(0, 10, 1, "", "",
        criterions);

    String paginationKey = getPaginationKey();
    request.getSession().setAttribute(paginationKey, pagination);
    return mapping.findForward("pickCheckShowAC");
  }

  protected String getPaginationKey() {
    return PickCheckShowAC.PAGINACTION_KEY;
  }

  protected HashMap makeCriterionsMap(PickCheckAF form) {
    HashMap m = new HashMap();
    String orgid = form.getOrgid();
    if (orgid != null && !"".equals(orgid.trim())) {
      m.put("orgid", orgid.trim());
    }
    String orgname = form.getOrgname();
    if (orgname != null && !"".equals(orgname.trim())) {
      m.put("orgname", orgname.trim());
    }
    String begdate = form.getBegdate();
    if (begdate != null && !"".equals(begdate.trim())) {
      m.put("begdate", begdate.trim());
    }
    String enddate = form.getEnddate();
    if (enddate != null && !"".equals(enddate.trim())) {
      m.put("enddate", enddate.trim());
    }
    String checkbegdate = form.getCheckbegdate();
    if (checkbegdate != null && !"".equals(checkbegdate.trim())) {
      m.put("checkbegdate", checkbegdate.trim());
    }
    String checkenddate = form.getCheckenddate();
    if (checkenddate != null && !"".equals(checkenddate.trim())) {
      m.put("checkenddate", checkenddate.trim());
    }
    String ischecked = form.getIschecked();
    if (ischecked != null && !"".equals(ischecked.trim())) {
      m.put("ischecked", ischecked.trim());
    }
    return m;
  }

}
