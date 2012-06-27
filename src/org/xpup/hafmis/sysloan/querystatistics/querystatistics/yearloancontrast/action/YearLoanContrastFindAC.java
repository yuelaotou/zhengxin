package org.xpup.hafmis.sysloan.querystatistics.querystatistics.yearloancontrast.action;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.yearloancontrast.form.YearLoanContrastAF;
/**
 * 
 * @author yqf
 * 年度贷款统计对照
 * 20081008
 */
public class YearLoanContrastFindAC extends Action{

  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    // TODO Auto-generated method stub
    YearLoanContrastAF yearLoanContrastAF = (YearLoanContrastAF)form;
    HashMap criterions = makeCriterionsMap(yearLoanContrastAF);
    Pagination pagination = new Pagination(0, 10, 1, "  ", "DESC",criterions);
    String paginationKey = YearLoanContrastShowAC.PAGINATION_KEY;
    request.getSession().setAttribute(paginationKey, pagination);
    return mapping.findForward("to_yearLoanContrastShowAC");
  }

  private HashMap makeCriterionsMap(YearLoanContrastAF form) {
    // TODO Auto-generated method stub
    HashMap m = new HashMap();
    String year = form.getYear();
    if(year!=null && !"".equals(year)){
      m.put("year", year.trim());
    }
    String officeCode = form.getOfficeCode();
    if(officeCode!=null && !"".equals(officeCode)){
      m.put("officeCode", officeCode.trim());
    }
    return m;
  }
}
