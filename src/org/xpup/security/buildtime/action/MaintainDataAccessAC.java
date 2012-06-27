package org.xpup.security.buildtime.action;

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
import org.xpup.security.buildtime.bizsrvc.IMaintainDataAccessBS;
import org.xpup.security.buildtime.form.DataAccessAF;
import org.xpup.security.buildtime.form.IdAF;
import org.xpup.security.common.domain.DataAccess;

public class MaintainDataAccessAC extends DispatchAction {

  public ActionForward preCreate(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    DataAccessAF dataAccessAF = new DataAccessAF();
    dataAccessAF.setActivity(DataAccessAF.CREATE);
    request.setAttribute("dataAccessAF", dataAccessAF);
    return mapping.findForward("data_access");
  }

  public ActionForward preModify(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    IdAF idAF = (IdAF) form;
    IMaintainDataAccessBS mo = (IMaintainDataAccessBS) BSUtils
        .getBusinessService("securityObjectBS", this, mapping.getModuleConfig());
    try {
      DataAccess dataAccess = mo.findDataAccess(idAF.getId());
      DataAccessAF dataAccessAF = new DataAccessAF();
      dataAccessAF.setDataAccess(dataAccess);
      dataAccessAF.setActivity(DataAccessAF.MODIFY);
      request.setAttribute("dataAccessAF", dataAccessAF);
    } catch (BusinessException ex) {
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(ex
          .getMessage(), false));
      saveErrors(request, messages);
      return mapping.findForward("show_data_accesses");
    }

    return mapping.findForward("data_access");
  }

  public ActionForward remove(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    IdAF idAF = (IdAF) form;
    IMaintainDataAccessBS mo = (IMaintainDataAccessBS) BSUtils
        .getBusinessService("securityObjectBS", this, mapping.getModuleConfig());

    try {
      mo.removeDataAccess(idAF.getId());
    } catch (BusinessException ex) {
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(ex
          .getMessage(), false));
      saveErrors(request, messages);
    }

    return mapping.findForward("show_data_accesses");
  }

}
