package org.xpup.security.buildtime.action;

import java.io.Serializable;

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
import org.xpup.security.buildtime.bizsrvc.IMaintainMenuBS;
import org.xpup.security.buildtime.form.MenuItemAF;
import org.xpup.security.common.domain.MenuItem;

public class CreateMenuItemAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    MenuItemAF menuItemAF = (MenuItemAF) form;
    String activity = menuItemAF.getActivity();
    MenuItem menuItem = menuItemAF.getMenuItem();
    Serializable parentId = StringUtils.trimToNull(menuItemAF.getParentId());

    try {
      IMaintainMenuBS mm = (IMaintainMenuBS) BSUtils.getBusinessService(
          "securityObjectBS", this, mapping.getModuleConfig());
      if (MenuItemAF.CREATE.equals(activity)) {
        if (parentId != null) {
          MenuItem parent = new MenuItem();
          parent.setId(parentId);
          menuItem.setParentMenuItem(parent);
        }
        mm.createMenuItem(menuItem);
        String script = null;
        if (parentId == null) {
          script = "addRootMenuItem(\"" + menuItem.getId() + "\"," + "\""
              + menuItem.getCaption() + "\")";
        } else {
          script = "addSubMenuItem(\"" + parentId + "\"," + "\""
              + menuItem.getId() + "\"," + "\"" + menuItem.getCaption() + "\")";
        }
        request.setAttribute("script", script);
      } else if (MenuItemAF.MODIFY.equals(activity)) {
        String oldId = (String) menuItem.getId();
        mm.modifyMenuItem(menuItem);
        String id = (String) menuItem.getId();
        String script = "editMenuItem(\"" + oldId + "\"," + "\"" + id + "\","
            + "\"" + menuItem.getCaption() + "\")";
        request.setAttribute("script", script);
      }
    } catch (BusinessException ex) {
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(ex
          .getMessage(), false));
      saveErrors(request, messages);
      if (ex instanceof EntityNotFoundException) {
        request.setAttribute("script", "removeMenuItem(\"" + menuItem.getId()
            + "\")");
      }
    }

    return mapping.findForward("editor_right");
  }
}
