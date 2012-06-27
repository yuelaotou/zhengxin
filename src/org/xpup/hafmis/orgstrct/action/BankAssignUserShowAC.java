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
import org.xpup.hafmis.orgstrct.form.BankAssignRoleAF;
import org.xpup.hafmis.orgstrct.form.BankAssignUserAF;

public class BankAssignUserShowAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.orgstrct.action.BankAssignUserShowAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
      try {
        SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      Pagination pagination = getPagination(PAGINATION_KEY, request); 
      PaginationUtils.updatePagination(pagination, request);  
      IToSecurityMngBS toSecurityMngBS = (IToSecurityMngBS) BSUtils
          .getBusinessService("toSecurityMngBS", this, mapping.getModuleConfig());
      BankAssignUserAF bankAssignUserAF = new BankAssignUserAF(); 
      if(securityInfo!=null)
      bankAssignUserAF= toSecurityMngBS.findUserBank(pagination, securityInfo);
      request.setAttribute("userBanklist", bankAssignUserAF.getUserBanklist());
      request.setAttribute("sparelist", bankAssignUserAF.getSparelist());
      request.setAttribute("bankAssignUserAF", bankAssignUserAF);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("show_userbank");
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
