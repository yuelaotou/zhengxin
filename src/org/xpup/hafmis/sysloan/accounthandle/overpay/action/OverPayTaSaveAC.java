package org.xpup.hafmis.sysloan.accounthandle.overpay.action;


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
import org.xpup.hafmis.sysloan.accounthandle.overpay.bsinterface.IOverPayBS;
import org.xpup.hafmis.sysloan.accounthandle.overpay.dto.OverPayTaDTO;
import org.xpup.hafmis.sysloan.accounthandle.overpay.form.OverPayTaAF;


public class OverPayTaSaveAC extends Action{
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) throws Exception{
    OverPayTaAF overPayTaAF=(OverPayTaAF)form;
    ActionMessages messages = new ActionMessages();
    try{
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      IOverPayBS overPayBS = (IOverPayBS) BSUtils
      .getBusinessService("overPayBS", this, mapping.getModuleConfig());
      OverPayTaDTO overPayTaDTO=overPayTaAF.getOverPayTaDTO();
      overPayBS.saveOverPayTa(overPayTaDTO,securityInfo);
    }catch (BusinessException bex) {
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex.getLocalizedMessage(),
          false));
      saveErrors(request, messages);
      request.setAttribute("overPayTaAF", overPayTaAF);
    }catch(Exception e){
      e.printStackTrace();
    }
    return mapping.findForward("overpayta_show");
  }
}
