package org.xpup.hafmis.syscollection.querystatistics.collfncomparison.action;

import java.util.ArrayList;
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
import org.xpup.hafmis.syscollection.querystatistics.collfncomparison.bsinterface.ICollFnComparisonBS;
import org.xpup.hafmis.syscollection.querystatistics.collfncomparison.form.CollFnComparisonOrgAccountShowAF;

public class CollFnComparisonOrgAccountShowAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.querystatistics.collfncomparison.action.CollFnComparisonOrgAccountShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    CollFnComparisonOrgAccountShowAF collFnComparisonOrgAccountShowAF = (CollFnComparisonOrgAccountShowAF)request.getSession().getAttribute("collFnComparisonOrgAccountShowAF");
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      List list = new ArrayList();
      String type = (String) request.getAttribute("type");
      
      String orgId=(String)request.getSession().getAttribute("orgId_gjp");
      String orgName=(String)request.getSession().getAttribute("orgName_gjp");
      String paginationKey = getPaginationKey();
      Pagination pagination = getPagination(paginationKey, request);
      pagination.getQueryCriterions().put("securityInfo", securityInfo);
      PaginationUtils.updatePagination(pagination, request);
      ICollFnComparisonBS collFnComparisonBS = (ICollFnComparisonBS) BSUtils
          .getBusinessService("collFnComparisonBS", this, mapping
              .getModuleConfig());
      
      if (type!=null) {
        list = collFnComparisonBS.queryqcyeorg(pagination, securityInfo, orgId);
        pagination.getQueryCriterions().put("qcyelist", list);
      }
//      if(request.getAttribute("qcyelist")!=null){
//        list = (List)request.getAttribute("qcyelist");
//        pagination.getQueryCriterions().put("qcyelist", list);
//      }

      Object[] obj = collFnComparisonBS.findCollectionuseinfo(securityInfo,pagination,orgId);
      if(collFnComparisonOrgAccountShowAF==null){
        collFnComparisonOrgAccountShowAF=new CollFnComparisonOrgAccountShowAF();
      }
      if(orgId!=null&&!orgId.equals("")){
        collFnComparisonOrgAccountShowAF.setOrgId(orgId);
      }
      if(orgName!=null&&!orgName.equals("")){
        collFnComparisonOrgAccountShowAF.setOrgName(orgName);
      }
      collFnComparisonOrgAccountShowAF.setList((List) obj[0]);
      request.setAttribute("collFnComparisonOrgAccountShowAF",
          collFnComparisonOrgAccountShowAF);
      request.getSession().setAttribute("countList_gjp", (List) obj[1]);
      request.getSession().setAttribute(paginationKey, pagination);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_collfncomparisonorgaccount_show");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "a1.sett_date,a1.id", "ASC",
          new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }

  protected String getPaginationKey() {
    return PAGINATION_KEY;
  }

}
