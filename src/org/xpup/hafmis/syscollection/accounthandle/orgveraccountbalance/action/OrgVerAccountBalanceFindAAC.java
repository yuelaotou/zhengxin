/**
 * Copy Right Information   : Goldsoft 
 * Project                  : OrgVerAccountBalanceFindAAC
 * @Version                 : 1.0
 * @author                  : wangy
 * 生成日期                   :2007-12-20
 **/
package org.xpup.hafmis.syscollection.accounthandle.orgveraccountbalance.action;

import org.xpup.common.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.xpup.hafmis.orgstrct.dto.SecurityInfo;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.xpup.hafmis.syscollection.accounthandle.orgveraccountbalance.bsinterface.IOrgVerAccountBalanceBS;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;

public class OrgVerAccountBalanceFindAAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache");
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    try {
      String orgId = (String) request.getParameter("orgId");// 单位ID
      IOrgVerAccountBalanceBS orgVerAccountBalanceBS = (IOrgVerAccountBalanceBS) BSUtils
          .getBusinessService("orgVerAccountBalanceBS", this, mapping
              .getModuleConfig());
      Org org = null;
      String orgName = "";
      if (orgId != null && !orgId.equals("")) {
        org = orgVerAccountBalanceBS.findOrgInfo(orgId, securityInfo);
        if (org != null) {
          orgName = org.getOrgInfo().getName();
        }

        String text = null;
        String paginationKey = getPaginationKey();
        Pagination pagination = (Pagination) request.getSession().getAttribute(
            paginationKey);
        pagination.getQueryCriterions().put("orgId", orgId);
        pagination.getQueryCriterions().put("orgName", orgName);
        pagination.getQueryCriterions().put("empId", "");// 清空查询条件，走AJAX时都查该单位下所有记录
        pagination.getQueryCriterions().put("empName", "");

        if (orgName == null || orgName.equals("") || orgName.length() < 1)
          orgName = "";

        text = "displays('" + orgId + "','" + orgName + "')";
        response.getWriter().write(text);
        response.getWriter().close();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  protected String getPaginationKey() {
    return OrgVerAccountBalanceShowAC.PAGINATION_KEY;
  }
}
