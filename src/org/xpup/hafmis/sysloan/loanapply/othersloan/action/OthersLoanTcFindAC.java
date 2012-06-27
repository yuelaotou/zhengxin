package org.xpup.hafmis.sysloan.loanapply.othersloan.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysloan.loanapply.othersloan.form.OthersLoanTbShowAF;

public class OthersLoanTcFindAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    String findType = "find";
    OthersLoanTbShowAF othersLoanTbShowAF = (OthersLoanTbShowAF) form;
    HashMap criterions = makeCriterionsMap(othersLoanTbShowAF, findType);
    Pagination pagination = new Pagination(0, 10, 1, "a.contract_id",
        "DESC", criterions);
    String paginationKey = getPaginationKey();
    request.getSession().setAttribute(paginationKey, pagination);
    return mapping.findForward("to_othersLoanTc_show");
  }

  protected HashMap makeCriterionsMap(OthersLoanTbShowAF form, String findType) {
    HashMap m = new HashMap();
    String name = form.getName();
    if (name != null && !"".equals(name)) {
      m.put("name", name.trim());
    }
    String empId = form.getEmpId();
    if (empId != null && empId.length() > 0) {
      m.put("empId", empId.trim());
    }
    String cardNum = form.getCardNum();
    if (cardNum != null && cardNum.length() > 0) {
      m.put("cardNum", cardNum.trim());
    }
    String office = form.getOffice();
    if (office != null && office.length() > 0) {
      m.put("office", office.trim());
    }
    String orgName = form.getOrgName();
    if (orgName != null && orgName.length() > 0) {
      m.put("orgName", orgName.trim());
    }
    String orgId = form.getOrgId();
    if (orgId != null && orgId.length() > 0) {
      m.put("orgId", orgId.trim());
    }
    String beginBizDate = form.getBinDate();// 起始操作时间
    if (beginBizDate != null && beginBizDate.length() > 0) {
      m.put("beginBizDate", beginBizDate.trim());
    }
    String endBizDate = form.getEndDate();// 终止操作时间
    if (endBizDate != null && endBizDate.length() > 0) {
      m.put("endBizDate", endBizDate.trim());
    }
    return m;
  }

  protected String getPaginationKey() {
    return OthersLoanTcShowAC.PAGINATION_KEY;
  }
}