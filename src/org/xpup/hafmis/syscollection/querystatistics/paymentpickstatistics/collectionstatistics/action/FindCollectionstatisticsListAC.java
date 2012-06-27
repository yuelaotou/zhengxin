package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.collectionstatistics.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.collectionstatistics.form.CollectionstatisticsAF;
/**
 * 
 * @author ÓÚÇì·á
 * 2007-07-31
 *
 */
public class FindCollectionstatisticsListAC extends Action{
  private HashMap criterions = null;
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    // TODO Auto-generated method stub
    CollectionstatisticsAF csAF = (CollectionstatisticsAF)form;
    criterions = makeCriterionsMap(csAF);
    Pagination pagination = new Pagination(0, 10, 1, "aa001.id", "ASC",
        criterions);
    String paginationKey = getPaginationKey();
    request.getSession().setAttribute(paginationKey, pagination);
    modifyPagination(pagination);
    csAF.reset(mapping, request);
    return mapping.findForward("to_showCollectionAC");
  }
  
  protected String getPaginationKey() {

    return ShowCollectionstatisticsListAC.PAGINATION_KEY;
  }

  protected HashMap makeCriterionsMap(CollectionstatisticsAF form) {
    HashMap m = new HashMap();
    String officeCode = form.getOfficeCode();
    if (officeCode != null && !"".equals(officeCode)) {
      m.put("officeCode", officeCode);
    }

    String collectionBank = form.getCollectionBank();
    if (collectionBank != null && !"".equals(collectionBank)) {
      m.put("collectionBank", collectionBank);
    }

    String orgId = form.getOrgId();
    if (orgId != null && !"".equals(orgId)) {
      m.put("orgId", orgId);
    }

    String orgName = form.getOrgName();
    if (orgName != null && !"".equals(orgName)) {
      m.put("orgName", orgName);
    }

    String orgCharacter = form.getOrgCharacter();
    if (orgCharacter != null && !"".equals(orgCharacter)) {
      m.put("orgCharacter", orgCharacter);
    }

    String deptInCharge = form.getDeptInCharge();
    if (deptInCharge != null && !"".equals(deptInCharge)) {
      m.put("deptInCharge", deptInCharge);
    }

    String startDate = form.getStartDate();
    if (startDate != null && !"".equals(startDate)) {
      m.put("startDate", startDate);
    }

    String endDate = form.getEndDate();
    if (endDate != null && !"".equals(endDate)) {
      m.put("endDate", endDate);
    }

    String region = form.getRegion();
    if (region != null && !"".equals(region)) {
      m.put("region", region);
    }

    return m;
  }

  protected void modifyPagination(Pagination pagination) {
  }

}
