/**
 * Copy Right Information   : Goldsoft 
 * Project                  : LoanBusiFlowQueryBailClearShowWindowAC
 * @Version                 : 1.0
 * @author                  : wangy
 * 生成日期                   :2007-10-18
 **/
package org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.loanbusiflowquery.action;

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
import org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.loanbusiflowquery.bsinterface.ILoanBusiFlowQueryBS;
import org.xpup.hafmis.sysloan.specialbiz.bailclearinterest.form.BailClearInterestTbAF;

public class LoanBusiFlowQueryBailClearShowWindowAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.loanbusiflowquery.action.LoanBusiFlowQueryBailClearShowWindowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);
      String flowHeadId = request.getParameter("flowHeadId");
      if (flowHeadId != null) {
        pagination.getQueryCriterions().put("flowHeadId", flowHeadId);
      }
      ILoanBusiFlowQueryBS loanBusiFlowQueryBS = (ILoanBusiFlowQueryBS) BSUtils
          .getBusinessService("loanBusiFlowQueryBS", this, mapping
              .getModuleConfig());
      BailClearInterestTbAF loanBusiFlowQueryBailClearAF = new BailClearInterestTbAF();
      loanBusiFlowQueryBailClearAF = loanBusiFlowQueryBS
          .queryLoanBusiFlowQueryBailClearListByFlowHeadId(pagination,
              securityInfo);
      request.getSession().setAttribute("printList",
          loanBusiFlowQueryBailClearAF.getPrintList());
      request.setAttribute("loanBusiFlowQueryBailClearAF",
          loanBusiFlowQueryBailClearAF);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_loanbusiflowquerybailclear_show");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1,
          "p202.biz_date, p202.loan_bank_id ", "ASC", new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
}
