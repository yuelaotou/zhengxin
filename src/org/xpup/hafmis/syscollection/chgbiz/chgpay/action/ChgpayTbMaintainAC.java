package org.xpup.hafmis.syscollection.chgbiz.chgpay.action;

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
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.chgbiz.chgpay.bsinterface.IChgpayBS;
import org.xpup.hafmis.syscollection.chgbiz.chgslarybase.bsinterface.IChgslarybaseBS;



/**
 * 
 * @author 吴洪涛 
 *2007-6-1
 */
public class ChgpayTbMaintainAC extends LookupDispatchAction{
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.chgbiz.chgpay.action.ChgpayTbSouwAC";

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

  /**
   * 维护的撤消提交
   * @author 徐世龙
   * @2008-02-26
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  public ActionForward pproval(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)throws Exception{
    
    ActionMessages messages = null;
    try {
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      IdAF idaf=(IdAF)form;
      //id为AA202.id
      String id=idaf.getId().toString();
      IChgpayBS chgpayBS = (IChgpayBS) BSUtils.getBusinessService("chgpayBS", this,
          mapping.getModuleConfig());
      String  orgid=chgpayBS.queryOrgidByChgPaymentHeadID(id);
      //传c2说明是维护页面的提交
      chgpayBS.removePickInChgPaymentTailMaintain(id, orgid,securityInfo,"c2"); 
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("撤消提交成功！",
          false));
      saveErrors(request, messages);
    }  catch(BusinessException e) {
       messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(e
          .getMessage(), false));
      saveErrors(request, messages);
      return mapping.findForward("chgpayTbSouwAC.do");   
    }
    return mapping.findForward("chgpayTbSouwAC.do");   
  }
  
  /**
   * 维护提交
   * @author 徐世龙
   * @2008-02-26
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  public ActionForward referring(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)throws Exception{
    
    ActionMessages messages = null;
    try {
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      IdAF idaf=(IdAF)form;
      //id为AA202.id
      String id=idaf.getId().toString();
      IChgpayBS chgpayBS = (IChgpayBS) BSUtils.getBusinessService("chgpayBS", this,
          mapping.getModuleConfig());
      String  orgid=chgpayBS.queryOrgidByChgPaymentHeadID(id);
      //传2说明是维护页面的提交
      chgpayBS.PickInChgPaymentTailMaintain(id, orgid,securityInfo,"t2"); 
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("提交成功！",
          false));
      saveErrors(request, messages);
    }  catch(BusinessException e) {
       messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(e
          .getMessage(), false));
      saveErrors(request, messages);
      return mapping.findForward("chgpayTbSouwAC.do");  
    }
    return mapping.findForward("chgpayTbSouwAC.do");   
  }
  
  public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    try{
    IdAF idaf=(IdAF)form;
    String id=idaf.getId().toString();
    Pagination pagination=new Pagination();
    IChgpayBS chgpayBS = (IChgpayBS) BSUtils.getBusinessService("chgpayBS", this,
        mapping.getModuleConfig());
    String  orgid=chgpayBS.findOrgidById(id);
    String   type="0";
    pagination.getQueryCriterions().put("org.id", orgid);
   pagination.getQueryCriterions().put("type", type);
 //   request.setAttribute("type","0");
    request.getSession().setAttribute(ChgpayTaSouwAC.PAGINATION_KEY, pagination);
  
  }catch(BusinessException e) {
    ActionMessages messages = new ActionMessages();
    messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(e
        .getMessage(), false));
    saveErrors(request, messages);
    return mapping.findForward("chgpayTbSouwAC.do");
  }
    return mapping.findForward("chgpayTaSouwAC.do");
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
      IChgpayBS chgpayBS = (IChgpayBS) BSUtils.getBusinessService("chgpayBS", this,
          mapping.getModuleConfig());
      chgpayBS.deleteAllChgPaymentTailMaintain(new Integer (id),ip,name,securityInfo);
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("删除成功！",
          false));
      saveErrors(request, messages);
    }  catch(BusinessException e) {
       messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(e
          .getMessage(), false));
      saveErrors(request, messages);
      return mapping.findForward("chgpayTbSouwAC.do");
    }
   
    return mapping.findForward("chgpayTbSouwAC.do");
  }


  public ActionForward use(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    saveToken(request);
    ActionMessages messages =null;
    try{
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        PAGINATION_KEY);
    IdAF idaf=(IdAF)form;
    SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
    String ip=securityInfo.getUserInfo().getUserIp();
    String name=securityInfo.getUserInfo().getUsername();
    String id=idaf.getId().toString();
    String chgMonth=request.getParameter("chgMonth2");
    pagination.getQueryCriterions().put("name", name);
    pagination.getQueryCriterions().put("id", id);
    pagination.getQueryCriterions().put("chgMonth", chgMonth);
    pagination.getQueryCriterions().put("ip", ip);

    IChgpayBS chgpayBS = (IChgpayBS) BSUtils.getBusinessService("chgpayBS", this,
        mapping.getModuleConfig());
    
    chgpayBS.useChgPaymentPaymentMaintain(pagination);
    messages=new ActionMessages();
    messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("启用成功！",
        false));
    saveErrors(request, messages);
  }  catch(BusinessException e) {
     messages = new ActionMessages();
    messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(e
        .getMessage(), false));
    saveErrors(request, messages);
    return mapping.findForward("chgpayTbSouwAC.do");
  }
 
  return mapping.findForward("chgpayTbSouwAC.do");
}
  

  public ActionForward deluse(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    saveToken(request);
    ActionMessages messages =null;
    try{
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        PAGINATION_KEY);
    IdAF idaf=(IdAF)form;
    SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
    String ip=securityInfo.getUserInfo().getUserIp();
    String name=securityInfo.getUserInfo().getUsername();
    String id=idaf.getId().toString();
    String chgMonth=request.getParameter("chgMonth2");
    pagination.getQueryCriterions ().put("name", name);
    pagination.getQueryCriterions().put("id", id);
    pagination.getQueryCriterions().put("chgMonth", chgMonth);
    pagination.getQueryCriterions().put("ip", ip);

    
    IChgpayBS chgpayBS = (IChgpayBS) BSUtils.getBusinessService("chgpayBS", this,
        mapping.getModuleConfig());
    
    if(chgpayBS.deluseChgPaymentPaymentMaintain(pagination))
    {
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
    return mapping.findForward("chgpayTbSouwAC.do");
  }
 
  return mapping.findForward("chgpayTbSouwAC.do");
}
  
  
  


  
  
  
 }

