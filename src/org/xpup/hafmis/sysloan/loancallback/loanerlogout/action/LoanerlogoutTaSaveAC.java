package org.xpup.hafmis.sysloan.loancallback.loanerlogout.action;

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
import org.xpup.hafmis.sysloan.loancallback.loanerlogout.bsinterface.ILoanerlogoutBS;


public class LoanerlogoutTaSaveAC extends Action{
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) throws Exception{
    ActionMessages messages = new ActionMessages();
    try{
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      ILoanerlogoutBS loanerlogoutBS = (ILoanerlogoutBS) BSUtils
      .getBusinessService("loanerlogoutBS", this, mapping.getModuleConfig());
      String contractId=(String)request.getParameter("contractId");         
      if(contractId!=null&&!"".equals(contractId.trim())){
        contractId=(String)request.getParameter("contractId");  
      }
      loanerlogoutBS.findLoanerlogouAvailable(contractId);
      loanerlogoutBS.saveLoanerlogouTa(contractId,securityInfo);
      request.setAttribute("save", "save");
    }catch (BusinessException be) {
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(be.getLocalizedMessage(),
          false));
      saveErrors(request, messages);
      return mapping.findForward("loanerlogout_show");
    }catch(Exception e){
      e.printStackTrace();
    }
    return mapping.findForward("loanerlogout_show");
  }
}
