package org.xpup.hafmis.sysloan.querystatistics.loanbackbyfund.loanxieyi.action;

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
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.querystatistics.loanbackbyfund.loanxieyi.bsinterface.ILoanXieYiBS;
import org.xpup.hafmis.sysloan.querystatistics.loanbackbyfund.loanxieyi.form.LoanXieYiAF;
import org.xpup.security.common.domain.Userslogincollbank;

/**
 * @author 王野 2007-10-15
 */
public class LoanXieYiShowAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.loanbusiflowquery.action.LoanXieYiShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);
      List officeList = null;
      if (securityInfo != null && !securityInfo.equals("")) {
        ILoanXieYiBS loanXieYiBS = (ILoanXieYiBS) BSUtils
            .getBusinessService("loanXieYiBS", this, mapping
                .getModuleConfig());
        LoanXieYiAF loanXieYiAF = new LoanXieYiAF();
        String type = (String) pagination.getQueryCriterions().get("type");
        if (type == null) {
          type = "1";
        }
        if (!type.equals("0")) {
          loanXieYiAF = loanXieYiBS
              .queryLoanBusiFlowQueryListByCriterions(pagination, securityInfo);
          String loanBank = (String)pagination.getQueryCriterions().get("loanBank");
          loanXieYiAF.setLoanBankName(loanBank);
        }
        
        // 取出用户权限办事处,显示在下拉菜单中
        List temp_officeList = securityInfo.getAllOfficeList();
        officeList = new ArrayList();
        OfficeDto officedto = null;
        Iterator it = temp_officeList.iterator();
        while (it.hasNext()) {
          officedto = (OfficeDto) it.next();
          officeList.add(new org.apache.struts.util.LabelValueBean(officedto
              .getOfficeName(), officedto.getOfficeCode()));
        }
    
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
       
        request.setAttribute("officeList", officeList);
        request.setAttribute("loanBankNameList", loanBankNameList);
        request.getSession().setAttribute("printLoanBusiFlowQueryList", loanXieYiAF.getPrintList());
        request.setAttribute("loanXieYiAF", loanXieYiAF);
        loanXieYiAF.reset(mapping, request);
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return mapping.findForward("to_loanXieYi_show");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      HashMap m = new HashMap();
      m.put("type", "0");
      pagination = new Pagination(0, 10, 1, "p110.contract_id", "DESC", m);
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }

}
