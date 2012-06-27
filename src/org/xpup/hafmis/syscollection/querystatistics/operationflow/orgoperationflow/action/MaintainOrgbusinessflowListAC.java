package org.xpup.hafmis.syscollection.querystatistics.operationflow.orgoperationflow.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;

public class MaintainOrgbusinessflowListAC extends LookupDispatchAction{
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgperson.action.ShowChgpersonOrgListAC";
  
  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.print", "print"); 
    return map;
  }

  public ActionForward print(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    SecurityInfo securityInfo = (SecurityInfo)request.getSession().getAttribute("SecurityInfo");
    Pagination pagination = (Pagination)request.getSession().getAttribute(getPaginationKey());
    String collBank = (String) pagination.getQueryCriterions().get("bankName");
    String operator = securityInfo.getUserInfo().getUsername();
    String bizDate = securityInfo.getUserInfo().getBizDate();
    request.setAttribute("operator",operator);
    request.setAttribute("bizDate", bizDate);
    request.setAttribute("collBank", collBank);
    return mapping.findForward("to_orgbusinessflow_report");
  }
  protected String getPaginationKey(){
    return ShowOrgbusinessflowListAC.PAGINATION_KEY;
  }
}
