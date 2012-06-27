package org.xpup.hafmis.orgstrct.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.orgstrct.bizsrvc.IToSecurityMngBS;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.orgstrct.form.UserAssignUsersAF;

public class UserAssignUsersShowAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.orgstrct.action.UserAssignUsersShowAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
      try {
        SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      Pagination pagination = getPagination(PAGINATION_KEY, request); 
      PaginationUtils.updatePagination(pagination, request);  
      IToSecurityMngBS toSecurityMngBS = (IToSecurityMngBS) BSUtils
          .getBusinessService("toSecurityMngBS", this, mapping.getModuleConfig());
      UserAssignUsersAF userAssignUsersAF = new UserAssignUsersAF(); 
      if(securityInfo != null)
      userAssignUsersAF= toSecurityMngBS.findUserUsers(pagination, securityInfo);
      request.setAttribute("userslist", userAssignUsersAF.getUserAuserlist());
      request.setAttribute("sparelist", userAssignUsersAF.getSparelist());
      request.setAttribute("userAssignUsersAF", userAssignUsersAF);
    } catch (Exception e) {
      e.printStackTrace();
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
