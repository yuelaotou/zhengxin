package org.xpup.hafmis.sysloan.querystatistics.loanbackbyfund.loanbackbyfund.action;

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
import org.xpup.hafmis.sysloan.querystatistics.loanbackbyfund.loanbackbyfund.bsinterface.ILoanBackByFundBS;
import org.xpup.hafmis.sysloan.querystatistics.loanbackbyfund.loanbackbyfund.form.LoanBackByFundAF;
import org.xpup.security.common.domain.Userslogincollbank;

/**
 * @author ั๎นโ 20090303
 */
public class LoanBackByFundShowAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.querystatistics.loanbackbyfund.loanbackbyfund.action.LoanBackByFundShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);
      
      List loanBankNameList = new ArrayList();
      LoanBackByFundAF loanBackByFundAF = new LoanBackByFundAF();
      List bangkList = securityInfo.getCollBankList();
      Userslogincollbank userslogincollbank = null;
      Iterator bank = bangkList.iterator();
      while (bank.hasNext()) {
        userslogincollbank = (Userslogincollbank) bank.next();
        loanBankNameList.add(new org.apache.struts.util.LabelValueBean(
            userslogincollbank.getCollBankName(), userslogincollbank
                .getCollBankId().toString()));
      }
      
      ILoanBackByFundBS loanBackByFundBS = (ILoanBackByFundBS) BSUtils
          .getBusinessService("loanBackByFundBS", this, mapping
              .getModuleConfig());
      String nosearch = (String)request.getAttribute("nosearch");
      if(nosearch!=null && nosearch.equals("nosearch")){
        
      }else{
        loanBackByFundAF = loanBackByFundBS.queryLoanBackByFund(pagination,
            securityInfo);
        String loanBank = (String)pagination.getQueryCriterions().get("loanBank");
        loanBackByFundAF.setLoanBankName(loanBank);
      }
      request.setAttribute("loanBankNameList", loanBankNameList);
      request.getSession().setAttribute("printLoanBusiFlowQueryList",
          loanBackByFundAF.getPrintList());
      request.setAttribute("loanBackByFundAF", loanBackByFundAF);
      loanBackByFundAF.reset(mapping, request);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return mapping.findForward("loanbackbyfund");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      HashMap m = new HashMap();
      pagination = new Pagination(0, 10, 1, "p1.contract_id", "DESC", m);
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }

}
