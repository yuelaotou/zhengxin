package org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.dto.CashDayClearTbFindDTO;
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.form.CashDayClearTbAF;


public class CashDayClearTbFindAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) throws Exception {
    try{
      CashDayClearTbAF cashDayClearTbAF=(CashDayClearTbAF)form;
      CashDayClearTbFindDTO cashDayClearTbFindDTO=cashDayClearTbAF.getCashDayClearTbFindDTO();
      Pagination pagination = new Pagination(0, 10, 1, "fn201.credence_id", "DESC",
          new HashMap(0));
      pagination.getQueryCriterions().put("cashDayClearTbFindDTO", cashDayClearTbFindDTO);
      String paginationKey = getPaginationKey();
      request.getSession().setAttribute(paginationKey, pagination);
    }catch(Exception e){
      e.printStackTrace();
    }
    return mapping.findForward("cashdaycleartb_show");
  }
  protected String getPaginationKey() {
    return CashDayClearTbShowAC.PAGINATION_KEY;
  }
}
