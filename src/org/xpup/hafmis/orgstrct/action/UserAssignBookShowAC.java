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
import org.xpup.hafmis.orgstrct.form.OfficeAssignRoleAF;
import org.xpup.hafmis.orgstrct.form.UserAssignBookAF;

public class UserAssignBookShowAC extends Action {
  
  public static final String PAGINATION_KEY = "org.xpup.hafmis.orgstrct.action.UserAssignBookShowAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
      try {
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      Pagination pagination = getPagination(PAGINATION_KEY, request); 
      PaginationUtils.updatePagination(pagination, request);  
      IToSecurityMngBS toSecurityMngBS = (IToSecurityMngBS) BSUtils
          .getBusinessService("toSecurityMngBS", this, mapping.getModuleConfig());
      UserAssignBookAF userAssignBookAF = (UserAssignBookAF)form; 
      if(securityInfo != null)
      userAssignBookAF= toSecurityMngBS.findBookUsers(pagination,securityInfo);
      request.setAttribute("userbooklist", userAssignBookAF.getUserbooklist());
      request.setAttribute("sparelist", userAssignBookAF.getSparelist());
      request.setAttribute("usersList", userAssignBookAF.getUsersList());
      request.setAttribute("userAssignBookAF", userAssignBookAF);
    } catch (Exception e) {
      e.printStackTrace();
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
