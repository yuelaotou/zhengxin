package org.xpup.hafmis.sysloan.accounthandle.bizcheck.action;

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
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.accounthandle.bizcheck.bsinterface.IBizCheckBS;

import org.xpup.hafmis.sysloan.accounthandle.bizcheck.dto.AdjustAccountDTO;

public class AdjustAccountFindAC  extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    try {
      String flowHeadId = request.getParameter("id");
      if (flowHeadId != null && !flowHeadId.equals("null")
          && !flowHeadId.equals("")) {
        SecurityInfo securityInfo = (SecurityInfo) request.getSession()
            .getAttribute("SecurityInfo");
        IBizCheckBS bizCheckBS = (IBizCheckBS) BSUtils.getBusinessService(
            "bizCheckBS", this, mapping.getModuleConfig());
        AdjustAccountDTO adjustAccountDTO = bizCheckBS.queryAdjustAccountById(
            flowHeadId, securityInfo);
        adjustAccountDTO.setFlowHeadId(flowHeadId);
        request.getSession().setAttribute("adjustAccountDTO",adjustAccountDTO);
      }
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      request.getSession().setAttribute("adjustAccountDTO",null);
      saveErrors(request, messages);
    }
    return mapping.findForward("to_adjustAccount_show");
  }

}