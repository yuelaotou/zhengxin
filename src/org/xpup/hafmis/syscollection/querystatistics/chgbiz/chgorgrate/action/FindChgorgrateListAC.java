package org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgorgrate.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.syscollection.paymng.paysure.bsinterface.IPaymentHeadBS;
import org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgorgrate.bsinterface.IChgorgrateBS;
import org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgorgrate.form.ChgorgrateAF;

/**
 * @author ÓÚÇì·á 2007-07-19
 */
public class FindChgorgrateListAC extends Action {

  private HashMap criterions = null;

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    // TODO Auto-generated method stub
    ChgorgrateAF chgorgrateAF = (ChgorgrateAF) form;
    criterions = makeCriterionsMap(chgorgrateAF);
    Pagination pagination = new Pagination(0, 10, 1, "chgOrgRate.id", "ASC",
        criterions);

    String paginationKey = getPaginationKey();
    request.getSession().setAttribute(paginationKey, pagination);
    modifyPagination(pagination);
    chgorgrateAF.reset(mapping, request);
    return mapping.findForward("to_ShowChgorgrateListAC");

  }

  protected String getPaginationKey() {

    return ShowChgorgrateListAC.PAGINATION_KEY;
  }

  protected HashMap makeCriterionsMap(ChgorgrateAF form) {
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

    String chgMonthStart = form.getChgMonthStart();
    if (chgMonthStart != null && !"".equals(chgMonthStart)) {
      m.put("chgMonthStart", chgMonthStart);
    }

    String chgMonthEnd = form.getChgMonthEnd();
    if (chgMonthEnd != null && !"".equals(chgMonthEnd)) {
      m.put("chgMonthEnd", chgMonthEnd);
    }

    String chgDateStart = form.getChgDateStart();
    if (chgDateStart != null && !"".equals(chgDateStart)) {
      m.put("chgDateStart", chgDateStart);
    }

    String chgDateEnd = form.getChgDateEnd();
    if (chgDateEnd != null && !"".equals(chgDateEnd)) {
      m.put("chgDateEnd", chgDateEnd);
    }

    Integer chgStatus = form.getChgStatus();
    if (chgStatus != null && chgStatus.longValue() != 0) {
      m.put("chgStatus", chgStatus);
    }

    return m;
  }

  protected void modifyPagination(Pagination pagination) {
  }

}
