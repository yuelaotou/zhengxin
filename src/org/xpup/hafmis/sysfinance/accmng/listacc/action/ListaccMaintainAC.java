package org.xpup.hafmis.sysfinance.accmng.listacc.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

public class ListaccMaintainAC extends LookupDispatchAction {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysfinance.accmng.listacc.action.ListaccShowAC";
  
  protected Map getKeyMethodMap() {
    HashMap map = new HashMap();
    map.put("button.print", "print");
    return map;
  }

  public ActionForward print(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {

    HttpSession session = request.getSession();
    session.setAttribute(PAGINATION_KEY, null);
    resetToken(request);

    return mapping.findForward("to_listacc_cell");
  }
}