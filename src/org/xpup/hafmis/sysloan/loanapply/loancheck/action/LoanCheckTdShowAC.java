/**
 * Copy Right Information   : Goldsoft 
 * Project                  : LoanCheckTdShowAC
 * @Version                 : 1.0
 * @author                  : wangy
 * 生成日期                   :2007-10-25
 **/
package org.xpup.hafmis.sysloan.loanapply.loancheck.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loanapply.loanapply.bsinterface.ILoanapplyBS;
import org.xpup.hafmis.sysloan.loanapply.loanapply.form.LoanapplyTdNewAF;

public class LoanCheckTdShowAC extends Action {

  /**
   * 审批贷款_借款人额度信息
   */
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    LoanapplyTdNewAF loanapplytdnewAF = new LoanapplyTdNewAF();
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    ILoanapplyBS loanapplyBS = (ILoanapplyBS) BSUtils.getBusinessService(
        "loanapplyBS", this, mapping.getModuleConfig());
    String contractid = (String) request.getSession().getAttribute(
        "contractIdWY");
    if (contractid != null) {
      try {
        loanapplytdnewAF = loanapplyBS.showBorrowerLoanInfoByContractid(
            contractid, securityInfo);
        String mood = loanapplytdnewAF.getLoanMood();
        if (mood != null) {
          try {
            mood = BusiTools.getBusiValue(Integer.parseInt(mood),
                BusiConst.PLRECOVERTYPE);
          } catch (NumberFormatException e) {
            e.printStackTrace();
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
        loanapplytdnewAF.setLoanMood(mood);
      } catch (BusinessException e) {
        e.printStackTrace();
      }
    }
    request.setAttribute("loanapplytdnewAF", loanapplytdnewAF);
    return mapping.findForward("to_loanchecktd_show");
  }
}
