package org.xpup.hafmis.sysloan.loancallback.baddebtdestroy.action; 


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
import org.xpup.hafmis.sysloan.loancallback.baddebtdestroy.bsinterface.IBadDebtDestroyBS;
import org.xpup.hafmis.sysloan.loancallback.baddebtdestroy.dto.BadDebtDestroyTaAFDTO;
import org.xpup.hafmis.sysloan.loancallback.loancallback.bsinterface.ILoancallbackBS;
import org.xpup.hafmis.sysloan.loancallback.loancallback.form.LoancallbackTaAF;

public class BadDebtDestroyTbMaintainAC extends LookupDispatchAction {
  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.delete", "delete");
    map.put("button.callback", "callback");
    map.put("button.print", "print");
    return map;
  }
  public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionMessages messages =null;
    SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
    try{
    messages=new ActionMessages();
    IdAF idaf=(IdAF)form;

    IBadDebtDestroyBS badDebtDestroyBS = (IBadDebtDestroyBS) BSUtils
    .getBusinessService("badDebtDestroyBS", this, mapping.getModuleConfig());
    badDebtDestroyBS.deleteCallbackInfos(idaf.getId().toString(), securityInfo);
    }catch(BusinessException b){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(b.getMessage(),
          false));
      saveErrors(request, messages);
    }
    return mapping.findForward("badDebtDestroyTbShowAC");
  }
  public ActionForward callback(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionMessages messages =null;
    SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
    try{
    messages=new ActionMessages();
    IdAF idaf=(IdAF)form;
    IBadDebtDestroyBS badDebtDestroyBS = (IBadDebtDestroyBS) BSUtils
    .getBusinessService("badDebtDestroyBS", this, mapping.getModuleConfig());
    badDebtDestroyBS.callbackCallbackInfo(idaf.getId().toString(), securityInfo);
    
    }catch(BusinessException b){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(b.getMessage(),
          false));
      saveErrors(request, messages);
    }
    return mapping.findForward("badDebtDestroyTbShowAC");
  }
  public ActionForward print(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionMessages messages =null;
    BadDebtDestroyTaAFDTO badDebtDestroyTaAFDTO = new BadDebtDestroyTaAFDTO();
    try{
      IdAF idaf=(IdAF)form;
      IBadDebtDestroyBS badDebtDestroyBS = (IBadDebtDestroyBS) BSUtils
      .getBusinessService("badDebtDestroyBS", this, mapping.getModuleConfig());
      badDebtDestroyTaAFDTO = badDebtDestroyBS.findPrintCallbackInfo(idaf.getId().toString());
      request.setAttribute("badDebtDestroyTaAFDTO", badDebtDestroyTaAFDTO);
    }catch(Exception b){
      b.printStackTrace();
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(b.getMessage(),
          false));
      saveErrors(request, messages);
    }
    return mapping.findForward("baddebtdestroy_cell");
  }
}