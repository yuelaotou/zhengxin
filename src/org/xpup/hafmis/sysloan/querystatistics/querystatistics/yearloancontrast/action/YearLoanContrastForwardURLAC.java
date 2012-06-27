package org.xpup.hafmis.sysloan.querystatistics.querystatistics.yearloancontrast.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
/**
 * 
 * @author yqf
 * 年度贷款统计对照
 * 20081008
 */
public class YearLoanContrastForwardURLAC extends Action{

  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    // TODO Auto-generated method stub
    request.getSession().setAttribute("yearLoanContrastAF", null);
    request.getSession().setAttribute(YearLoanContrastShowAC.PAGINATION_KEY, null);
    return mapping.findForward("to_yearLoanContrastShowAC");
  }
}
