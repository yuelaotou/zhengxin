package org.xpup.hafmis.sysloan.querystatistics.querystatistics.assuremode.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.assuremode.form.AssureModeAF;

/**
 * @author ÍõÒ° 2007-10-11
 */
public class AssureModeFindAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try {
      AssureModeAF assureModeAF = (AssureModeAF) form;
      HashMap criterions = makeCriterionsMap(assureModeAF);
      Pagination pagination = new Pagination(0, 10, 1, "", "DESC", criterions);
      String paginationKey = getPaginationKey();
      request.getSession().setAttribute(paginationKey, pagination);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("assureModeShowAC");
  }

  protected HashMap makeCriterionsMap(AssureModeAF form) {
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

    String floorId = form.getFloorid();
    if (floorId != null && floorId.length() > 0) {
      m.put("floorId", floorId);
    }

    String floorNum = form.getFloorNum();
    if (floorNum != null && floorNum.length() > 0) {
      m.put("floorNum", floorNum);
    }

    String assistantOrgName = form.getAssistantOrgName();
    if (assistantOrgName != null && assistantOrgName.length() > 0) {
      m.put("assistantOrgName", assistantOrgName);
    }
    return m;
  }

  protected String getPaginationKey() {
    return AssureModeShowAC.PAGINATION_KEY;
  }
}
