package org.xpup.hafmis.sysfinance.accmng.totleacc.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

public class TotleaccMaintainAC extends LookupDispatchAction {

  protected Map getKeyMethodMap() {
    HashMap map = new HashMap();
    map.put("button.print","print");
    return map;
  }

  public ActionForward print(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    System.out.println("================>");
    return mapping.findForward("to_totleacc_cell");
  }
}