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
import org.xpup.security.buildtime.bizsrvc.IMaintainDataAccessBS;
import org.xpup.security.buildtime.form.DataAccessAF;
import org.xpup.security.common.domain.DataAccess;

public class CreateDataAccessAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    DataAccessAF f = (DataAccessAF) form;
    DataAccess dataAccess = f.getDataAccess();

    IMaintainDataAccessBS mo = (IMaintainDataAccessBS) BSUtils
        .getBusinessService("securityObjectBS", this, mapping.getModuleConfig());
    try {
      String activity = f.getActivity();
      if (DataAccessAF.CREATE.equals(activity)) {
        mo.createDataAccess(dataAccess);
        // Pagination p = (Pagination) request.getSession().getAttribute(
        // ShowOperationsAC.PAGINATION_KEY);
      } else {
        mo.modifyDataAccess(dataAccess);
      }
    } catch (BusinessException ex) {
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(ex
          .getMessage(), false));
      saveErrors(request, messages);
      return mapping.findForward("data_access");
    }
    if (f.isCreateAgain()) {
      f.setDataAccess(new DataAccess());
      return mapping.findForward("data_access");
    }
    return mapping.findForward("show_data_accesses");
  }

}
