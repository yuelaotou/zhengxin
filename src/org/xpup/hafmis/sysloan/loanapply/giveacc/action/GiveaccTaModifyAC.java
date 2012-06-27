package org.xpup.hafmis.sysloan.loanapply.giveacc.action;

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
import org.xpup.hafmis.sysloan.loanapply.giveacc.bsinterface.IGiveaccBS;
import org.xpup.hafmis.sysloan.loanapply.giveacc.form.GiveaccModifyAF;

public class GiveaccTaModifyAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    // TODO Auto-generated method stub
    try {
      GiveaccModifyAF giveaccModifyAF = (GiveaccModifyAF) form;
      // 原划款银行
      String oldSellerPayBank = "";

      // 新划款银行
      String newSellerPayBank = "";

      // 原划款银行帐号
      String oldPayBankAcc = "";

      // 新划款银行帐号
      String newPayBankAcc = "";
      String hourseType = "";
      hourseType = (String) request.getSession().getAttribute("houseType");
      oldSellerPayBank =giveaccModifyAF.getOldSellerPayBank();
      if(oldSellerPayBank!=null&&!"".equals(oldSellerPayBank.trim())){
        oldSellerPayBank = giveaccModifyAF.getOldSellerPayBank();
      }
      oldPayBankAcc = giveaccModifyAF.getOldPayBankAcc();
      if(oldPayBankAcc!=null&&!"".equals(oldPayBankAcc.trim())){
        oldPayBankAcc = giveaccModifyAF.getOldPayBankAcc();
      }
      newSellerPayBank = giveaccModifyAF.getNewSellerPayBank();
      if(newSellerPayBank!=null&&!"".equals(newSellerPayBank.trim())){
        newSellerPayBank = giveaccModifyAF.getNewSellerPayBank();
      }
      newPayBankAcc = giveaccModifyAF.getNewPayBankAcc();
      if(newPayBankAcc!=null&&!"".equals(newPayBankAcc.trim())){
        newPayBankAcc =giveaccModifyAF.getNewPayBankAcc();
      }
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      String contractId = "";
      contractId = giveaccModifyAF.getContractId();
      IGiveaccBS giveaccBS = (IGiveaccBS) BSUtils.getBusinessService(
          "giveaccBS", this, mapping.getModuleConfig());
      giveaccBS.modifyGiveAccInfo(contractId, oldSellerPayBank, oldPayBankAcc,
          newSellerPayBank, newPayBankAcc, hourseType, securityInfo);
      request.setAttribute("type", "1");
    } catch (BusinessException e) {
      e.printStackTrace();
      ActionMessages messages = null;
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("划款账号重复！！",
          false));
      saveErrors(request, messages);
      return mapping.findForward("to_show_giveacc");
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return mapping.findForward("to_show_giveacc");
  }
}
