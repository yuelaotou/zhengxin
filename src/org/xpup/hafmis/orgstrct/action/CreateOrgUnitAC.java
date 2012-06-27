package org.xpup.hafmis.orgstrct.action;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.exception.EntityNotFoundException;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.orgstrct.bizsrvc.IMaintainOrgUnitBS;
import org.xpup.hafmis.orgstrct.domain.OrgUnitProperty;
import org.xpup.hafmis.orgstrct.domain.OrganizationUnit;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.orgstrct.form.OrgUnitAF;
import org.xpup.hafmis.orgstrct.form.OupDecorator;

public class CreateOrgUnitAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {

    OrgUnitAF orgUnitAF = (OrgUnitAF) form;

    ActionMessages messages = new ActionMessages();
    IMaintainOrgUnitBS mm = (IMaintainOrgUnitBS) BSUtils.getBusinessService(
        "orgStructureBS", this, mapping.getModuleConfig());
    List attrs = orgUnitAF.getOrgUnit().getOrgUnitProperties();
    messages = validate(attrs);
    if (!messages.isEmpty()) {
      saveErrors(request, messages);
      List oupts = mm.findAllOupts();
      request.setAttribute("oupts", oupts);
      return mapping.findForward("orgunit");
    }

    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
    .getAttribute("SecurityInfo");
    
    String activity = orgUnitAF.getActivity();
    OrganizationUnit orgUnit = orgUnitAF.getOrgUnit();
    Serializable parentId = StringUtils.trimToNull(orgUnitAF.getParentId());
    try {
      if (OrgUnitAF.CREATE.equals(activity)) {
        if (parentId != null) {
          OrganizationUnit parent = new OrganizationUnit();
          parent.setId(parentId);
          orgUnit.setParent(parent);
        }
        mm.createOrgUnit(orgUnit,securityInfo);
    
        String script = null;
        if (parentId == null) {
          script = "addRootMenuItem(\"" + orgUnit.getId() + "\"," + "\""
              + orgUnit.getName() + "\")";
        } else {
          script = "addSubMenuItem(\"" + parentId + "\"," + "\""
              + orgUnit.getId() + "\"," + "\"" + orgUnit.getName() + "\")";
        }
        request.setAttribute("script", script);
      } else if (OrgUnitAF.MODIFY.equals(activity)) {
        String oldId = (String) orgUnit.getId();
        
        mm.modifyOrgUnit(orgUnit,securityInfo);
        
        String id = (String) orgUnit.getId();
        String script = "editMenuItem(\"" + oldId + "\"," + "\"" + id + "\","
            + "\"" + orgUnit.getName() + "\")";
        request.setAttribute("script", script);
      }
    } catch (BusinessException ex) {
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(ex
          .getMessage(), false));
      saveErrors(request, messages);
      if (ex instanceof EntityNotFoundException) {
        request.setAttribute("script", "removeMenuItem(\"" + orgUnit.getId()
            + "\")");
      }
    }

    return mapping.findForward("editor_right");
  }

  private ActionMessages validate(List props) {
    ActionMessages messages = new ActionMessages();
    for (int i = 0; i < props.size(); i++) {
      OrgUnitProperty oup = (OrgUnitProperty) props.get(i);
      String message = new OupDecorator(oup).validate();
      if (message != null) {
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(message,
            false));
      }
    }
    return messages;
  }
}
