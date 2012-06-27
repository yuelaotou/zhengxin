package org.xpup.hafmis.sysloan.loancallback.advancepayloan.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysloan.loancallback.advancepayloan.form.AdvancepayloanTaAF;

public class AdvancepayloanTaShowAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.loancallback.advancepayloan.action.AdvancepayloanTaShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse rsponse) throws Exception {
    Pagination pagination = null;
    AdvancepayloanTaAF advancepayloanTaAF = new AdvancepayloanTaAF();
    try {
      pagination = getPagination(PAGINATION_KEY, request);
    } catch (Exception e) {
      e.printStackTrace();
    }
    request.setAttribute("advancepayloanTaAF", advancepayloanTaAF);
    return mapping.findForward("to_advancepayloanTa_show");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "null", "ASC", new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }

    return pagination;
  }

}
