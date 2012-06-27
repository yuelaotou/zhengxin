package org.xpup.hafmis.sysloan.loanaccord.loanaccord.action;

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
import org.xpup.hafmis.sysloan.common.domain.entity.BorrowerAcc;
import org.xpup.hafmis.sysloan.loanaccord.loanaccord.bsinterface.ILoanaccordBS;
import org.xpup.hafmis.sysloan.loanaccord.loanaccord.dto.LoanaccordDTO;
import org.xpup.hafmis.sysloan.loanaccord.loanaccord.form.LoanaccordNewAF;

public class LoanaccordTaNewAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    LoanaccordNewAF loanaccordNewAF = (LoanaccordNewAF) form;
    ActionMessages messages = null;
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      ILoanaccordBS loanaccordBS = (ILoanaccordBS) BSUtils.getBusinessService(
          "loanaccordBS", this, mapping.getModuleConfig());
      LoanaccordDTO loanaccordDTO = loanaccordNewAF.getLoanaccordDTO();
      String headInfo = loanaccordBS.updateBorrowerAccInfo(loanaccordDTO,
          securityInfo);
      if (headInfo != null && !headInfo.equals("")) {
        request.getSession().setAttribute("headInfo", headInfo);
        request.getSession().setAttribute("infoMassage", "pass");
      }
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      saveErrors(request, messages);
      return mapping.findForward("to_loanaccord_new");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("loanaccordTaShowAC");
  }
}
