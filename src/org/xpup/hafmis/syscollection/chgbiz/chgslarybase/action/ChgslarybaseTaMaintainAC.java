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
import org.xpup.hafmis.syscollection.chgbiz.chgslarybase.form.ChgslarybaseImportTaAF;
import org.xpup.hafmis.syscollection.chgbiz.chgslarybase.form.ChgslarybaseNewAF;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPaymentTail;
import org.xpup.hafmis.syscollection.paymng.orgaddpay.form.OrgaddpayImportTaAF;


/**
 * 
 * @author 吴洪涛 
 *2007-6-1
 */
public class ChgslarybaseTaMaintainAC extends LookupDispatchAction{
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.chgbiz.chgslarybase.action.ChgslarybaseTaShowAC";

  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.add", "add");
    map.put("button.update", "update");
    map.put("button.delete", "remove");
    map.put("button.deleteall", "removeAll");
    map.put("button.exports", "exports");
    map.put("button.imports", "imports");
    map.put("button.use", "use");
    map.put("button.referring.data", "referring");
    map.put("button.pproval.data", "pproval");
    map.put("button.pickup.data", "pickup");
    //吴洪涛 2008-6-16
    map.put("button.database.exports", "databaseExports");
    
    return map;
  }

  public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    saveToken(request);
    ChgslarybaseNewAF chgslarybaseNewAF=new ChgslarybaseNewAF();
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        PAGINATION_KEY);
    SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
    String ip=securityInfo.getUserInfo().getUserIp();
    String name=securityInfo.getUserInfo().getUsername();
    String date=securityInfo.getUserInfo().getBizDate();
    String pkid=request.getParameter("PKID");
    String orgid=request.getParameter("org.id");
    String chgMonth=request.getParameter("chgMonth2");
    String payPrecision=request.getParameter("payPrecision");
    String orgRate=request.getParameter("orgRate");
    String empRate=request.getParameter("empRate");
    pagination.getQueryCriterions().put("date", date);
    pagination.getQueryCriterions().put("name", name);
    pagination.getQueryCriterions().put("pkid", pkid);
    pagination.getQueryCriterions().put("org.id", orgid);
    pagination.getQueryCriterions().put("chgMonth", chgMonth);
    pagination.getQueryCriterions().put("ip", ip);
    pagination.getQueryCriterions().put("payPrecision", payPrecision);
    pagination.getQueryCriterions().put("orgRate", orgRate);
    pagination.getQueryCriterions().put("empRate", empRate);
    Map sexMap=BusiTools.listBusiProperty(BusiConst.SEX);
    Map documentsstateMap=BusiTools.listBusiProperty(BusiConst.DOCUMENTSSTATE);
    chgslarybaseNewAF.setDocumentsstateMap(documentsstateMap);
    chgslarybaseNewAF.setSexMap(sexMap);
    chgslarybaseNewAF.setType("1");
    chgslarybaseNewAF.setOrgRate(orgRate);
    chgslarybaseNewAF.setEmpRate(empRate);
    chgslarybaseNewAF.setOrgId(orgid);
    chgslarybaseNewAF.setPayPrecision(payPrecision);
    request.setAttribute("chgslarybaseNewAF", chgslarybaseNewAF); 
    saveToken(request);
    return mapping.findForward("to_chgslarybase_new.jsp");
  }

  
  public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    IdAF idaf=(IdAF)form;
    
    ChgslarybaseNewAF chgslarybaseNewAF=new ChgslarybaseNewAF();
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        PAGINATION_KEY);
    SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
    String ip=securityInfo.getUserInfo().getUserIp();
    String name=securityInfo.getUserInfo().getUsername();

    String orgid=request.getParameter("org.id");
    pagination.getQueryCriterions().put("org.id", orgid);
    String pkid=request.getParameter("PKID");
    String chgMonth=request.getParameter("chgMonth2");
    String payPrecision=request.getParameter("payPrecision");
 
    pagination.getQueryCriterions().put("payPrecision", payPrecision);
    pagination.getQueryCriterions().put("name", name);
    pagination.getQueryCriterions().put("pkid", pkid);
    pagination.getQueryCriterions().put("chgMonth", chgMonth);
    pagination.getQueryCriterions().put("ip", ip);
    String orgRate=request.getParameter("orgRate");
    String empRate=request.getParameter("empRate");
    
    IChgslarybaseBS chgslarybaseBS = (IChgslarybaseBS) BSUtils.getBusinessService("chgslarybaseBS", this,
        mapping.getModuleConfig());
    ChgPaymentTail chgPaymentTail=chgslarybaseBS.findChgPaymentTailWuht(idaf.getId().toString(),pagination);
    Map sexMap=BusiTools.listBusiProperty(BusiConst.SEX);
    Map documentsstateMap=BusiTools.listBusiProperty(BusiConst.DOCUMENTSSTATE);
    chgslarybaseNewAF.setSexMap(sexMap);
    chgslarybaseNewAF.setDocumentsstateMap(documentsstateMap);
    chgslarybaseNewAF.setType("2");  
    chgslarybaseNewAF.setChgPaymentTail(chgPaymentTail); 
    chgslarybaseNewAF.setOrgRate(orgRate);
    chgslarybaseNewAF.setEmpRate(empRate);
    chgslarybaseNewAF.setPayPrecision(payPrecision);
    request.setAttribute("chgslarybaseNewAF", chgslarybaseNewAF);
    return mapping.findForward("to_chgslarybase_new.jsp");
  }

  public ActionForward use(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    saveToken(request);
    ActionMessages messages =null;
    try{
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        PAGINATION_KEY);
    SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
    String ip=securityInfo.getUserInfo().getUserIp();
    String name=securityInfo.getUserInfo().getUsername();
    String orgid=request.getParameter("org.id");
    pagination.getQueryCriterions().put("org.id", orgid);
    String pkid=request.getParameter("PKID");
    String chgMonth=request.getParameter("chgMonth2");
 
    pagination.getQueryCriterions().put("name", name);
    pagination.getQueryCriterions().put("pkid", pkid);
    pagination.getQueryCriterions().put("chgMonth", chgMonth);
    pagination.getQueryCriterions().put("ip", ip);

    
    IChgslarybaseBS chgslarybaseBS = (IChgslarybaseBS) BSUtils.getBusinessService("chgslarybaseBS", this,
        mapping.getModuleConfig()); 
    
    chgslarybaseBS.useChgPaymentSalaryBase(pagination);
    chgslarybaseBS.setAa202(pagination);
    messages=new ActionMessages();
    messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("启用成功！",
        false));
    saveErrors(request, messages);
    }  catch(BusinessException e) {
      messages = new ActionMessages();
     messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(e
         .getMessage(), false));
     saveErrors(request, messages);
     return mapping.findForward("chgslarybaseTaShowAC");
   }
    return mapping.findForward("chgslarybaseTaShowAC");
  }
  
  
  //删除AA203表中记录，条件是：id=选中的id，插入BA003：操作业务=AA202中该笔变更的id
  //ip： IP地址 id：aa203的ID pkid:aa202的ID nrOfElements:还省几个203的数据条数 orgid：单位编号
  public ActionForward remove(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionMessages messages =null;
    try{
      IdAF idaf=(IdAF)form;
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      String ip=securityInfo.getUserInfo().getUserIp();
      String name=securityInfo.getUserInfo().getUsername();
      
    //  String ip=request.getRemoteAddr();
      String id=idaf.getId().toString();
      String pkid=request.getParameter("PKID");
      String orgid=request.getParameter("org.id");
      String nrOfElements=request.getParameter("nrOfElements");
   
      IChgslarybaseBS chgslarybaseBS = (IChgslarybaseBS) BSUtils.getBusinessService("chgslarybaseBS", this,
          mapping.getModuleConfig());
      chgslarybaseBS.deleteChgPaymentTail(new Integer (id),pkid,orgid,ip,nrOfElements, name,securityInfo);
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("删除成功！",
          false));
      saveErrors(request, messages);
    }catch(BusinessException bex){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex.getMessage(),
          false));
      saveErrors(request, messages);
    }
   
    return mapping.findForward("chgslarybaseTaShowAC");
  }



  public ActionForward removeAll(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    try{
  
      IdAF idaf=(IdAF)form;
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      String ip=securityInfo.getUserInfo().getUserIp();
      String name=securityInfo.getUserInfo().getUsername();
      String id=idaf.getId().toString();
      String pkid=request.getParameter("PKID");
      String orgid=request.getParameter("org.id");
      IChgslarybaseBS chgslarybaseBS = (IChgslarybaseBS) BSUtils.getBusinessService("chgslarybaseBS", this,
          mapping.getModuleConfig());
      chgslarybaseBS.deleteAllChgPaymentTail(new Integer (id),pkid,orgid,ip,name,securityInfo);
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("删除成功！",
          false));
      saveErrors(request, messages);
    }catch(BusinessException bex){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex.getMessage(),
          false));
      saveErrors(request, messages);
    }
   
    return mapping.findForward("chgslarybaseTaShowAC");
  }
  
  public ActionForward exports(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionMessages messages =null;
    try{
      HttpSession session=request.getSession(false);
      Pagination pagination=new Pagination();
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      String ip=securityInfo.getUserInfo().getUserIp();
      String name=securityInfo.getUserInfo().getUsername();
      IChgslarybaseBS chgslarybaseBS = (IChgslarybaseBS) BSUtils.getBusinessService("chgslarybaseBS", this,
          mapping.getModuleConfig());
      String orgId=request.getParameter("org.id");
      pagination.getQueryCriterions().put("org.id", orgId);
      String orgName=request.getParameter("org.orgInfo.name");
      String chgMonth=request.getParameter("chgMonth2");
      pagination.getQueryCriterions().put("org.orgInfo.name", orgName);
      pagination.getQueryCriterions().put("chgMonth", chgMonth);
 
      pagination.getQueryCriterions().put("ip", ip);
      pagination.getQueryCriterions().put("name", name);
      
      List expList=chgslarybaseBS.queryEmpOrgList(pagination);
      if(expList.size()>0)
      {
          messages=new ActionMessages();
          messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("导出成功！",
            false));
          saveErrors(request, messages);
          request.getSession().setAttribute("explist",expList);
          response.sendRedirect(request.getContextPath()+"/ExportServlet?ISCANSHU=false&EXP_NAME=chgslarybase_exp");
      }else
      {
        messages=new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("没有数据！",
            false));
        saveErrors(request, messages);
      }
            return null;
    }catch(BusinessException bex){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      saveErrors(request, messages);
    }
    return mapping.findForward("chgslarybaseTaShowAC");
  }
  public ActionForward imports(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

    String orgId=request.getParameter("org.id");
    String chgMonth=request.getParameter("chgMonth2");
   
    ChgslarybaseImportTaAF chgslarybaseImportTaAF = new ChgslarybaseImportTaAF();
    chgslarybaseImportTaAF.setOrgId(orgId);
    chgslarybaseImportTaAF.setChgMonth(chgMonth);
    request.setAttribute("chgslarybaseImportTaAF", chgslarybaseImportTaAF);
    return mapping.findForward("to_chgslarybase_imports.jsp");
  }
  public ActionForward maintainReport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionMessages messages =null;
    try{
      HttpSession session=request.getSession(false);
      Pagination pagination = (Pagination)session.getAttribute(PAGINATION_KEY);
      
      IDemoBS demoBS = (IDemoBS) BSUtils
      .getBusinessService("demoBS", this, mapping.getModuleConfig());
      List expList=demoBS.findDemoListAll(pagination);
      request.setAttribute("cellList", expList);
    }catch(BusinessException bex){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("显示失败！",
          false));
      saveErrors(request, messages);
    }
    return mapping.findForward("to_demo_report");
  }
  protected String getPaginationKey() {
    return ChgslarybaseTaShowAC.PAGINATION_KEY;
  }
  //提交数据
  public ActionForward referring(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionMessages messages =null;
    try{
      IdAF idaf=(IdAF)form;
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      String id=idaf.getId().toString(); 
      String pkid=request.getParameter("PKID");      
      String orgid=request.getParameter("org.id");
      IChgslarybaseBS chgslarybaseBS = (IChgslarybaseBS) BSUtils.getBusinessService("chgslarybaseBS", this,
          mapping.getModuleConfig());
      String flag="1";
      chgslarybaseBS.PickInChgPaymentTailMaintain(pkid, orgid, securityInfo,flag);   
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("提交成功！",
          false));
      saveErrors(request, messages);
    }  catch(BusinessException e) {
       messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(e
          .getMessage(), false));
      saveErrors(request, messages);
      return mapping.findForward("chgslarybaseTaShowAC");
    }
   
    return mapping.findForward("chgslarybaseTaShowAC");
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
      String pkid=request.getParameter("PKID");      
      String orgid=request.getParameter("org.id");
      String flag="1";
      chgslarybaseBS.removePickInChgPaymentTailMaintain(pkid, orgid, securityInfo,flag);   
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("撤消提交成功！",
          false));
      saveErrors(request, messages);
    }  catch(BusinessException e) {
       messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(e
          .getMessage(), false));
      saveErrors(request, messages);
      return mapping.findForward("chgslarybaseTaShowAC");
    }
    return mapping.findForward("chgslarybaseTaShowAC");
  }
  //提取数据
  public ActionForward pickup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionMessages messages =null;
    try{
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");     
      String orgid=request.getParameter("org.id");
      String chgMonth=request.getParameter("chgMonth2");
      //System.out.println(request.getParameter("chgMonth2")+"chgMonth");
      IChgslarybaseBS chgslarybaseBS = (IChgslarybaseBS) BSUtils.getBusinessService("chgslarybaseBS", this,
          mapping.getModuleConfig());
      chgslarybaseBS.pickUpChgPaymentChgslarybase(orgid, chgMonth,securityInfo);      
//      messages=new ActionMessages();
//      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("提取成功！",
//          false));
//      saveErrors(request, messages);
    }  catch(BusinessException e) {
       messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(e
          .getMessage(), false));
      saveErrors(request, messages);
      return mapping.findForward("chgslarybaseTaShowAC");
    }
   
    return mapping.findForward("chgslarybaseTaShowAC");
  }
  // 吴洪涛 2008-6-16 数据导出
  public ActionForward databaseExports(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    try {
      String orgid=request.getParameter("org.id");
      String chgMonth=request.getParameter("chgMonth2");
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");    
      Pagination pagination = (Pagination) request.getSession().getAttribute(
          PAGINATION_KEY);
      List chgslarybaseCellList = (List) pagination.getQueryCriterions().get(
          "chgslarybaseCellList");
      IChgslarybaseBS chgslarybaseBS = (IChgslarybaseBS) BSUtils.getBusinessService("chgslarybaseBS", this,
          mapping.getModuleConfig());
      chgslarybaseCellList=chgslarybaseBS.chgslarybaseCellList(chgslarybaseCellList,orgid, chgMonth,securityInfo,pagination);      
      if (chgslarybaseCellList != null && chgslarybaseCellList.size() > 0) {
        messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("导出成功！",
            false));
        saveErrors(request, messages);
        request.getSession().setAttribute("explist",
            chgslarybaseCellList);
        response.sendRedirect(request.getContextPath()
            + "/ExportServlet?ISCANSHU=false&EXP_NAME=chgslarybaseCellList_exp");
      } else {
        messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("没有数据！",
            false));
        saveErrors(request, messages);
      }
      return null;
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      saveErrors(request, messages);
    }
    return mapping.findForward("chgslarybaseTaShowAC");
  }
  
 }

