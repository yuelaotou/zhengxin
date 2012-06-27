package org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgperson.action;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

public class MaintainChgpersonOrgMaintainListAC extends LookupDispatchAction{

  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.querystatistics.businessflow.orgbusinessflow.action.ShowOrgbusinessflowListAC";
  
  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.print", "print"); 
    return map;
  }

  public ActionForward print(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

    return mapping.findForward("to_chgpersonorg_report");
  }
  
}
