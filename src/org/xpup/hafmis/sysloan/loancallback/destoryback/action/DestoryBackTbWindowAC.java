package org.xpup.hafmis.sysloan.loancallback.destoryback.action;
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
import org.xpup.hafmis.sysloan.loancallback.destoryback.bsinterface.IDestoryBackBS;
import org.xpup.hafmis.sysloan.loancallback.destoryback.dto.DestoryBackTaDTO;

public class DestoryBackTbWindowAC  extends Action {
  
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
        IDestoryBackBS destoryBackBS = (IDestoryBackBS) BSUtils.getBusinessService(
            "destoryBackBS", this, mapping.getModuleConfig());
        DestoryBackTaDTO destoryBackTaDTO = destoryBackBS.queryDestoryBackTbWindowById(
            flowHeadId, securityInfo);
        destoryBackTaDTO.setFlowHeadId(flowHeadId);
        request.getSession().setAttribute("destoryBackWindowDTO",destoryBackTaDTO);
      }
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      saveErrors(request, messages);
    }
    return mapping.findForward("to_destoryBackTbWindow_show");
  }

}