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
import org.xpup.hafmis.orgstrct.domain.enums.PropertyTypeEnum;
import org.xpup.hafmis.orgstrct.domain.enums.ValueTypeEnum;
import org.xpup.hafmis.orgstrct.form.OuptItemCriterionsAF;

public class FindOuptItemsAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    OuptItemCriterionsAF f = (OuptItemCriterionsAF) form;
    HashMap criterions = makeCriterionsMap(f);
    Pagination pagination = new Pagination(0, f.getPageSize(), 1,
        "ouptItem.id", OrderEnum.ASC, criterions);
    request.getSession().setAttribute(ShowOuptItemsAC.PAGINATION_KEY,
        pagination);
    f.reset(mapping, request);
    return mapping.findForward("show_oupt_items");
  }

  private HashMap makeCriterionsMap(OuptItemCriterionsAF form) {

    HashMap criterions = new HashMap();

    criterions.put("ouptId", form.getOuptId());
    if (StringUtils.isNotBlank(form.getName()))
      criterions.put("name", form.getName());
    if (form.getType() != -1)
      criterions.put("type", PropertyTypeEnum.getEnum(form.getType()));
    if (form.getValueType() != -1)
      criterions.put("valueType", ValueTypeEnum.getEnum(form.getValueType()));
    if (form.getNullable() != -1)
      criterions.put("nullable", Boolean
          .valueOf((form.getNullable() == 1) ? true : false));

    return criterions;
  }
}
