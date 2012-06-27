package org.xpup.hafmis.syscollection.chgbiz.chgslarybase.action;

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
import org.xpup.hafmis.syscollection.chgbiz.chgslarybase.bsinterface.IChgslarybaseBS;
import org.xpup.hafmis.syscollection.chgbiz.chgslarybase.form.ChgslarybaseListAF;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPaymentTail;

/**
 * 吴洪涛 2007.6.27 @
 */
public class ChgslarybaseTaShowAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.chgbiz.chgslarybase.action.ChgslarybaseTaShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    ChgslarybaseListAF chgslarybaseListAF = new ChgslarybaseListAF();
    List list = null;
    Pagination pagination = getPagination(PAGINATION_KEY, request);
    PaginationUtils.updatePagination(pagination, request);
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    pagination.getQueryCriterions().put("SecurityInfo", securityInfo);
    String type = (String) request.getAttribute("type");
    String orgid = (String) pagination.getQueryCriterions().get("org.id");
    try {

      IChgslarybaseBS chgslarybaseBS = (IChgslarybaseBS) BSUtils
          .getBusinessService("chgslarybaseBS", this, mapping.getModuleConfig());
      chgslarybaseListAF = chgslarybaseBS.findChgslarybaseList(pagination);

      if (chgslarybaseListAF == null) {
        chgslarybaseListAF = new ChgslarybaseListAF();
      }
      // 是否显示按钮查询
      if (type == null) {
        type = "1";
        chgslarybaseListAF.setType(type);
      }
      request.setAttribute("type", chgslarybaseListAF.getType());
      request.setAttribute("statetype", chgslarybaseListAF.getStatetype());
      request.setAttribute("chgslarybaseListAF", chgslarybaseListAF);
      chgslarybaseListAF.setType(type);

      list = chgslarybaseListAF.getList();

      if (list != null && list.size() > 0) {
        chgslarybaseListAF.setListCount("1");
      }
      // 吴洪涛 2008-6-16 数据导出
      List chgslarybaseCellList = chgslarybaseListAF.getChgslarybaseCellList();
      pagination.getQueryCriterions().put("chgslarybaseCellList",
          chgslarybaseCellList);
      // 吴洪涛 2008-6-16 数据导出
      pagination.getQueryCriterions().put("pageList", list);
    } catch (BusinessException e) {
      chgslarybaseListAF = new ChgslarybaseListAF();
      request.setAttribute("chgslarybaseListAF", chgslarybaseListAF);
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(e
          .getMessage(), false));
      int typetem = securityInfo.getIsOrgEdition();
      request.setAttribute("typetem", typetem + "");
      saveErrors(request, messages);
    } catch (Exception ex) {
      int typetem = securityInfo.getIsOrgEdition();
      request.setAttribute("typetem", typetem + "");
      saveErrors(request, messages);
      ex.printStackTrace();
    }
    return mapping.findForward("to_chgslarybase_list");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "chgPaymentTail.id", "ASC",
          new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }

    return pagination;
  }
}
