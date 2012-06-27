package org.xpup.hafmis.orgstrct.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.OrgDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.orgstrct.dto.UserToSecurityDTO;
import org.xpup.hafmis.orgstrct.form.UserToSecurityAF;

public class UserToSecurityShowAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.orgstrct.action.UserToSecurityShowAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
      HttpSession session = request.getSession();
      try {
        SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
        UserToSecurityAF uf = new UserToSecurityAF(); 
        if(securityInfo != null){
          String username=securityInfo.getUserInfo().getUsername();
          Pagination pagination = getPagination(PAGINATION_KEY, request); 
          PaginationUtils.updatePagination(pagination, request);  
          pagination.getQueryCriterions().put("users", username);
          IToSecurityMngBS toSecurityMngBS = (IToSecurityMngBS) BSUtils
              .getBusinessService("toSecurityMngBS", this, mapping.getModuleConfig());
            uf=toSecurityMngBS.findTree(pagination,securityInfo);
        }
      request.setAttribute("orginfolist",uf.getUserToSecurityDTO().getOrgList());
      request.setAttribute("sparelist",uf.getUserToSecurityDTO().getSparelist());
      request.setAttribute("userToSecurityAF", uf);
      
    } catch (Exception e) {
      e.printStackTrace();
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
