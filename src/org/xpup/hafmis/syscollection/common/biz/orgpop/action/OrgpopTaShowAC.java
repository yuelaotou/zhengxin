package org.xpup.hafmis.syscollection.common.biz.orgpop.action;

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
import org.xpup.hafmis.syscollection.common.biz.orgpop.bsinterface.IOrgpopBS;

/**
 * 根据单位编号、单位名称、单位状态查询单位信息
 * 
 * @author 李娟 2007-6-27
 */
public class OrgpopTaShowAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.common.biz.orgpop.action.OrgpopTaShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    String[] status = null;
    try {
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      String statuss = request.getParameter("status");
      String indexs = request.getParameter("indexs");
      String qx = request.getParameter("qx");
      if (indexs != null)
        request.getSession().setAttribute("indexs", indexs);
      if (statuss == null) {
        statuss = request.getSession().getAttribute("status").toString();
      }
      if (qx != null) {
        request.getSession().setAttribute("qxs", qx);
        pagination.getQueryCriterions().put("qx", qx);
      }

      status = new String[statuss.length()];
      for (int i = 0; i < statuss.length(); i++) {
        status[i] = statuss.substring(i, i + 1);
      }
      request.getSession().setAttribute("status", statuss);
      PaginationUtils.updatePagination(pagination, request);
      pagination.getQueryCriterions().put("status", status);
      IOrgpopBS orgpopBS = (IOrgpopBS) BSUtils.getBusinessService("orgpopBS",
          this, mapping.getModuleConfig());
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      List orgpopList = orgpopBS.findOrgpopList(pagination, securityInfo);
      request.setAttribute("orgpopList", orgpopList);
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
          "没有您要查询的信息！", false));
      saveErrors(request, messages);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward(getForword());
  }

  protected String getForword() {
    return "show_orgpop";
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 3, 1, "orgs.id", "ASC", new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }

    return pagination;
  }
}
