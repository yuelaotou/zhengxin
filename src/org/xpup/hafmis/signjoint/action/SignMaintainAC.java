package org.xpup.hafmis.signjoint.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jcifs.smb.PictureUpload;
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
import org.xpup.hafmis.signjoint.bsinterface.ISignjointBS;
import org.xpup.hafmis.signjoint.dto.RequestSignDTO;
import org.xpup.hafmis.signjoint.dto.TempDTO;
import org.xpup.hafmis.signjoint.form.SignAddAF;
import org.xpup.hafmis.signjoint.util.BatchSignTools;

public class SignMaintainAC extends LookupDispatchAction{
  public static final String PAGINATION_KEY = "org.xpup.hafmis.signjoint.action.CompanyShowAC";
  
  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.add", "add");
    map.put("button.update", "update");
    map.put("button.delete", "delete");  
    map.put("button.deleteall", "deleteAll"); 
    map.put("button.export", "export"); 
    map.put("button.import", "imports"); 
    return map;
  }
  
  public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    saveToken(request);
    SignAddAF addAF=new SignAddAF();
    addAF.setType("1");
    request.setAttribute("signAddAF", addAF);  
    return mapping.findForward("to_add");
  }

  
  public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    IdAF idaf=(IdAF)form;
    String info=(String)idaf.getId();
    String [] str=BatchSignTools.Compart(info.trim());
    Pagination pagination=(Pagination)request.getSession().getAttribute(PAGINATION_KEY);
    String orgid=(String)pagination.getQueryCriterions().get("orgid");
    String orgname=(String)pagination.getQueryCriterions().get("orgname");
    SignAddAF addAF=new SignAddAF();
    ISignjointBS bs=(ISignjointBS)BSUtils.getBusinessService("SignjointBS",this, mapping.getModuleConfig());
    TempDTO temp= bs.queryUserInfo(orgid,str[0],str[1]);
    temp.setOrgname(orgname);
    temp.setOrgid(orgid.trim());
    
    addAF.setUserinfo(temp);
    addAF.setOlduserinfo(temp);
    addAF.setType("2");  
    request.setAttribute("signAddAF", addAF);  
    return mapping.findForward("to_add");
  }

  
  public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionMessages messages =null;
    try{
      IdAF idaf=(IdAF)form;
      String info=(String)idaf.getId();
      String [] str=BatchSignTools.Compart(info.trim());
      ISignjointBS bs=(ISignjointBS)BSUtils.getBusinessService("SignjointBS",this, mapping.getModuleConfig());
      Pagination pagination=(Pagination)request.getSession().getAttribute(PAGINATION_KEY);
      String orgid=(String)pagination.getQueryCriterions().get("orgid");
      bs.deleteUserInfo(orgid, str[0].trim(),str[1].trim());
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("É¾³ý³É¹¦£¡",
          false));
      saveErrors(request, messages);
    }catch(BusinessException bex){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("É¾³ýÊ§°Ü£¡",
          false));
      saveErrors(request, messages);
    }
    return mapping.findForward("showSignListAC");
  }
/*
  public ActionForward deleteMultibox(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionMessages messages =null;
    try{
      IdAF idaf=(IdAF)form;
      String[] rowArray=idaf.getRowArray();
      IDemoBS demoBS = (IDemoBS) BSUtils
      .getBusinessService("demoBS", this, mapping.getModuleConfig());
      demoBS.deleteDemoMultibox(rowArray);
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("É¾³ý³É¹¦£¡",
          false));
      saveErrors(request, messages);
    }catch(BusinessException bex){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("É¾³ýÊ§°Ü£¡",
          false));
      saveErrors(request, messages);
    }
  
    return mapping.findForward("showDemoListAC");
  }
*/
  public ActionForward deleteAll(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionMessages messages =null;
    try{
      messages=new ActionMessages();
      if(!isTokenValid(request)){
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("Çë²»Òª¹àË®£¡",
            false));
        saveErrors(request, messages);
      }
      else
      {
        HttpSession session=request.getSession(false);
        Pagination pagination = (Pagination)session.getAttribute(PAGINATION_KEY);
        String orgid=(String)pagination.getQueryCriterions().get("orgid");   
        ISignjointBS bs=(ISignjointBS)BSUtils.getBusinessService("SignjointBS",this, mapping.getModuleConfig());
        bs.deleteAll(orgid.trim());
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("É¾³ý³É¹¦£¡",
            false));
        saveErrors(request, messages);
      }
    }catch(BusinessException bex){
      bex.printStackTrace();
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("ÅúÁ¿É¾³ýÊ§°Ü£¡",
          false));
      saveErrors(request, messages);
    }
   
    return mapping.findForward("showSignListAC");
  }
  
  public ActionForward export(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionMessages messages =null;
    try{
      HttpSession session=request.getSession(false);
      Pagination pagination = (Pagination)session.getAttribute(PAGINATION_KEY);
      ISignjointBS bs=(ISignjointBS)BSUtils.getBusinessService("SignjointBS",this, mapping.getModuleConfig());
      List expList=bs.queryEmpListAll(pagination);
      if(expList.size()>0)
      {
          messages=new ActionMessages();
          messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("µ¼³ö³É¹¦£¡",
            false));
          saveErrors(request, messages);
          request.getSession().setAttribute("explist",expList);
          response.sendRedirect(request.getContextPath()+"/ExportServlet?ISCANSHU=false&EXP_NAME=sign_exp");
      }else
      {
        messages=new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("Ã»ÓÐÊý¾Ý£¡",
            false));
        saveErrors(request, messages);
      }
      return null;
    }catch(BusinessException bex){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("µ¼³öÊ§°Ü£¡",
          false));
      saveErrors(request,messages);
    }
    return mapping.findForward("showSignListAC");
  }
  
  public ActionForward imports(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    return mapping.findForward("to_imports");
  }



  
  /*
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
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("ÏÔÊ¾Ê§°Ü£¡",
          false));
      saveErrors(request, messages);
    }
    return mapping.findForward("to_demo_report");
  }
  */
  
 }

