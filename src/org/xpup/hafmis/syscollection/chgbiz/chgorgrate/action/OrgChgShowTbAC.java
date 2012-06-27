package org.xpup.hafmis.syscollection.chgbiz.chgorgrate.action;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.chgbiz.chgorgrate.bsinterface.IChgorgrateBS;
import org.xpup.hafmis.syscollection.chgbiz.chgorgrate.form.OrgChgAF;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgChg;

public class OrgChgShowTbAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.chgbiz.chgorgrate.action.OrgChgShowTbAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    OrgChgAF orgChgAF = new OrgChgAF();
    ActionMessages messages = null;
    List list = null;
    Pagination pagination = getPagination(PAGINATION_KEY, request);
    PaginationUtils.updatePagination(pagination, request);
    try {
      IChgorgrateBS chgorgrateBS = (IChgorgrateBS) BSUtils.getBusinessService(
          "chgorgrateBS", this, mapping.getModuleConfig());
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      list = chgorgrateBS.queryOrgChgList(pagination,securityInfo);
      for(int i=0;i<list.size();i++){
        OrgChg orgChg = (OrgChg)list.get(i);
        orgChg.setStatus(orgChg.getStatus().equals("0")?"未提交":"已提交");
        orgChg.setPrint(orgChg.getPrint().equals("0")?"审核未通过":"审核通过");
      }
      int count = chgorgrateBS.queryOrgChgListAll(pagination,securityInfo);
      pagination.setNrOfElements(count);
      orgChgAF.setList(list);
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      saveErrors(request, messages);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    request.setAttribute("orgChgAF", orgChgAF);
    return mapping.findForward("orgchgtb");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "orgChg.id", "ASC",
          new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }

    return pagination;
  }
}
