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
import org.xpup.hafmis.syscollection.common.domain.entity.Org;

public class MaintainChgpersonMaintainListAC extends LookupDispatchAction {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.chgbiz.chgperson.action.ShowChgpersonDoListAC";

  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.update", "modify");
    map.put("button.delete", "delete");
    map.put("button.use", "use");
    map.put("button.deluse", "deluse");
    map.put("button.referring.data", "referring");
    map.put("button.pproval.data", "pproval"); 
    map.put("button.print", "print");
    return map;
  }
  
  public ActionForward modify(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    IdAF idAF = (IdAF) form;
    String id = (String) idAF.getId();

    IChgpersonDoBS chgpersonDoBS = (IChgpersonDoBS) BSUtils
    .getBusinessService("chgpersonDoBS", this, mapping.getModuleConfig());
    //根据头表ID 查询单位ID
    String orgID = chgpersonDoBS.getOrgID(id);
    
    // 将orgID保存到session中
    HttpSession session = request.getSession();
    session.setAttribute("orgIDByChgPersonHeadID", orgID);
    session.setAttribute("changeHeadId", id);
    return mapping.findForward("to_chgperson_modify");
  }

  public ActionForward delete(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    ActionMessages messages = null;
    
    try{
      IdAF idAF = (IdAF) form;
      String id = (String) idAF.getId();

      IChgpersonDoBS chgpersonDoBS = (IChgpersonDoBS) BSUtils
      .getBusinessService("chgpersonDoBS", this, mapping.getModuleConfig());

      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");

      HttpSession session = request.getSession();
      session.setAttribute("deleteflag", "2");
      
      chgpersonDoBS.deleteChgPersonALL(id,securityInfo);

      
      }catch (BusinessException bex) {
        messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex.getMessage(), false));
        saveErrors(request, messages);
      }
    return mapping.findForward("to_chgperson_delete");

  }

  public ActionForward use(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    ActionMessages messages = null;

    try{
      IdAF idAF = (IdAF) form;
      String id = (String) idAF.getId();
      
      String chgdate = null;//启用时更新变更年月
      
      IChgpersonDoBS chgpersonDoBS = (IChgpersonDoBS) BSUtils
      .getBusinessService("chgpersonDoBS", this, mapping.getModuleConfig());
      
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");

      HttpSession session = request.getSession();
      session.setAttribute("deleteflag", "2");
      
      //根据头表ID 查询单位ID
      String orgID = chgpersonDoBS.getOrgID(id);
      
      chgpersonDoBS.useChgPerson(orgID,chgdate,securityInfo);
      
      
    }catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getLocalizedMessage().toString(), false));
      saveErrors(request, messages);
    }
    
    return mapping.findForward("to_chgperson_use");
  }

  public ActionForward deluse(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    ActionMessages messages = null;
    try{
      IdAF idAF = (IdAF) form;
      String id = (String) idAF.getId();
  
      IChgpersonDoBS chgpersonDoBS = (IChgpersonDoBS) BSUtils
      .getBusinessService("chgpersonDoBS", this, mapping.getModuleConfig());
      
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      

      HttpSession session = request.getSession();
      session.setAttribute("deleteflag", "2");
      
      chgpersonDoBS.checkDelUseMessage(id,securityInfo);
      }catch (BusinessException bex) {
        messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
            .getLocalizedMessage().toString(), false));
        saveErrors(request, messages);
      }
    return mapping.findForward("to_chgperson_deluse");

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
      String flag="2";
      chgpersonDoBS.PickInDate(id, securityInfo, flag);
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("提交成功！",
          false));
      saveErrors(request, messages);
    }  catch(BusinessException e) {
       messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(e
          .getMessage(), false));
      saveErrors(request, messages);
      return mapping.findForward("to_chgperson_delete");
    }
   
    return mapping.findForward("to_chgperson_delete");
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
       String flag="2";
       chgpersonDoBS.removePickInDate(id, securityInfo, flag);
       messages=new ActionMessages();
       messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("撤消提交成功！",
           false));
       saveErrors(request, messages);
     }  catch(BusinessException e) {
        messages = new ActionMessages();
       messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(e
           .getMessage(), false));
       saveErrors(request, messages);
       return mapping.findForward("to_chgperson_delete");
     }
     return mapping.findForward("to_chgperson_delete");
   }
   public ActionForward print(ActionMapping mapping,
       ActionForm form, HttpServletRequest request, HttpServletResponse response)
       throws Exception {
     ActionMessages messages = null;
     IdAF idAF = (IdAF) form;
     String id = (String) idAF.getId();

     IChgpersonDoBS chgpersonDoBS = (IChgpersonDoBS) BSUtils
     .getBusinessService("chgpersonDoBS", this, mapping.getModuleConfig());
     //根据头表ID 查询单位ID
     String orgID = chgpersonDoBS.getOrgID(id);
     List explist =new ArrayList();
     try {
       HttpSession session=request.getSession(false);
       Pagination pagination = (Pagination)session.getAttribute(PAGINATION_KEY);
       pagination.getQueryCriterions().put("id", orgID);
       pagination.getQueryCriterions().put("headId", id);
       SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo"); 
       explist=chgpersonDoBS.findChgpersonDoListAllByCriterions_wsh(pagination,securityInfo);
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
        id = (String) pagination.getQueryCriterions().get("id");
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
