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
import org.xpup.hafmis.orgstrct.domain.OuptItem;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.orgstrct.form.OuptItemAF;
import org.xpup.security.buildtime.form.OperationAF;

public class CreateOuptItemAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    OuptItemAF f = (OuptItemAF) form;
    OuptItem ouptItem = f.getOuptItem();
    OrgUnitPropTemplate oupt = new OrgUnitPropTemplate();
    oupt.setId(f.getOuptId());
    ouptItem.setOrgUnitPropTemplate(oupt);

    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
    .getAttribute("SecurityInfo");
    IMaintainOuptBS maintainOuptBS = (IMaintainOuptBS) BSUtils
        .getBusinessService("orgStructureBS", this, mapping.getModuleConfig());
    try {
      String activity = f.getActivity();
      if (OperationAF.CREATE.equals(activity)) {
        maintainOuptBS.createOuptItem(ouptItem,securityInfo);
      } else {
        maintainOuptBS.modifyOuptItem(ouptItem,securityInfo);
      }
    } catch (BusinessException ex) {
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(ex
          .getMessage(), false));
      saveErrors(request, messages);
      return mapping.findForward("oupt_item");
    }
    if (f.isCreateAgain()) {
      OuptItem newOuptItem = new OuptItem();
      OrgUnitPropTemplate newOupt = new OrgUnitPropTemplate();
      newOupt.setId(f.getOuptId());
      newOuptItem.setOrgUnitPropTemplate(newOupt);
      f.setOuptItem(newOuptItem);
      return mapping.findForward("oupt_item");
    }
    return mapping.findForward("show_oupt_items");
  }

}
