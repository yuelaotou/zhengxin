package org.xpup.hafmis.syscollection.accounthandle.clearaccount.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accounthandle.clearaccount.bsinterface.IclearAccountBS;
import org.xpup.hafmis.syscollection.accounthandle.clearaccount.form.ClearAccountBalanceForm;

/**
 * @author ¿Ó≈Ù 2007-6-29
 */
public class ClearAccountBalanceMaintainAC extends LookupDispatchAction {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.accounthandle.clearaccount.action.ClearAccountTaBalanceShowAC";

  public static final String PAGINATION_KEY1 = "org.xpup.hafmis.syscollection.querystatistics.operationflow.orgoperationflow.action.ShowOrgbusinessflowListAC";

  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.infor", "info");
    map.put("button.print", "prints");
    return map;
  }

  public ActionForward info(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        PAGINATION_KEY);
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    // String orgcode = (String) pagination.getQueryCriterions().get("orgId");
    String orgname = (String) pagination.getQueryCriterions().get("orgName");
    String bstype = (String) pagination.getQueryCriterions().get("bis_type1");
    String setmonthstart = (String) pagination.getQueryCriterions().get(
        "startday");
    String setmonthend = (String) pagination.getQueryCriterions().get(
        "untilday");
    if (setmonthstart == null && setmonthend == null) {
      setmonthstart = securityInfo.getUserInfo().getBizDate();
    }
    pagination.getQueryCriterions().put("orgid",
        pagination.getQueryCriterions().get("orgId"));
    pagination.getQueryCriterions().put("orgname", orgname);
    pagination.getQueryCriterions().put("bstype", bstype);
    pagination.getQueryCriterions().put("setmonthstart", setmonthstart);
    pagination.getQueryCriterions().put("setmonthend", setmonthend);
    request.getSession().setAttribute(PAGINATION_KEY1, pagination);
    return mapping.findForward("showOrgFlow");
  }

  public ActionForward prints(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ClearAccountBalanceForm clearAccountBalanceDTO = (ClearAccountBalanceForm) form;
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        PAGINATION_KEY);
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    String collBankid = (String) pagination.getQueryCriterions().get("bank1");
    String specialType = (String) pagination.getQueryCriterions().get(
        "specialType");
    String setDate = "";
    String office = "";
    String setDate1 = (String) pagination.getQueryCriterions().get("startday");
    String setDate2 = (String) pagination.getQueryCriterions().get("untilday");
    String bank1 = (String) pagination.getQueryCriterions().get("bank1");
    if (setDate1 == null && setDate2 == null) {
      setDate = securityInfo.getUserInfo().getBizDate();
    } else if (setDate1 != null && setDate2 != null) {
      setDate = setDate1 + "-" + setDate1;
    } else if (setDate1 != null) {
      setDate = setDate1;
    } else if (setDate2 != null) {
      setDate = setDate2;
    }
    IclearAccountBS clearaccountBS = (IclearAccountBS) BSUtils
        .getBusinessService("clearaccountBS", this, mapping.getModuleConfig());
    if (collBankid != null) {
      String bankname = clearaccountBS.findCollBank(collBankid);
      request.setAttribute("bankname", bankname);
    }
    List list = securityInfo.getAllCenterList();
    if (list != null) {
      OfficeDto dto = (OfficeDto) list.get(0);
      office = dto.getOfficeName();
    }

    String userName = "";
    String name = clearaccountBS.queryMakerPara();
    if (name.equals("1")) {
      userName = securityInfo.getUserName();
    } else {
      userName = securityInfo.getRealName();
    }

    request.setAttribute("userName", userName);
    request.setAttribute("setDate", setDate);
    request.setAttribute("bank1", bank1);
    request.setAttribute("office", office);
    request.setAttribute("clearAccountBalanceForm", clearAccountBalanceDTO);
    if (specialType != null) {
      return mapping.findForward("to_print_month");
    }
    return mapping.findForward("to_print");
  }
}
