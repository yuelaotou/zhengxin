package org.xpup.security.buildtime.action;

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
import org.xpup.security.buildtime.bizsrvc.IMaintainOperationBS;
import org.xpup.security.buildtime.form.OperationAF;
import org.xpup.security.common.domain.Operation;

public class CreateOperationAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    OperationAF f = (OperationAF) form;
    Operation operation = f.getOperation();

    IMaintainOperationBS mo = (IMaintainOperationBS) BSUtils
        .getBusinessService("securityObjectBS", this, mapping.getModuleConfig());
    try {
      String activity = f.getActivity();
      if (OperationAF.CREATE.equals(activity)) {
        mo.createOperation(operation);
        // Pagination p = (Pagination) request.getSession().getAttribute(
        // ShowOperationsAC.PAGINATION_KEY);
      } else {
        mo.modifyOperation(operation);
      }
    } catch (BusinessException ex) {
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(ex
          .getMessage(), false));
      saveErrors(request, messages);
      return mapping.findForward("operation");
    }
    if (f.isCreateAgain()) {
      f.setOperation(new Operation());
      return mapping.findForward("operation");
    }
    return mapping.findForward("show_operations");
  }

}
