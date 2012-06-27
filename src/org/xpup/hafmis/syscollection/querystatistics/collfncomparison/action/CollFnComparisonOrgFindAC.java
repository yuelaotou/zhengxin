package org.xpup.hafmis.syscollection.querystatistics.collfncomparison.action;

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
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.querystatistics.collfncomparison.bsinterface.ICollFnComparisonBS;
import org.xpup.hafmis.syscollection.querystatistics.collfncomparison.form.CollFnComparisonOrgAccountShowAF;

/**
 * @author ÓÚÇì·á 2007-07-19
 */
public class CollFnComparisonOrgFindAC extends Action {

  private HashMap criterions = null;

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    // TODO Auto-generated method stub
    
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
    .getAttribute("SecurityInfo");
    String orgId=(String)request.getSession().getAttribute("orgId_gjp");
    
    request.getSession().setAttribute("CollFnComparison_orgId",null);
    request.getSession().setAttribute("orgId_gjp",null);
    request.getSession().setAttribute("orgName_gjp",null);
    CollFnComparisonOrgAccountShowAF collFnComparisonOrgAccountShowAF = (CollFnComparisonOrgAccountShowAF) form;
    criterions = makeCriterionsMap(collFnComparisonOrgAccountShowAF);
    Pagination pagination = new Pagination(0, 10, 1, "a1.sett_date,a1.id", "ASC",
        criterions);
    
    ICollFnComparisonBS collFnComparisonBS = (ICollFnComparisonBS) BSUtils
    .getBusinessService("collFnComparisonBS", this, mapping
        .getModuleConfig());
    
    List list = collFnComparisonBS.queryqcyeorg(pagination, securityInfo, orgId);
    pagination.getQueryCriterions().put("qcyelist", list);
    
    String paginationKey = getPaginationKey();
    request.getSession().setAttribute(paginationKey, pagination);
    modifyPagination(pagination);
    request.getSession().setAttribute("collFnComparisonOrgAccountShowAF", collFnComparisonOrgAccountShowAF);
    request.setAttribute("qcyelist", list);
    return mapping.findForward("to_collFnOrgComparisonShowAC");

  }

  protected String getPaginationKey() {

    return CollFnComparisonOrgAccountShowAC.PAGINATION_KEY;
  }

  protected HashMap makeCriterionsMap(CollFnComparisonOrgAccountShowAF form) {
    HashMap m = new HashMap();
    String officeCode = form.getOfficeCode();
    if (officeCode != null && !"".equals(officeCode)) {
      m.put("officeCode", officeCode);
    }

    String collectionBank = form.getBankId();
    if (collectionBank != null && !"".equals(collectionBank)) {
      m.put("bankId", collectionBank);
    }

    String orgId = form.getOrgId();
    if (orgId != null && !"".equals(orgId)) {
      m.put("orgId", orgId);
    }

    String orgName = form.getOrgName();
    if (orgName != null && !"".equals(orgName)) {
      m.put("orgName", orgName);
    }


    String timeSt = form.getTimeSt();
    if (timeSt != null && !"".equals(timeSt)) {
      m.put("timeSt", timeSt);
    }

    String timeEnd = form.getTimeEnd();
    if (timeEnd != null && !"".equals(timeEnd)) {
      m.put("timeEnd", timeEnd);
    }

  

    return m;
  }

  protected void modifyPagination(Pagination pagination) {
  }

}
