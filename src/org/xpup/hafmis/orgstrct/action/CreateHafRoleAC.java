package org.xpup.hafmis.orgstrct.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.bizsrvc.IMaintainRoleBS;
import org.xpup.hafmis.orgstrct.domain.HafRole;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.orgstrct.form.HafRoleAF;
import org.xpup.security.common.domain.User;
import org.xpup.security.wsf.util.UserUtils;

public class CreateHafRoleAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    HafRoleAF f = (HafRoleAF) form;
    HafRole hafRole = f.getHafRole();
    hafRole.getOrganizationUnit().setId(f.getOrgUnitId());
    HttpSession session=request.getSession(false);
    SecurityInfo securityInfo=(SecurityInfo)session.getAttribute("SecurityInfo");
    IMaintainRoleBS maintainRoleBS = (IMaintainRoleBS) BSUtils
        .getBusinessService("orgStructureBS", this, mapping.getModuleConfig());
    try {
      String activity = f.getActivity();
      if (HafRoleAF.CREATE.equals(activity)) {
//        User creator = UserUtils.getInstance(this).findUserByUsername(
//            request.getRemoteUser());
        User creator = UserUtils.getInstance(this).findUserByUsername(
            securityInfo.getUserInfo().getUsername());
        hafRole.setOperatorId(creator.getId());
        maintainRoleBS.createHafRole(hafRole);
      } else {
        maintainRoleBS.modifyHafRole(hafRole);
      }
    } catch (BusinessException ex) {
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(ex
          .getMessage(), false));
      saveErrors(request, messages);
      return mapping.findForward("role");
    }
    if (f.isCreateAgain()) {
      HafRole newHafRole = new HafRole();
      f.setHafRole(newHafRole);
      return mapping.findForward("role");
    }
    return mapping.findForward("show_roles");
  }

}
