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
import org.xpup.hafmis.sysloan.loanaccord.loanaccord.bsinterface.ILoanaccordBS;
import org.xpup.hafmis.sysloan.loanaccord.loanaccord.form.LoanaccordNewAF;

public class LoanaccordTaShowAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      {
    ActionMessages messages = null;
    LoanaccordNewAF loanaccordNewAF = new LoanaccordNewAF();
    try {
      String contractId = request.getParameter("contractId") + "";
      if(contractId!=null&&!contractId.equals("null")&&!contractId.equals("")){
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      ILoanaccordBS loanaccordBS = (ILoanaccordBS) BSUtils.getBusinessService(
          "loanaccordBS", this, mapping.getModuleConfig());
      loanaccordNewAF = loanaccordBS.queryLoanaccordInfo(contractId,
          securityInfo);
      }
      request.setAttribute("loanaccordNewAF", loanaccordNewAF);
      loanaccordNewAF.reset(mapping, request);
     String infoMassage=(String)request.getSession().getAttribute("infoMassage");
     if(infoMassage!=null&&!infoMassage.equals("")){
     if(infoMassage.equals("pass")){
       messages=new ActionMessages();
       messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("pass",
       false));
       saveErrors(request, messages);
       request.getSession().setAttribute("infoMassage", null);
     }
     }
    }catch(BusinessException bex){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage(bex.getMessage(),
      false));
      saveErrors(request, messages);
   }
    catch (Exception e) {
      e.printStackTrace();
    }
    request.setAttribute("loanaccordNewAF", loanaccordNewAF);
    loanaccordNewAF.reset(mapping, request);
    return mapping.findForward("to_loanaccord_new");
  }
}
