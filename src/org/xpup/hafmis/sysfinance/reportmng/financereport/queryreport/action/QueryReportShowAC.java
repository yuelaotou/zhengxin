package org.xpup.hafmis.sysfinance.reportmng.financereport.queryreport.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.reportmng.financereport.queryreport.bsinterface.IQueryReportBS;
import org.xpup.hafmis.sysfinance.reportmng.financereport.queryreport.form.QueryReportAF;

public class QueryReportShowAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysfinance.reportmng.financereport.queryreport.action.QueryReportShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    QueryReportAF queryReportAF = new QueryReportAF();
    try {
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);

      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      IQueryReportBS queryReportBS = (IQueryReportBS) BSUtils
          .getBusinessService("queryReportBS", this, mapping.getModuleConfig());

      List officeList = securityInfo.getOfficeList();
      List officeList1 = new ArrayList();
      OfficeDto officedto = null;
      Iterator itr1 = officeList.iterator();
      while (itr1.hasNext()) {
        officedto = (OfficeDto) itr1.next();
        officeList1.add(new org.apache.struts.util.LabelValueBean(officedto
            .getOfficeName(), officedto.getOfficeCode()));
      }
      request.getSession(true).setAttribute("officeList1", officeList1);

      HttpSession session = request.getSession();
      String tablename=(String)session.getAttribute("tablename");
      pagination.getQueryCriterions().put("tablename", tablename);
      
      String office = (String) pagination.getQueryCriterions().get("office");
      if (!(office == null || office.length() == 0)) {
        queryReportAF = queryReportBS.queryReportMessage(queryReportAF,
            pagination, securityInfo);
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
     request.getSession().setAttribute("queryReportAFPrint", queryReportAF);
     request.setAttribute("queryReportAF", queryReportAF);
    return mapping.findForward("to_queryreport_show");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "", "DESC", new HashMap(0));
      request.getSession().setAttribute(PAGINATION_KEY, pagination);
    }

    return pagination;
  }
}
