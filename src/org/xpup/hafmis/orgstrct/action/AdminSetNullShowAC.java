package org.xpup.hafmis.orgstrct.action;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;


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
import org.xpup.hafmis.orgstrct.form.UserAF;

public class AdminSetNullShowAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.orgstrct.action.AdminSetNull";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    // 清空查询后表单的内容。  
  List userList=new ArrayList();
    try {
      String paginationKey = getPaginationKey();
      Pagination pagination = getPagination(paginationKey, request);
      modifyPagination(pagination);
      PaginationUtils.updatePagination(pagination, request);   
      IToSecurityMngBS toSecurityMngBS = (IToSecurityMngBS) BSUtils
          .getBusinessService("toSecurityMngBS", this, mapping.getModuleConfig());     
      userList=toSecurityMngBS.findUser(pagination);    
      request.setAttribute("list", userList);    
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("userList");
  }

  protected String getForword() {
    return "organizations";
  }

  protected void modifyPagination(Pagination pagination) {
    
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "user.username", "DESC", new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }

  protected String getPaginationKey() {
    return PAGINATION_KEY;
  }

}
