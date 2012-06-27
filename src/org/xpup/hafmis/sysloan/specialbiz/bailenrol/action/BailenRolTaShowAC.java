package org.xpup.hafmis.sysloan.specialbiz.bailenrol.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.security.common.domain.Userslogincollbank;

/**
 * @author 王野 2007-09-30
 */
public class BailenRolTaShowAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.specialbiz.bailenrol.action.BailenRolTaShowAC";

  /**
   * 办理保证金登记
   */
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try {
      saveToken(request);
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      if (securityInfo != null && !securityInfo.equals("")) {
        // 收款银行下拉框
        List loanBankNameList = new ArrayList();
        List bangkList = securityInfo.getDkUserBankList();
        Userslogincollbank userslogincollbank = null;
        Iterator bank = bangkList.iterator();
        while (bank.hasNext()) {
          userslogincollbank = (Userslogincollbank) bank.next();
          loanBankNameList.add(new org.apache.struts.util.LabelValueBean(
              userslogincollbank.getCollBankName(), userslogincollbank
                  .getCollBankId().toString()));
        }
        request.getSession(true).setAttribute("loanBankNameList",
            loanBankNameList);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_bailenrol_new");
  }
}
