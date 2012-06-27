package org.xpup.hafmis.sysfinance.reportmng.financereport.definereport.action;

import java.util.HashMap;

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
import org.xpup.hafmis.sysfinance.reportmng.financereport.definereport.bsinterface.IDefineReportBS;
import org.xpup.hafmis.sysfinance.reportmng.financereport.definereport.form.DefineReportAF;

public class DefineReportSaveAC extends Action {

  public static final String PAGINATION_KEY =
    "org.xpup.hafmis.sysfinance.reportmng.financereport.definereport.action.DefineReportShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    DefineReportAF defineReportAF =(DefineReportAF)form;
    try {
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);
      
      SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
      IDefineReportBS defineReportBS = (IDefineReportBS) BSUtils.getBusinessService("defineReportBS", this, mapping.getModuleConfig());
      defineReportBS.saveReportMessage(defineReportAF,pagination,securityInfo);
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    request.setAttribute("defineReportAF", defineReportAF);
    return mapping.findForward("defineReportShowAC");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "",
          "DESC", new HashMap(0));
      request.getSession().setAttribute(PAGINATION_KEY, pagination);
    }

    return pagination;
  }
}  
