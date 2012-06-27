package org.xpup.hafmis.sysfinance.common.biz.queryflow.action;

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
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlow;
import org.xpup.hafmis.syscollection.querystatistics.operationflow.orgoperationflow.form.OrgbusinessflowAF;
import org.xpup.hafmis.sysfinance.common.biz.queryflow.bsinterface.IQueryFlowBS;

/**
 * Copy Right Information : 显示归集流水的ShowAction Goldsoft Project :
 * CollectionFlowShowAC
 * 
 * @Version : v1.0
 * @author : 付云峰
 * @Date : 2007.10.10
 */
public class CollectionFlowShowAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysfinance.common.biz.queryflow.action.CollectionFlowShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      OrgbusinessflowAF orgbusinessflowAF  = new OrgbusinessflowAF();
      
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);

      IQueryFlowBS queryFlowBS = (IQueryFlowBS) BSUtils.getBusinessService(
          "queryFlowBS", this, mapping.getModuleConfig());

      orgbusinessflowAF = queryFlowBS.findOrgFlowListByCriterions(pagination,
          securityInfo);
      request.setAttribute("orgbusinessflowAF", orgbusinessflowAF);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_collectionflow_show");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      String settNum = (String) request.getAttribute("settNum");
      pagination = new Pagination(0, 10, 1, "id", "DESC", new HashMap(0));
      pagination.getQueryCriterions().put("notenum", settNum);
      request.getSession().setAttribute(paginationKey, pagination);
    }

    return pagination;
  }
}
