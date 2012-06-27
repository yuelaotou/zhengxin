package org.xpup.hafmis.sysloan.systemmaintain.hafoperateLog.action;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

public class HafoperatorLogMaintainAC extends LookupDispatchAction{
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.accounthandle.adjustaccount.action.ClearAccountTaShowAC";
  
  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.print", "print");
    return map;
  }
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    return mapping.findForward("goto_print");
  }
  
}
