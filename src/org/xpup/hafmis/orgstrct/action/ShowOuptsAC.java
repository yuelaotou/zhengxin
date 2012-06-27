package org.xpup.hafmis.orgstrct.action;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.enums.OrderEnum;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.orgstrct.bizsrvc.IMaintainOuptBS;

public class ShowOuptsAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    Pagination pagination = getPagination(PAGINATION_KEY, request);
    PaginationUtils.updatePagination(pagination, request);

    IMaintainOuptBS maintainOuptBS = (IMaintainOuptBS) BSUtils
        .getBusinessService("orgStructureBS", this, mapping.getModuleConfig());
    List oupts = maintainOuptBS.findOupts(pagination);
    request.setAttribute("oupts", oupts);

    return mapping.findForward("oupts");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "oupt.id", OrderEnum.ASC,
          new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }

  public static final String PAGINATION_KEY = "org.xpup.hafmis.orgstrct.action.ShowOuptsAC";

}
