package org.xpup.hafmis.sysfinance.accounthandle.monthclear.action;

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
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.accounthandle.monthclear.form.MonthclearAF;
import org.xpup.security.wsf.bizsrvc.IUserControlBS;

public class MonthclearDoAC extends Action {
  public ActionForward execute(ActionMapping arg0, ActionForm arg1,
      HttpServletRequest arg2, HttpServletResponse arg3) throws Exception {
    ActionMessages messages = null;
    try {
      MonthclearAF lform = (MonthclearAF) arg1;
      SecurityInfo securityInfo = (SecurityInfo) arg2.getSession()
          .getAttribute("SecurityInfo");
      IUserControlBS securityControlBS = (IUserControlBS) BSUtils
          .getBusinessService("securityControlBS", this, arg0.getModuleConfig());
      securityControlBS.updateFBizDate(securityInfo.getBookId(), lform.getDate());
      messages = new ActionMessages();
      messages
          .add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("0", false));
      saveErrors(arg2, messages);
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getLocalizedMessage(), false));
      saveErrors(arg2, messages);
    } catch (Exception e) {
      messages = new ActionMessages();
      messages
          .add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("1", false));
      saveErrors(arg2, messages);
    }
    return arg0.findForward("monthclearShowAC");
  }
}
