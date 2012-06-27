package org.xpup.hafmis.sysfinance.treasurermng.bankcheckacc.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.treasurermng.bankcheckacc.bsinterface.IBankCheckAccBS;
import org.xpup.hafmis.sysfinance.treasurermng.bankcheckacc.dto.BankCheckAccTaDTO;
import org.xpup.hafmis.sysfinance.treasurermng.bankcheckacc.form.BankCheckAccTaAF;



public class BankCheckAccTaMaintainAC extends DispatchAction {
  public ActionForward save(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    ActionMessages messages = null;
    BankCheckAccTaAF bankCheckAccTaAF = (BankCheckAccTaAF) form;
    if (!isTokenValid(request, true)) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("请不要重复提交！",
          false));
      saveErrors(request, messages);
      saveToken(request);
    } else {
      try {
        BankCheckAccTaDTO bankCheckAccTaDTO = bankCheckAccTaAF
            .getBankCheckAccTaDTO();
        SecurityInfo securityInfo = (SecurityInfo) request.getSession()
            .getAttribute("SecurityInfo");
        IBankCheckAccBS bankCheckAccBS = (IBankCheckAccBS) BSUtils
        .getBusinessService("bankCheckAccBS", this, mapping.getModuleConfig());
        bankCheckAccBS.saveBankCheckAccTa(bankCheckAccTaDTO, securityInfo);
        //type为1时表示保存成功
        bankCheckAccTaAF.setType("1");
        request.setAttribute("bankCheckAccTaAF", bankCheckAccTaAF);
        request.setAttribute("office", bankCheckAccTaDTO.getOffice());
        return mapping.findForward("bankcheckaccta_show");
      } catch (BusinessException bex) {
        saveToken(request);
        messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
            .getLocalizedMessage(), false));
        saveErrors(request, messages);
        request.setAttribute("bankCheckAccTaAF", bankCheckAccTaAF);
        return mapping.findForward("bankcheckaccta_show");
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return mapping.findForward("bankcheckaccta_show");
  }
  public ActionForward modify(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    ActionMessages messages = null;
    BankCheckAccTaAF bankCheckAccTaAF = (BankCheckAccTaAF) form;
    if (!isTokenValid(request, true)) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("请不要重复提交！",
          false));
      saveErrors(request, messages);
      saveToken(request);
    } else {
      try {
        BankCheckAccTaDTO bankCheckAccTaDTO = bankCheckAccTaAF
            .getBankCheckAccTaDTO();
        SecurityInfo securityInfo = (SecurityInfo) request.getSession()
            .getAttribute("SecurityInfo");
        IBankCheckAccBS bankCheckAccBS = (IBankCheckAccBS) BSUtils
        .getBusinessService("bankCheckAccBS", this, mapping.getModuleConfig());
        bankCheckAccBS.modifyInfo(bankCheckAccTaDTO,securityInfo);
        bankCheckAccTaAF.setType("1");
        request.setAttribute("bankCheckAccTaAF", bankCheckAccTaAF);
        return mapping.findForward("bankcheckaccta_show");
      } catch (BusinessException bex) {
        saveToken(request);
        messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
            .getLocalizedMessage(), false));
        saveErrors(request, messages);
        request.setAttribute("bankCheckAccTaAF", bankCheckAccTaAF);
        return mapping.findForward("bankcheckaccta_show");
      } catch (Exception e) {
        e.printStackTrace();
      } 
    }
    return mapping.findForward("bankcheckaccta_show");
  }
}
