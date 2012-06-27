package org.xpup.hafmis.syscollection.accounthandle.clearaccount.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.syscollection.accounthandle.clearaccount.form.ChenYanDSBAF;

public class ChenYanDSBFindAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ChenYanDSBAF chenYanDSBAF = (ChenYanDSBAF) form;
    request.setAttribute("chenYanDSBAF", chenYanDSBAF);
    HashMap criterions = makeCriterionsMap(chenYanDSBAF);
    Pagination pagination = new Pagination(0, 10, 1, "empHAFAccountFlow.id",
        "DESC", criterions);
    String paginationKey = ChenYanDSBShowAC.PAGINATION_KEY;
    request.getSession().setAttribute(paginationKey, pagination);

    return mapping.findForward("chenYanDSBShowAC");
  }

  protected HashMap makeCriterionsMap(ChenYanDSBAF chenYanDSBAF) {
    HashMap m = new HashMap();
    String yearmonth = chenYanDSBAF.getYearmonth();
    String bank1 = chenYanDSBAF.getBank1();
    m.put("yearmonth", yearmonth.trim());
    m.put("bank1", bank1.trim());
    return m;
  }

}
