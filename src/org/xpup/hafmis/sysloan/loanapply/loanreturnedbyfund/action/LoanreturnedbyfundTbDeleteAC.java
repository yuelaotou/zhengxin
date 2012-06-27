package org.xpup.hafmis.sysloan.loanapply.loanreturnedbyfund.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.sysloan.common.domain.entity.AssistantBorrower;
import org.xpup.hafmis.sysloan.common.domain.entity.Borrower;
import org.xpup.hafmis.sysloan.common.domain.entity.BorrowerAcc;
import org.xpup.hafmis.sysloan.common.domain.entity.BorrowerLoanInfo;
import org.xpup.hafmis.sysloan.loanapply.issuenotice.bsinterface.IIssuenoticeBS;
import org.xpup.hafmis.sysloan.loanapply.issuenotice.form.IssuenoticeTaAF;
import org.xpup.hafmis.sysloan.loanapply.loanreturnedbyfund.bsinterface.ILoanreturnedbyfundBS;
import org.xpup.hafmis.sysloan.loanapply.loanreturnedbyfund.form.LoanreturnedbyfundTaAF;

public class LoanreturnedbyfundTbDeleteAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception, BusinessException {
    String contractId = (String) request.getParameter("contractId");
    ActionMessages messages = null;
    try {

      ILoanreturnedbyfundBS loanreturnedbyfundBS = (ILoanreturnedbyfundBS) BSUtils
          .getBusinessService("loanreturnedbyfundBS", this, mapping
              .getModuleConfig());
      loanreturnedbyfundBS.deleteLoanreturnedbyfundTbInfo(contractId, "");
    } catch (BusinessException e) {
      e.printStackTrace();
      request.setAttribute("error", e.getMessage());
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(e
          .getMessage(), false));
      saveErrors(request, messages);
      return mapping.findForward("to_showloanreturnedbyfundTb");
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return mapping.findForward("to_showloanreturnedbyfundTb");
  }
}
