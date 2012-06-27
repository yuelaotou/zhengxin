package org.xpup.security.buildtime.action;

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
import org.xpup.security.buildtime.form.DataAccessCriterionsAF;

public class FindDataAccessesAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    DataAccessCriterionsAF f = (DataAccessCriterionsAF) form;
    HashMap criterions = makeCriterionsMap(f);
    Pagination pagination = new Pagination(0, f.getPageSize(), 1,
        "dataAccess.id", OrderEnum.ASC, criterions);
    request.getSession().setAttribute(ShowDataAccessesAC.PAGINATION_KEY,
        pagination);
    f.reset(mapping, request);
    return mapping.findForward("show_data_accesses");
  }

  private HashMap makeCriterionsMap(DataAccessCriterionsAF form) {
    HashMap criterions = new HashMap();

    if (StringUtils.isNotBlank(form.getName()))
      criterions.put("name", form.getName());
    if (StringUtils.isNotBlank(form.getInnerName()))
      criterions.put("innerName", form.getInnerName());

    return criterions;
  }

}
