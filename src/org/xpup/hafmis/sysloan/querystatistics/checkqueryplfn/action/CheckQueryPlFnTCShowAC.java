package org.xpup.hafmis.sysloan.querystatistics.checkqueryplfn.action;

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
import org.xpup.hafmis.sysfinance.common.biz.queryflow.bsinterface.IQueryFlowBS;
import org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.loanbusiflowquery.form.LoanBusiFlowQueryAF;

public class CheckQueryPlFnTCShowAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.querystatistics.checkqueryplfn.action.CheckQueryPlFnTCShowAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
try {  
      LoanBusiFlowQueryAF loanBusiFlowQueryAF1=(LoanBusiFlowQueryAF)form;
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);
      if(loanBusiFlowQueryAF1!=null){
        String loankouacc=loanBusiFlowQueryAF1.getLoanKouAcc();
        pagination.getQueryCriterions().put("loanKouAcc", loankouacc);
      }
      IQueryFlowBS queryFlowBS = (IQueryFlowBS) BSUtils.getBusinessService(
          "queryFlowBS", this, mapping.getModuleConfig());
      
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      
      LoanBusiFlowQueryAF loanBusiFlowQueryAF = queryFlowBS.queryLoanBusiFlowQueryListByCriterions(pagination, securityInfo);
      request.setAttribute("loanBusiFlowQueryAF", loanBusiFlowQueryAF);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_checkQueryPlFntc_show");
  }
  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      String settNum = (String) request.getAttribute("settNum");
      pagination = new Pagination(0, 10, 1, "notenum", "DESC", new HashMap(0));
      pagination.getQueryCriterions().put("notenum", settNum);
      request.getSession().setAttribute(paginationKey, pagination);
    }

    return pagination;
  }
}
