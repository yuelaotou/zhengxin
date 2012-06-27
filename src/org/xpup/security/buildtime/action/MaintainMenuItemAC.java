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
import org.xpup.common.exception.EntityNotFoundException;
import org.xpup.common.util.BSUtils;
import org.xpup.security.buildtime.bizsrvc.IMaintainMenuBS;
import org.xpup.security.buildtime.form.MenuItemAF;
import org.xpup.security.common.domain.MenuItem;

public class MaintainMenuItemAC extends DispatchAction {

  public ActionForward preCreate(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    String parentId = request.getParameter("parentId");

    MenuItemAF menuItemAF = new MenuItemAF();
    menuItemAF.setParentId(parentId);
    request.setAttribute("menuItemAF", menuItemAF);
    return mapping.findForward("menuitem");
  }

  public ActionForward preModify(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    String menuItemId = request.getParameter("menuItemId");
    try {
      IMaintainMenuBS mm = (IMaintainMenuBS) BSUtils.getBusinessService(
          "securityObjectBS", this, mapping.getModuleConfig());
      MenuItem menuItem = mm.findMenuItem(menuItemId);
      MenuItemAF menuItemAF = new MenuItemAF();
      menuItemAF.setMenuItem(menuItem);
      menuItemAF.setActivity(MenuItemAF.MODIFY);
      request.setAttribute("menuItemAF", menuItemAF);
    } catch (BusinessException ex) {
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(ex
          .getMessage(), false));
      saveErrors(request, messages);
      if (ex instanceof EntityNotFoundException) {
        request
            .setAttribute("script", "removeMenuItem(\"" + menuItemId + "\")");
      }
      return mapping.findForward("editor_right");
    }

    return mapping.findForward("menuitem");
  }

  public ActionForward remove(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    String menuItemId = request.getParameter("menuItemId");

    try {
      IMaintainMenuBS mm = (IMaintainMenuBS) BSUtils.getBusinessService(
          "securityObjectBS", this, mapping.getModuleConfig());
      mm.removeMenuItem(menuItemId);
      request.setAttribute("script", "removeMenuItem(\"" + menuItemId + "\")");
    } catch (BusinessException ex) {
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(ex
          .getMessage(), false));
      saveErrors(request, messages);
    }
    return mapping.findForward("editor_right");
  }

}
