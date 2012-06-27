package org.xpup.hafmis.orgstrct.action;

import java.io.Serializable;

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
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.orgstrct.bizsrvc.IMaintainOuptBS;
import org.xpup.hafmis.orgstrct.domain.OuptItem;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.orgstrct.form.OuptAF;
import org.xpup.hafmis.orgstrct.form.OuptItemAF;

public class MaintainOuptItemAC extends DispatchAction {

  public ActionForward preCreate(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    Pagination pagination = (Pagination) request.getSession().getAttribute(
        ShowOuptItemsAC.PAGINATION_KEY);
    Serializable ouptId = (Serializable) pagination.getQueryCriterions().get(
        "ouptId");
    if (ouptId == null)
      return mapping.findForward("show_oupt_items");

    OuptItemAF ouptItemAF = new OuptItemAF();
    ouptItemAF.setOuptId(ouptId);
    ouptItemAF.setActivity(OuptAF.CREATE);
    request.setAttribute("ouptItemAF", ouptItemAF);
    return mapping.findForward("oupt_item");
  }

  public ActionForward preModify(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    IdAF idAF = (IdAF) form;
    IMaintainOuptBS maintainOuptBS = (IMaintainOuptBS) BSUtils
        .getBusinessService("orgStructureBS", this, mapping.getModuleConfig());
    try {
      OuptItem ouptItem = maintainOuptBS.findOuptItem(idAF.getId());

      OuptItemAF ouptItemAF = new OuptItemAF();
      ouptItemAF.setOuptId(ouptItem.getOrgUnitPropTemplate().getId());
      ouptItemAF.setOuptItem(ouptItem);
      ouptItemAF.setActivity(OuptAF.MODIFY);
      request.setAttribute("ouptItemAF", ouptItemAF);
    } catch (BusinessException ex) {
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(ex
          .getMessage(), false));
      saveErrors(request, messages);
      return mapping.findForward("show_oupt_items");
    }

    return mapping.findForward("oupt_item");
  }

  public ActionForward remove(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    IdAF idAF = (IdAF) form;
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
    .getAttribute("SecurityInfo");
    IMaintainOuptBS maintainOuptBS = (IMaintainOuptBS) BSUtils
        .getBusinessService("orgStructureBS", this, mapping.getModuleConfig());
    try {
      maintainOuptBS.removeOuptItem(idAF.getId(),securityInfo);
    } catch (BusinessException ex) {
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(ex
          .getMessage(), false));
      saveErrors(request, messages);
    }

    return mapping.findForward("show_oupt_items");
  }

}
