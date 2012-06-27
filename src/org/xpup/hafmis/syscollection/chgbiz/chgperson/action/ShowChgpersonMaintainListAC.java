package org.xpup.hafmis.syscollection.chgbiz.chgperson.action;

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
import org.xpup.hafmis.syscollection.chgbiz.chgperson.bsinterface.IChgpersonDoBS;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.form.ChgpersonMaintainListAF;

public class ShowChgpersonMaintainListAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.chgbiz.chgperson.action.ShowChgpersonMaintainListAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    ActionMessages messages = null;
    ChgpersonMaintainListAF af = (ChgpersonMaintainListAF) form;
    try {
      /**
       * 分页
       */

      HttpSession session = request.getSession();

      String orgIDByChgPersonHeadID = (String) session
          .getAttribute("orgIDByChgPersonHeadID");
      if (orgIDByChgPersonHeadID != null) {
        session.removeAttribute("orgIDByChgPersonHeadID");
      }
      String firstsearch = (String) session.getAttribute("firstsearch");
      String deleteflag = (String) session.getAttribute("deleteflag");

      Pagination pagination = getPagination(PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);
      IChgpersonDoBS chgpersonDoBS = (IChgpersonDoBS) BSUtils
          .getBusinessService("chgpersonDoBS", this, mapping.getModuleConfig());

      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");

      List list = null;

      if (firstsearch.equals("1")) {
        list = chgpersonDoBS
            .findChgpersonMaintainList(pagination, securityInfo);
      } else if (firstsearch.equals("2")
          && (deleteflag != null && deleteflag.equals("2"))) {
        list = chgpersonDoBS.findChgpersonMaintainListByCriterions(pagination,
            securityInfo);
        session.setAttribute("deleteflag", null);
      } else {
        list = chgpersonDoBS.findChgpersonMaintainListByCriterions(pagination,
            securityInfo);
      }
      af.setList(list);
      af.setType("1");// 按钮全部禁用
      request.setAttribute("chgOrgRateMaintainListAF", af);

      af.setId("");
      af.setName("");
      af.setDate("");
      int type = securityInfo.getIsOrgEdition();
      request.setAttribute("type", type + "");
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getLocalizedMessage().toString(), false));
      saveErrors(request, messages);
    } catch (Exception ex) {
    }
    return mapping.findForward("to_chgpersonMaintain_list");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "chgPersonHead.chgStatus ASC,chgPersonHead.id DESC", "",
          new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }

    return pagination;
  }
}
