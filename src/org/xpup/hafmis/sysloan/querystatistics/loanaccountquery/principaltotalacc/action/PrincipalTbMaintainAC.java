package org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.principaltotalacc.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;

public class PrincipalTbMaintainAC extends LookupDispatchAction {


  protected Map getKeyMethodMap() {
    HashMap map = new HashMap();
    map.put("button.print", "print");
    map.put("button.back", "back");
    return map;
  }

  public ActionForward print(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    try {
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_principalta_cell");
  }
  public ActionForward back(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    return mapping.findForward("principalTaShowAC");
  }
}
