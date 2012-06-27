package org.xpup.hafmis.syscollection.accounthandle.clearaccount.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accounthandle.clearaccount.bsinterface.IclearAccountBS;

public class MaintainOrgbusinessflowListAC extends LookupDispatchAction{
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.accounthandle.clearaccount.action.ClearAccountTaBalanceShowAC";
  
  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.print", "print"); 
    return map;
  }

  public ActionForward print(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        PAGINATION_KEY);
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
    .getAttribute("SecurityInfo");
    String collBankid=(String)pagination.getQueryCriterions().get("bank1");
    String specialType = (String) pagination.getQueryCriterions().get("specialType");
    String setDate="";
    String office="";
    String setDate1=(String)pagination.getQueryCriterions().get("startday");
    String setDate2=(String)pagination.getQueryCriterions().get("untilday");
    String bank1=(String)pagination.getQueryCriterions().get("bank1");
    
    IclearAccountBS clearaccountBS = (IclearAccountBS) BSUtils
    .getBusinessService("clearaccountBS", this, mapping.getModuleConfig());
    if(collBankid != null){
      String bankname=clearaccountBS.findCollBank(collBankid);
      request.setAttribute("bankname", bankname);
    }
    String userName="";
    String name = clearaccountBS.queryMakerPara();
    if (name.equals("1")) {
      userName = securityInfo.getUserName();
    } else {
      userName = securityInfo.getRealName();
    } 
    request.setAttribute("setDate", setDate1);  
    request.setAttribute("setDate2", setDate2);  
    request.setAttribute("office", office); 
    request.setAttribute("username", userName);
    return mapping.findForward("to_orgbusinessflow_report");
  }
}
