package org.xpup.hafmis.sysloan.loanapply.loanfirstcheck.action;

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
import org.xpup.hafmis.sysloan.loanapply.loanfirstcheck.bsinterface.ILoanFirstCheckBS;

/**
 * @author wshuang 2008-012-26
 */
public class LoanFirstCheckMaintainAC extends LookupDispatchAction {

  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.firstcheck.pass", "pass");
    map.put("button.auditing", "notpass");
    map.put("button.firstcheck.quash", "quash");
    return map;
  }

  /**
   * 初审通过
   * 
   * @author wshuang
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
      ILoanFirstCheckBS loanFirstCheckBS = (ILoanFirstCheckBS) BSUtils
          .getBusinessService("loanFirstCheckBS", this, mapping
              .getModuleConfig());
      loanFirstCheckBS.updateContractStFirCheckPass(rowArray, securityInfo);
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      saveErrors(request, messages);
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("loanFirstCheckShowAC");
  }
  /**
   * 初审不通过
   * @author wshuang
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
      loanCheckBS.updateContractSTCheckNotPassrowArray(rowArray, null, securityInfo);
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      saveErrors(request, messages);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("loanFirstCheckShowAC");
  }
  /**
   * 撤消初审
   * @author wshuang
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
      ILoanFirstCheckBS loanFirstCheckBS = (ILoanFirstCheckBS) BSUtils
          .getBusinessService("loanFirstCheckBS", this, mapping
              .getModuleConfig());
      loanFirstCheckBS.updateContractStFirCheckQuash(rowArray, securityInfo);
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      saveErrors(request, messages);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("loanFirstCheckShowAC");
  }

}
