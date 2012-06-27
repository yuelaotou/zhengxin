package org.xpup.hafmis.sysfinance.accounthandle.credencefillin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class CredenceFillinTdForward_jjAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      request.getSession().setAttribute("type", "0");
      // 打印要用到的状态
      request.getSession().setAttribute("print_type", "1");
      request.getSession().setAttribute("single", "single");
      request.getSession().removeAttribute(
          CredenceFillinTdShowdAC.PAGINATION_KEY);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("credenceFillinTdShowdAC");
  }

}
