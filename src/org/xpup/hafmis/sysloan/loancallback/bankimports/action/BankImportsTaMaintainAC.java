package org.xpup.hafmis.sysloan.loancallback.bankimports.action;

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
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loancallback.bankimports.bsinterface.IBankImportsBS;

public class BankImportsTaMaintainAC extends LookupDispatchAction {
  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.deleteall", "deleteall");
    map.put("button.callback", "callback");
    return map;
  }

  public ActionForward deleteall(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    try {
      messages = new ActionMessages();
      Pagination pagination = (Pagination) request.getSession().getAttribute(
          BankImportsTaShowAC.PAGINATION_KEY);
      pagination.getQueryCriterions().put("loanBankId",null);
      IdAF idAF = (IdAF)form;
      IBankImportsBS bankImportsBS = (IBankImportsBS) BSUtils
          .getBusinessService("bankImportsBS", this, mapping.getModuleConfig());
      bankImportsBS.deleteAllCallbackInfo(idAF.getId().toString(), securityInfo);
      
    } catch (BusinessException b) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(b
          .getMessage(), false));
      saveErrors(request, messages);
    }
    return mapping.findForward("bankImportsTaShowAC");
  }

  public ActionForward callback(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
    .getAttribute("SecurityInfo");
    try {
      messages = new ActionMessages();
      IdAF idAF = (IdAF)form;
      IBankImportsBS bankImportsBS = (IBankImportsBS) BSUtils
      .getBusinessService("bankImportsBS", this, mapping.getModuleConfig());
      bankImportsBS.loanCallback(idAF.getId().toString(), securityInfo);
      request.getSession().setAttribute(BankImportsTaShowAC.PAGINATION_KEY, null);
    } catch (Exception b) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(b
          .getMessage(), false));
      saveErrors(request, messages);
    }
    return mapping.findForward("bankImportsTaShowAC");
  }

}