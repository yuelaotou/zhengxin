package org.xpup.hafmis.sysfinance.bookmng.settleincanddec.action;

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
import org.xpup.hafmis.sysfinance.bookmng.settleincanddec.bsinterface.ISettleincanddecBS;

public class SettleincanddecDeleteAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    // TODO Auto-generated method stub
    try {
      IdAF idAF = (IdAF) form;
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      ISettleincanddecBS settleincanddecBS = (ISettleincanddecBS) BSUtils
          .getBusinessService("settleincanddecBS", this, mapping
              .getModuleConfig());
      settleincanddecBS.deleteSettleIncAndDec(idAF.getId().toString(),
          securityInfo);
    } catch (BusinessException e) {
      e.printStackTrace();
      ActionMessages messages = null;
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(e
          .getLocalizedMessage(), false));
      saveErrors(request, messages);
      return mapping.findForward("to_show_settleincanddec");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_show_settleincanddec");
  }

}
