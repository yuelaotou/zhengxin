package org.xpup.hafmis.sysloan.loanapply.issuenotice.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.LookupDispatchAction;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.domain.entity.Borrower;
import org.xpup.hafmis.sysloan.dataready.loanpara.bsinterface.ILoanDocNumDesignBS;
import org.xpup.hafmis.sysloan.loanapply.issuenotice.bsinterface.IIssuenoticeBS;
import org.xpup.hafmis.sysloan.loanapply.issuenotice.dto.IssuenoticeTaDTO;

public class IssuenoticeTcMaintainAC extends LookupDispatchAction {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.loanapply.issuenotice.action.IssuenoticeTcShowAC";

  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.sure", "sure");
    map.put("button.auditing.quash", "quash");
    return map;
  }

  public ActionForward sure(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      IdAF idAF = (IdAF) form;
      String[] settNum = null;
      String rowArray = request.getParameter("rowArrayHid");
      if (rowArray == null) {
        throw new BusinessException("请选择要划款的合同编号！");
      } else {
        settNum = rowArray.split("-");
      }
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      IIssuenoticeBS issuenoticeBS = (IIssuenoticeBS) BSUtils
          .getBusinessService("issuenoticeBS", this, mapping.getModuleConfig());
      // for (int i = 0; i < rowArray.length; i++) {
      issuenoticeBS.updateIssuenoticeBorrowerAcc(settNum, securityInfo);
      // }
      rowArray = null;
      settNum = null;
    } catch (BusinessException e) {
      e.printStackTrace();
      ActionMessages messages = null;
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
          "存在已确认过的借款合同！！", false));
      saveErrors(request, messages);
      return mapping.findForward("issuenoticetc_show");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("issuenoticetc_show");
  }

  public ActionForward quash(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      IdAF idAF = (IdAF) form;
      String[] settNum = null;
      String rowArray = request.getParameter("rowArrayHid");
      if (rowArray == null) {
        throw new BusinessException("请选择要划款的合同编号！");
      } else {
        settNum = rowArray.split("-");
      }
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      IIssuenoticeBS issuenoticeBS = (IIssuenoticeBS) BSUtils
          .getBusinessService("issuenoticeBS", this, mapping.getModuleConfig());
      // for (int i = 0; i < rowArray.length; i++) {
      issuenoticeBS.updateIssuenoticeBorrowerAcc_3(settNum, securityInfo);
      // }
    } catch (BusinessException e) {
      e.printStackTrace();
      ActionMessages messages = null;
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
          "存在未确认过或出纳打印过的借款合同！", false));
      saveErrors(request, messages);
      return mapping.findForward("issuenoticetc_show");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("issuenoticetc_show");
  }

}
