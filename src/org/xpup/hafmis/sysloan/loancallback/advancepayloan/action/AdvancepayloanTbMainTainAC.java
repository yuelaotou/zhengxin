package org.xpup.hafmis.sysloan.loancallback.advancepayloan.action;

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
import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loancallback.advancepayloan.bsinterface.IAdvancepayloanBS;
import org.xpup.hafmis.sysloan.loancallback.advancepayloan.form.AdvancepayloanTaAF;

public class AdvancepayloanTbMainTainAC extends LookupDispatchAction{

  protected Map getKeyMethodMap() {
    // TODO Auto-generated method stub
    Map map = new HashMap();
    map.put("button.delete", "delete");
    return map;
  }
  public ActionForward delete(ActionMapping mapping,
      ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    ActionMessages messages = null;
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      IAdvancepayloanBS advancepayloanBS = (IAdvancepayloanBS) BSUtils
      .getBusinessService("advancepayloanBS", this, mapping
          .getModuleConfig());
    IdAF af = (IdAF)form;
    String id = af.getId().toString();//PKId
    advancepayloanBS.delete(id, securityInfo);
    saveErrors(request, messages);
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      saveErrors(request, messages);
    }catch(Exception e){
      e.printStackTrace();
    }
    return mapping.findForward("to_advancepayloanTbShowAC");
  }
}
