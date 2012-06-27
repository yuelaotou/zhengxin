/**
 * Copy Right Information   : Goldsoft 
 * Project                  : OrgVerAccountBalanceFindAC
 * @Version                 : 1.0
 * @author                  : wangy
 * 生成日期                   :2007-12-19
 **/
package org.xpup.hafmis.syscollection.accounthandle.orgveraccountbalance.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accounthandle.orgveraccountbalance.bsinterface.IOrgVerAccountBalanceBS;
import org.xpup.hafmis.syscollection.accounthandle.orgveraccountbalance.dto.OrgVerAccountBalanceDTO;
import org.xpup.hafmis.syscollection.accounthandle.orgveraccountbalance.form.OrgVerAccountBalanceAF;

public class OrgVerAccountBalanceShowAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.accounthandle.orgveraccountbalance.action.OrgVerAccountBalanceShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    ActionMessages messages = null;
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);
      String orgId = (String) pagination.getQueryCriterions().get("orgId");
      String orgName = (String) pagination.getQueryCriterions().get("orgName");
      String accYear = (String) pagination.getQueryCriterions().get("accYear");
      IOrgVerAccountBalanceBS orgVerAccountBalanceBS = (IOrgVerAccountBalanceBS) BSUtils
          .getBusinessService("orgVerAccountBalanceBS", this, mapping
              .getModuleConfig());
      String bizDate = securityInfo.getUserInfo().getBizDate();
      String bizYear = bizDate.substring(0, 4);
      if (accYear == null || "".equals(accYear)) {
        accYear = bizYear;
        pagination.getQueryCriterions().put("accYear", accYear);
      }
      OrgVerAccountBalanceAF orgVerAccountBalanceAF = new OrgVerAccountBalanceAF();
      if (orgId != null && !orgId.equals("")) {
        orgVerAccountBalanceAF = orgVerAccountBalanceBS
            .queryOVAccountBalanceListByCriterions(bizYear, pagination,
                securityInfo);
      }
      
      // 结转年度下拉框
      List accYearList = new ArrayList();
      Iterator itr = orgVerAccountBalanceAF.getAccYearList().iterator();
      while (itr.hasNext()) {
        OrgVerAccountBalanceDTO dto = (OrgVerAccountBalanceDTO) itr.next();
        accYearList.add(new org.apache.struts.util.LabelValueBean(dto
            .getAccYear(), dto.getAccYear()));
      }
      request.getSession().setAttribute("accYearList", accYearList);

      orgVerAccountBalanceAF.setOrgId(orgId);
      orgVerAccountBalanceAF.setOrgName(orgName);
      orgVerAccountBalanceAF.setAccYear(accYear);
      request.setAttribute("orgVerAccountBalanceAF", orgVerAccountBalanceAF);
    } catch (BusinessException bex) {
      if (bex.getMessage().equals("没有此单位信息！")) {
        request.getSession().setAttribute("accYearList", new ArrayList());
      }// 当查不到单位时，重新设置下拉列表为空
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      saveErrors(request, messages);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return mapping.findForward("to_orgveraccountbalance_show");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      HashMap m = new HashMap();
      pagination = new Pagination(0, 10, 1, null, "DESC", m);
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
}
