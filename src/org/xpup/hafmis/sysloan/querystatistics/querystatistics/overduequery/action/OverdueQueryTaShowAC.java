package org.xpup.hafmis.sysloan.querystatistics.querystatistics.overduequery.action;

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
import org.xpup.hafmis.sysfinance.accounthandle.credencefillin.bsinterface.ICredenceFillinBS;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.overduequery.bsinterface.IOverdueQueryBS;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.overduequery.form.OverdueQueryTaAF;
import org.xpup.security.common.domain.Userslogincollbank;
/**
 * 
 * @author wangshuang
 * 2009-04-03
 */
public class OverdueQueryTaShowAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.querystatistics.querystatistics.overduequery.action.OverdueQueryTaShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    OverdueQueryTaAF af = new OverdueQueryTaAF();
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      // 求办事处
      List temp_officeList = securityInfo.getOfficeList();
      List li = new ArrayList();
      for (int i = 0; i < temp_officeList.size(); i++) {
        OfficeDto officedto = null;
        officedto = (OfficeDto) temp_officeList.get(i);
        li.add(new org.apache.struts.util.LabelValueBean(officedto
            .getOfficeName(), officedto.getOfficeCode()));
      }
      request.getSession(true).setAttribute("officeList", li);

      // 放款银行下拉框
      List loanBankNameList = new ArrayList();
      List bangkList = securityInfo.getCollBankList();
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
      Pagination pagination = (Pagination) request.getSession().getAttribute(
          PAGINATION_KEY);
      if (pagination == null) {
        pagination = getPagination(PAGINATION_KEY, request);
      } else {
        PaginationUtils.updatePagination(pagination, request);
        IOverdueQueryBS overdueQueryBS = (IOverdueQueryBS) BSUtils
        .getBusinessService("overdueQueryBS", this, mapping
            .getModuleConfig());
        af = overdueQueryBS.queryOverdueList(pagination, securityInfo);
        String bankId = (String)pagination.getQueryCriterions().get("bankId");
        af.setBankId(bankId);
      }
      Map yesNoMap = BusiTools.listBusiProperty(BusiConst.YesNo);
      af.setYesNoMap(yesNoMap);
      request.setAttribute("overdueQueryTaAF", af);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_overduequeryta_show");
  }
  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      HashMap m = new HashMap();
      pagination = new Pagination(0, 10, 1, "p111.contract_id", "ASC", m);
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
}
