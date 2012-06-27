/**
 * Copy Right Information   : Goldsoft 
 * Project                  : LoanVIPCheckTcShowAC
 * @Version                 : 1.0
 * @author                  : wangy
 * 生成日期                   :2007-10-24
 **/
package org.xpup.hafmis.sysloan.loanapply.loanvipcheck.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.sysloan.contractchg.baseinfochg.bsinterface.IBaseinfochgBS;
import org.xpup.hafmis.sysloan.loanapply.loanapply.form.LoanapplyTcNewAF;

public class LoanVIPCheckTcShowAC extends Action {

  /**
   * 审批贷款_购房信息
   */
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    LoanapplyTcNewAF loanapplytcnewAF = new LoanapplyTcNewAF();
    String contractid = (String) request.getSession().getAttribute(
        "contractIdWY");
    if (contractid != null) {
      IBaseinfochgBS baseinfochgBS = (IBaseinfochgBS) BSUtils
          .getBusinessService("baseinfochgBS", this, mapping.getModuleConfig());
      try {
        loanapplytcnewAF = baseinfochgBS.showHouseInfo(contractid);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    request.setAttribute("loanapplytcnewAF", loanapplytcnewAF);
    return mapping.findForward("to_loanvipchecktc_show");
  }
}
