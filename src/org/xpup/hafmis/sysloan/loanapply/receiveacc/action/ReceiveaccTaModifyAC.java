package org.xpup.hafmis.sysloan.loanapply.receiveacc.action;

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

import org.xpup.hafmis.sysloan.loanapply.receiveacc.bsinterface.IReceiveaccBS;
import org.xpup.hafmis.sysloan.loanapply.receiveacc.form.ReceiveaccModifyAF;

public class ReceiveaccTaModifyAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    // TODO Auto-generated method stub
    try {
      ReceiveaccModifyAF receiveaccModifyAF = (ReceiveaccModifyAF) form;
      String newLoanKouAcc = "";
      String oldBankAcc = "";
      String contractId = "";
      String newLoanBankId="";
      newLoanKouAcc = receiveaccModifyAF.getNewLoanKouAcc();
      if(newLoanKouAcc!=null&&!"".equals(newLoanKouAcc.trim())){
        newLoanKouAcc = receiveaccModifyAF.getNewLoanKouAcc().trim();
      }
      oldBankAcc = receiveaccModifyAF.getOldLoanKouAcc();
      if(oldBankAcc!=null&&!"".equals(oldBankAcc.trim())){
        oldBankAcc = receiveaccModifyAF.getOldLoanKouAcc().trim();
      }
      newLoanBankId = receiveaccModifyAF.getNewloanBankName();
      if(newLoanBankId!=null&&!"".equals(newLoanBankId.trim())){
        newLoanBankId = receiveaccModifyAF.getNewloanBankName().trim();
      }
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      contractId = receiveaccModifyAF.getContractId();
      if(contractId!=null&&!"".equals(contractId.trim())){
        contractId = receiveaccModifyAF.getContractId().trim();
      }
      IReceiveaccBS receiveaccBS = (IReceiveaccBS) BSUtils.getBusinessService(
          "receiveaccBS", this, mapping.getModuleConfig());
      receiveaccBS.findReceiveaccAvailable(contractId);
      receiveaccBS.isLoanKouAccDuplicate(newLoanKouAcc);
//    관멀
      String flag="0";
      flag=receiveaccBS.findExitGJJBack(contractId);
      receiveaccBS.modifyBorrowerAccInfo(contractId, newLoanKouAcc, oldBankAcc,
          securityInfo, flag,newLoanBankId);
      //관멀
//      receiveaccBS.modifyBorrowerAccInfo(contractId, newLoanKouAcc, oldBankAcc,
//          securityInfo);
      request.setAttribute("type", "1");
    } catch (BusinessException e) {
      e.printStackTrace();
      ActionMessages messages = null;
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(e.getLocalizedMessage(),
          false));
      saveErrors(request, messages);
      return mapping.findForward("to_show_receiveacc");
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return mapping.findForward("to_show_receiveacc");
  }

}
