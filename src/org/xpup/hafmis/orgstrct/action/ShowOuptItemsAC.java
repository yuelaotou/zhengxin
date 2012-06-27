package org.xpup.hafmis.orgstrct.action;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.xpup.common.enums.OrderEnum;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.orgstrct.bizsrvc.IMaintainOuptBS;
import org.xpup.hafmis.orgstrct.dto.OuptDTO;
import org.xpup.hafmis.orgstrct.form.OuptItemCriterionsAF;

public class ShowOuptItemsAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    Pagination pagination = getPagination(PAGINATION_KEY, request);
    PaginationUtils.updatePagination(pagination, request);

    IMaintainOuptBS maintainOuptBS = (IMaintainOuptBS) BSUtils
        .getBusinessService("orgStructureBS", this, mapping.getModuleConfig());

    try {
      OuptDTO ouptDTO = maintainOuptBS.findOuptItems(pagination);

      OuptItemCriterionsAF f = new OuptItemCriterionsAF();
      f.setOuptId(ouptDTO.getOupt().getId());
      f.setOuptName(ouptDTO.getOupt().getName());
      f.setOuptDescription(ouptDTO.getOupt().getDescription());
      request.getSession().setAttribute("ouptItemCriterionsAF", f);

      List ouptItems = ouptDTO.getOuptItems();
      request.setAttribute("ouptItems", ouptItems);
    } catch (BusinessException ex) {
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(ex
          .getMessage(), false));
      saveErrors(request, messages);
    }

    return mapping.findForward("oupt_items");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "ouptItem.id", OrderEnum.ASC,
          new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }

  public static final String PAGINATION_KEY = "org.xpup.hafmis.orgstrct.action.ShowOuptItemsAC";

}
