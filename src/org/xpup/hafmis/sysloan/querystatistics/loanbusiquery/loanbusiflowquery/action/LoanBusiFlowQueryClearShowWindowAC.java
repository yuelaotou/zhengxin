/**
 * Copy Right Information   : Goldsoft 
 * Project                  : LoanBusiFlowQueryClearShowWindowAC
 * @Version                 : 1.0
 * @author                  : wangy
 * 生成日期                   :2007-10-18
 **/
package org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.loanbusiflowquery.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.loanbusiflowquery.bsinterface.ILoanBusiFlowQueryBS;
import org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.loanbusiflowquery.dto.LoanBusiFlowQueryClearDTO;
import org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.loanbusiflowquery.form.LoanBusiFlowQueryClearAF;


public class LoanBusiFlowQueryClearShowWindowAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try {
      String flowHeadId = request.getParameter("flowHeadId");
      LoanBusiFlowQueryClearAF loanBusiFlowQueryClearAF = new LoanBusiFlowQueryClearAF();
      if (flowHeadId != null && !flowHeadId.equals("")) {
        SecurityInfo securityInfo = (SecurityInfo) request.getSession()
            .getAttribute("SecurityInfo");
        ILoanBusiFlowQueryBS loanBusiFlowQueryBS = (ILoanBusiFlowQueryBS) BSUtils
        .getBusinessService("loanBusiFlowQueryBS", this, mapping
            .getModuleConfig());
        LoanBusiFlowQueryClearDTO loanBusiFlowQueryClearDTO = loanBusiFlowQueryBS.queryLoanBusiFlowQueryClearByFlowHeadId(
            flowHeadId, securityInfo);
        loanBusiFlowQueryClearAF.setLoanBusiFlowQueryClearDTO(loanBusiFlowQueryClearDTO);
        request.setAttribute("loanBusiFlowQueryClearAF", loanBusiFlowQueryClearAF);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_loanbusiflowqueryclear_show");
  }

}