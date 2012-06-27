package org.xpup.hafmis.orgstrct.action;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.acegisecurity.ui.webapp.AuthenticationProcessingFilter;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.orgstrct.bizsrvc.IToSecurityMngBS;
import org.xpup.hafmis.orgstrct.form.RoleToSecurityAF;

public class RoleToSecurityShowAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.orgstrct.action.RoleToSecurityShowAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
      HttpSession session = request.getSession();
      try {
      String username=(String)session.getAttribute(AuthenticationProcessingFilter.ACEGI_SECURITY_LAST_USERNAME_KEY);
      Pagination pagination = getPagination(PAGINATION_KEY, request); 
      PaginationUtils.updatePagination(pagination, request);  
      pagination.getQueryCriterions().put("users", username);
      IToSecurityMngBS toSecurityMngBS = (IToSecurityMngBS) BSUtils
          .getBusinessService("toSecurityMngBS", this, mapping.getModuleConfig());
      RoleToSecurityAF roleToSecurityAF = new RoleToSecurityAF();
      roleToSecurityAF = toSecurityMngBS.findRoleTree(pagination);      
      request.setAttribute("roleOrglist",roleToSecurityAF.getRoleOrglist());
      request.setAttribute("sparelist",roleToSecurityAF.getSparelist());
      request.setAttribute("roleToSecurityAF", roleToSecurityAF);
    } catch (Exception e) {
      e.printStackTrace();
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
