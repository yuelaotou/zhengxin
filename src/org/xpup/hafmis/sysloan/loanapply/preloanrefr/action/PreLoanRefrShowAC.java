/**
 * Copy Right Information   : Goldsoft 
 * Project                  : LoanCheckShowAC
 * @Version                 : 1.0
 * @author                  : wangy
 * 生成日期                   :2008-05-19
 **/
package org.xpup.hafmis.sysloan.loanapply.preloanrefr.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loanapply.preloanrefr.bsinterface.IPreLoanRefrBS;
import org.xpup.hafmis.sysloan.loanapply.preloanrefr.form.PreLoanRefrShowAF;

public class PreLoanRefrShowAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.loanapply.preloanrefr.action.PreLoanRefrShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);
      IPreLoanRefrBS preLoanRefrBS = (IPreLoanRefrBS) BSUtils
          .getBusinessService("preLoanRefrBS", this, mapping.getModuleConfig());
      PreLoanRefrShowAF preLoanRefrShowAF = (PreLoanRefrShowAF) form;
      if (pagination.getQueryCriterions().size() > 0) {
        preLoanRefrShowAF = preLoanRefrBS.queryPreLoanRefrListByCriterions(
            pagination, securityInfo);
      }
      request.setAttribute("preLoanRefrShowAF", preLoanRefrShowAF);
    } catch (BusinessException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_preloanrefr_show");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "", "DESC", new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
}
