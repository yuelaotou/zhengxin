/**
 * Copy Right Information   : Goldsoft 
 * Project                  : PreLoanRefrMaintainAC
 * @Version                 : 1.0
 * @author                  : wangy
 * 生成日期                   :2008-05-19
 **/
package org.xpup.hafmis.sysloan.loanapply.preloanrefr.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loanapply.preloanrefr.bsinterface.IPreLoanRefrBS;
import org.xpup.hafmis.sysloan.loanapply.preloanrefr.form.PreLoanRefrShowAF;

public class PreLoanRefrMaintainAC extends LookupDispatchAction {

  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.print", "print");
    return map;
  }

  /**
   * Description 贷前咨询
   * 
   * @author wangy 2008-05-20
   * @param 打印
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return ActionForward
   * @throws Exception
   */
  public ActionForward print(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      PreLoanRefrShowAF preLoanRefrShowAF = new PreLoanRefrShowAF();
      Pagination pagination = (Pagination) request.getSession().getAttribute(
          PreLoanRefrShowAC.PAGINATION_KEY);
      IPreLoanRefrBS preLoanRefrBS = (IPreLoanRefrBS) BSUtils
          .getBusinessService("preLoanRefrBS", this, mapping.getModuleConfig());
      preLoanRefrShowAF = preLoanRefrBS.queryPreLoanRefrListByCriterions(
          pagination, securityInfo);
      request.setAttribute("URL", "preLoanRefrShowAC.do");
      request.setAttribute("preLoanRefrShowAF", preLoanRefrShowAF);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_preloanrefr_cell");
  }
}
