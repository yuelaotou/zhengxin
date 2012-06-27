package org.xpup.hafmis.sysloan.loanapply.loandelete.action;

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
import org.xpup.hafmis.sysloan.loanapply.loandelete.bsinterface.ILoandeleteBS;
import org.xpup.hafmis.sysloan.loanapply.loandelete.form.LoandeleteAF;
import org.xpup.hafmis.sysloan.loancallback.loanerlogout.action.LoanerlogoutTbShowAC;
import org.xpup.hafmis.sysloan.loancallback.loanerlogout.bsinterface.ILoanerlogoutBS;
import org.xpup.hafmis.sysloan.loancallback.loanerlogout.form.LoanerlogoutTbAF;
import org.xpup.security.common.domain.Userslogincollbank;

/**
 * 贷款户删除
 * 
 * @author yqf 20081015
 */
public class LoandeleteShowAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.loanapply.loandelete.action.LoandeleteShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    // TODO Auto-generated method stub
    try {
      LoandeleteAF loandeleteAF = new LoandeleteAF();
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      List loanbankList1 = null;
      List loanbankList2 = null;
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

      ILoandeleteBS loandeleteBS = (ILoandeleteBS) BSUtils.getBusinessService(
          "loandeleteBS", this, mapping.getModuleConfig());
      List list = loandeleteBS
          .findLoanerlogoutTbList(pagination, loanbankList2);
      if (list != null) {
        loandeleteAF.setList(list);
      }
      loandeleteAF.reset();
      request.setAttribute("loandeleteAF", loandeleteAF);
      request.setAttribute("loanbankList1", loanbankList1);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_loandelete_show");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      HashMap m = new HashMap();
      pagination = new Pagination(0, 10, 1, " a.contract_id ", "DESC", m);
      request.getSession().setAttribute(paginationKey, pagination);
    }

    return pagination;
  }
}
