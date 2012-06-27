package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pickmonthreport.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pickmonthreport.bsinterface.IPickMonthReportBS;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pickmonthreport.form.PickMonthReportFindAF;
import org.xpup.security.common.domain.Userslogincollbank;

public class PickMonthReportShowAC extends Action {
  /**
   * @author wangshuang
   * 2008-09-22
   * 提取统计月报表
   */
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pickmonthreport.action.PickMonthReportShowAC";
  
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    List list = null;
    PickMonthReportFindAF af = new PickMonthReportFindAF();
    try{
      String paginationKey = getPaginationKey();
      SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
      //办事处信息显示在下拉列表中
      List officeList = securityInfo.getOfficeList();
      List officeList1 = new ArrayList();
      OfficeDto officedto = null;
      Iterator itr1 = officeList.iterator();
      while (itr1.hasNext()) {
        officedto = (OfficeDto) itr1.next();
        officeList1.add(new org.apache.struts.util.LabelValueBean(officedto.getOfficeName(), officedto.getOfficeCode()));
      }
      //取出归集银行下拉列表
      List collBankList = securityInfo.getCollBankList();
      List collBankList1 = new ArrayList();
      Userslogincollbank userslogincollbank = null;
      Iterator itr2 = collBankList.iterator();
      while (itr2.hasNext()) {
        userslogincollbank = (Userslogincollbank) itr2.next();
        collBankList1.add(new org.apache.struts.util.LabelValueBean(userslogincollbank.getCollBankName().toString(), userslogincollbank.getCollBankId().toString()));
      }
      Pagination pagination = (Pagination) request.getSession().getAttribute(paginationKey);
      if(pagination == null){
        pagination = getPagination(paginationKey, request);
      }else{
        IPickMonthReportBS pickMonthReportBS = (IPickMonthReportBS)BSUtils
        .getBusinessService("pickMonthReportBS", this, mapping.getModuleConfig());
        list = pickMonthReportBS.queryPickMonRepInfo(pagination, securityInfo);
      }
      request.setAttribute("officeList", officeList1);
      request.setAttribute("bankList",collBankList1);
      request.getSession().setAttribute("infoList", list);
      request.setAttribute("af", af);
    }catch(Exception e){
      e.printStackTrace();
    }
    return mapping.findForward("to_pickMonthReport.jsp");
  }
  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "a.org_id", "ASC",
          new HashMap(0));

      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
  protected String getPaginationKey() {
    return PAGINATION_KEY;
  }
}


