package org.xpup.hafmis.sysloan.common.biz.buildingpop.action;

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
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.biz.buildingpop.bsinterface.IBuildingPopBS;
import org.xpup.hafmis.sysloan.common.biz.buildingpop.form.BuildingPopShowAF;

public class BuildingPopShowAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.common.biz.buildingpop.action.BuildingPopShowAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    BuildingPopShowAF af = new BuildingPopShowAF();
    try {
      String developerId = request.getParameter("developerId");
      String indexs=request.getParameter("indexs");
      if(indexs != null){
        request.getSession().setAttribute("indexs", indexs);
      }
      String objInput=request.getParameter("objInput");
      if(objInput != null){
        request.getSession().setAttribute("objInput", objInput);
      }
      SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
      IBuildingPopBS buildingPopBS = (IBuildingPopBS) BSUtils
        .getBusinessService("buildingPopBS", this, mapping.getModuleConfig());
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      pagination.getQueryCriterions().put("developerId", developerId);
      PaginationUtils.updatePagination(pagination, request);
      
      List list = buildingPopBS.findBuildingList(pagination, securityInfo);
      af.setList(list);
      request.setAttribute("buildingPopShowAF", af);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_buildingPop_show");
  }
  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "p6.head_id", "ASC",
          new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }   

    return pagination;
  }
}
