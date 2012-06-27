package org.xpup.hafmis.sysloan.loancallback.advancepayloan.action;

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
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loancallback.advancepayloan.bsinterface.IAdvancepayloanBS;
import org.xpup.hafmis.sysloan.loancallback.advancepayloan.form.AdvancepayloanTbAF;

public class AdvancepayloanTbShowAC extends Action{
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.loancallback.advancepayloan.action.AdvancepayloanTbShowAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    // TODO Auto-generated method stub
    Pagination pagination = null;
    List list = null;
    AdvancepayloanTbAF advancepayloanTbAF = new AdvancepayloanTbAF();
    try{
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      pagination = getPagination(PAGINATION_KEY, request);
      IAdvancepayloanBS advancepayloanBS = (IAdvancepayloanBS) BSUtils
      .getBusinessService("advancepayloanBS", this, mapping
          .getModuleConfig());
      PaginationUtils.updatePagination(pagination, request);
      list = advancepayloanBS.queryTbList(pagination, securityInfo);
      advancepayloanTbAF.setList(list);
    } catch (Exception e) {
      e.printStackTrace();
    }
    request.setAttribute("advancepayloanTbAF", advancepayloanTbAF);
    return mapping.findForward("to_advancepayloanTb_show");
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
