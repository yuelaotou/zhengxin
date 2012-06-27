package org.xpup.hafmis.orgstrct.action;

import java.util.List;

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
import org.xpup.hafmis.orgstrct.form.UserToSecurityAF;

public class UserToSecurityMaintainAC extends DispatchAction {
  
  public ActionForward save(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
      ActionMessages messages =null;
      try {
          String orgid[]=request.getParameterValues("sorgid"); 
          Pagination pagination = getPagination(UserToSecurityShowAC.PAGINATION_KEY, request); 
          String username=(String)pagination.getQueryCriterions().get("username");
          pagination = new Pagination();
          pagination.getQueryCriterions().put("username",username);
          IToSecurityMngBS toSecurityMngBS = (IToSecurityMngBS) BSUtils
          .getBusinessService("toSecurityMngBS", this, mapping.getModuleConfig());
          toSecurityMngBS.addUserOrg(username, orgid);
          messages=new ActionMessages();
          messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("分配成功！",
              false));
          saveErrors(request, messages);
          
    } catch (BusinessException bex) {
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("分配失败！",
          false));
      saveErrors(request, messages);
    }
    return mapping.findForward("show_usersecurity");
  }  
  public ActionForward saveAll(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
      ActionMessages messages =null;
      try {
          Pagination pagination = getPagination(UserToSecurityShowAC.PAGINATION_KEY, request); 
          SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
          String username=(String)pagination.getQueryCriterions().get("username");
          IToSecurityMngBS toSecurityMngBS = (IToSecurityMngBS) BSUtils
          .getBusinessService("toSecurityMngBS", this, mapping.getModuleConfig());
          toSecurityMngBS.addAllUserOrg(pagination,securityInfo);
          pagination = new Pagination();
          pagination.getQueryCriterions().put("username",username);
          messages=new ActionMessages();
          messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("分配成功！",
              false));
          saveErrors(request, messages);
    } catch (BusinessException bex) {
      bex.printStackTrace();
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("分配失败！",
          false));
      saveErrors(request, messages);
    }
    return mapping.findForward("show_usersecurity");
  }  
  public ActionForward delete(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
      ActionMessages messages =null;
      try {
          String orgid[]=request.getParameterValues("orgid"); 
          Pagination pagination = getPagination(UserToSecurityShowAC.PAGINATION_KEY, request); 
          String username=(String)pagination.getQueryCriterions().get("username");
          pagination = new Pagination();
          pagination.getQueryCriterions().put("username",username);
          IToSecurityMngBS toSecurityMngBS = (IToSecurityMngBS) BSUtils
          .getBusinessService("toSecurityMngBS", this, mapping.getModuleConfig());
          toSecurityMngBS.deleteUserOrg(username,orgid);
          messages=new ActionMessages();
          messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("撤消成功！",
              false));
          saveErrors(request, messages);
    } catch (BusinessException bex) {
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("撤消失败！",
          false));
      saveErrors(request, messages);
    }
    return mapping.findForward("show_usersecurity");
  }  
  public ActionForward deleteAll(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
      ActionMessages messages =null;
      try {
          Pagination pagination = getPagination(UserToSecurityShowAC.PAGINATION_KEY, request); 
          String username=(String)pagination.getQueryCriterions().get("username");
          pagination = new Pagination();
          pagination.getQueryCriterions().put("username",username);
          IToSecurityMngBS toSecurityMngBS = (IToSecurityMngBS) BSUtils
          .getBusinessService("toSecurityMngBS", this, mapping.getModuleConfig());
          toSecurityMngBS.deleteAllUserOrg(username);
          messages=new ActionMessages();
          messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("撤消成功！",
              false));
          saveErrors(request, messages);
    } catch (BusinessException bex) {
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("撤消失败！",
          false));
      saveErrors(request, messages);
    }
    return mapping.findForward("show_usersecurity");
  }  

  public ActionForward assignOffice(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
      ActionMessages messages =null;
      try {
          UserToSecurityAF uf =  (UserToSecurityAF)form;
          String office=uf.getOffices();
          Pagination pagination = getPagination(UserToSecurityShowAC.PAGINATION_KEY, request); 
          String username=(String)pagination.getQueryCriterions().get("username");
          pagination = new Pagination();
          pagination.getQueryCriterions().put("username",username);
          IToSecurityMngBS toSecurityMngBS = (IToSecurityMngBS) BSUtils
          .getBusinessService("toSecurityMngBS", this, mapping.getModuleConfig());
          toSecurityMngBS.addUserOffice(username, office);
          messages=new ActionMessages();
          messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("分配成功！",
              false));
          saveErrors(request, messages);
    } catch (BusinessException bex) {
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex.getMessage(),
          false));
      saveErrors(request, messages);
    }
    return mapping.findForward("show_usersecurity");
  }  

  public ActionForward assignBank(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
      ActionMessages messages =null;
      try {
          UserToSecurityAF uf =  (UserToSecurityAF)form;
          String collBankId = uf.getBanks();
          Pagination pagination = getPagination(UserToSecurityShowAC.PAGINATION_KEY, request); 
          String username=(String)pagination.getQueryCriterions().get("username");
          pagination = new Pagination();
          pagination.getQueryCriterions().put("username",username);
          IToSecurityMngBS toSecurityMngBS = (IToSecurityMngBS) BSUtils
          .getBusinessService("toSecurityMngBS", this, mapping.getModuleConfig());
          toSecurityMngBS.addUserBank(username, collBankId);
          messages=new ActionMessages();
          messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("分配成功！",
              false));
          saveErrors(request, messages);
    } catch (BusinessException bex) {
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex.getMessage(),
          false));
      saveErrors(request, messages);
    }
    return mapping.findForward("show_usersecurity");
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
