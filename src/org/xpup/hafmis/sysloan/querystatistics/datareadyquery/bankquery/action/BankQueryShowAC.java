package org.xpup.hafmis.sysloan.querystatistics.datareadyquery.bankquery.action;

import java.util.HashMap;

import java.util.ArrayList;
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
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.querystatistics.datareadyquery.bankquery.bsinterface.IBankQueryBS;
import org.xpup.hafmis.sysloan.querystatistics.datareadyquery.bankquery.form.BankQueryAF;
import org.xpup.security.common.domain.Userslogincollbank;

public class BankQueryShowAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.querystatistics.datareadyquery.bankquery.action.BankQueryShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    // TODO Auto-generated method stub
    try {
      BankQueryAF bankQueryAF = new BankQueryAF();
      Pagination pagination = getPagination(BankQueryShowAC.PAGINATION_KEY,
          request);
      PaginationUtils.updatePagination(pagination, request);
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      List loanbankList1 = null;
      List loanbankList2 = null;
      List officeList1 = null;
      List officeList2 = null;
      try {
        // 取出用户权限办事处,显示在下拉菜单中
        List officeList = securityInfo.getOfficeList();
        officeList1 = new ArrayList();
        officeList2 = new ArrayList();
        OfficeDto officedto = null;
        Iterator itr1 = officeList.iterator();
        while (itr1.hasNext()) {
          officedto = (OfficeDto) itr1.next();
          officeList1.add(new org.apache.struts.util.LabelValueBean(officedto
              .getOfficeName(), officedto.getOfficeCode()));
          officeList2.add(officedto.getOfficeCode());
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
      try {
        // 取出用户权限放款银行,显示在下拉菜单中
        List loanbankList = securityInfo.getDkUserBankList();
        loanbankList1 = new ArrayList();
        loanbankList2 = new ArrayList();
        Userslogincollbank bank = null;
        Iterator itr1 = loanbankList.iterator();
        while (itr1.hasNext()) {
          bank = (Userslogincollbank) itr1.next();
          loanbankList1.add(new org.apache.struts.util.LabelValueBean(bank
              .getCollBankName(), bank.getCollBankId().toString()));
          loanbankList2.add(bank.getCollBankId());
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
      IBankQueryBS bankQueryBS = (IBankQueryBS) BSUtils.getBusinessService(
          "bankQueryBS", this, mapping.getModuleConfig());
      List list = bankQueryBS.findBankQueryList(pagination, officeList2,
          loanbankList2);
      bankQueryAF.setList(list);
      bankQueryAF.reset(mapping, request);
      request.setAttribute("loanbankList1", loanbankList1);
      request.setAttribute("officeList1", officeList1);
      request.setAttribute("bankQueryAF", bankQueryAF);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_bankquery_show");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      HashMap m = new HashMap();
      pagination = new Pagination(0, 10, 1, "office,loanbankid,id ", "DESC", m);
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
}
