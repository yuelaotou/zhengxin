package org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.action;



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
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.bsinterface.ICashDayClearBS;
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.dto.CashDayClearTaDTO;
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.form.CashDayClearTaAF;

public class CashDayClearTaMaintainAC extends DispatchAction {

  public ActionForward save(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    ActionMessages messages = null;
    CashDayClearTaAF cashDayClearTaAF = (CashDayClearTaAF) form;
    if (!isTokenValid(request, true)) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("请不要重复提交！",
          false));
      saveErrors(request, messages);
      saveToken(request);
    } else {
      try {
        CashDayClearTaDTO cashDayClearTaDTO = cashDayClearTaAF
            .getCashDayClearTaDTO();
        SecurityInfo securityInfo = (SecurityInfo) request.getSession()
            .getAttribute("SecurityInfo");
        ICashDayClearBS cashDayClearBS = (ICashDayClearBS) BSUtils
            .getBusinessService("cashDayClearBS", this, mapping
                .getModuleConfig());
        //用credenceType来判断是现金日记账还是银行存款日记账
        String credenceType=(String)request.getSession().getAttribute("credenceType_gjp");
        cashDayClearBS.saveCashDayClearTa(cashDayClearTaDTO,credenceType,securityInfo);
        cashDayClearTaAF.setType("1");
        request.getSession().setAttribute("cashDayClearTaAF", cashDayClearTaAF);
        request.getSession().setAttribute("office_gjp", cashDayClearTaDTO.getOffice());
        request.getSession().setAttribute("credenceDate_gjp", cashDayClearTaDTO.getCredenceDate());
        return mapping.findForward("cashdayclearta_show");
      } catch (BusinessException bex) {
        saveToken(request);
        messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
            .getLocalizedMessage(), false));
        saveErrors(request, messages);
        request.getSession().setAttribute("cashDayClearTaAF", cashDayClearTaAF);
        return mapping.findForward("cashdayclearta_show");
      }catch (Exception e) {
        e.printStackTrace();
      }
    }
    return mapping.findForward("cashdayclearta_show");
  }

  public ActionForward modify(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    ActionMessages messages = null;
    CashDayClearTaAF newCashDayClearTaAF = (CashDayClearTaAF) form;
    CashDayClearTaAF oldCashDayClearTaAF = (CashDayClearTaAF) request
        .getSession().getAttribute("cashDayClearTaAF");
    if (!isTokenValid(request, true)) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("请不要重复提交！",
          false));
      saveErrors(request, messages);
      saveToken(request);
    } else {
      try {
        CashDayClearTaDTO newCashDayClearTaDTO = newCashDayClearTaAF
            .getCashDayClearTaDTO();
        CashDayClearTaDTO oldCashDayClearTaDTO = oldCashDayClearTaAF
            .getCashDayClearTaDTO();
        SecurityInfo securityInfo = (SecurityInfo) request.getSession()
            .getAttribute("SecurityInfo");
        ICashDayClearBS cashDayClearBS = (ICashDayClearBS) BSUtils
            .getBusinessService("cashDayClearBS", this, mapping.getModuleConfig());
        //用credenceType来判断是现金日记账还是银行存款日记账
        String credenceType=(String)request.getSession().getAttribute("credenceType_gjp");
        cashDayClearBS.modifyInfo(newCashDayClearTaDTO,oldCashDayClearTaDTO,credenceType,
            securityInfo);
        newCashDayClearTaAF.setType("1");
        newCashDayClearTaAF.getCashDayClearTaDTO().setOffice(oldCashDayClearTaAF.getCashDayClearTaDTO().getOffice());
        newCashDayClearTaAF.getCashDayClearTaDTO().setCredenceId(oldCashDayClearTaAF.getCashDayClearTaDTO().getCredenceId());
        request.getSession().setAttribute("cashDayClearTaAF", newCashDayClearTaAF);
        return mapping.findForward("cashdayclearta_show");
      } catch (BusinessException bex) {
        saveToken(request);
        messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
            .getLocalizedMessage(), false));
        saveErrors(request, messages);
        newCashDayClearTaAF.getCashDayClearTaDTO().setOffice(oldCashDayClearTaAF.getCashDayClearTaDTO().getOffice());
        newCashDayClearTaAF.getCashDayClearTaDTO().setCredenceId(oldCashDayClearTaAF.getCashDayClearTaDTO().getCredenceId());
        request.getSession().setAttribute("cashDayClearTaAF", newCashDayClearTaAF);
        return mapping.findForward("cashdayclearta_show");
      } catch (Exception e) {
        e.printStackTrace();
      } 
    }
    return mapping.findForward("cashdayclearta_show");
  }
}
