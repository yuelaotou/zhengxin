package org.xpup.hafmis.sysloan.querystatistics.querystatistics.queryPayOffRecords.action;

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
import org.xpup.hafmis.sysloan.common.domain.entity.AssistantOrg;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.queryPayOffRecords.bsinterface.IQueryPayOffRecordsBS;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.queryPayOffRecords.form.QueryPayOffRecordsTaShowAF;
import org.xpup.security.common.domain.Userslogincollbank;

public class QueryPayOffRecordsTaShowAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.querystatistics.querystatistics.queryPayOffRecords.action.QueryPayOffRecordsTaShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    try {

      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);
      saveToken(request);
      IQueryPayOffRecordsBS queryPayOffRecordsBS = (IQueryPayOffRecordsBS) BSUtils
          .getBusinessService("queryPayOffRecordsBS", this, mapping
              .getModuleConfig());
      QueryPayOffRecordsTaShowAF queryPayOffRecordsTaShowAF = new QueryPayOffRecordsTaShowAF();

      queryPayOffRecordsTaShowAF = queryPayOffRecordsBS
          .queryBorrowerListByCriterions(pagination, securityInfo);
      String loanBankName = (String)pagination.getQueryCriterions().get("loanBankName");
      queryPayOffRecordsTaShowAF.setLoanBankName(loanBankName);
      // 求办事处
      List officeList = securityInfo.getOfficeList();
      List officeList1 = null;
      officeList1 = new ArrayList();
      OfficeDto officedto = null;
      Iterator itr1 = officeList.iterator();
      while (itr1.hasNext()) {
        officedto = (OfficeDto) itr1.next();
        officeList1.add(new org.apache.struts.util.LabelValueBean(officedto
            .getOfficeName(), officedto.getOfficeCode()));
      }
      request.getSession(true).setAttribute("officeList1", officeList1);

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
      Map houseTypeMap=BusiTools.listBusiProperty(BusiConst.PLHOUSETYPE);
      queryPayOffRecordsTaShowAF.setHouseTypeMap(houseTypeMap);
      request.getSession(true).setAttribute("loanBankNameList", loanBankNameList);
      request.setAttribute("queryPayOffRecordsTaShowAF", queryPayOffRecordsTaShowAF);
      queryPayOffRecordsTaShowAF.reset(mapping, request);
      request.getSession(true).setAttribute("celllist", queryPayOffRecordsTaShowAF.getListAll());
      request.getSession(true).setAttribute("cellqueryPayOffRecordsTaShowAF", queryPayOffRecordsTaShowAF);

    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return mapping.findForward("to_queryPayOffRecords_talist");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      Map m = new HashMap(0);

      pagination = new Pagination(0, 10, 1, "pl111.contract_id", "DESC", m);
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }

}
