package org.xpup.security.buildtime.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.security.buildtime.bizsrvc.IMaintainOperationBS;
import org.xpup.security.buildtime.form.IdAF;
import org.xpup.security.buildtime.form.OperationAF;
import org.xpup.security.common.domain.Operation;

public class MaintainOperationAC extends DispatchAction {

  public ActionForward preCreate(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    IMaintainOperationBS mo = (IMaintainOperationBS) BSUtils
        .getBusinessService("securityObjectBS", this, mapping.getModuleConfig());
    List groups = mo.findAllOperationGroups();
    OperationAF operationAF = new OperationAF();
    operationAF.setGroups(groups);
    operationAF.setActivity(OperationAF.CREATE);
    request.setAttribute("operationAF", operationAF);
    return mapping.findForward("operation");
  }

  public ActionForward preModify(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    IdAF idAF = (IdAF) form;
    IMaintainOperationBS mo = (IMaintainOperationBS) BSUtils
        .getBusinessService("securityObjectBS", this, mapping.getModuleConfig());

    try {
      Operation operation = mo.findOperation(idAF.getId());
      List groups = mo.findAllOperationGroups();

      OperationAF operationAF = new OperationAF();
      operationAF.setOperation(operation);
      operationAF.setGroups(groups);
      operationAF.setActivity(OperationAF.MODIFY);
      request.setAttribute("operationAF", operationAF);
    } catch (BusinessException ex) {
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(ex
          .getMessage(), false));
      saveErrors(request, messages);
      return mapping.findForward("show_operations");
    }

    return mapping.findForward("operation");
  }

  public ActionForward remove(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    IdAF idAF = (IdAF) form;
    IMaintainOperationBS mo = (IMaintainOperationBS) BSUtils
        .getBusinessService("securityObjectBS", this, mapping.getModuleConfig());

    try {
      mo.removeOperation(idAF.getId());
    } catch (BusinessException ex) {
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(ex
          .getMessage(), false));
      saveErrors(request, messages);
    }

    return mapping.findForward("show_operations");
  }

}
