package org.xpup.hafmis.sysloan.dataready.loanconditionsset.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.dataready.loanconditionsset.bsinterface.ILoanConditionsSetBS;
import org.xpup.hafmis.sysloan.dataready.loanconditionsset.dto.LoanConditionsSetDTO;
import org.xpup.hafmis.sysloan.dataready.loanconditionsset.form.LoanConditionsSetAF;


public class LoanConditionsSetSaveAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try{
      LoanConditionsSetAF loanConditionsSetAF=(LoanConditionsSetAF)form;
      LoanConditionsSetDTO loanConditionsSetDTO=loanConditionsSetAF.getLoanConditionsSetDTO();
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      ILoanConditionsSetBS loanConditionsSetBS = (ILoanConditionsSetBS) BSUtils
      .getBusinessService("loanConditionsSetBS", this, mapping.getModuleConfig());
      loanConditionsSetBS.saveLoanConditionsSetInfo(loanConditionsSetDTO, securityInfo);
    }catch(Exception e){
      e.printStackTrace();
    }
    return mapping.findForward("loanconditionsset_show");
  }
}
