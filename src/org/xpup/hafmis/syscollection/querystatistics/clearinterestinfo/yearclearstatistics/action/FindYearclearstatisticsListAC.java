package org.xpup.hafmis.syscollection.querystatistics.clearinterestinfo.yearclearstatistics.action;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.syscollection.querystatistics.clearinterestinfo.yearclearstatistics.form.YearclearstatisticsListAF;

public class FindYearclearstatisticsListAC extends Action{

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    YearclearstatisticsListAF f = (YearclearstatisticsListAF) form;

    HashMap m = makeCriterionsMap(f);

    String officeCode = f.getOfficeCode(); 
    if(officeCode != null && !"".equals(officeCode)){
      m.put("officeCode", officeCode);
    }
    
    String collectionBank = f.getCollectionBank();
    if(collectionBank != null && !"".equals(collectionBank)){
      m.put("collectionBank", collectionBank);
    }
    
    String orgId = f.getOrgId();
    if(orgId != null && !"".equals(orgId)){
      m.put("orgId", orgId);
    }
    
    String orgName = f.getOrgName();
    if(orgName != null && !"".equals(orgName)){
      m.put("orgName", orgName);
    }
    
    String chgYearStart = f.getChgYearStart();
    if(chgYearStart != null && !"".equals(chgYearStart)){
      m.put("chgYearStart", chgYearStart);
    }
    
    String chgYearEnd = f.getChgYearEnd();
    if(chgYearEnd != null && !"".equals(chgYearEnd)){
      m.put("chgYearEnd", chgYearEnd);
    }
    String isZero = f.getIsZero();
    if(isZero != null && !"".equals(isZero)){
      m.put("isZero", isZero);
    }
    Pagination pagination = new Pagination(0, 10, 1,
        "aa001.id", "DESC", m); 
    String paginationKey = getPaginationKey();
    request.getSession().setAttribute(paginationKey, pagination);
    modifyPagination(pagination);
    
    f.reset(mapping, request);

    return mapping.findForward(getForword());
  }

  protected String getForword() {
    return "to_yearclearstatistics_list";
  }

  protected String getPaginationKey() {
    return "org.xpup.hafmis.syscollection.querystatistics.clearinterestinfo.yearclearstatistics.action.ShowYearclearstatisticsListAC";
  }

  protected HashMap makeCriterionsMap(YearclearstatisticsListAF form) {
    HashMap m = new HashMap();
    return m;
  }

  protected void modifyPagination(Pagination pagination) {
  }

}
