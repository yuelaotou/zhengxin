package org.xpup.hafmis.sysloan.dataready.rate.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.LookupDispatchAction;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accounthandle.ratemng.bsinterface.IRatemngBS;
import org.xpup.hafmis.syscollection.accounthandle.ratemng.form.RatemngAF;
import org.xpup.hafmis.syscollection.common.domain.entity.HafInterestRate;
import org.xpup.hafmis.sysloan.dataready.rate.bsinterface.IRateBS;
import org.xpup.hafmis.sysloan.dataready.rate.form.RateNewAF;

public class RateTaNewAC extends LookupDispatchAction{
  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.add", "addRateInfo");
    map.put("button.update", "updateRateInfo");
    map.put("button.back", "backRateList");
    return map;
  }
  public ActionForward backRateList(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    return mapping.findForward("rateTaShowAC");
  }
  public ActionForward addRateInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    ActionMessages messages = null;
    try {
      RateNewAF rateNewAF=(RateNewAF)form;
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      IRateBS rateBS = (IRateBS) BSUtils.getBusinessService("rateBS", this,
          mapping.getModuleConfig());
      rateBS.addRateInfo(rateNewAF, securityInfo);
      rateNewAF.reset(mapping, request);
     }catch (BusinessException bex) {
       messages = new ActionMessages();
       messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
           .getLocalizedMessage().toString(), false));
       saveErrors(request, messages);
     }
    catch (Exception e) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("Ìí¼ÓÊ§°Ü£¡",
          false));
      saveErrors(request, messages);
    }
    return mapping.findForward("rateTaShowAC");
  }
  public ActionForward updateRateInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    ActionMessages messages = null;
    try {
      RateNewAF rateNewAF=(RateNewAF)form;
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      IRateBS rateBS = (IRateBS) BSUtils.getBusinessService("rateBS", this,
          mapping.getModuleConfig());
      rateBS.updateRateInfo(rateNewAF, securityInfo);
      rateNewAF.reset(mapping, request);
     }catch (BusinessException bex) {
       messages = new ActionMessages();
       messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
           .getLocalizedMessage().toString(), false));
       saveErrors(request, messages);
     }
    catch (Exception e) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("ÐÞ¸ÄÊ§°Ü£¡",
          false));
      saveErrors(request, messages);
    }
    return mapping.findForward("rateTaShowAC");
  }
}
