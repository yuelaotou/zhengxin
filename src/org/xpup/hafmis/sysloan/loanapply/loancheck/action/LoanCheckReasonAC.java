package org.xpup.hafmis.sysloan.loanapply.loancheck.action;

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
import org.xpup.hafmis.sysloan.loanapply.loancheck.bsinterface.ILoanCheckBS;
import org.xpup.hafmis.sysloan.loanapply.loancheck.form.LoanCheckReasonAF;

/**
 * @author 王野 2007-09-25
 */
public class LoanCheckReasonAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    ActionMessages messages = null;
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      ILoanCheckBS loanCheckBS = (ILoanCheckBS) BSUtils.getBusinessService(
          "loanCheckBS", this, mapping.getModuleConfig());
      LoanCheckReasonAF loanCheckReasonAF = (LoanCheckReasonAF) form;
      String buttonMethod = loanCheckReasonAF.getButtonMethod();
      String reason = loanCheckReasonAF.getReason().trim();
      // 审核不通过业务操作
      if (buttonMethod.equals("checkNotPass")) {
        String[] temp_contractId=request.getParameter("contractId").split(",");
        loanCheckBS.updateContractSTCheckNotPassrowArray(temp_contractId, reason,securityInfo);
      }
      request.setAttribute("isFinished", "1");
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      saveErrors(request, messages);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return new ActionForward("/loanapply/loancheck/loancheck_reason.jsp");
  }
}
