package org.xpup.hafmis.sysloan.querystatistics.querystatistics.yearloancontrast.action;

import java.util.ArrayList;
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
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.yearloancontrast.bsinterface.IYearLoanContrastBS;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.yearloancontrast.form.YearLoanContrastAF;
/**
 * 
 * @author yqf
 * 年度贷款统计对照
 * 20081008
 */
public class YearLoanContrastShowAC extends Action{
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.querystatistics.querystatistics.yearloancontrast.action.YearLoanContrastShowAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    // TODO Auto-generated method stub
    YearLoanContrastAF yearLoanContrastAF = new YearLoanContrastAF();
    Pagination pagination = getPagination(PAGINATION_KEY, request);
    PaginationUtils.updatePagination(pagination, request);
    IYearLoanContrastBS yearLoanContrastBS = (IYearLoanContrastBS) BSUtils.getBusinessService("yearLoanContrastBS", this, mapping.getModuleConfig());
    SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
    try{
//    求办事处
      List temp_officeList = securityInfo.getOfficeList();
      List li = new ArrayList();
      for (int i = 0; i < temp_officeList.size(); i++) {
        OfficeDto officedto = null;
        officedto = (OfficeDto) temp_officeList.get(i);
        li.add(new org.apache.struts.util.LabelValueBean(officedto
            .getOfficeName(), officedto.getOfficeCode()));
      }
      yearLoanContrastAF = yearLoanContrastBS.queryList(pagination, securityInfo);
      request.setAttribute("officlist",li);
      request.getSession().setAttribute("yearLoanContrastAF", yearLoanContrastAF);
    }catch(Exception e){
      e.printStackTrace();
    }
    return mapping.findForward("to_yearLoanContrast_show");
  }
  
  private Pagination getPagination(String paginationKey,HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, null, "DESC", new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
}
