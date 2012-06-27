package org.xpup.hafmis.syscollection.accountmng.accountopen.action;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.LookupDispatchAction;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accountmng.accountopen.bsinterface.IOrgOpenAccountBS;
import org.xpup.hafmis.syscollection.accountmng.accountopen.form.EmpChangeAF;
import org.xpup.hafmis.syscollection.common.domain.entity.TranInTail;
import org.xpup.hafmis.syscollection.tranmng.tranin.bsinterface.ITraninBS;
import org.xpup.hafmis.syscollection.tranmng.tranin.form.TraninAddAF;

public class EmpChangeEmpIdAC extends LookupDispatchAction {
  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.sure", "add");
    map.put("button.back", "back");
    return map;
  }

  public ActionForward back(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    return mapping.findForward("employee_kh_show");
  }

  public ActionForward add(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws BusinessException {
    ActionMessages messages = null;
    try {
      messages = new ActionMessages();
      EmpChangeAF empChangeAF = (EmpChangeAF) form;
      String empId=empChangeAF.getId().toString();
      String orgId=empChangeAF.getOrgId();
      String oldEmpId=empChangeAF.getOldEmpId();
//      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      IOrgOpenAccountBS orgOpenAccountBS = (IOrgOpenAccountBS) BSUtils
      .getBusinessService("orgOpenAccountBS", this, mapping
          .getModuleConfig());
      orgOpenAccountBS.changeEmpid(empId, orgId, oldEmpId);
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getLocalizedMessage().toString(), false));
      saveErrors(request, messages);
    } 
    catch (Exception e) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("²Ù×÷Ê§°Ü£¡",
          false));
      saveErrors(request, messages);
    }
    return mapping.findForward("employee_kh_show");
  }
}
