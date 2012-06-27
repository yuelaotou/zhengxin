package org.xpup.hafmis.syscollection.querystatistics.collfncomparison.action;

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
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.querystatistics.collfncomparison.bsinterface.ICollFnComparisonBS;

/**
 * 根据单位编号、单位名称、模糊查询单位信息
 */
public class OrgpopTaShowAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.querystatistics.collfncomparison.action.OrgpopTaShowAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    try {

      String orgid=request.getParameter("orgid");
      String orgname=request.getParameter("orgname");
      if(orgid!=null&&orgname!=null){
        request.getSession().removeAttribute(PAGINATION_KEY);
        request.getSession().setAttribute("OrgpopTaShowAC_orgid", orgid);
        request.getSession().setAttribute("OrgpopTaShowAC_orgname", orgname);
      }else{
        orgid = (String) request.getSession().getAttribute("OrgpopTaShowAC_orgid");
        orgname = (String) request.getSession().getAttribute("OrgpopTaShowAC_orgname");
      }
      
      
      String indexs = request.getParameter("indexs");
      if (indexs != null)
        request.getSession().setAttribute("indexs", indexs);

      Pagination pagination = getPagination(PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);
      pagination.getQueryCriterions().put("orgid", orgid);
      pagination.getQueryCriterions().put("orgname", orgname);

      ICollFnComparisonBS collFnComparisonBS = (ICollFnComparisonBS) BSUtils
          .getBusinessService("collFnComparisonBS", this, mapping
              .getModuleConfig());
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      List orgpopList = collFnComparisonBS.findOrgpopList(pagination,
          securityInfo);
      request.setAttribute("orgpopList", orgpopList);
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
          "没有您要查询的信息！", false));
      saveErrors(request, messages);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward(getForword());
  }

  protected String getForword() {
    return "to_orgpop_show";
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "orgs.id", "ASC",
          new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }   

    return pagination;
  }
}


