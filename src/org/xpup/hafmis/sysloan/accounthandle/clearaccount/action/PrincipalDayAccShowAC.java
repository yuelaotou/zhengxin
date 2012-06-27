package org.xpup.hafmis.sysloan.accounthandle.clearaccount.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.security.common.domain.Userslogincollbank;

public class PrincipalDayAccShowAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.accounthandle.clearaccount.action.PrincipalDayAccShowAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    Pagination pagination = getPagination(PAGINATION_KEY,securityInfo, request);
    PaginationUtils.updatePagination(pagination, request);
    // 放款银行下拉框
    List loanBankNameList = new ArrayList();
    List bangkList = securityInfo.getDkUserBankList();
    Userslogincollbank userslogincollbank = null;
    Iterator bank = bangkList.iterator();
    while (bank.hasNext()) {
      userslogincollbank = (Userslogincollbank) bank.next();
      loanBankNameList.add(new org.apache.struts.util.LabelValueBean(
          userslogincollbank.getCollBankName(), userslogincollbank
              .getCollBankId().toString()));
    }
    request.setAttribute("loanBankNameList", loanBankNameList);
    return mapping.findForward("principaldayacc");
  }
  private Pagination getPagination(String paginationKey,SecurityInfo securityInfo,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      HashMap m = new HashMap();
      m.put("searchDate",securityInfo.getUserInfo().getPlbizDate());
      pagination = new Pagination(0, 10, 1, "",
          "", m);
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
}
