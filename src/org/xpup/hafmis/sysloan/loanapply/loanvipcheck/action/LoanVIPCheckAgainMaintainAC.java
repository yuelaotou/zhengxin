package org.xpup.hafmis.sysloan.loanapply.loanvipcheck.action;

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
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loanapply.loanvipcheck.bsinterface.ILoanVIPCheckBS;

/**
 * @author wshuang 2008-12-29
 */
public class LoanVIPCheckAgainMaintainAC extends LookupDispatchAction {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.loanapply.loanvipcheck.action.LoanVIPCheckMaintainAC";

  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.approvalagain.pass", "pass");
    map.put("button.approval", "notpass");
    map.put("button.approvalagain.quash", "quash");
    map.put("button.print", "print");
    return map;
  }

  public ActionForward pass(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    ActionMessages messages = null;
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      ILoanVIPCheckBS loanVIPCheckBS = (ILoanVIPCheckBS) BSUtils
          .getBusinessService("loanVIPCheckBS", this, mapping.getModuleConfig());
      IdAF idAF = (IdAF) form;
      String[] rowArray = idAF.getRowArray();
      loanVIPCheckBS.updateContractStApprovalPassAgain(rowArray, securityInfo);
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      saveErrors(request, messages);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("loanvipcheckShowAC");
  }

  public ActionForward notpass(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    ActionMessages messages = null;
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      ILoanVIPCheckBS loanVIPCheckBS = (ILoanVIPCheckBS) BSUtils
          .getBusinessService("loanVIPCheckBS", this, mapping.getModuleConfig());
      IdAF idAF = (IdAF) form;
      String[] rowArray = idAF.getRowArray();
      loanVIPCheckBS.updateContractSTApprovalNotPass(rowArray, null, securityInfo);
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      saveErrors(request, messages);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("loanvipcheckShowAC");
  }
  
  public ActionForward quash(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    ActionMessages messages = null;
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      ILoanVIPCheckBS loanVIPCheckBS = (ILoanVIPCheckBS) BSUtils
          .getBusinessService("loanVIPCheckBS", this, mapping.getModuleConfig());
      IdAF idAF = (IdAF) form;
      String[] rowArray = idAF.getRowArray();
      loanVIPCheckBS.updateContractStApprovalQuashAgain(rowArray, securityInfo);
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      saveErrors(request, messages);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("loanvipcheckShowAC");
  }
  
  public ActionForward print(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try {
      Pagination pagination = (Pagination) request.getSession().getAttribute(LoanVIPCheckShowAC.PAGINATION_KEY);
      String officeCode = (String )pagination.getQueryCriterions().get("office");
      String bankName = (String )pagination.getQueryCriterions().get("bankName");
      String str = "";
      if(bankName!=null){
        str = "银行:"+bankName;
      } else if(officeCode!=null){
        str = "部门:"+officeCode;
      } else {
        str = "部门:全部";
      }
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
      request.setAttribute("str", str);
      request.setAttribute("bizDate", securityInfo.getUserInfo().getPlbizDate());
    } catch (Exception e) {
      e.printStackTrace();
    } 
    return mapping.findForward("to_loanvipcheck_cell");
  }
}
