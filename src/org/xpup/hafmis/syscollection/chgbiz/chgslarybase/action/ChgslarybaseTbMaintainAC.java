package org.xpup.hafmis.syscollection.chgbiz.chgslarybase.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.LookupDispatchAction;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.demo.bsinterface.IDemoBS;
import org.xpup.hafmis.demo.domain.entity.Demo;
import org.xpup.hafmis.demo.form.DemoAddAF;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.chgbiz.chgslarybase.bsinterface.IChgslarybaseBS;
import org.xpup.hafmis.syscollection.chgbiz.chgslarybase.form.ChgslarybaseNewAF;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPaymentTail;


/**
 * 
 * @author 吴洪涛 
 *2007-6-1
 */
public class ChgslarybaseTbMaintainAC extends LookupDispatchAction{
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.chgbiz.chgslarybase.action.ChgslarybaseTbShowAC";
 
  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.update", "update");
    map.put("button.delete", "remove");  
    map.put("button.deluse", "deluse"); 
    map.put("button.use", "use");
    map.put("button.referring.data", "referring");
    map.put("button.pproval.data", "pproval"); 
    return map;
  }

 

  
  public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    try{
    IdAF idaf=(IdAF)form;
    String id=idaf.getId().toString();
    Pagination pagination=new Pagination();
    IChgslarybaseBS chgslarybaseBS = (IChgslarybaseBS) BSUtils.getBusinessService("chgslarybaseBS", this,
        mapping.getModuleConfig());
    String  orgid=chgslarybaseBS.findOrgidById(id);
    String   type="2";
 
    pagination.getQueryCriterions().put("org.id", orgid);
    request.setAttribute("type","0");
    request.getSession().setAttribute(ChgslarybaseTaShowAC.PAGINATION_KEY, pagination);
  
  }catch(BusinessException e) {
    ActionMessages messages = new ActionMessages();
    messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(e
        .getMessage(), false));
    saveErrors(request, messages);
    return mapping.findForward("chgslarybaseTbShowAC.do");
  }
    return mapping.findForward("chgslarybaseTaShowAC.do");
  }

  
//删除AA202表中记录，条件是：id=选择的id 除AA203表中记录，条件是：变更清册id=选择的id
  public ActionForward remove(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionMessages messages =null;
    try{
      IdAF idaf=(IdAF)form;
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      String ip=securityInfo.getUserInfo().getUserIp();
      String name=securityInfo.getUserInfo().getUsername();
      String id=idaf.getId().toString();
      IChgslarybaseBS chgslarybaseBS = (IChgslarybaseBS) BSUtils.getBusinessService("chgslarybaseBS", this,
          mapping.getModuleConfig());
      chgslarybaseBS.deleteAllChgPaymentTailMaintain(new Integer (id),ip,name,securityInfo);
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("删除成功！",
          false));
      saveErrors(request, messages);
    }  catch(BusinessException e) {
        messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(e
          .getMessage(), false));
      saveErrors(request, messages);
      return mapping.findForward("chgslarybaseTbShowAC.do");
    }
   
    return mapping.findForward("chgslarybaseTbShowAC.do");
  }


  public ActionForward use(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    saveToken(request);
    ActionMessages messages =null;
    try{
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        PAGINATION_KEY);
    IdAF idaf=(IdAF)form;
    String id=idaf.getId().toString();
    String chgMonth=request.getParameter("chgMonth2");
    SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
    String ip=securityInfo.getUserInfo().getUserIp();
    String name=securityInfo.getUserInfo().getUsername();
    pagination.getQueryCriterions().put("id", id);
    pagination.getQueryCriterions().put("chgMonth", chgMonth);
    pagination.getQueryCriterions().put("ip", ip);
    pagination.getQueryCriterions().put("name", name);
    
    IChgslarybaseBS chgslarybaseBS = (IChgslarybaseBS) BSUtils.getBusinessService("chgslarybaseBS", this,
        mapping.getModuleConfig()); 
    
    chgslarybaseBS.useChgPaymentSalaryBaseMaintain(pagination);
    chgslarybaseBS.setAa202_wsh(idaf.getId().toString());
    messages=new ActionMessages();
    messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("启用成功！",
        false));
    saveErrors(request, messages);
  }  catch(BusinessException e) {
     messages = new ActionMessages();
    messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(e
        .getMessage(), false));
    saveErrors(request, messages);
    return mapping.findForward("chgslarybaseTbShowAC.do");
  }
 
  return mapping.findForward("chgslarybaseTbShowAC.do");
}
  

  public ActionForward deluse(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    saveToken(request);
    ActionMessages messages =null;
    try{
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        PAGINATION_KEY);
    IdAF idaf=(IdAF)form;
    String id=idaf.getId().toString();

    SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
    String ip=securityInfo.getUserInfo().getUserIp();
    String name=securityInfo.getUserInfo().getUsername();
    pagination.getQueryCriterions().put("id", id);
   
    pagination.getQueryCriterions().put("ip", ip);
    pagination.getQueryCriterions().put("name", name);
    
    IChgslarybaseBS chgslarybaseBS = (IChgslarybaseBS) BSUtils.getBusinessService("chgslarybaseBS", this,
        mapping.getModuleConfig()); 
    
    if(chgslarybaseBS.deluseChgPaymentSalaryBaseMaintain(pagination)){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("撤消启用成功！",
          false));
      saveErrors(request, messages);
    }
  }  catch(BusinessException e) {
     messages = new ActionMessages();
    messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(e
        .getMessage(), false));
    saveErrors(request, messages);
    return mapping.findForward("chgslarybaseTbShowAC.do");
  }
 
  return mapping.findForward("chgslarybaseTbShowAC.do");
}
  
  //提交数据
  public ActionForward referring(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionMessages messages =null;
    try{
      IdAF idaf=(IdAF)form;
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      String id=idaf.getId().toString(); 
      IChgslarybaseBS chgslarybaseBS = (IChgslarybaseBS) BSUtils.getBusinessService("chgslarybaseBS", this,
          mapping.getModuleConfig());
      String  orgid=chgslarybaseBS.queryOrgidByChgPaymentHeadID(id);
      String flag="2";
      chgslarybaseBS.PickInChgPaymentTailMaintain(id, orgid, securityInfo,flag);   
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("提交成功！",
          false));
      saveErrors(request, messages);
    }  catch(BusinessException e) {
       messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(e
          .getMessage(), false));
      saveErrors(request, messages);
      return mapping.findForward("chgslarybaseTbShowAC.do");
    }
   
    return mapping.findForward("chgslarybaseTbShowAC.do");
  }
  //撤消提交数据
  public ActionForward pproval(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionMessages messages =null;
    try{
      IdAF idaf=(IdAF)form;
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      String id=idaf.getId().toString();     
      IChgslarybaseBS chgslarybaseBS = (IChgslarybaseBS) BSUtils.getBusinessService("chgslarybaseBS", this,
          mapping.getModuleConfig());
      String  orgid=chgslarybaseBS.queryOrgidByChgPaymentHeadID(id);
      String flag="2";
      chgslarybaseBS.removePickInChgPaymentTailMaintain(id, orgid, securityInfo,flag);  
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("撤消提交成功！",
          false));
      saveErrors(request, messages);
    }  catch(BusinessException e) {
       messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(e
          .getMessage(), false));
      saveErrors(request, messages);
      return mapping.findForward("chgslarybaseTbShowAC.do");
    }
    return mapping.findForward("chgslarybaseTbShowAC.do");
  }
}

