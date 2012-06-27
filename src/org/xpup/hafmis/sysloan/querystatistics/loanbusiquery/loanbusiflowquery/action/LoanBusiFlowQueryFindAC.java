package org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.loanbusiflowquery.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.loanbusiflowquery.form.LoanBusiFlowQueryAF;

/**
 * @author 王野 2007-10-15
 */
public class LoanBusiFlowQueryFindAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try {
      LoanBusiFlowQueryAF loanBusiFlowQueryAF = (LoanBusiFlowQueryAF) form;
      HashMap criterions = makeCriterionsMap(loanBusiFlowQueryAF);
      Pagination pagination = new Pagination(0, 10, 1, "bizDate", "ASC",
          criterions);
      String paginationKey = getPaginationKey();
      request.getSession().setAttribute(paginationKey, pagination);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("loanBusiFlowQueryShowAC");
  }

  protected HashMap makeCriterionsMap(LoanBusiFlowQueryAF form) {
    HashMap m = new HashMap();

    String docNum = form.getDocNum().trim();// 凭证编号
    if (docNum != null && !"".equals(docNum)) {
      m.put("docNum", docNum);
    }

    String contractId = form.getContractId().trim();// 合同编号
    if (contractId != null && !"".equals(contractId)) {
      m.put("contractId", contractId);
    }

    String loanKouAcc = form.getLoanKouAcc().trim();// 贷款账号
    if (loanKouAcc != null && !"".equals(loanKouAcc)) {
      m.put("loanKouAcc", loanKouAcc);
    }

    String borrowerName = form.getBorrowerName()
        .trim();// 借款人姓名
    if (borrowerName != null && borrowerName.length() > 0) {
      m.put("borrowerName", borrowerName);
    }
    
    String isGjjLoanback = form.getIsGjjLoanback();
    if (isGjjLoanback != null && !"".equals(isGjjLoanback)) {
      m.put("isGjjLoanback", isGjjLoanback);
    }
    
    String makePerson = form.getMakePerson().trim();// 制单人
    if (makePerson != null && makePerson.length() > 0) {
      m.put("makePerson", makePerson);
    }

    String bizType = form.getBizType().trim();// 业务类型
    if (bizType != null && bizType.length() > 0) {
      m.put("bizType", bizType);
    }

    String bizSt = form.getBizSt().trim();// 业务状态
    if (bizSt != null && bizSt.length() > 0) {
      m.put("bizSt", bizSt);
    }

    String loanBankName = form.getLoanBankName()
        .trim();// 放款银行
    if (loanBankName != null && loanBankName.length() > 0) {
      m.put("loanBankName", loanBankName);
    }

    String beginBizDate = form.getBeginBizDate()
        .trim();// 起始办理日期
    if (beginBizDate != null && beginBizDate.length() > 0) {
      m.put("beginBizDate", beginBizDate);
    }

    String endBizDate = form.getEndBizDate().trim();// 终止办理日期;
    if (endBizDate != null && endBizDate.length() > 0) {
      m.put("endBizDate", endBizDate);
    }
    return m;
  }

  protected String getPaginationKey() {
    return LoanBusiFlowQueryShowAC.PAGINATION_KEY;
  }
}