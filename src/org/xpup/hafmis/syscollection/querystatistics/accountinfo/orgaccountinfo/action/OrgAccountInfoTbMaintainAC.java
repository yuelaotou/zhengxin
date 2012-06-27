package org.xpup.hafmis.syscollection.querystatistics.accountinfo.orgaccountinfo.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.LookupDispatchAction;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.querystatistics.accountinfo.orgaccountinfo.bsinterface.IOrgAccountInfoBS;

public class OrgAccountInfoTbMaintainAC extends LookupDispatchAction {
  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.print", "print");

    return map;
  }

  public ActionForward print(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) throws Exception{

    SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
    Pagination pagination =(Pagination) request.getSession().getAttribute(OrgAccountInfoTbShowAC.PAGINATION_KEY); 
    IOrgAccountInfoBS orgAccountInfoBS = (IOrgAccountInfoBS) BSUtils
    .getBusinessService("orgAccountInfoBS", this, mapping.getModuleConfig());
    List printlist=orgAccountInfoBS.findOrgAccountInfoByMonthPrint(pagination, securityInfo);
    request.setAttribute("ORGPRINTLIST", printlist);
    return mapping.findForward("to_orgAccountprint");
  }
}
