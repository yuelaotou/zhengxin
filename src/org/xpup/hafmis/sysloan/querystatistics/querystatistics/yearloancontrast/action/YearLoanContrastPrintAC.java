package org.xpup.hafmis.sysloan.querystatistics.querystatistics.yearloancontrast.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
/**
 * 
 * @author yqf
 * 年度贷款统计对照
 * 20081008
 */
public class YearLoanContrastPrintAC extends Action{

  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    // TODO Auto-generated method stub
    SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
    String opertname = securityInfo.getRealName();
    String time = securityInfo.getUserInfo().getPlbizDate();
    request.setAttribute("userName", opertname);
    request.setAttribute("plbizDate", time);
    return mapping.findForward("to_yearLoanContrast_cell");
  }

}
