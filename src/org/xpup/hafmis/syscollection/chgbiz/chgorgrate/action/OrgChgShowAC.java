package org.xpup.hafmis.syscollection.chgbiz.chgorgrate.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.syscollection.chgbiz.chgorgrate.form.OrgChgAF;

public class OrgChgShowAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    OrgChgAF orgChgAF = new OrgChgAF();
    request.setAttribute("orgChgAF", orgChgAF);
    return mapping.findForward("orgchgta");
  }
}
