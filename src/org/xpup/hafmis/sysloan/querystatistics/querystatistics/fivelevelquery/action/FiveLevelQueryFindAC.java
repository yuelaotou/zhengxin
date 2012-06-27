/**
 * Copy Right Information   : Goldsoft 
 * Project                  : FiveLevelQueryFindAC
 * @Version                 : 1.0
 * @author                  : wangy
 * 生成日期                   :2007-10-19
 **/
package org.xpup.hafmis.sysloan.querystatistics.querystatistics.fivelevelquery.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.fivelevelquery.form.FiveLevelQueryAF;

public class FiveLevelQueryFindAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try {
      FiveLevelQueryAF fiveLevelQueryAF = (FiveLevelQueryAF) form;
      HashMap criterions = makeCriterionsMap(fiveLevelQueryAF);
      Pagination pagination = new Pagination(0, 10, 1, "", "DESC", criterions);
      String paginationKey = getPaginationKey();
      request.getSession().setAttribute(paginationKey, pagination);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("fiveLevelQueryShowAC");
  }

  protected HashMap makeCriterionsMap(FiveLevelQueryAF form) {
    HashMap m = new HashMap();

    String mode = form.getMode();
    if (!(mode == null || mode.length() == 0)) {
      m.put("mode", form.getMode());
    }

    String office = form.getOffice();
    if (office != null && office.length() > 0) {
      m.put("office", office);
    }

    String loanBankName = form.getLoanBankName();
    if (loanBankName != null && loanBankName.length() > 0) {
      m.put("loanBankName", loanBankName);
    }

    String developerName = form.getDeveloperName();
    if (developerName != null && developerName.length() > 0) {
      m.put("developerName", developerName);
    }

    String developerId = form.getBuyhouseorgid();
    if (developerId != null && developerId.length() > 0) {
      m.put("developerId", developerId);
    }

    String floorid = form.getFloorid();
    if (floorid != null && floorid.length() > 0) {
      m.put("floorid", floorid);
    }

    String floorId = form.getFloorId();
    if ( floorId!= null && floorId.length() > 0) {
      m.put("floorId", floorId);
    }

    String assistantOrgName = form.getAssistantOrgName();
    if (assistantOrgName != null && assistantOrgName.length() > 0) {
      m.put("assistantOrgName", assistantOrgName);
    }
    return m;
  }

  protected String getPaginationKey() {
    return FiveLevelQueryShowAC.PAGINATION_KEY;
  }
}
