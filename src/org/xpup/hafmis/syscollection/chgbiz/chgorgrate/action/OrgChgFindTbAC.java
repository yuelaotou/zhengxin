package org.xpup.hafmis.syscollection.chgbiz.chgorgrate.action;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.syscollection.chgbiz.chgorgrate.form.OrgChgAF;

public class OrgChgFindTbAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    OrgChgAF orgChgAF = (OrgChgAF) form;
    HashMap map = makeCriterionsMap(orgChgAF);
    String paginationKey = OrgChgShowTbAC.PAGINATION_KEY;
    Pagination pagination = new Pagination(0, 10, 1, "orgChg.id ASC", "", map);
    request.getSession().setAttribute(paginationKey, pagination);
    return mapping.findForward("orgChgShowTbAC");
  }

  protected HashMap makeCriterionsMap(OrgChgAF f) {
    HashMap m = new HashMap();
    String orgid = f.getOrgid().trim();
    if (!((orgid == null || "".equals(orgid)))) {
      m.put("orgid", orgid);
    }
    String status = f.getOrgChg().getStatus().trim();
    if(status!=null && !status.equals("")){
      m.put("status", status);
    }
    String print = f.getOrgChg().getPrint().trim();
    if(print!=null && !print.equals("")){
      m.put("print", print);
    }
    return m;
  }
}