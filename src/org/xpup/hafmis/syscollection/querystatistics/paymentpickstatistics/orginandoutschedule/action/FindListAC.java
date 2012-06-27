package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.orginandoutschedule.action;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.orginandoutschedule.dto.SearchDTO;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.orginandoutschedule.form.DisplayAF;

public class FindListAC extends Action{
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    try {
      DisplayAF displayAF = (DisplayAF) form;
      SearchDTO searchDTO = displayAF.getSearchDTO();
      Pagination pagination = new Pagination(0, 10, 1, "a.org_id", "DESC",
          new HashMap(0));
      pagination.getQueryCriterions().put("search", searchDTO);
      String paginationKey = getPaginationKey();
      request.getSession().setAttribute(paginationKey, pagination);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("showlist");
  }

  protected String getPaginationKey() {
    return ShowListAC.PAGINATION_KEY;
  }
}
