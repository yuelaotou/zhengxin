package org.xpup.hafmis.sysfinance.treasurermng.accountclear.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.dto.CashDayClearTcFindDTO;
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.form.CashDayClearTcAF;

public class AccountClearFindAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try{
      CashDayClearTcAF cashDayClearTcAF=(CashDayClearTcAF)form;
      CashDayClearTcFindDTO cashDayClearTcFindDTO=cashDayClearTcAF.getCashDayClearTcFindDTO();
      cashDayClearTcFindDTO.setType("1");
      Pagination pagination = new Pagination(0, 10, 1, "fn210.credence_id", "DESC",
          new HashMap(0));
      pagination.getQueryCriterions().put("cashDayClearTcFindDTO", cashDayClearTcFindDTO);
      String paginationKey = getPaginationKey();
      request.getSession().setAttribute(paginationKey, pagination);
    }catch(Exception e){
      e.printStackTrace();
    }
    return mapping.findForward("accountclear_show");
  }
  protected String getPaginationKey() {
    return AccountClearShowAC.PAGINATION_KEY;
  }
}
