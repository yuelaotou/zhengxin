package org.xpup.hafmis.orgstrct.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;

import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.orgstrct.bizsrvc.IToSecurityMngBS;

public class AdminSetNullAction extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      IdAF idAF = (IdAF) form;
      IToSecurityMngBS toSecurityMngBS = (IToSecurityMngBS) BSUtils
          .getBusinessService("toSecurityMngBS", this, mapping
              .getModuleConfig());
      String id = idAF.getId().toString();
      toSecurityMngBS.setNull(id);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("adminSetNullAction");
  }
}
