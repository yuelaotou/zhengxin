package org.xpup.hafmis.sysloan.accounthandle.clearaccount.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysloan.accounthandle.clearaccount.form.ClearAccountBalanceAF;
import org.xpup.hafmis.sysloan.accounthandle.clearaccount.form.ClearaccountAF;

/**
 * @author jj 2007-11-01
 */
public class ClearaccountTbMXFindAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {    
    ClearaccountAF f = (ClearaccountAF)form;
    HashMap criterions = makeCriterionsMap(f);
    Pagination pagination = new Pagination(0, 10, 1,
        null, "", criterions);
    String paginationKey = ClearaccountTbMXShowAC.PAGINATION_KEY;
    request.getSession().setAttribute(paginationKey, pagination);
    f.reset(mapping, request);
    return mapping.findForward(getForword());
  }
  protected String getForword() {
    return "clearaccountTbMXShowAC";
  }
  protected HashMap makeCriterionsMap(ClearaccountAF form) {
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

    String borrowerName = form.getBorrowerName().trim();// 借款人姓名
    if (borrowerName != null && borrowerName.length() > 0) {
      m.put("borrowerName", borrowerName);
    }

    String makePerson = form.getMakePerson().trim();// //制单人
    if (makePerson != null && makePerson.length() > 0) {
      m.put("makePerson", makePerson);
    }

    String bizType = form.getBizType().trim();// 业务类型
    if (bizType != null && bizType.length() > 0) {
      m.put("bizType", bizType);
    }

    String loanBankName = form.getLoanBankName().trim();// 放款银行
    if (loanBankName != null && loanBankName.length() > 0) {
      m.put("loanBankName", loanBankName);
    }

    return m;
  }


}
