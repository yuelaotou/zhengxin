package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.searchLackInfo.action;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.searchLackInfo.form.SearchLackInfoListAF;

public class FindSearchLackInfoListAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    SearchLackInfoListAF f = (SearchLackInfoListAF) form;

    HashMap m = makeCriterionsMap(f);
    String officeCode = f.getOfficeCode();
    if (officeCode != null && !"".equals(officeCode.trim())) {
      m.put("officeCode", officeCode.trim());
    }
    String collectionBank = f.getCollectionBank();
    if (collectionBank != null && !"".equals(collectionBank.trim())) {
      m.put("collectionBank", collectionBank.trim());
    }
    String natureOfUnits = f.getNatureOfUnits();
    if (natureOfUnits != null && !"".equals(natureOfUnits.trim())) {
      m.put("natureOfUnits", natureOfUnits.trim());
    }
    String authorities = f.getAuthorities();
    if (authorities != null && !"".equals(authorities.trim())) {
      m.put("authorities", authorities.trim());
    }
    String orgId = f.getOrgId();
    if (orgId != null && !"".equals(orgId.trim())) {
      m.put("orgId", orgId.trim());
    }
    String orgName = f.getOrgName();
    if (orgName != null && !"".equals(orgName.trim())) {
      m.put("orgName", orgName.trim());
    }
    String chgMonthStart = f.getChgMonthStart();
    if (chgMonthStart != null && !"".equals(chgMonthStart.trim())) {
      m.put("chgMonthStart", chgMonthStart.trim());
    }
    String chgMonthEnd = f.getChgMonthEnd();
    if (chgMonthEnd != null && !"".equals(chgMonthEnd.trim())) {
      m.put("chgMonthEnd", chgMonthEnd.trim());
    }
    String inArea = f.getInArea();
    if (inArea != null && !"".equals(inArea.trim())) {
      m.put("inArea", inArea.trim());
    }
    String yearMonth = f.getYearMonth();
    if (yearMonth != null && !"".equals(yearMonth.trim())) {
      m.put("yearMonth", yearMonth.trim());
    }
    String orgratebeg = f.getOrgratebeg();
    if (orgratebeg != null && !"".equals(orgratebeg.trim())) {
      m.put("orgratebeg", orgratebeg.trim());
    }
    String orgrateend = f.getOrgrateend();
    if (orgrateend != null && !"".equals(orgrateend.trim())) {
      m.put("orgrateend", orgrateend.trim());
    }
    String empratebeg = f.getEmpratebeg();
    if (empratebeg != null && !"".equals(empratebeg.trim())) {
      m.put("empratebeg", empratebeg.trim());
    }
    String emprateend = f.getEmprateend();
    if (emprateend != null && !"".equals(emprateend.trim())) {
      m.put("emprateend", emprateend.trim());
    }

    Pagination pagination = new Pagination(0, 10, 1, "aa305.org_id", "DESC", m);
    String paginationKey = getPaginationKey();
    request.getSession().setAttribute(paginationKey, pagination);
    modifyPagination(pagination);

    f.reset(mapping, request);

    return mapping.findForward(getForword());
  }

  protected String getForword() {
    return "to_searchLackInfo_list";
  }

  protected String getPaginationKey() {
    return "org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.searchLackInfo.action.ShowSearchLackInfoListAC";
  }

  protected HashMap makeCriterionsMap(SearchLackInfoListAF form) {
    HashMap m = new HashMap();
    return m;
  }

  protected void modifyPagination(Pagination pagination) {
  }

}
