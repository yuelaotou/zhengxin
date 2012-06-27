package org.xpup.hafmis.orgstrct.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.actions.LookupDispatchAction;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.bizsrvc.IToSecurityMngBS;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.orgstrct.form.BankAssignUserAF;
import org.xpup.hafmis.orgstrct.form.CollBankTaAF;
import org.xpup.hafmis.orgstrct.form.RoleToSecurityAF;

public class CollBankTaEditMaintainAC extends LookupDispatchAction {

  protected Map getKeyMethodMap() {
    // TODO Auto-generated method stub
    Map map = new HashMap();
    map.put("button.sure", "sure");
    map.put("button.edit", "edit");
    map.put("button.back", "back");
    return map;
  }
  public ActionForward sure(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
      ActionMessages messages =null;
      try {
        SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
          CollBankTaAF collBankTaAF=(CollBankTaAF)form;
          IToSecurityMngBS toSecurityMngBS = (IToSecurityMngBS) BSUtils
          .getBusinessService("toSecurityMngBS", this, mapping.getModuleConfig());        
           toSecurityMngBS.addCollBankInfo(collBankTaAF.getOfficecode(), collBankTaAF.getBankname(),securityInfo,collBankTaAF.getCollectionbankacc(),collBankTaAF.getContactprsn(),collBankTaAF.getContacttel(),collBankTaAF.getCentername());
           request.getSession().removeAttribute(CollBankTaShowAC.PAGINATION_KEY);
           messages=new ActionMessages();
           messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("添加成功！",
               false));
           saveErrors(request, messages);         
    } catch(BusinessException b){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(b.getMessage(),
          false));
      saveErrors(request, messages);
    }
      catch (Exception bex) {
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("添加失败！",
          false));
      saveErrors(request, messages);
    }
    return mapping.findForward("show_collbank");
  }  
  public ActionForward edit(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
      ActionMessages messages =null;
      try {
        SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
          CollBankTaAF collBankTaAF=(CollBankTaAF)form;
          IToSecurityMngBS toSecurityMngBS = (IToSecurityMngBS) BSUtils
          .getBusinessService("toSecurityMngBS", this, mapping.getModuleConfig());
          toSecurityMngBS.editCollBank(collBankTaAF,securityInfo);
          request.getSession().removeAttribute(CollBankTaShowAC.PAGINATION_KEY);
          messages=new ActionMessages();
          messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("修改成功！",
              false));
          saveErrors(request, messages);
    } catch (BusinessException bex) {
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex.getMessage().toString(),
          false));
      saveErrors(request, messages);
    }
    return mapping.findForward("show_collbank");
  }  
  public ActionForward back(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    return mapping.findForward("show_collbank");
  }  

}
