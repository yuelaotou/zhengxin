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

public class UserAssignBookMaintainAC extends DispatchAction {

  public ActionForward save(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    try {
      String bookid[] = request.getParameterValues("sbookid");
      Pagination pagination = getPagination(
          UserAssignBookShowAC.PAGINATION_KEY, request);
      String username = (String) pagination.getQueryCriterions()
          .get("username");
      IToSecurityMngBS toSecurityMngBS = (IToSecurityMngBS) BSUtils
          .getBusinessService("toSecurityMngBS", this, mapping
              .getModuleConfig());
      toSecurityMngBS.addUserBook(username, bookid);
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
    return mapping.findForward("show_userbook");
  }

  public ActionForward saveAll(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      Pagination pagination = getPagination(
          UserAssignBookShowAC.PAGINATION_KEY, request);
      String username = (String) pagination.getQueryCriterions()
          .get("username");
      IToSecurityMngBS toSecurityMngBS = (IToSecurityMngBS) BSUtils
          .getBusinessService("toSecurityMngBS", this, mapping
              .getModuleConfig());
      toSecurityMngBS.addAllUserBook(username, securityInfo);
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
    return mapping.findForward("show_userbook");
  }

  public ActionForward delete(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    try {
      String bookid[] = request.getParameterValues("bookid");
      Pagination pagination = getPagination(
          UserAssignBookShowAC.PAGINATION_KEY, request);
      String username = (String) pagination.getQueryCriterions()
          .get("username");
      IToSecurityMngBS toSecurityMngBS = (IToSecurityMngBS) BSUtils
          .getBusinessService("toSecurityMngBS", this, mapping
              .getModuleConfig());
      toSecurityMngBS.deleteUserBook(username, bookid);
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
    return mapping.findForward("show_userbook");
  }

  public ActionForward deleteAll(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      Pagination pagination = getPagination(
          UserAssignBookShowAC.PAGINATION_KEY, request);
      String username = (String) pagination.getQueryCriterions()
          .get("username");
      IToSecurityMngBS toSecurityMngBS = (IToSecurityMngBS) BSUtils
          .getBusinessService("toSecurityMngBS", this, mapping
              .getModuleConfig());
      toSecurityMngBS.deleteAllUserBook(username, securityInfo);
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
    return mapping.findForward("show_userbook");
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
