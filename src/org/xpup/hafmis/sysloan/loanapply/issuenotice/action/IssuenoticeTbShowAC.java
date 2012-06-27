package org.xpup.hafmis.sysloan.loanapply.issuenotice.action;

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
import org.xpup.hafmis.sysloan.loanapply.issuenotice.bsinterface.IIssuenoticeBS;
import org.xpup.hafmis.sysloan.loanapply.issuenotice.dto.IssuenoticeTbDTO;
import org.xpup.hafmis.sysloan.loanapply.issuenotice.form.IssuenoticeTbAF;

import org.xpup.security.common.domain.Userslogincollbank;

public class IssuenoticeTbShowAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.loanapply.issuenotice.action.IssuenoticeTbShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    // TODO Auto-generated method stub
    try {
      IssuenoticeTbAF issuenoticeTbAF = new IssuenoticeTbAF();
      Pagination pagination = getPagination(IssuenoticeTbShowAC.PAGINATION_KEY,
          request);
      PaginationUtils.updatePagination(pagination, request);
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      List loanbankList1 = null;
      List loanbankList2 = null;
      String type="";//用来判断如何进入show的，在默认条件下查询的是contract_st=9的记录，在查询的时候是contract_st in(9、10、11、12、13)
      type=(String)request.getAttribute("type");   
      type=(String)request.getSession().getAttribute("find");
      String del = (String) request.getAttribute("delete");
      if ("1".equals(del)) {
        issuenoticeTbAF.setIsDelete("1");
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
      IIssuenoticeBS issuenoticeBS = (IIssuenoticeBS) BSUtils
          .getBusinessService("issuenoticeBS", this, mapping.getModuleConfig());
      List list = issuenoticeBS
          .findIssuenoticeTbList(pagination, loanbankList2,type);
      if (list.size() != 0) {
        IssuenoticeTbDTO issuenoticeTbDTO = (IssuenoticeTbDTO) list.get(0);
        issuenoticeTbAF.setLoanMoneySum(issuenoticeTbDTO.getLoanMoneySum());
        issuenoticeTbAF.setList(list);
      }
      issuenoticeTbAF.reset();
      if("1".equals(type)){
        issuenoticeTbAF.setType("1");
      }      
      request.setAttribute("issuenoticeTbAF", issuenoticeTbAF);
      request.setAttribute("loanbankList1", loanbankList1);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_issuenoticetb_show");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      HashMap m = new HashMap();
      pagination = new Pagination(0, 10, 1, "contractid", "DESC", m);
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
}
