package org.xpup.hafmis.syscollection.accountmng.accountchg.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.syscollection.accountmng.accountchg.bsinterface.IOrgChgBS;
import org.xpup.hafmis.syscollection.accountmng.accountchg.form.OrgChgListAF;
import org.xpup.hafmis.syscollection.accountmng.accountopen.action.OrgOpenShowListAC;
import org.xpup.hafmis.syscollection.accountmng.accountopen.bsinterface.IOrgOpenAccountBS;
import org.xpup.hafmis.syscollection.accountmng.accountopen.form.OrgkhCriteronsAF;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgChgLog;

public class OrgChgShowListAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    OrgChgListAF af = (OrgChgListAF) form;
    Map orgchgtypeMap = BusiTools.listBusiProperty(BusiConst.ORGCHGTYPE);
    af.setOrgchgtypeMap(orgchgtypeMap);
    request.setAttribute("orgChgListAF", af);
    try {
      String paginationKey = getPaginationKey();
      Pagination pagination = getPagination(paginationKey, request);
      modifyPagination(pagination);
      PaginationUtils.updatePagination(pagination, request);
      IOrgChgBS orgChgBS = (IOrgChgBS) BSUtils.getBusinessService("orgChgBS",
          this, mapping.getModuleConfig());
      List organizations = orgChgBS.findOrgChgList(pagination);
      request.setAttribute("organizations", organizations);
      OrgChgListAF aff = new OrgChgListAF();
      OrgChgLog orgChgLog = (OrgChgLog)organizations.get(0);
      request.setAttribute("printid",orgChgLog.getId().toString());
      Map orgchgtypenewMap = BusiTools.listBusiProperty(BusiConst.ORGCHGSTRUTS);
      aff.setOrgchgtypeMap(orgchgtypenewMap);
      aff.setList(organizations);
      request.setAttribute("orgChgListAF", aff);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward(getForword());
  }

  protected String getForword() {
    return "orgchg_showlist";
  }

  protected void modifyPagination(Pagination pagination) {
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "orgChgLog.id", "DESC",
          new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }

  protected String getPaginationKey() {
    return PAGINATION_KEY;
  }

  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.accountmng.accountchg.action.OrgChgShowListAC";

}
