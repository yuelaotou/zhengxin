package org.xpup.hafmis.orgstrct.action;

import java.io.Serializable;
import java.util.List;

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
import org.xpup.hafmis.orgstrct.bizsrvc.IMaintainRoleBS;
import org.xpup.hafmis.orgstrct.form.DataAuthzAF;

public class AssignDataAccessesToRole extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    DataAuthzAF dataAuthzAF = (DataAuthzAF) form;
    IMaintainRoleBS maintainRoleBS = (IMaintainRoleBS) BSUtils
        .getBusinessService("orgStructureBS", this, mapping.getModuleConfig());
    try {
      List daRelations = dataAuthzAF.getDaRelationsSelected();
      Serializable roleId = dataAuthzAF.getItemId();
      maintainRoleBS.assignDrRelationsToHafRole(daRelations, roleId);
    } catch (BusinessException ex) {
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(ex
          .getMessage(), false));
      saveErrors(request, messages);
    }
    return mapping.findForward("show_roles");
  }

}
