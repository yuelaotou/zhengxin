package org.xpup.hafmis.syscollection.chgbiz.chgorgrate.action;

import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
import org.xpup.hafmis.syscollection.chgbiz.chgorgrate.form.ChgOrgRateMaintainListAF;

public class ShowChgorgrateMaintainListAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.chgbiz.chgorgrate.action.ShowChgorgrateMaintainListAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ChgOrgRateMaintainListAF af = (ChgOrgRateMaintainListAF) form;
    ActionMessages messages = null;

    try {
      /**
       * 分页
       */
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);

      IChgorgrateBS chgorgrateBS = (IChgorgrateBS) BSUtils.getBusinessService(
          "chgorgrateBS", this, mapping.getModuleConfig());

      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");

      List list = null;
      HttpSession session = request.getSession();
      String firstsearch = (String) session.getAttribute("firstsearch");
      String deleteflag = (String) session.getAttribute("deleteflag");

      if (firstsearch.equals("1")) {
        list = chgorgrateBS.findChgpersonMaintainList(pagination, securityInfo);
      } else if (firstsearch.equals("2")
          && (deleteflag != null && deleteflag.equals("2"))) {
        list = chgorgrateBS.findChgorgrateMaintainListByCriterions(pagination,
            securityInfo);
        session.setAttribute("deleteflag", null);
      } else {
        list = chgorgrateBS.findChgorgrateMaintainListByCriterions(pagination,
            securityInfo);
      }
      af.setList(list);
      af.setType("1");// 按钮全部禁用
      request.setAttribute("chgOrgRateMaintainListAF", af);

      af.setId("");
      af.setName("");
      af.setDate("");
      af.setEndDate("");

    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getLocalizedMessage().toString(), false));
      saveErrors(request, messages);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return mapping.findForward("to_chgorgrateMaintain_list");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1,
          "chgOrgRate.chgStatus ASC,chgOrgRate.id DESC", "", new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }

    return pagination;
  }
}
