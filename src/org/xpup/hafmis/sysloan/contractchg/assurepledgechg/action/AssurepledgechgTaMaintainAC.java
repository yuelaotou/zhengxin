package org.xpup.hafmis.sysloan.contractchg.assurepledgechg.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import org.xpup.hafmis.common.form.IdAF;

public class AssurepledgechgTaMaintainAC extends LookupDispatchAction {

  protected Map getKeyMethodMap() {
    // TODO Auto-generated method stub
    Map map = new HashMap();
    map.put("button.update", "update");
    return map;
  }

  public ActionForward update(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    
    IdAF idAF = (IdAF)form;
    String contractId = idAF.getId().toString();
    request.getSession().setAttribute("contractId",contractId);
    return mapping.findForward("to_assurepledgechgTbShowAC");
  }

}
