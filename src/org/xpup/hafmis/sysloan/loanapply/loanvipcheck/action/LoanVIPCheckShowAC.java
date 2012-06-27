package org.xpup.hafmis.sysloan.loanapply.loanvipcheck.action;

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
import org.xpup.hafmis.sysloan.loanapply.loanvipcheck.bsinterface.ILoanVIPCheckBS;
import org.xpup.hafmis.sysloan.loanapply.loanvipcheck.form.LoanVIPCheckShowAF;
import org.xpup.security.common.domain.Userslogincollbank;

/**
 * @author 王野 2007-09-27
 */
public class LoanVIPCheckShowAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.loanapply.loanvipcheck.action.LoanVIPCheckShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    String url = null;
    try {
      url = (String) request.getAttribute("url");
      if (url != null) {
        request.getSession().setAttribute("url", url);
      } else {
        url = (String) request.getSession().getAttribute("url");
      }
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      List loanBankNameList = new ArrayList();
      if (securityInfo != null && !securityInfo.equals("")) {
        PaginationUtils.updatePagination(pagination, request);
        saveToken(request);
        ILoanVIPCheckBS loanVIPCheckBS = (ILoanVIPCheckBS) BSUtils
            .getBusinessService("loanVIPCheckBS", this, mapping
                .getModuleConfig());
        LoanVIPCheckShowAF loanVIPCheckShowAF = new LoanVIPCheckShowAF();
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
        String contractSt = "";
        // search变量如果有值则表示点击了查询按钮
        String search = (String) pagination.getQueryCriterions().get("search");
        if (url != null) {
          if (url.equals("again")) {
            contractSt = "17";
          } else if (url.equals("query")) {
            contractSt = "none";// 合同查询默认进来的,目的是什么也查不到
            pagination.getQueryCriterions().put("findType", "1");
          }
        } else {
          contractSt = "3";
        }
        if(search==null)
          pagination.getQueryCriterions().put("contractStFind", contractSt);
        loanVIPCheckShowAF = loanVIPCheckBS.queryBorrowerListByCriterions(
            pagination, securityInfo);
        request.getSession(true).setAttribute("printList",
            loanVIPCheckShowAF.getAllList());
        // 购房类型下拉框
        Map houseTypeMap = BusiTools.listBusiProperty(BusiConst.PLHOUSETYPE);
        loanVIPCheckShowAF.setHouseTypeMap(houseTypeMap);
        // 合同状态下拉框
        Map contractStMap = BusiTools
            .listBusiProperty(BusiConst.PLCONTRACTSTATUS);
        loanVIPCheckShowAF.setContractStMap(contractStMap);
        request.setAttribute("loanvipcheckShowAF", loanVIPCheckShowAF);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (url != null) {
      if(url.equals("again"))
        return mapping.findForward("to_loanvipcheckagain_show");
      else
        return mapping.findForward("to_loanquery_show");
    }
    return mapping.findForward("to_loanvipcheck_show");
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
