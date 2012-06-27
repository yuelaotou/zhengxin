package org.xpup.hafmis.sysloan.common.biz.buildingpop.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysloan.common.biz.assistantorgpop.action.AssistantorgpopShowAC;
import org.xpup.hafmis.sysloan.common.biz.assistantorgpop.form.AssistantorgpopAF;
import org.xpup.hafmis.sysloan.common.biz.buildingpop.form.BuildingPopShowAF;

/**
 * @author wshuang
 */
public class BuildingPopFindAC extends Action{
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    // TODO Auto-generated method stub
    BuildingPopShowAF buildingPopShowAF = (BuildingPopShowAF) form;
    HashMap criterions = makeCriterionsMap(buildingPopShowAF);
    Pagination pagination = new Pagination(0, 10, 1,
        "p6.head_id", "DESC", criterions);
    String paginationKey = BuildingPopShowAC.PAGINATION_KEY;
    request.getSession().setAttribute(paginationKey, pagination);

    buildingPopShowAF.reset(mapping, request);

    return mapping.findForward("buildingPopShowAC");
  }

  protected HashMap makeCriterionsMap(BuildingPopShowAF form) {
    HashMap m = new HashMap();
    String developerId = form.getDeveloperId();
    if(developerId != null && !"".equals(developerId.trim())){
      m.put("developerId", developerId.trim());
    }
    String developerName = form.getDeveloperName();
    if(developerName != null && !"".equals(developerName.trim())){
      m.put("developerName", developerName.trim());
    }
    return m;
  }
}
