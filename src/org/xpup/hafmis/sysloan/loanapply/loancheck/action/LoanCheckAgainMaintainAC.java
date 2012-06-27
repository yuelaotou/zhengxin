package org.xpup.hafmis.sysloan.loanapply.loancheck.action;

import java.util.HashMap;
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
import org.xpup.hafmis.sysloan.loanapply.loancheck.bsinterface.ILoanCheckBS;

/**
 * @author wshuang 2008-12-29
 */
public class LoanCheckAgainMaintainAC extends LookupDispatchAction {

  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.auditingagain.pass", "pass");
    map.put("button.auditing", "notpass");
    map.put("button.auditingagain.quash", "quash");
    return map;
  }

  /**
   * 再次审核通过
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   */
  public ActionForward pass(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    ActionMessages messages = null;
    IdAF idAF = (IdAF) form;
    try {
      String[] rowArray = idAF.getRowArray();
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      ILoanCheckBS loanCheckBS = (ILoanCheckBS) BSUtils.getBusinessService(
          "loanCheckBS", this, mapping.getModuleConfig());
      request.setAttribute("url", "again");
      loanCheckBS.updateContractStChkAgainPass(rowArray, securityInfo);
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      saveErrors(request, messages);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("loancheckShowAC");
  }
  /**
   * 审核不通过
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   */
  public ActionForward notpass(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    ActionMessages messages = null;
    IdAF idAF = (IdAF) form;
    try {
      String[] rowArray = idAF.getRowArray();
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      ILoanCheckBS loanCheckBS = (ILoanCheckBS) BSUtils.getBusinessService(
          "loanCheckBS", this, mapping.getModuleConfig());
      request.setAttribute("url", "again");
      loanCheckBS.updateContractSTCheckNotPassrowArray(rowArray, null, securityInfo);
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      saveErrors(request, messages);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("loancheckShowAC");
  }
  /**
   * 撤消再次审核
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   */
  public ActionForward quash(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    ActionMessages messages = null;
    IdAF idAF = (IdAF) form;
    try {
      String[] rowArray = idAF.getRowArray();
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      ILoanCheckBS loanCheckBS = (ILoanCheckBS) BSUtils.getBusinessService(
          "loanCheckBS", this, mapping.getModuleConfig());
      request.setAttribute("url", "again");
      loanCheckBS.updateContractStChkAgainQuash(rowArray, securityInfo);
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      saveErrors(request, messages);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("loancheckShowAC");
  }
}
