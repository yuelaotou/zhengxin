package org.xpup.hafmis.orgstrct.action;

import java.util.Date;

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
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.bizsrvc.IMaintainEmployeeBS;
import org.xpup.hafmis.orgstrct.domain.HafEmployee;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.orgstrct.form.HafEmployeeAF;
import org.xpup.security.common.domain.User;
import org.xpup.security.wsf.util.UserUtils;

public class CreateHafEmployeeAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    HafEmployeeAF f = (HafEmployeeAF) form;
    HafEmployee hafEmployee = f.getHafEmployee();
    hafEmployee.getOrganizationUnit().setId(f.getOrgUnitId());
    HttpSession session=request.getSession(false);
    SecurityInfo securityInfo=(SecurityInfo)session.getAttribute("SecurityInfo");
    IMaintainEmployeeBS maintainEmployeeBS = (IMaintainEmployeeBS) BSUtils
        .getBusinessService("orgStructureBS", this, mapping.getModuleConfig());
    try {
      String activity = f.getActivity();
      if (HafEmployeeAF.CREATE.equals(activity)) {
//        User creator = UserUtils.getInstance(this).findUserByUsername(
//            request.getRemoteUser());
        User creator = UserUtils.getInstance(this).findUserByUsername(
            securityInfo.getUserInfo().getUsername());
        hafEmployee.setOperatorId(creator.getId());
        maintainEmployeeBS.createHafEmployee(hafEmployee);
      } else {
        maintainEmployeeBS.modifyHafEmployee(hafEmployee);
      }
    } catch (BusinessException ex) {
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(ex
          .getMessage(), false));
      saveErrors(request, messages);
      return mapping.findForward("employee");
    }
    if (f.isCreateAgain()) {
      HafEmployee newHafEmployee = new HafEmployee();
      newHafEmployee.setBizDate(BusiTools.dateToString(new Date(), BusiConst.PUB_LONG_YMD_PATTERN));
      newHafEmployee.setPlbizDate(BusiTools.dateToString(new Date(), BusiConst.PUB_LONG_YMD_PATTERN));  
      newHafEmployee.setFbizDate(BusiTools.dateToString(new Date(), BusiConst.PUB_LONG_YMD_PATTERN));  
      f.setHafEmployee(newHafEmployee);
      return mapping.findForward("employee");
    }

    return mapping.findForward("show_employees");
  }

}
