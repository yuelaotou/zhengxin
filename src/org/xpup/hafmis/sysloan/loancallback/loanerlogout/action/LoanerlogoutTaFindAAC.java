package org.xpup.hafmis.sysloan.loancallback.loanerlogout.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loancallback.loanerlogout.bsinterface.ILoanerlogoutBS;
import org.xpup.security.common.domain.Userslogincollbank;

public class LoanerlogoutTaFindAAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    // TODO Auto-generated method stub
    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache");

    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    List loanbankList1 = null;
    try {
      // 取出用户权限放款银行,显示在下拉菜单中
      List loanbankList = securityInfo.getDkUserBankList();
      loanbankList1 = new ArrayList();
      Userslogincollbank bank = null;
      Iterator itr1 = loanbankList.iterator();
      while (itr1.hasNext()) {
        bank = (Userslogincollbank) itr1.next();
        loanbankList1.add(bank.getCollBankId());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    try {
      String loanKouAcc = request.getParameter("loanKouAcc").trim();
      request.getSession().setAttribute("loanKouAcc", loanKouAcc);
      ILoanerlogoutBS loanerlogoutBS = (ILoanerlogoutBS) BSUtils
          .getBusinessService("loanerlogoutBS", this, mapping.getModuleConfig());
      loanerlogoutBS.findLoanerlogoutTaExit(loanKouAcc, loanbankList1);
      String text = "displays('" + loanKouAcc + "')";
      response.getWriter().write(text);
      response.getWriter().close();
    } catch (BusinessException bex) {
      System.err.println(bex.getLocalizedMessage().toString());
      String text = "backErrors('" + bex.getLocalizedMessage() + "')";
      response.getWriter().write(text);
      response.getWriter().close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
