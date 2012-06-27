package org.xpup.hafmis.sysloan.loanapply.issuenotice.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loanapply.issuenotice.bsinterface.IIssuenoticeBS;
import org.xpup.hafmis.sysloan.loanapply.issuenotice.form.IssuenoticeTbAF;

public class IissuenoticeTbDeleteAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    IssuenoticeTbAF issuenoticeTbAF = (IssuenoticeTbAF) form;
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      request.setAttribute("issuenoticeTbAF", issuenoticeTbAF);
      IIssuenoticeBS issuenoticeBS = (IIssuenoticeBS) BSUtils
          .getBusinessService("issuenoticeBS", this, mapping.getModuleConfig());
      String rowArray = (String) request.getSession().getAttribute(
          "rowArray");
      issuenoticeBS.deleteIssuenoticeTb(rowArray, securityInfo);
      request.getSession().removeAttribute("rowArray");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("issuenoticetb_show");
  }
}
