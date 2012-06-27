package org.xpup.hafmis.sysloan.querystatistics.querystatistics.queryPayOffRecords.action;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.queryPayOffRecords.form.QueryPayOffRecordsTaShowAF;

public class QueryPayOffRecordsTaFindAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    String findType = "find";
    QueryPayOffRecordsTaShowAF queryPayOffRecordsTaShowAF = (QueryPayOffRecordsTaShowAF) form;
    HashMap criterions = makeCriterionsMap(queryPayOffRecordsTaShowAF, findType);
    Pagination pagination = new Pagination(0, 10, 1, "pl111.contract_id",
        "DESC", criterions);
    String paginationKey = getPaginationKey();
    request.getSession().setAttribute(paginationKey, pagination);
    return mapping.findForward("queryPayOffRecordsTaShowAC");
  }

  protected HashMap makeCriterionsMap(QueryPayOffRecordsTaShowAF form, String findType) {
    HashMap m = new HashMap();
    String contractId = form.getContractId();
    if (contractId != null && !"".equals(contractId)) {
      m.put("contractId", contractId.trim());
    }
    String borrowerName = form.getBorrowerName();
    if (borrowerName != null && borrowerName.length() > 0) {
      m.put("borrowerName", borrowerName.trim());
    }
    String cardNum = form.getCardNum();
    if (cardNum != null && cardNum.length() > 0) {
      m.put("cardNum", cardNum.trim());
    }
 
    String loanBankName = form.getLoanBankName();
    if (loanBankName != null && loanBankName.length() > 0) {
      m.put("loanBankName", loanBankName.trim());
    }
    String office = form.getOffice();
    if (office != null && office.length() > 0) {
      m.put("office", office.trim());
    }
    String loanStartDate = form.getLoanStartDate();
    if (loanStartDate != null && loanStartDate.length() > 0) {
      m.put("loanStartDate", loanStartDate.trim());
    }
    String loanEndDate = form.getLoanEndDate();
    if (loanEndDate != null && loanEndDate.length() > 0) {
      m.put("loanEndDate", loanEndDate.trim());
    }
    String loanPayOffDate = form.getLoanPayOffDate(); 
    if (loanPayOffDate != null && loanPayOffDate.length() > 0) {
      m.put("loanPayOffDate", loanPayOffDate.trim());
    }
    
    String loanPayOffEndDate = form.getLoanPayOffEndDate();
    if (loanPayOffEndDate != null && loanPayOffEndDate.length() > 0) {
      m.put("loanPayOffEndDate", loanPayOffEndDate.trim());
    }
    String houseType = form.getHouseType();
    if (houseType != null && houseType.length() > 0) {
      m.put("houseType_off", houseType.trim());
    }
    m.put("findType", findType);
    return m;
  }
  protected String getPaginationKey() {
    return QueryPayOffRecordsTaShowAC.PAGINATION_KEY;
  }
}