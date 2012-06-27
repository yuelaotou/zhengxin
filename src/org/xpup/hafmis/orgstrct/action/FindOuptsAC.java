package org.xpup.hafmis.orgstrct.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.enums.OrderEnum;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.form.OuptCriterionsAF;

public class FindOuptsAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    OuptCriterionsAF f = (OuptCriterionsAF) form;
    HashMap criterions = makeCriterionsMap(f);
    Pagination pagination = new Pagination(0, f.getPageSize(), 1, "oupt.id",
        OrderEnum.ASC, criterions);
    request.getSession().setAttribute(ShowOuptsAC.PAGINATION_KEY, pagination);
    f.reset(mapping, request);
    return mapping.findForward("show_oupts");
  }

  private HashMap makeCriterionsMap(OuptCriterionsAF form) {
    HashMap criterions = new HashMap();

    if (StringUtils.isNotBlank(form.getName()))
      criterions.put("name", form.getName());
    if (StringUtils.isNotBlank(form.getDescription()))
      criterions.put("description", form.getDescription());

    return criterions;
  }

}
