package org.xpup.hafmis.sysloan.dataready.ratetype.action;

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
import org.xpup.hafmis.sysloan.dataready.ratetype.bsinterface.IRateTypeBS;
import org.xpup.hafmis.sysloan.dataready.ratetype.form.RateTypeShowAF;

public class RateTypeTaShowAC extends Action{
  
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.dataready.ratetype.action.RateTypeTaShowAC";
  
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    RateTypeShowAF af = new RateTypeShowAF();
    Pagination pagination = getPagination(PAGINATION_KEY, request);
    PaginationUtils.updatePagination(pagination, request);
    IRateTypeBS rateTypeBS = (IRateTypeBS) BSUtils.getBusinessService("rateTypeBS", this,
        mapping.getModuleConfig());
    List list = rateTypeBS.findRateTypeList(pagination);
    af.setList(list);
    request.setAttribute("rateTypeShowAF", af);
    return mapping.findForward("to_ratetype_show");
  }
  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "loanRateType.id", "DESC",
          new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }

    return pagination;
  }
}
