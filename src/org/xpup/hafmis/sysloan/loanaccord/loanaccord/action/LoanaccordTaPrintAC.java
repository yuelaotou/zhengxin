package org.xpup.hafmis.sysloan.loanaccord.loanaccord.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loanaccord.loanaccord.bsinterface.ILoanaccordBS;
import org.xpup.hafmis.sysloan.loanaccord.loanaccord.dto.LoanaccordDTO;

public class LoanaccordTaPrintAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    ActionMessages messages = null;
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
    .getAttribute("SecurityInfo");
    ILoanaccordBS loanaccordBS = (ILoanaccordBS) BSUtils.getBusinessService(
    "loanaccordBS", this, mapping.getModuleConfig());
    String flowHeadId=(String)request.getSession().getAttribute("headInfo");
    try {
      LoanaccordDTO loanaccordDTO=loanaccordBS.printLoanaccordInfo(flowHeadId, securityInfo);
      if(loanaccordDTO!=null){
        request.getSession().setAttribute("loanaccordDTOInfo", loanaccordDTO); 
      }else{
        throw new BusinessException("¥Ú”° ß∞‹£°");
      }
      request.setAttribute("URL", "loanaccordTaShowAC.do");
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      saveErrors(request, messages);
      return mapping.findForward("loanaccordTaShowAC");
    }
    return mapping.findForward("to_loanaccord_print");
  }

}
