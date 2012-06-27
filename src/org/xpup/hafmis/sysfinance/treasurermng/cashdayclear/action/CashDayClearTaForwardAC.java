package org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class CashDayClearTaForwardAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    request.getSession().removeAttribute("cashDayClearTaAF");
    request.getSession().removeAttribute("office_gjp");
    request.getSession().removeAttribute("credenceDate_gjp");
    //现金日记账和银行存款日记账公用同一个办理页面、转账页面、维护页面  
    //"credenceType_gjp"为0就表示：是从现金日记账中跳到页面的
    request.getSession().setAttribute("credenceType_gjp", "0");
    return mapping.findForward("cashdayclearta_show");
  }
}
