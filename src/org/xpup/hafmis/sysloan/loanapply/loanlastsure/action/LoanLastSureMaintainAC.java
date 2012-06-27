package org.xpup.hafmis.sysloan.loanapply.loanlastsure.action;

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
import org.xpup.hafmis.sysloan.loanapply.loanlastsure.bsinterface.ILoanLastSureBS;
import org.xpup.hafmis.sysloan.loanapply.loanvipcheck.bsinterface.ILoanVIPCheckBS;


public class LoanLastSureMaintainAC extends LookupDispatchAction {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.loanapply.loanlastsure.action.LoanLastSureShowAC";

  protected Map getKeyMethodMap() {
    Map map = new HashMap();

    map.put("button.lastsure", "lastsure");
    map.put("button.dellastsure", "dellastsure");
    return map;
  }


 
  public ActionForward lastsure(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    ActionMessages messages = null;
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      ILoanLastSureBS loanLastSureBS = (ILoanLastSureBS) BSUtils
          .getBusinessService("loanLastSureBS", this, mapping.getModuleConfig());
      IdAF idAF = (IdAF) form;
      String[] contractId = idAF.getRowArray();
      loanLastSureBS.updateContractSTlastsure(contractId, securityInfo);
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      saveErrors(request, messages);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("loanLastSureShowAC");
  }
  public ActionForward dellastsure(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    ActionMessages messages = null;
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      ILoanLastSureBS loanLastSureBS = (ILoanLastSureBS) BSUtils
      .getBusinessService("loanLastSureBS", this, mapping.getModuleConfig());
      IdAF idAF = (IdAF) form;
      String[] contractId = idAF.getRowArray();
      loanLastSureBS.updateContractSTdellastsure(contractId, securityInfo);
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      saveErrors(request, messages);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("loanLastSureShowAC");
  }
}
