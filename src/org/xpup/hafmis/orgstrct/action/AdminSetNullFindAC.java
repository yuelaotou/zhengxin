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

public class AdminSetNullFindAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    UserAF af = (UserAF) form;
    // 清空查询后表单的内容。
    try {
      Pagination pagination = (Pagination) request.getSession().getAttribute(
          AdminSetNullShowAC.PAGINATION_KEY);
      if (af.getUsername() != null && !af.getUsername().trim().equals("")) {
        pagination.getQueryCriterions()
            .put("username", af.getUsername().trim());
      } else {
        request.getSession().removeAttribute(AdminSetNullShowAC.PAGINATION_KEY);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("adminSetNullShowAC");
  }
}
