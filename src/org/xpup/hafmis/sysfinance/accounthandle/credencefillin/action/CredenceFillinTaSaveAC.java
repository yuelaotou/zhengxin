package org.xpup.hafmis.sysfinance.accounthandle.credencefillin.action;

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
import org.xpup.hafmis.sysfinance.accounthandle.credencefillin.bsinterface.ICredenceFillinBS;
import org.xpup.hafmis.sysfinance.accounthandle.credencefillin.dto.CredenceFillinTaShowDTO;
import org.xpup.hafmis.sysfinance.accounthandle.credencefillin.form.CredenceFillinTaAF;

/**
 * 凭证录入页确定按钮的Action
 * 
 * @author 刘冰
 */
public class CredenceFillinTaSaveAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    ActionMessages messages = null;
    ICredenceFillinBS credenceFillinBS = (ICredenceFillinBS) BSUtils
        .getBusinessService("credenceFillinBS", this, mapping.getModuleConfig());
    CredenceFillinTaAF credenceFillinTaAF = (CredenceFillinTaAF) form;
    try {
      CredenceFillinTaShowDTO credenceFillinTaShowDTO = credenceFillinTaAF
          .getCredenceFillinTaShowDTO();
      String listAllContent = credenceFillinTaAF.getListAllContent();
      credenceFillinBS.insertCredenceFillinTa(credenceFillinTaShowDTO,
          securityInfo, listAllContent);
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getLocalizedMessage(), false));
      saveErrors(request, messages);
      return mapping.findForward("to_credencefillinta_show");
    } catch (Exception e) {
      e.printStackTrace();
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("保存失败！",
          false));
      saveErrors(request, messages);

    }
    return mapping.findForward("to_credencefillinta_show");
  }
}
