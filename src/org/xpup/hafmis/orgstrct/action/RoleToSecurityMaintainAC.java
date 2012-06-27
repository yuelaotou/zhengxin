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
import org.xpup.hafmis.orgstrct.form.RoleToSecurityAF;

public class RoleToSecurityMaintainAC extends DispatchAction {
  
  public ActionForward save(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
      ActionMessages messages =null;
      try {
          String orgid[]=request.getParameterValues("sorgid"); 
          Pagination pagination = getPagination(RoleToSecurityShowAC.PAGINATION_KEY, request); 
          String roleid=(String)pagination.getQueryCriterions().get("roleid");
          pagination = new Pagination();
          pagination.getQueryCriterions().put("roleid",roleid);
          IToSecurityMngBS toSecurityMngBS = (IToSecurityMngBS) BSUtils
          .getBusinessService("toSecurityMngBS", this, mapping.getModuleConfig());
          toSecurityMngBS.addRoleOrg(roleid, orgid);
          messages=new ActionMessages();
          messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("分配成功！",
              false));
          saveErrors(request, messages);
          
    } catch (Exception bex) {
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("分配失败！",
          false));
      saveErrors(request, messages);
    }
    return mapping.findForward("show_rolesecurity");
  }  
  public ActionForward saveAll(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
      ActionMessages messages =null;
      try {
          Pagination pagination = getPagination(RoleToSecurityShowAC.PAGINATION_KEY, request); 
          String roleid=(String)pagination.getQueryCriterions().get("roleid");
          IToSecurityMngBS toSecurityMngBS = (IToSecurityMngBS) BSUtils
          .getBusinessService("toSecurityMngBS", this, mapping.getModuleConfig());
          toSecurityMngBS.addAllRoleOrg(pagination);
          pagination = new Pagination();
          pagination.getQueryCriterions().put("roleid",roleid);
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
    return mapping.findForward("show_rolesecurity");
  }  
  public ActionForward delete(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
      ActionMessages messages =null;
      try {
          String orgid[]=request.getParameterValues("orgid"); 
          Pagination pagination = getPagination(RoleToSecurityShowAC.PAGINATION_KEY, request); 
          String roleid=(String)pagination.getQueryCriterions().get("roleid");
          pagination = new Pagination();
          pagination.getQueryCriterions().put("roleid",roleid);
          IToSecurityMngBS toSecurityMngBS = (IToSecurityMngBS) BSUtils
          .getBusinessService("toSecurityMngBS", this, mapping.getModuleConfig());
          toSecurityMngBS.deleteRoleOrg(roleid, orgid);
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
    return mapping.findForward("show_rolesecurity");
  }  
  public ActionForward deleteAll(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
      ActionMessages messages =null;
      try {
          Pagination pagination = getPagination(RoleToSecurityShowAC.PAGINATION_KEY, request); 
          String roleid=(String)pagination.getQueryCriterions().get("roleid");
          pagination = new Pagination();
          pagination.getQueryCriterions().put("roleid",roleid);
          IToSecurityMngBS toSecurityMngBS = (IToSecurityMngBS) BSUtils
          .getBusinessService("toSecurityMngBS", this, mapping.getModuleConfig());
          toSecurityMngBS.deleteAllRoleOrg(roleid);
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
    return mapping.findForward("show_rolesecurity");
  }  

  public ActionForward assignOffice(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
      ActionMessages messages =null;
      try {
          RoleToSecurityAF uf =  (RoleToSecurityAF)form;
          String office=uf.getOffices();
          Pagination pagination = getPagination(RoleToSecurityShowAC.PAGINATION_KEY, request); 
          String roleid=(String)pagination.getQueryCriterions().get("roleid");
          pagination = new Pagination();
          pagination.getQueryCriterions().put("roleid",roleid);
          IToSecurityMngBS toSecurityMngBS = (IToSecurityMngBS) BSUtils
          .getBusinessService("toSecurityMngBS", this, mapping.getModuleConfig());
          toSecurityMngBS.addRoleOffice(roleid, office);
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
    return mapping.findForward("show_rolesecurity");
  }  

  public ActionForward assignBank(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
      ActionMessages messages =null;
      try {
          RoleToSecurityAF uf =  (RoleToSecurityAF)form;
          String collbankid = uf.getBanks();
          Pagination pagination = getPagination(RoleToSecurityShowAC.PAGINATION_KEY, request); 
          String roleid=(String)pagination.getQueryCriterions().get("roleid");
          pagination = new Pagination();
          pagination.getQueryCriterions().put("roleid",roleid);
          IToSecurityMngBS toSecurityMngBS = (IToSecurityMngBS) BSUtils
          .getBusinessService("toSecurityMngBS", this, mapping.getModuleConfig());
          toSecurityMngBS.addRoleBank(roleid, collbankid);
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
    return mapping.findForward("show_rolesecurity");
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
