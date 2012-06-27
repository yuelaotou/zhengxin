package org.xpup.hafmis.orgstrct.action;

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
import org.xpup.hafmis.common.form.IdStringAF;
import org.xpup.hafmis.orgstrct.bizsrvc.IMaintainEmployeeBS;

public class AssignRolesToUser extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    IdStringAF f = (IdStringAF) form;
    String userId = f.getItemId();
    List roleIds = f.getIdList();

    IMaintainEmployeeBS maintainEmployeeBS = (IMaintainEmployeeBS) BSUtils
        .getBusinessService("orgStructureBS", this, mapping.getModuleConfig());
    try {
      maintainEmployeeBS.assignRolesToHafEmployee(userId, roleIds);
    } catch (BusinessException ex) {
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(ex
          .getMessage(), false));
      saveErrors(request, messages);
    }
    return mapping.findForward("show_employees");
  }

}
