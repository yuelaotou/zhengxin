package org.xpup.hafmis.sysloan.dataready.ratetype.action;

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
import org.xpup.hafmis.sysloan.dataready.ratetype.bsinterface.IRateTypeBS;
import org.xpup.hafmis.sysloan.dataready.ratetype.form.RateTypeNewAF;

public class RateTypeTaSaveAC extends LookupDispatchAction{
  /**
   * @author wangshuang
   */
  protected Map getKeyMethodMap() {
    HashMap map = new HashMap();
    map.put("button.add", "add");
    map.put("button.update", "update");
    map.put("button.back", "back");
    return map;
  }
  public ActionForward add(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    ActionMessages messages = null;
    RateTypeNewAF af = (RateTypeNewAF)form;
    try {
      IRateTypeBS rateTypeBS = (IRateTypeBS) BSUtils.getBusinessService("rateTypeBS", this,
          mapping.getModuleConfig());
      SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
      rateTypeBS.saveRateType(af, securityInfo);
      af.reset(mapping, request);
      af.setButtonType("add");
      request.setAttribute("rateTypeNewAF", af);
    } catch (BusinessException e) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("Ìí¼ÓÊ§°Ü£¡",
          false));
      saveErrors(request, messages);
      return mapping.findForward("to_rate_new");
    }
    return mapping.findForward("to_ratetype_new");
  }
  public ActionForward update(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    ActionMessages messages = null;
    RateTypeNewAF af = (RateTypeNewAF)form;
    try {
      IRateTypeBS rateTypeBS = (IRateTypeBS) BSUtils.getBusinessService("rateTypeBS", this,
          mapping.getModuleConfig());
      SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
      rateTypeBS.updateRateType(af, securityInfo);
    } catch (BusinessException e) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("ÐÞ¸ÄÊ§°Ü£¡",
          false));
      saveErrors(request, messages);
      return mapping.findForward("to_rateType_new");
    }
    return mapping.findForward("rateTypeTaShowAC");
  }
  public ActionForward back(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    return mapping.findForward("rateTypeTaShowAC");
  }
}
