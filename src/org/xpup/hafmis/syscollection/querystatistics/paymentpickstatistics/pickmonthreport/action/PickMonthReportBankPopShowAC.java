package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pickmonthreport.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pickmonthreport.bsinterface.IPickMonthReportBS;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pickmonthreport.form.PickMonthReportBankPopAF;
/**
 * 
 * @author yqf
 * 提取统计月报表  银行弹出框
 * 20080925
 */
public class PickMonthReportBankPopShowAC extends Action{
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pickmonthreport.action.PickMonthReportBankPopShowAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    // TODO Auto-generated method stub
    List list = new ArrayList();
    PickMonthReportBankPopAF pickMonthReportBankPopAF = new PickMonthReportBankPopAF();
    try{
    SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
    IPickMonthReportBS pickMonthReportBS = (IPickMonthReportBS)BSUtils
    .getBusinessService("pickMonthReportBS", this, mapping.getModuleConfig());
    Pagination pagination = getPagination(PAGINATION_KEY, request);
    PaginationUtils.updatePagination(pagination, request);
    String bankId = request.getParameter("bankId");
    if(bankId!=null && !"".equals(bankId)){
      request.getSession().setAttribute("bankId", bankId);
    }else{
      bankId = (String)request.getSession().getAttribute("bankId");
    }
    //父页的Pagination,取其时间的查询条件
    Pagination lastpagination = (Pagination)request.getSession().getAttribute(getLastPagination());
    String startDate = (String)lastpagination.getQueryCriterions().get("startDate");
    String endDate = (String)lastpagination.getQueryCriterions().get("endDate");
    HashMap map = (HashMap) pagination.getQueryCriterions();
    map.put("startDate", startDate);
    map.put("endDate", endDate);
    map.put("bankId", bankId);
    list = pickMonthReportBS.queryPickMonRepBankPopInfo(pagination, securityInfo);
    }catch(Exception e){
      e.printStackTrace();
    }
    Map other_map = BusiTools.listBusiProperty(BusiConst.PICKUPREASON);
    pickMonthReportBankPopAF.setMap(other_map);
    request.getSession().setAttribute("pickMonthReportBankPopAF", pickMonthReportBankPopAF);
    request.getSession().setAttribute("bankinfoList", list);
    return mapping.findForward("to_pickMonthReportBankPop.jsp");
  }
  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    // TODO Auto-generated method stub
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "a.org_id", "ASC", new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
  private String getLastPagination(){
    return PickMonthReportShowAC.PAGINATION_KEY;
  }
}
