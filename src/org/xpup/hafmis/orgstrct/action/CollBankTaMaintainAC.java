package org.xpup.hafmis.orgstrct.action;

import java.util.ArrayList;
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
import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.orgstrct.bizsrvc.IToSecurityMngBS;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.orgstrct.form.BankAssignUserAF;
import org.xpup.hafmis.orgstrct.form.CollBankTaAF;
import org.xpup.hafmis.orgstrct.form.RoleToSecurityAF;

public class CollBankTaMaintainAC extends LookupDispatchAction {

  protected Map getKeyMethodMap() {
    // TODO Auto-generated method stub
    Map map = new HashMap();
    map.put("button.add", "add");
    map.put("button.update", "update");
    map.put("button.cancel", "cancel");
    map.put("button.delcancel", "delcancel");
    return map;
  }
  
  public ActionForward add(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
      try {
          SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo"); 
          List officelist=new ArrayList();
          CollBankTaAF collBankTaAF = new CollBankTaAF();
          IToSecurityMngBS toSecurityMngBS = (IToSecurityMngBS) BSUtils
          .getBusinessService("toSecurityMngBS", this, mapping.getModuleConfig());
          officelist=toSecurityMngBS.findOffice(securityInfo);
          collBankTaAF.setOfficelist(officelist);
          collBankTaAF.setType("1");
          request.setAttribute("collBankTaAF", collBankTaAF);
          
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("show_collbank_edit");
  }  
  public ActionForward update(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
      try {
          SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo"); 
          CollBankTaAF collBankTaAF = new CollBankTaAF();
          IdAF idAF=(IdAF)form;
          List officelist=new ArrayList();
          IToSecurityMngBS toSecurityMngBS = (IToSecurityMngBS) BSUtils
          .getBusinessService("toSecurityMngBS", this, mapping.getModuleConfig());
          officelist=toSecurityMngBS.findOffice(securityInfo);
          CollBank collBank = toSecurityMngBS.findCollBank(idAF.getId().toString());
          collBankTaAF.setOfficelist(officelist);
          collBankTaAF.setBankname(collBank.getCollBankName());
          collBankTaAF.setBankid(idAF.getId().toString());
          collBankTaAF.setCollectionbankacc(collBank.getCollectionbankacc());
          collBankTaAF.setContactprsn(collBank.getContactprsn());
          collBankTaAF.setContacttel(collBank.getContacttel());
          collBankTaAF.setType("2");
          request.setAttribute("collBankTaAF", collBankTaAF);
    } catch (Exception bex) {
      bex.printStackTrace();
    }
    return mapping.findForward("show_collbank_edit");
  }  
  public ActionForward cancel(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
      ActionMessages messages =null;
      try {
        SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo"); 
        IdAF idAF=(IdAF)form;
        IToSecurityMngBS toSecurityMngBS = (IToSecurityMngBS) BSUtils
        .getBusinessService("toSecurityMngBS", this, mapping.getModuleConfig());
        toSecurityMngBS.cancelCollBank(idAF.getId().toString(),securityInfo);
          messages=new ActionMessages();
          messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("作废成功！",
              false));
          saveErrors(request, messages);
    } catch (Exception bex) {
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("作废成功！",
          false));
      saveErrors(request, messages);
    }
    return mapping.findForward("show_collbank");
  }  
  public ActionForward delcancel(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
      ActionMessages messages =null;
      try {
        SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo"); 
        IdAF idAF=(IdAF)form;
        IToSecurityMngBS toSecurityMngBS = (IToSecurityMngBS) BSUtils
        .getBusinessService("toSecurityMngBS", this, mapping.getModuleConfig());
        toSecurityMngBS.delCancelCollBank(idAF.getId().toString(),securityInfo);
          messages=new ActionMessages();
          messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("撤销作废成功！",
              false));
          saveErrors(request, messages);
    } catch (Exception bex) {
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("撤销作废失败！",
          false));
      saveErrors(request, messages);
    }
    return mapping.findForward("show_collbank");
  }  

}
