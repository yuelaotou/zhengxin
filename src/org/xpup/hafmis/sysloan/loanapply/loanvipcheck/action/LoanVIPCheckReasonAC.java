package org.xpup.hafmis.sysloan.loanapply.loanvipcheck.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loanapply.loanvipcheck.bsinterface.ILoanVIPCheckBS;
import org.xpup.hafmis.sysloan.loanapply.loanvipcheck.form.LoanVIPCheckReasonAF;

/**
 * @author 王野 2007-09-27
 */
public class LoanVIPCheckReasonAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    ActionMessages messages = null;
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      ILoanVIPCheckBS loanVIPCheckBS = (ILoanVIPCheckBS) BSUtils
          .getBusinessService("loanVIPCheckBS", this, mapping.getModuleConfig());
      LoanVIPCheckReasonAF loanVIPCheckReasonAF = (LoanVIPCheckReasonAF) form;
      String[] contractId = loanVIPCheckReasonAF.getContractId().split(",");
      String buttonMethod = loanVIPCheckReasonAF.getButtonMethod();
      String reason = loanVIPCheckReasonAF.getReason().trim();
      // 审批不通过业务操作
      if (buttonMethod.equals("approvalNotPass")) {
        loanVIPCheckBS.updateContractSTApprovalNotPass(contractId, reason,
            securityInfo);
      }
      request.setAttribute("isFinished", "1");
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      saveErrors(request.getSession(), messages);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return new ActionForward("/loanapply/loanvipcheck/loanvipcheck_reason.jsp");
  }

}
