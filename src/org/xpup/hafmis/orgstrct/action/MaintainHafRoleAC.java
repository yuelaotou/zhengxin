package org.xpup.hafmis.orgstrct.action;

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
import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.orgstrct.bizsrvc.IMaintainRoleBS;
import org.xpup.hafmis.orgstrct.domain.HafRole;
import org.xpup.hafmis.orgstrct.form.DataAuthzAF;
import org.xpup.hafmis.orgstrct.form.HafRoleAF;

public class MaintainHafRoleAC extends DispatchAction {

  public ActionForward preCreate(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    HafRoleAF hafRoleAF = new HafRoleAF();
    hafRoleAF.setActivity(HafRoleAF.CREATE);
    request.setAttribute("hafRoleAF", hafRoleAF);

    return mapping.findForward("role");
  }

  public ActionForward preModify(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    IdAF idAF = (IdAF) form;
    IMaintainRoleBS maintainRoleBS = (IMaintainRoleBS) BSUtils
        .getBusinessService("orgStructureBS", this, mapping.getModuleConfig());
    try {
      HafRole role = maintainRoleBS.findHafRole(idAF.getId());
      HafRoleAF hafRoleAF = new HafRoleAF();
      hafRoleAF.setActivity(HafRoleAF.MODIFY);
      hafRoleAF.setHafRole(role);
      request.setAttribute("hafRoleAF", hafRoleAF);
    } catch (BusinessException ex) {
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(ex
          .getMessage(), false));
      saveErrors(request, messages);
      return mapping.findForward("show_roles");
    }
    return mapping.findForward("role");
  }

  public ActionForward remove(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    IdAF idAF = (IdAF) form;
    IMaintainRoleBS maintainRoleBS = (IMaintainRoleBS) BSUtils
        .getBusinessService("orgStructureBS", this, mapping.getModuleConfig());
    try {
      maintainRoleBS.removeHafRole(idAF.getId());
    } catch (BusinessException ex) {
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(ex
          .getMessage(), false));
      saveErrors(request, messages);
    }

    return mapping.findForward("show_roles");
  }

  public ActionForward preAssignMenuItems(ActionMapping mapping,
      ActionForm form, HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    IdAF idAF = (IdAF) form;
    IMaintainRoleBS maintainRoleBS = (IMaintainRoleBS) BSUtils
        .getBusinessService("orgStructureBS", this, mapping.getModuleConfig());
    try {
      HafRole role = maintainRoleBS.findHafRole(idAF.getId());
      request.setAttribute("role", role);
      request.setAttribute("id", idAF.getId());
    } catch (BusinessException ex) {
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(ex
          .getMessage(), false));
      saveErrors(request, messages);
      return mapping.findForward("show_roles");
    }

    return mapping.findForward("role_menutree");
  }

  public ActionForward preAssignDataAccesses(ActionMapping mapping,
      ActionForm form, HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    IdAF idAF = (IdAF) form;
    IMaintainRoleBS maintainRoleBS = (IMaintainRoleBS) BSUtils
        .getBusinessService("orgStructureBS", this, mapping.getModuleConfig());
    try {
      HafRole role = maintainRoleBS.findHafRole(idAF.getId());
      List drRelations = maintainRoleBS.findDrRelationsAvailable(idAF.getId());
      request.setAttribute("role", role);
      DataAuthzAF dataAuthzAF = new DataAuthzAF();
      dataAuthzAF.setDaRelations(drRelations);
      dataAuthzAF.setItemId(idAF.getId());
      request.getSession().setAttribute("dataAuthzAF", dataAuthzAF);
    } catch (BusinessException ex) {
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(ex
          .getMessage(), false));
      saveErrors(request, messages);
      return mapping.findForward("show_roles");
    }

    return mapping.findForward("role_data_accesses");
  }

  public ActionForward preAssignOperations(ActionMapping mapping,
      ActionForm form, HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    IdAF idAF = (IdAF) form;
    IMaintainRoleBS maintainRoleBS = (IMaintainRoleBS) BSUtils
        .getBusinessService("orgStructureBS", this, mapping.getModuleConfig());
    try {
      HafRole role = maintainRoleBS.findHafRole(idAF.getId());
      List available = maintainRoleBS.findOperationsAvailableByRoleId(idAF
          .getId());
      List selected = maintainRoleBS.findOperationsByRoleId(idAF.getId());
      request.setAttribute("role", role);
      request.setAttribute("available", available);
      request.setAttribute("selected", selected);
    } catch (BusinessException ex) {
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(ex
          .getMessage(), false));
      saveErrors(request, messages);
      return mapping.findForward("show_roles");
    }

    return mapping.findForward("role_operations");
  }

}
