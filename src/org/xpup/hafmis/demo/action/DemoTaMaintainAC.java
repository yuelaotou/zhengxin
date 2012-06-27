package org.xpup.hafmis.demo.action;

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


/**
 * 
 * @author ÁõÑó
 *2007-6-1
 */
public class DemoTaMaintainAC extends LookupDispatchAction{
  public static final String PAGINATION_KEY = "org.xpup.hafmis.demo.action.DemoTaShowAC";
  
  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.add", "add");
    map.put("button.update", "update");
    map.put("button.delete", "delete");  
    map.put("button.delete.all", "deleteAll"); 
    map.put("button.delete.multibox", "deleteMultibox");
    map.put("button.exports", "exportsDemo"); 
    map.put("button.imports", "importsDemo"); 
    map.put("button.reports", "maintainReport");
    return map;
  }

  public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    saveToken(request);
    DemoAddAF demoAddAF=new DemoAddAF();
    Map sexMap=BusiTools.listBusiProperty(BusiConst.SEX);
    demoAddAF.setSexMap(sexMap);
    demoAddAF.setType("1");
    demoAddAF.setIsNeedDel("0");
    request.setAttribute("demoAddAF", demoAddAF);  
    return mapping.findForward("to_demo_add");
  }

  
  public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    IdAF idaf=(IdAF)form;
    DemoAddAF demoAddAF=new DemoAddAF();
    IDemoBS demoBS = (IDemoBS) BSUtils
    .getBusinessService("demoBS", this, mapping.getModuleConfig());
    Demo demo=demoBS.findDemoById(new Integer(idaf.getId().toString()));
    Map sexMap=BusiTools.listBusiProperty(BusiConst.SEX);
    demoAddAF.setSexMap(sexMap);
    demoAddAF.setType("2");  
    demoAddAF.setIsNeedDel("0");
    demoAddAF.setDemo(demo);
    request.setAttribute("demoAddAF", demoAddAF);  
    return mapping.findForward("to_demo_add");
  }

  
  public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionMessages messages =null;
    try{
      IdAF idaf=(IdAF)form;
      String id=idaf.getId().toString();
      IDemoBS demoBS = (IDemoBS) BSUtils
      .getBusinessService("demoBS", this, mapping.getModuleConfig());
      demoBS.deleteDemo(new Integer (id));
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
        List list=(List)pagination.getQueryCriterions().get("pageList");   
        IDemoBS demoBS = (IDemoBS) BSUtils
        .getBusinessService("demoBS", this, mapping.getModuleConfig());
        demoBS.deletePageList(list);
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("É¾³ý³É¹¦£¡",
            false));
        saveErrors(request, messages);
      }
    }catch(BusinessException bex){
      bex.printStackTrace();
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("É¾³ýÊ§°Ü£¡",
          false));
      saveErrors(request, messages);
    }
   
    return mapping.findForward("showDemoListAC");
    }
  
  public ActionForward exportsDemo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionMessages messages =null;
    try{
      HttpSession session=request.getSession(false);
      Pagination pagination = (Pagination)session.getAttribute(PAGINATION_KEY);
      
      IDemoBS demoBS = (IDemoBS) BSUtils
      .getBusinessService("demoBS", this, mapping.getModuleConfig());
      List expList=demoBS.findDemoListAll(pagination);
      if(expList.size()>0)
      {
          messages=new ActionMessages();
          messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("µ¼³ö³É¹¦£¡",
            false));
          saveErrors(request, messages);
          request.getSession().setAttribute("explist",expList);
          response.sendRedirect(request.getContextPath()+"/ExportServlet?ISCANSHU=false&EXP_NAME=demo_exp");
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
      saveErrors(request, messages);
    }
    return mapping.findForward("showDemoListAC");
  }
  public ActionForward importsDemo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    return mapping.findForward("to_demo_imports");
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
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("ÏÔÊ¾Ê§°Ü£¡",
          false));
      saveErrors(request, messages);
    }
    return mapping.findForward("to_demo_report");
  }

  
 }

