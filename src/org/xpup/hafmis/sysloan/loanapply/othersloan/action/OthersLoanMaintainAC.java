package org.xpup.hafmis.sysloan.loanapply.othersloan.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loanapply.othersloan.bsinterface.IOthersLoanBS;
import org.xpup.hafmis.sysloan.loanapply.othersloan.form.OthersLoanShowAF;

public class OthersLoanMaintainAC extends LookupDispatchAction {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.loanapply.loanlastsure.action.LoanLastSureShowAC";

  protected Map getKeyMethodMap() {
    Map map = new HashMap();

    map.put("button.sure", "sure");
    return map;
  }

  public ActionForward sure(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      IOthersLoanBS othersLoanBS = (IOthersLoanBS) BSUtils.getBusinessService(
          "othersLoanBS", this, mapping.getModuleConfig());
      OthersLoanShowAF othersLoanShowAF = (OthersLoanShowAF) form;
      othersLoanBS.saveOthersLoanInfo(othersLoanShowAF, securityInfo);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("othersLoanShowAC");
  }
}
