package org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.principaltotalacc.action;

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
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.principaltotalacc.bsinterface.IPrincipalBS;
import org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.principaltotalacc.form.PrincipalTaAF;
import org.xpup.security.common.domain.Userslogincollbank;

public class PrincipalTcShowAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.principaltotalacc.action.PrincipalTcShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    try {
      PrincipalTaAF af = null;
      Pagination pagination = (Pagination) request.getSession().getAttribute(
          PAGINATION_KEY);
      // 放款银行下拉框
      List loanBankNameList = new ArrayList();
      List bankList = securityInfo.getDkUserBankList();
      Userslogincollbank userslogincollbank = null;
      Iterator bank = bankList.iterator();
      while (bank.hasNext()) {
        userslogincollbank = (Userslogincollbank) bank.next();
        loanBankNameList.add(new org.apache.struts.util.LabelValueBean(
            userslogincollbank.getCollBankName(), userslogincollbank
                .getCollBankId().toString()));
      }
      request.getSession(true).setAttribute("loanBankNameList",
          loanBankNameList);
      if (pagination == null) {
        pagination = getPagination(PAGINATION_KEY, request);
      } else {
        PaginationUtils.updatePagination(pagination, request);
        IPrincipalBS principalBS = (IPrincipalBS) BSUtils.getBusinessService(
            "principalBS", this, mapping.getModuleConfig());
        af = principalBS.queryDayAccList(pagination,
            securityInfo);
        String loanBankId = (String)pagination.getQueryCriterions().get("loanBankId");
        af.setLoanBankId(loanBankId);
      }
      request.setAttribute("principalTaAF", af);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_principaltc_show");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      HashMap m = new HashMap();
      pagination = new Pagination(0, 12, 1, "t1.flow_head_id", "ASC", m);
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
}
