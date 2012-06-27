package org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.principalgl.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.principalgl.form.PrincipalglTaAF;
/**
 * 
 * @author yuqf
 *2007-10-19
 */
public class PrincipalglTaFindAC extends Action{
  private HashMap criterions = null;
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    // TODO Auto-generated method stub
    PrincipalglTaAF principalglTaAF = (PrincipalglTaAF)form;
    criterions = makeCriterionsMap(principalglTaAF);
    Pagination pagination = new Pagination(0, 10, 1, null, "ASC",
        criterions);
    String paginationKey = getPaginationKey();
    request.getSession().setAttribute(paginationKey, pagination);
    modifyPagination(pagination);
    principalglTaAF.reset(mapping, request);
    return mapping.findForward("to_principalglTaFindAC");
  }
  protected String getPaginationKey() {

    return PrincipalglTaShowAC.PAGINATION_KEY;
  }
  protected HashMap makeCriterionsMap(PrincipalglTaAF form) {
    HashMap m = new HashMap();
    String radioValue = form.getRadioValue();
    if (radioValue != null && !"".equals(radioValue)) {
      m.put("radioValue", radioValue.trim());
    }

    String office = form.getOffice();
    if (office != null && !"".equals(office)) {
      m.put("office", office.trim());
    }

    String loanBank = form.getLoanBank();
    if (loanBank != null && !"".equals(loanBank)) {
      m.put("loanBank", loanBank.trim());
    }

    String developerName = form.getDeveloperName();
    if (developerName != null && !"".equals(developerName)) {
      m.put("developerName", developerName.trim());
    }

    String floorNum = form.getFloorNum();
    if (floorNum != null && !"".equals(floorNum)) {
      m.put("floorNum", floorNum.trim());
    }

    String assistantOrgName = form.getAssistantOrgName();
    if (assistantOrgName != null && !"".equals(assistantOrgName)) {
      m.put("assistantOrgName", assistantOrgName.trim());
    }

    String startDate = form.getStartDate();
    if (startDate != null && !"".equals(startDate)) {
      m.put("startDate", startDate.trim());
    }

    String endDate = form.getEndDate();
    if (endDate != null && !"".equals(endDate)) {
      m.put("endDate", endDate.trim());
    }

    String buyhouseorgid = form.getBuyhouseorgid();
    if (buyhouseorgid != null && !"".equals(buyhouseorgid)) {
      m.put("buyhouseorgid", buyhouseorgid.trim());
    }
    
    String floorid = form.getFloorid();
    if (floorid != null && !"".equals(floorid)) {
      m.put("floorid", floorid.trim());
    }
    m.put("key", "value");
    return m;
  }
  protected void modifyPagination(Pagination pagination) {
  }
}
