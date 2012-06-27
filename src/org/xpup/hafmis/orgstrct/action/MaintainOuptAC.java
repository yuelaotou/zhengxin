package org.xpup.hafmis.orgstrct.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.xpup.common.enums.OrderEnum;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.orgstrct.bizsrvc.IMaintainOuptBS;
import org.xpup.hafmis.orgstrct.domain.OrgUnitPropTemplate;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.orgstrct.form.OuptAF;

public class MaintainOuptAC extends DispatchAction {

  public ActionForward preCreate(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    OuptAF ouptAF = new OuptAF();
    ouptAF.setActivity(OuptAF.CREATE);
    request.setAttribute("ouptAF", ouptAF);
    return mapping.findForward("oupt");
  }

  public ActionForward preModify(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    IdAF idAF = (IdAF) form;
    IMaintainOuptBS maintainOuptBS = (IMaintainOuptBS) BSUtils
        .getBusinessService("orgStructureBS", this, mapping.getModuleConfig());
    try {
      OrgUnitPropTemplate oupt = maintainOuptBS.findOupt(idAF.getId());

      OuptAF ouptAF = new OuptAF();
      ouptAF.setOupt(oupt);
      ouptAF.setActivity(OuptAF.MODIFY);
      request.setAttribute("ouptAF", ouptAF);
    } catch (BusinessException ex) {
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(ex
          .getMessage(), false));
      saveErrors(request, messages);
      return mapping.findForward("show_oupts");
    }

    return mapping.findForward("oupt");
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
      maintainOuptBS.removeOupt(idAF.getId(), securityInfo);
    } catch (BusinessException ex) {
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(ex
          .getMessage(), false));
      saveErrors(request, messages);
    }

    return mapping.findForward("show_oupts");
  }

  public ActionForward preModifyDetail(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    IdAF idAF = (IdAF) form;

    IMaintainOuptBS maintainOuptBS = (IMaintainOuptBS) BSUtils
        .getBusinessService("orgStructureBS", this, mapping.getModuleConfig());
    try {
      maintainOuptBS.findOupt(idAF.getId());
      Pagination p = new Pagination(0, 10, 1, "ouptItem.id", OrderEnum.ASC,
          new HashMap(0));
      p.getQueryCriterions().put("ouptId", idAF.getId());
      request.getSession().setAttribute(ShowOuptItemsAC.PAGINATION_KEY, p);
    } catch (BusinessException ex) {
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(ex
          .getMessage(), false));
      saveErrors(request, messages);
      return mapping.findForward("show_oupts");
    }

    return mapping.findForward("show_oupt_items");
  }

}
