package org.xpup.hafmis.syscollection.chgbiz.chgperson.action;

import java.util.ArrayList;
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
import org.xpup.hafmis.syscollection.chgbiz.chgperson.bsinterface.IChgpersonDoBS;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.form.ChgpersonDoIdAF;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.sysfinance.accounthandle.credencefillin.action.CredenceFillinTdShowdAC;
import org.xpup.hafmis.sysfinance.accounthandle.credencefillin.bsinterface.ICredenceFillinBS;
import org.xpup.hafmis.sysloan.dataready.loanpara.bsinterface.ILoanDocNumDesignBS;

public class MaintainChgpersonDoListAC extends LookupDispatchAction{
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.chgbiz.chgperson.action.ShowChgpersonDoListAC";
  
  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.exports", "exports");
    map.put("button.imports", "imports");
    map.put("button.add", "save");
    map.put("button.delete", "delete");
    map.put("button.deleteall", "deleteAll");
    map.put("button.use", "use");
    map.put("button.referring.data", "referring");
    map.put("button.pproval.data", "pproval");
    map.put("button.pickup.data", "pickup");
    //吴洪涛 2008-6-16
    map.put("button.database.exports", "databaseExports");
    map.put("button.print", "print");
    
    return map;
  }
  public ActionForward exports(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionMessages messages =null;
    try{
      HttpSession session=request.getSession(false);
      Pagination pagination = (Pagination)session.getAttribute(PAGINATION_KEY);

      String orgID = (String)session.getAttribute("orgID");//变更单位ID
      
      IChgpersonDoBS chgpersonDoBS = (IChgpersonDoBS) BSUtils
      .getBusinessService("chgpersonDoBS", this, mapping.getModuleConfig());
      

      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      
      List expList=chgpersonDoBS.getChgpersonListAll(pagination,orgID,securityInfo);
      
//      if(expList.size()>0){
          messages=new ActionMessages();
          messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("导出成功！",
            false));
          saveErrors(request, messages);
          request.getSession().setAttribute("explist",expList);
          response.sendRedirect(request.getContextPath()+"/ExportServlet?ISCANSHU=false&EXP_NAME=chgperson_exp");

          return null;
//      }
    }catch(BusinessException bex){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("导出失败！",
          false));
      saveErrors(request, messages);
    }
    return mapping.findForward("to_chgperson_list");
  }

  public ActionForward imports(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    return mapping.findForward("to_chgperson_imports");
  }
  
  public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

    String chgDate = (String)request.getParameter("chgDate");
    HttpSession session = request.getSession();
    session.setAttribute("chgDate", chgDate); 

    request.setAttribute("action", "1");
    
    return mapping.findForward("to_chgperson_save");
  }
  
  public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionMessages messages =null;
    try{
      ChgpersonDoIdAF idaf=(ChgpersonDoIdAF)form;
      String id=idaf.getId().toString();
      
      IChgpersonDoBS chgpersonDoBS = (IChgpersonDoBS) BSUtils
      .getBusinessService("chgpersonDoBS", this, mapping.getModuleConfig());

      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      
      chgpersonDoBS.deleteChgPersonTail(new Integer (id),securityInfo);
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
   
    return mapping.findForward("to_chgperson_list");
  }   
  
  public ActionForward deleteAll(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    
    ActionMessages messages =null;
    try{
      ChgpersonDoIdAF idAF = (ChgpersonDoIdAF)form;
      
      messages=new ActionMessages();
        
        IChgpersonDoBS chgpersonDoBS = (IChgpersonDoBS) BSUtils
        .getBusinessService("chgpersonDoBS", this, mapping.getModuleConfig());

        SecurityInfo securityInfo = (SecurityInfo) request.getSession()
            .getAttribute("SecurityInfo");
        
        String chgpersonHeadID = chgpersonDoBS.queryChgpersonHeadID(idAF.getId().toString());
        chgpersonDoBS.deleteChgPersonALL(chgpersonHeadID, securityInfo);
        
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("删除成功！",
            false));
        saveErrors(request, messages);

    }catch(BusinessException bex){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex.getMessage(),
          false));
      saveErrors(request, messages);
    }
   
    return mapping.findForward("to_chgperson_list");    
    }
  
  public ActionForward use(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionMessages messages =null;
    ChgpersonDoIdAF idaf=(ChgpersonDoIdAF)form;
    
    try{
      String chgDate = idaf.getChgDate();//变更年月
      HttpSession session = request.getSession();
      String orgID = (String)session.getAttribute("orgID");//变更单位ID

      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      
      IChgpersonDoBS chgpersonDoBS = (IChgpersonDoBS) BSUtils
      .getBusinessService("chgpersonDoBS", this, mapping.getModuleConfig());
      
      chgpersonDoBS.useChgPerson(orgID,chgDate,securityInfo);
      
    }catch(Exception bex){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("启用失败！",
          false));
      saveErrors(request, messages);
    }
   
    return mapping.findForward("to_chgperson_list");
    }
  //提交数据
  public ActionForward referring(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionMessages messages =null;
    try{
      IdAF idaf=(IdAF)form;
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      String id=idaf.getId().toString();   
      IChgpersonDoBS chgpersonDoBS = (IChgpersonDoBS) BSUtils
      .getBusinessService("chgpersonDoBS", this, mapping.getModuleConfig());
      String chgpersonHeadID = chgpersonDoBS.queryChgpersonHeadID(id);
      String flag="1";
      chgpersonDoBS.PickInDate(chgpersonHeadID, securityInfo, flag);
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("提交成功！",
          false));
      saveErrors(request, messages);
    }  catch(BusinessException e) {
       messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(e
          .getMessage(), false));
      saveErrors(request, messages);
      return mapping.findForward("to_chgperson_list");
    }
   
    return mapping.findForward("to_chgperson_list");
  }
  //撤消提交数据
  public ActionForward pproval(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionMessages messages =null;
    try{
      IdAF idaf=(IdAF)form;
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      String id=idaf.getId().toString();     
      IChgpersonDoBS chgpersonDoBS = (IChgpersonDoBS) BSUtils
      .getBusinessService("chgpersonDoBS", this, mapping.getModuleConfig());
      String flag="1";
      String chgpersonHeadID = chgpersonDoBS.queryChgpersonHeadID(id);
      //System.out.println(chgpersonHeadID+"chgpersonHeadID");
      chgpersonDoBS.removePickInDate(chgpersonHeadID, securityInfo, flag);
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("撤消提交成功！",
          false));
      saveErrors(request, messages);
    }  catch(BusinessException e) {
       messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(e
          .getMessage(), false));
      saveErrors(request, messages);
      return mapping.findForward("to_chgperson_list");
    }
    return mapping.findForward("to_chgperson_list");
  }
  //提取数据
  public ActionForward pickup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionMessages messages =null;
    try{
      HttpSession session=request.getSession(false);
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");     
      String orgid = (String)session.getAttribute("orgID");//变更单位ID
      IChgpersonDoBS chgpersonDoBS = (IChgpersonDoBS) BSUtils
      .getBusinessService("chgpersonDoBS", this, mapping.getModuleConfig());
      chgpersonDoBS.pickUpDate(orgid,securityInfo);
//      messages=new ActionMessages();
//      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("提取成功！",
//          false));
//      saveErrors(request, messages);
    }  catch(BusinessException e) {
       messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(e
          .getMessage(), false));
      saveErrors(request, messages);
      return mapping.findForward("to_chgperson_list");
    }
   
    return mapping.findForward("to_chgperson_list");
  }
  // 吴洪涛 2008-6-16 数据导出
  public ActionForward databaseExports(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    List explist =new ArrayList();
    try {
      HttpSession session=request.getSession(false);
      Pagination pagination = (Pagination)session.getAttribute(PAGINATION_KEY);
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");    
      IChgpersonDoBS chgpersonDoBS = (IChgpersonDoBS) BSUtils
      .getBusinessService("chgpersonDoBS", this, mapping.getModuleConfig());
      explist=chgpersonDoBS.findChgpersonDoListAllByCriterions(pagination,securityInfo);      
      if (explist != null && explist.size() > 0) {
        messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("导出成功！",
            false));
        saveErrors(request, messages);
        request.getSession().setAttribute("explist",
            explist);
        response.sendRedirect(request.getContextPath()
            + "/ExportServlet?ISCANSHU=false&EXP_NAME=chgpersonCellList_exp");
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
  /**
   * 打印的方法
   * 
   *5、列表增加打印功能
表头：住房公积金人员变更清册
单位名称，单位账号，变更年月
内容
与查询列表内容相同

列表信息下面显示：归集银行，制表人，操作日期（业务日期）

   */
  public ActionForward print(ActionMapping mapping,
      ActionForm form, HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    List explist =new ArrayList();
    try {
      HttpSession session=request.getSession(false);
      Pagination pagination = (Pagination)session.getAttribute(PAGINATION_KEY);
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");    
      IChgpersonDoBS chgpersonDoBS = (IChgpersonDoBS) BSUtils
      .getBusinessService("chgpersonDoBS", this, mapping.getModuleConfig());
      explist=chgpersonDoBS.findChgpersonDoListAllByCriterions(pagination,securityInfo);
      String bizDate = securityInfo.getUserInfo().getBizDate();
 
      String userName="";
      try {
        String name = chgpersonDoBS.getNamePara();

        if (name.equals("1")) {
          userName = securityInfo.getUserName();
        } else {
          userName = securityInfo.getRealName();
        }

      } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      String collectionBankId="";
      String collectionBankName="";
      String id = (String) pagination.getQueryCriterions().get("id");
      if(id!=null && !id.equals("")){
       Org org= chgpersonDoBS.queryOrgById(new Integer(id),securityInfo);
       collectionBankId=org.getOrgInfo().getCollectionBankId();
       if(collectionBankId!=null && !collectionBankId.equals("")){
         collectionBankName=chgpersonDoBS.queryCollectionBankNameById(id, securityInfo);
       }
      }
      request.setAttribute("userName", userName);
      request.setAttribute("celllist", explist);
      request.setAttribute("bizDate", bizDate);
      request.setAttribute("collectionBankName", collectionBankName);
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      saveErrors(request, messages);
    }
    return mapping.findForward("to_chgpersonList_cell");
  }
}