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
import org.xpup.common.enums.SexEnum;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.domain.enums.DutyEnum;
import org.xpup.hafmis.orgstrct.form.HafEmployeeCriterionsAF;

public class FindHafEmployeesAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    HafEmployeeCriterionsAF f = (HafEmployeeCriterionsAF) form;
    HashMap criterions = makeCriterionsMap(f);
    Pagination pagination = new Pagination(0, f.getPageSize(), 1,
        "employee.id", OrderEnum.ASC, criterions);
    request.getSession().setAttribute(ShowHafEmployeesAC.PAGINATION_KEY,
        pagination);
    f.reset(mapping, request);
    return mapping.findForward("show_employees");
  }

  private HashMap makeCriterionsMap(HafEmployeeCriterionsAF form) {

    HashMap criterions = new HashMap();
    if (StringUtils.isNotBlank(form.getUsername()))
      criterions.put("username", form.getUsername());
    if (StringUtils.isNotBlank(form.getRealName()))
      criterions.put("realName", form.getRealName());
    if (StringUtils.isNotBlank(form.getEmail()))
      criterions.put("email", form.getEmail());

    if (form.getSex() != -1)
      criterions.put("sex", SexEnum.getEnum(form.getSex()));
    if (form.getDuty() != -1)
      criterions.put("duty", DutyEnum.getEnum(form.getDuty()));
    
    if (form.getEnabled() != -1)
      criterions.put("enabled", Boolean.valueOf((form.getEnabled() == 1) ? true
          : false));
    if (form.getLocked() != -1)
      criterions.put("locked", Boolean.valueOf((form.getLocked() == 1) ? true
          : false));

    return criterions;
  }
}
