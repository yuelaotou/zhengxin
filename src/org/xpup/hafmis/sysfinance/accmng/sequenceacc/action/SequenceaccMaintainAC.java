package org.xpup.hafmis.sysfinance.accmng.sequenceacc.action;

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
import org.xpup.hafmis.sysfinance.accounthandle.credenceclear.bsinterface.ICredenceclearBS;

public class SequenceaccMaintainAC extends LookupDispatchAction {
  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.print", "print");
    map.put("button.individual.flow", "buttonindividualflow");
    return map;
  }

  public ActionForward print(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {    
    try {
      request.setAttribute("url", "credenceclearShowAC.do");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_sequenceacc_print");
  }

  public ActionForward buttonindividualflow(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = new ActionMessages();
    try {
      IdAF idAF = (IdAF) form;
      String id[] = idAF.getRowArray();
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      ICredenceclearBS credenceclearBS = (ICredenceclearBS) BSUtils
          .getBusinessService("credenceclearBS", this, mapping
              .getModuleConfig());
      credenceclearBS.credenceclear(id, securityInfo);
    } catch (BusinessException bex) {
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getLocalizedMessage(), false));
      saveErrors(request, messages);
      return mapping.findForward("to_credenceclear_show");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_credenceclear_show");
  }
}