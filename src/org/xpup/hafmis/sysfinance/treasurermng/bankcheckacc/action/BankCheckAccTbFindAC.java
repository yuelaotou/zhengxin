package org.xpup.hafmis.sysfinance.treasurermng.bankcheckacc.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysfinance.treasurermng.bankcheckacc.dto.BankCheckAccTbFindDTO;
import org.xpup.hafmis.sysfinance.treasurermng.bankcheckacc.form.BankCheckAccTbAF;

public class BankCheckAccTbFindAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) throws Exception {
    try{
      BankCheckAccTbAF bankCheckAccTbAF=(BankCheckAccTbAF)form;
      BankCheckAccTbFindDTO bankCheckAccTbFindDTO=bankCheckAccTbAF.getBankCheckAccTbFindDTO();
      Pagination pagination = new Pagination(0, 10, 1, "fn211.credence_id", "DESC",
          new HashMap(0));
      pagination.getQueryCriterions().put("bankCheckAccTbFindDTO", bankCheckAccTbFindDTO);
      String paginationKey = getPaginationKey();
      request.getSession().setAttribute(paginationKey, pagination);
    }catch(Exception e){
      e.printStackTrace();
    }
    return mapping.findForward("bankcheckacctb_show");
  }
  protected String getPaginationKey() {
    return BankCheckAccTbShowAC.PAGINATION_KEY;
  }
}
