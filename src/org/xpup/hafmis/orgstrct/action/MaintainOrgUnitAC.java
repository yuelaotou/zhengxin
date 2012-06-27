package org.xpup.hafmis.orgstrct.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.util.RequestUtils;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.exception.EntityNotFoundException;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.bizsrvc.IMaintainOrgUnitBS;
import org.xpup.hafmis.orgstrct.domain.OrganizationUnit;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.orgstrct.form.OrgUnitAF;

public class MaintainOrgUnitAC extends DispatchAction {

  public ActionForward preCreate(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    IMaintainOrgUnitBS mm = (IMaintainOrgUnitBS) BSUtils.getBusinessService(
        "orgStructureBS", this, mapping.getModuleConfig());
    List oupts = mm.findAllOupts();
    request.setAttribute("oupts", oupts);
    String parentId = request.getParameter("parentId");
    OrgUnitAF orgUnitAF = new OrgUnitAF();
    orgUnitAF.setParentId(parentId);
    request.getSession().setAttribute("orgUnitAF", orgUnitAF);

    return mapping.findForward("orgunit");
  }

  public ActionForward preModify(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    String orgUnitId = request.getParameter("orgUnitId");
    IMaintainOrgUnitBS mm = (IMaintainOrgUnitBS) BSUtils.getBusinessService(
        "orgStructureBS", this, mapping.getModuleConfig());
    try {
      List oupts = mm.findAllOupts();
      request.setAttribute("oupts", oupts);
      OrganizationUnit orgUnit = mm.findOrgUnit(orgUnitId);
      OrgUnitAF orgUnitAF = new OrgUnitAF();
      orgUnitAF.setOrgUnit(orgUnit);
      orgUnitAF.setActivity(OrgUnitAF.MODIFY);
      request.getSession().setAttribute("orgUnitAF", orgUnitAF);
    } catch (BusinessException ex) {
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(ex
          .getMessage(), false));
      saveErrors(request, messages);
      if (ex instanceof EntityNotFoundException) {
        request.setAttribute("script", "removeMenuItem(\"" + orgUnitId + "\")");
      }
      return mapping.findForward("editor_right");
    }

    return mapping.findForward("orgunit");
  }

  public ActionForward remove(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    String orgUnitId = request.getParameter("orgUnitId");

    IMaintainOrgUnitBS mm = (IMaintainOrgUnitBS) BSUtils.getBusinessService(
        "orgStructureBS", this, mapping.getModuleConfig());
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      mm.removeOrgUnit(orgUnitId,securityInfo);
      request.setAttribute("script", "removeMenuItem(\"" + orgUnitId + "\")");
    } catch (BusinessException ex) {
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(ex
          .getMessage(), false));
      saveErrors(request, messages);
    }
    return mapping.findForward("editor_right");
  }

  public ActionForward changeOupt(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    String ouptId = request.getParameter("ouptId");
    OrgUnitAF orgUnitAF = (OrgUnitAF) request.getSession().getAttribute(
        "orgUnitAF");
    RequestUtils.populate(orgUnitAF, request);

    IMaintainOrgUnitBS mm = (IMaintainOrgUnitBS) BSUtils.getBusinessService(
        "orgStructureBS", this, mapping.getModuleConfig());
    if (StringUtils.trimToNull(ouptId) == null) {
      orgUnitAF.getOrgUnit().setAttributes(new ArrayList(0));
      orgUnitAF.getOrgUnit().setParameters(new ArrayList(0));
    } else {
      if (orgUnitAF.getActivity().equals(OrgUnitAF.MODIFY)) {
        OrganizationUnit orgUnit = mm.findOrgUnit(orgUnitAF.getOrgUnit()
            .getId());
        if (ouptId.equals(orgUnit.getOuptId())) {
          orgUnitAF.getOrgUnit().setAttributes(
              mm.findAllAttributes(orgUnit.getId()));
          orgUnitAF.getOrgUnit().setParameters(
              mm.findAllParameters(orgUnit.getId()));
        } else {
          orgUnitAF.getOrgUnit().setAttributes(
              mm.getAllAttributesFromOupt(ouptId));
          orgUnitAF.getOrgUnit().setParameters(
              mm.getAllParametersFromOupt(ouptId));
        }
      } else {
        orgUnitAF.getOrgUnit().setAttributes(
            mm.getAllAttributesFromOupt(ouptId));
        orgUnitAF.getOrgUnit().setParameters(
            mm.getAllParametersFromOupt(ouptId));
      }
    }
    List oupts = mm.findAllOupts();
    request.setAttribute("oupts", oupts);
    orgUnitAF.getOrgUnit().setOuptId(ouptId);
    return mapping.findForward("orgunit");
  }

}
