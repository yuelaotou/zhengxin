package org.xpup.hafmis.syscollection.querystatistics.collbyfund.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.syscollection.querystatistics.collbyfund.form.CollByFundAF;

public class CollByFundFindAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try {
      CollByFundAF collByFundAF = (CollByFundAF) form;
      HashMap criterions = makeCriterionsMap(collByFundAF);
      Pagination pagination = new Pagination(0, 10, 1, "a001.id",
          "ASC", criterions);
      request.getSession().setAttribute(CollByFundShowAC.PAGINATION_KEY,
          pagination);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("collByFundShowAC");
  }

  protected HashMap makeCriterionsMap(CollByFundAF form) {
    HashMap m = new HashMap();
    
    String officeCode = form.getOfficeCode();
    if (officeCode != null && !"".equals(officeCode.trim())) {
      m.put("officeCode", officeCode.trim());
    }
    String collBankId = form.getCollBankId();
    if (collBankId != null && !"".equals(collBankId.trim())) {
      m.put("collBankId", collBankId.trim());
    }
    String contractId = form.getContractId();
    if (contractId != null && !"".equals(contractId.trim())) {
      m.put("contractId", contractId.trim());
    }
    String empName = form.getEmpName();
    if (empName != null && !"".equals(empName.trim())) {
      m.put("empName", empName.trim());
    }
    String empId = form.getEmpId();
    if (empId != null && !"".equals(empId.trim())) {
      m.put("empId", empId.trim());
    }
    String orgName = form.getOrgName();
    if (orgName != null && !"".equals(orgName.trim())) {
      m.put("orgName", orgName.trim());
    }
    String orgId = form.getOrgId();
    if (orgId != null && !"".equals(orgId.trim())) {
      m.put("orgId", orgId.trim());
    }
    String cardNum = form.getCardNum();
    if (cardNum != null && !"".equals(cardNum.trim())) {
      m.put("orgId", cardNum.trim());
    }
    String begDate = form.getBegDate();
    if (begDate != null && !"".equals(begDate.trim())) {
      m.put("begDate", begDate.trim());
    }
    String endDate = form.getEndDate();
    if (endDate != null && !"".equals(endDate.trim())) {
      m.put("endDate", endDate.trim());
    }
    String batchNum = form.getBatchNum();
    if (batchNum != null && !"".equals(batchNum.trim())) {
      m.put("batchNum", batchNum.trim());
    }
    return m;
  }

}