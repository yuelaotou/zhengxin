package org.xpup.hafmis.sysfinance.accounthandle.credenceclear.action;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysfinance.accounthandle.credencecheck.dto.CredencecheckFindDTO;
import org.xpup.hafmis.sysfinance.accounthandle.credenceclear.form.CredenceclearAF;

public class CredenceclearFindAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      CredenceclearAF credenceclearAF = (CredenceclearAF) form;
      request.getSession().setAttribute("type", "1");
      CredencecheckFindDTO credencecheckFindDTO = credenceclearAF
          .getCredencecheckFindDTO();
      Pagination pagination = new Pagination(0, 10, 1,
          "fn201.credence_date,to_number(fn201.credence_num)", "", new HashMap(0));
      pagination.getQueryCriterions().put("credencecheckFindDTO",
          credencecheckFindDTO);
      String paginationKey = getPaginationKey();
      request.getSession().setAttribute(paginationKey, pagination);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_credenceclear_show");
  }

  protected String getPaginationKey() {
    return CredenceclearShowAC.PAGINATION_KEY;
  }
}
