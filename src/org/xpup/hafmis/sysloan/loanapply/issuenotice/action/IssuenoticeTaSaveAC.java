package org.xpup.hafmis.sysloan.loanapply.issuenotice.action;

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

import org.xpup.hafmis.sysloan.loanapply.issuenotice.bsinterface.IIssuenoticeBS;
import org.xpup.hafmis.sysloan.loanapply.issuenotice.form.IssuenoticeTaAF;

public class IssuenoticeTaSaveAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = new ActionMessages();
    IssuenoticeTaAF issuenoticeTaAF = (IssuenoticeTaAF) form;
    try {
      IIssuenoticeBS issuenoticeBS = (IIssuenoticeBS) BSUtils
          .getBusinessService("issuenoticeBS", this, mapping.getModuleConfig());
      String contractId = (String) request.getParameter("contractId");
      if(!issuenoticeTaAF.getType().equals("3")){
        issuenoticeBS.findIssuenoticeAvailable(contractId);
      }
      issuenoticeTaAF.setIsPrint("1");
    } catch (BusinessException be) {
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(be
          .getLocalizedMessage(), false));
      saveErrors(request, messages);
      return mapping.findForward("issuenotice_show");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("issuenotice_show");
  }
}
