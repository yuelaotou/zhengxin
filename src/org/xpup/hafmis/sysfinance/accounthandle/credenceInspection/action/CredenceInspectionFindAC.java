package org.xpup.hafmis.sysfinance.accounthandle.credenceInspection.action;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysfinance.accounthandle.credenceInspection.dto.CredenceInspectionFindDTO;
import org.xpup.hafmis.sysfinance.accounthandle.credenceInspection.form.CredenceInspectionAF;

public class CredenceInspectionFindAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      CredenceInspectionAF credenceInspectionAF = (CredenceInspectionAF) form;
      request.getSession().setAttribute("type", "1");
      CredenceInspectionFindDTO credenceInspectionFindDTO = credenceInspectionAF
          .getCredenceInspectionFindDTO();

      Pagination pagination = new Pagination(0, 0, 0, "", "", new HashMap(0));
      pagination.getQueryCriterions().put("credenceInspectionFindDTO",
          credenceInspectionFindDTO);
      String paginationKey = getPaginationKey();
      request.getSession().setAttribute(paginationKey, pagination);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_credenceInspection_show");
  }

  protected String getPaginationKey() {
    return CredenceInspectionShowAC.PAGINATION_KEY;
  }
}
