package org.xpup.hafmis.orgstrct.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.bizsrvc.IToSecurityMngBS;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;

public class UserAssignUsersMaintainAC extends DispatchAction {

  public ActionForward save(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    try {
      String subusername[] = request.getParameterValues("ssubusername");
      Pagination pagination = getPagination(
          UserAssignUsersShowAC.PAGINATION_KEY, request);
      String username = (String) pagination.getQueryCriterions()
          .get("username");
      IToSecurityMngBS toSecurityMngBS = (IToSecurityMngBS) BSUtils
          .getBusinessService("toSecurityMngBS", this, mapping
              .getModuleConfig());
      toSecurityMngBS.addUserUsers(username, subusername);
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("分配成功！",
          false));
      saveErrors(request, messages);

    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("分配失败！",
          false));
      saveErrors(request, messages);
    }
    return mapping.findForward("show_useruser");
  }

  public ActionForward saveAll(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      Pagination pagination = getPagination(
          UserAssignUsersShowAC.PAGINATION_KEY, request);
      String username = (String) pagination.getQueryCriterions()
          .get("username");
      IToSecurityMngBS toSecurityMngBS = (IToSecurityMngBS) BSUtils
          .getBusinessService("toSecurityMngBS", this, mapping
              .getModuleConfig());
      toSecurityMngBS.addAllUserUsers(username, securityInfo);
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("分配成功！",
          false));
      saveErrors(request, messages);
    } catch (BusinessException bex) {
      bex.printStackTrace();
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("分配失败！",
          false));
      saveErrors(request, messages);
    }
    return mapping.findForward("show_useruser");
  }

  public ActionForward delete(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    try {
      String subusername[] = request.getParameterValues("subusername");
      Pagination pagination = getPagination(
          UserAssignUsersShowAC.PAGINATION_KEY, request);
      String username = (String) pagination.getQueryCriterions()
          .get("username");
      IToSecurityMngBS toSecurityMngBS = (IToSecurityMngBS) BSUtils
          .getBusinessService("toSecurityMngBS", this, mapping
              .getModuleConfig());
      toSecurityMngBS.deleteUserUser(username, subusername);
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("撤消成功！",
          false));
      saveErrors(request, messages);
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("撤消失败！",
          false));
      saveErrors(request, messages);
    }
    return mapping.findForward("show_useruser");
  }

  public ActionForward deleteAll(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    try {
      Pagination pagination = getPagination(
          UserAssignUsersShowAC.PAGINATION_KEY, request);
      String username = (String) pagination.getQueryCriterions()
          .get("username");
      IToSecurityMngBS toSecurityMngBS = (IToSecurityMngBS) BSUtils
          .getBusinessService("toSecurityMngBS", this, mapping
              .getModuleConfig());
      toSecurityMngBS.deleteAllUserUser(username);
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("撤消成功！",
          false));
      saveErrors(request, messages);
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("撤消失败！",
          false));
      saveErrors(request, messages);
    }
    return mapping.findForward("show_useruser");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination();
      request.getSession().setAttribute(paginationKey, pagination);
    }

    return pagination;
  }
}
