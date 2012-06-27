package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.paymentyearreport.action;

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
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.paymentyearreport.bsinterface.IPaymentYearReportBS;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.paymentyearreport.form.PaymentYearReportAF;
import org.xpup.security.common.domain.Userslogincollbank;

public class PaymentYearReportShowAC extends Action {
  /**
   * @author yg 2009-04-20 缴存统计年报表
   */
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.paymentyearreport.action.PaymentYearReportShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    List list = null;
    PaymentYearReportAF af = new PaymentYearReportAF();
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");

      List officeList = securityInfo.getOfficeList();
      List officeList1 = new ArrayList();
      OfficeDto officedto = null;
      Iterator itr1 = officeList.iterator();
      while (itr1.hasNext()) {
        officedto = (OfficeDto) itr1.next();
        officeList1.add(new org.apache.struts.util.LabelValueBean(officedto
            .getOfficeName(), officedto.getOfficeCode()));
      }

      List collBankList = securityInfo.getCollBankList();
      List collBankList1 = new ArrayList();
      Userslogincollbank userslogincollbank = null;
      Iterator itr2 = collBankList.iterator();
      while (itr2.hasNext()) {
        userslogincollbank = (Userslogincollbank) itr2.next();
        collBankList1.add(new org.apache.struts.util.LabelValueBean(
            userslogincollbank.getCollBankName().toString(), userslogincollbank
                .getCollBankId().toString()));
      }
      Pagination pagination = (Pagination) request.getSession().getAttribute(
          PAGINATION_KEY);
      if (pagination == null) {
        pagination = getPagination(PAGINATION_KEY, request);
      } else {
        IPaymentYearReportBS paymentYearReportBS = (IPaymentYearReportBS) BSUtils
            .getBusinessService("paymentYearReportBS", this, mapping
                .getModuleConfig());
        list = paymentYearReportBS.queryPaymentYearReportInfo(pagination,
            securityInfo);
      }
      request.setAttribute("officeList", officeList1);
      request.setAttribute("bankList", collBankList1);
      request.getSession().setAttribute("infoList", list);
      request.setAttribute("af", af);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("paymentyearreport");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "", "ASC", new HashMap(0));

      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
}
