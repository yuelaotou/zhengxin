package org.xpup.hafmis.sysfinance.bookmng.credencemodle.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.bookmng.credencemodle.bsinterface.ICredencemodleBS;

public class CredencemodleDeleteAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    // TODO Auto-generated method stub
    try {
      IdAF idAF = (IdAF) form;
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      ICredencemodleBS credencemodleBS = (ICredencemodleBS) BSUtils
          .getBusinessService("credencemodleBS", this, mapping
              .getModuleConfig());
      credencemodleBS
          .deleteCredencemodle(idAF.getId().toString(), securityInfo);
    } catch (BusinessException e) {
      e.printStackTrace();
      ActionMessages messages = null;
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(e
          .getLocalizedMessage(), false));
      saveErrors(request, messages);
      return mapping.findForward("to_show_credencemodle");
    } catch (Exception e) {
      e.printStackTrace();
    }

    return mapping.findForward("to_show_credencemodle");
  }

}
