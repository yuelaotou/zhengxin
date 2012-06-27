package org.xpup.hafmis.sysloan.loanapply.loanlastsure.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loanapply.loanlastsure.bsinterface.ILoanLastSureBS;
import org.xpup.hafmis.sysloan.loanapply.loanlastsure.form.LoanLastSureShowAF;
import org.xpup.hafmis.sysloan.loanapply.loanvipcheck.bsinterface.ILoanVIPCheckBS;
import org.xpup.hafmis.sysloan.loanapply.loanvipcheck.form.LoanVIPCheckShowAF;
import org.xpup.security.common.domain.Userslogincollbank;

/**
 * @author 王野 2007-09-27
 */
public class LoanLastSureShowAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.loanapply.loanlastsure.action.LoanLastSureShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      List loanBankNameList = new ArrayList();
      if (securityInfo != null && !securityInfo.equals("")) {
        PaginationUtils.updatePagination(pagination, request);
        saveToken(request);
        ILoanLastSureBS loanLastSureBS = (ILoanLastSureBS) BSUtils
        .getBusinessService("loanLastSureBS", this, mapping.getModuleConfig());
        LoanLastSureShowAF loanLastSureShowAF = new LoanLastSureShowAF();
        // 办事处下拉
        List officeList = securityInfo.getOfficeList();
        List officeList1 = new ArrayList();
        OfficeDto officedto = null;
        Iterator itr1 = officeList.iterator();
        while (itr1.hasNext()) {
          officedto = (OfficeDto) itr1.next();
          officeList1.add(new org.apache.struts.util.LabelValueBean(officedto
              .getOfficeName(), officedto.getOfficeCode()));
        }
        request.getSession(true).setAttribute("officeList1", officeList1);
        // 放款银行下拉框
        List bangkList = securityInfo.getDkUserBankList();
        Userslogincollbank userslogincollbank = null;
        Iterator bank = bangkList.iterator();
        while (bank.hasNext()) {
          userslogincollbank = (Userslogincollbank) bank.next();
          loanBankNameList.add(new org.apache.struts.util.LabelValueBean(
              userslogincollbank.getCollBankName(), userslogincollbank
                  .getCollBankId().toString()));
        }
        request.getSession(true).setAttribute("loanBankNameList",
            loanBankNameList);
        loanLastSureShowAF = loanLastSureBS.queryBorrowerListByCriterions(
            pagination, securityInfo);
        // 购房类型下拉框
        Map houseTypeMap = BusiTools.listBusiProperty(BusiConst.PLHOUSETYPE);
        loanLastSureShowAF.setHouseTypeMap(houseTypeMap);
        // 合同状态下拉框
        Map contractStMap = BusiTools
            .listBusiProperty(BusiConst.PLCONTRACTSTATUS);
        loanLastSureShowAF.setContractStMap(contractStMap);
        request.setAttribute("loanLastSureShowAF", loanLastSureShowAF);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_loanLastSure_show");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "p110.contract_id", "DESC",
          new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
}
