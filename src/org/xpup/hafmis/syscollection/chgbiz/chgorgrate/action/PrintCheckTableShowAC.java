package org.xpup.hafmis.syscollection.chgbiz.chgorgrate.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.syscollection.chgbiz.chgorgrate.bsinterface.IChgorgrateBS;
import org.xpup.hafmis.syscollection.chgbiz.chgorgrate.form.PrintCheckTableAF;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;

public class PrintCheckTableShowAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    IChgorgrateBS chgorgrateBS = (IChgorgrateBS) BSUtils.getBusinessService(
        "chgorgrateBS", this, mapping.getModuleConfig());
    PrintCheckTableAF f = (PrintCheckTableAF) form;
    String orgID = f.getOrgid();
    Org org = chgorgrateBS.queryOrgByorgID(orgID);
    if (org == null) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
          "无此单位信息！！！", false));
      saveErrors(request, messages);
      return new ActionForward("/printCheckTableForwardURLAC.do");
    }
    int count = chgorgrateBS.queryPersonCountByOrgID(orgID);
    request.setAttribute("org", org);
    request.setAttribute("count", count+"");
    return mapping.findForward("printchecktable_cell");
  }
}
