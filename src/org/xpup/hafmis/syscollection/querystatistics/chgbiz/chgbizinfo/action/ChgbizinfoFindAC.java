package org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgbizinfo.action;

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
import org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgbizinfo.form.ChgbizinfoAF;
import org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgorgrate.bsinterface.IChgorgrateBS;
import org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgorgrate.form.ChgorgrateAF;

/**
 * @author ÓÚÇì·á 2007-07-19
 */
public class ChgbizinfoFindAC extends Action {

  private HashMap criterions = null;

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    // TODO Auto-generated method stub
    ChgbizinfoAF chgbizinfoAF = (ChgbizinfoAF) form;
    criterions = makeCriterionsMap(chgbizinfoAF);
    Pagination pagination = new Pagination(0, 10, 1, "chgOrgRate.id", "ASC",
        criterions);

    String paginationKey = getPaginationKey();
    request.getSession().setAttribute(paginationKey, pagination);
    modifyPagination(pagination);
    chgbizinfoAF.reset(mapping, request);
    return mapping.findForward("to_ShowChgbizinfoListAC");

  }

  protected String getPaginationKey() {

    return ChgbizinfoShowAC.PAGINATION_KEY;
  }

  protected HashMap makeCriterionsMap(ChgbizinfoAF form) {
    HashMap m = new HashMap();
   

    String orgId = form.getOrgId();
    if (orgId != null && !"".equals(orgId)) {
      m.put("orgId", orgId);
    }

    String orgName = form.getChgType();
    if (orgName != null && !"".equals(orgName)) {
      m.put("orgName", orgName);
    }
    String chgMonth = form.getChgMonth();
    if (chgMonth != null && !"".equals(chgMonth)) {
      m.put("chgMonth", chgMonth);
    }

    String bizDate = form.getBizDate();
    if (bizDate != null && !"".equals(bizDate)) {
      m.put("bizDate", bizDate);
    }


    

    return m;
  }

  protected void modifyPagination(Pagination pagination) {
  }

}
