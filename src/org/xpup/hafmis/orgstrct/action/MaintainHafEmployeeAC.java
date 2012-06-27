package org.xpup.hafmis.orgstrct.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.springframework.orm.hibernate3.HibernateObjectRetrievalFailureException;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.bizsrvc.IMaintainEmployeeBS;
import org.xpup.hafmis.orgstrct.domain.HafEmployee;
import org.xpup.hafmis.orgstrct.form.DataAuthzAF;
import org.xpup.hafmis.orgstrct.form.HafEmployeeAF;

public class MaintainHafEmployeeAC extends DispatchAction {

  public ActionForward preCreate(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    HafEmployeeAF hafEmployeeAF = new HafEmployeeAF();
    HafEmployee employee=new HafEmployee();
    employee.setBizDate(BusiTools.dateToString(new Date(), BusiConst.PUB_LONG_YMD_PATTERN));
    employee.setPlbizDate(BusiTools.dateToString(new Date(), BusiConst.PUB_LONG_YMD_PATTERN));  
    employee.setFbizDate(BusiTools.dateToString(new Date(), BusiConst.PUB_LONG_YMD_PATTERN));  
    hafEmployeeAF.setActivity(HafEmployeeAF.CREATE);
    hafEmployeeAF.setHafEmployee(employee);  
    request.setAttribute("hafEmployeeAF", hafEmployeeAF);

    return mapping.findForward("employee");
  }

  public ActionForward preModify(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    IdAF idAF = (IdAF) form;
    IMaintainEmployeeBS maintainEmployeeBS = (IMaintainEmployeeBS) BSUtils
        .getBusinessService("orgStructureBS", this, mapping.getModuleConfig());
    try {
      HafEmployee employee = maintainEmployeeBS.findHafEmployee(idAF.getId());
      HafEmployeeAF hafEmployeeAF = new HafEmployeeAF();
      hafEmployeeAF.setHafEmployee(employee);
      hafEmployeeAF.setActivity(HafEmployeeAF.MODIFY);
      request.setAttribute("hafEmployeeAF", hafEmployeeAF);
    } catch (BusinessException ex) {
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(ex
          .getMessage(), false));
      saveErrors(request, messages);
      return mapping.findForward("show_employees");
    }
    return mapping.findForward("employee");
  }

  public ActionForward remove(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    IdAF idAF = (IdAF) form;
    IMaintainEmployeeBS maintainEmployeeBS = (IMaintainEmployeeBS) BSUtils
        .getBusinessService("orgStructureBS", this, mapping.getModuleConfig());
    try {
      maintainEmployeeBS.removeHafEmployee(idAF.getId());
    } catch (BusinessException ex) {
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(ex
          .getMessage(), false));
      saveErrors(request, messages);
    }catch (HibernateObjectRetrievalFailureException hex) {
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("请将此人分配的角色撤消再删除！", false));
      saveErrors(request, messages);
    }

    return mapping.findForward("show_employees");
  }

  public ActionForward preAssignMenuItems(ActionMapping mapping,
      ActionForm form, HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    IdAF idAF = (IdAF) form;
    IMaintainEmployeeBS maintainEmployeeBS = (IMaintainEmployeeBS) BSUtils
        .getBusinessService("orgStructureBS", this, mapping.getModuleConfig());
    try {
      HafEmployee employee = maintainEmployeeBS.findHafEmployee(idAF.getId());
      request.setAttribute("employee", employee);
      request.setAttribute("id", idAF.getId());
    } catch (BusinessException ex) {
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(ex
          .getMessage(), false));
      saveErrors(request, messages);
      return mapping.findForward("show_employees");
    }

    return mapping.findForward("employee_menutree");
  }

  public ActionForward preAssignDataAccesses(ActionMapping mapping,
      ActionForm form, HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    IdAF idAF = (IdAF) form;
    IMaintainEmployeeBS maintainEmployeeBS = (IMaintainEmployeeBS) BSUtils
        .getBusinessService("orgStructureBS", this, mapping.getModuleConfig());
    try {
      HafEmployee employee = maintainEmployeeBS.findHafEmployee(idAF.getId());
      List duRelations = maintainEmployeeBS.findDuRelationsAvailable(idAF
          .getId());
      request.setAttribute("employee", employee);
      DataAuthzAF dataAuthzAF = new DataAuthzAF();
      dataAuthzAF.setDaRelations(duRelations);
      dataAuthzAF.setItemId(idAF.getId());
      request.getSession().setAttribute("dataAuthzAF", dataAuthzAF);
    } catch (BusinessException ex) {
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(ex
          .getMessage(), false));
      saveErrors(request, messages);
      return mapping.findForward("show_employees");
    }

    return mapping.findForward("employee_data_accesses");
  }

  public ActionForward preAssignOperations(ActionMapping mapping,
      ActionForm form, HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    IdAF idAF = (IdAF) form;
    IMaintainEmployeeBS maintainEmployeeBS = (IMaintainEmployeeBS) BSUtils
        .getBusinessService("orgStructureBS", this, mapping.getModuleConfig());
    try {
      HafEmployee employee = maintainEmployeeBS.findHafEmployee(idAF.getId());
      List available = maintainEmployeeBS.findOperationsAvailableByUserId(idAF.getId());
      List selected = maintainEmployeeBS.findOperationsByUserId(idAF.getId());
      request.setAttribute("employee", employee);
      request.setAttribute("available", available);
      request.setAttribute("selected", selected);
    } catch (BusinessException ex) {
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(ex
          .getMessage(), false));
      saveErrors(request, messages);
      return mapping.findForward("show_employees");
    }

    return mapping.findForward("employee_operations");
  }
  
  public ActionForward preAssignRoles(ActionMapping mapping,
      ActionForm form, HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    IdAF idAF = (IdAF) form;
    IMaintainEmployeeBS maintainEmployeeBS = (IMaintainEmployeeBS) BSUtils
        .getBusinessService("orgStructureBS", this, mapping.getModuleConfig());
    try {
      HafEmployee employee = maintainEmployeeBS.findHafEmployee(idAF.getId());
      List available = maintainEmployeeBS.findRolesAvailableByUserId(idAF.getId());
      List selected = maintainEmployeeBS.findRolesByUserId(idAF.getId());
      request.setAttribute("employee", employee);
      request.setAttribute("available", available);
      request.setAttribute("selected", selected);
    } catch (BusinessException ex) {
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(ex
          .getMessage(), false));
      saveErrors(request, messages);
      return mapping.findForward("show_employees");
    }

    return mapping.findForward("employee_roles");
  }

}
