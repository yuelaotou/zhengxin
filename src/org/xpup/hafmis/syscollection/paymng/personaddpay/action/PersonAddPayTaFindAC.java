package org.xpup.hafmis.syscollection.paymng.personaddpay.action;

import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.syscollection.paymng.personaddpay.bsinterface.IPersonAddPayBS;
import org.xpup.hafmis.syscollection.paymng.personaddpay.form.PersonAddPayAF;

public class PersonAddPayTaFindAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.paymng.personaddpay.action.PersonAddPayTaShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    try {
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);
      saveToken(request);
      IPersonAddPayBS personAddPayBS = (IPersonAddPayBS) BSUtils
          .getBusinessService("personAddPayBS", this, mapping.getModuleConfig());
      List personAddPayList = personAddPayBS
          .findPersonAddPayListByCriterions(pagination);
      PersonAddPayAF personAddPayAF = new PersonAddPayAF();
      personAddPayAF.setPersonAddPayList(personAddPayList);
      request.setAttribute("personAddPayAF", personAddPayAF);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return mapping.findForward("to_personaddpay_show.jsp");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "addPayTail.id", "ASC",
          new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }

    return pagination;
  }
}
