package org.xpup.hafmis.orgstrct.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.bizsrvc.IMaintainOuptBS;
import org.xpup.hafmis.orgstrct.domain.OrgUnitPropTemplate;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.orgstrct.form.OuptAF;
import org.xpup.security.buildtime.form.OperationAF;

public class CreateOuptAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    OuptAF f = (OuptAF) form;
    OrgUnitPropTemplate oupt = f.getOupt();

    IMaintainOuptBS maintainOuptBS = (IMaintainOuptBS) BSUtils
        .getBusinessService("orgStructureBS", this, mapping.getModuleConfig());
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      String activity = f.getActivity();
      if (OperationAF.CREATE.equals(activity)) {
        maintainOuptBS.createOupt(oupt,securityInfo);
      } else {
        maintainOuptBS.modifyOupt(oupt,securityInfo);
      }
    } catch (BusinessException ex) {
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(ex
          .getMessage(), false));
      saveErrors(request, messages);
      return mapping.findForward("oupt");
    }
    if (f.isCreateAgain()) {
      f.setOupt(new OrgUnitPropTemplate());
      return mapping.findForward("oupt");
    }
    return mapping.findForward("show_oupts");
  }

}
