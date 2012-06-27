package org.xpup.hafmis.demo.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
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
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.demo.bsinterface.IDemoBS;
import org.xpup.hafmis.demo.domain.entity.Demo;
import org.xpup.hafmis.demo.form.DemoAddAF;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.chgbiz.chgorgrate.bsinterface.IChgorgrateBS;



public class DemoEditMaintainAC extends LookupDispatchAction{  
  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.add", "add");
    map.put("button.add.update", "update");
    map.put("button.back", "back");
    map.put("button.upload", "upload"); 
    return map;
  }

  public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionMessages messages =null;
    try{
      messages=new ActionMessages();
      if(!isTokenValid(request))
      {
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("请不要灌水！",
            false));
        saveErrors(request, messages);
      }else{
        resetToken(request);
        Pagination pagination = (Pagination) request.getSession().getAttribute(
            DemoTaShowAC.PAGINATION_KEY);
        DemoAddAF demoAddAF=(DemoAddAF)form;
        Demo demo=demoAddAF.getDemo();
        IDemoBS demoBS = (IDemoBS) BSUtils
        .getBusinessService("demoBS", this, mapping.getModuleConfig());
        Integer id=null;
        id=demoBS.addDemo(demo);
        pagination.getQueryCriterions().put("demo.id",id.toString());
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("添加成功！",
            false));
        saveErrors(request, messages);
      }
    }catch(BusinessException bex){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("添加失败！",
          false)); 
      saveErrors(request, messages);
      return mapping.findForward("to_demo_add");
    } 
    return mapping.findForward("showDemoListAC");
  }
  public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionMessages messages =null;
    try{
    DemoAddAF demoAddAF=(DemoAddAF)form;
    Demo demo=demoAddAF.getDemo();
    IDemoBS demoBS = (IDemoBS) BSUtils
    .getBusinessService("demoBS", this, mapping.getModuleConfig());
    demoBS.updateDemo(demo);
    messages=new ActionMessages();
    messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("修改成功！",
        false));
    saveErrors(request, messages);
    }catch(BusinessException bex){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("修改失败！",
          false));
      saveErrors(request, messages);
      return mapping.findForward("to_demo_add");  
    }
    return mapping.findForward("showDemoListAC");
  }
  public ActionForward back(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    return mapping.findForward("showDemoListAC");
  }
  
  public ActionForward upload(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    PictureUpload pu = new PictureUpload();
    String path;
    try
    {
      
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
        String serverPath=BusiConst.UPLOAD_SERVER_PATH;
        //securityInfo.getUserInfo().getUserIp()
        path = pu.upload("192.168.1.200", "picture", serverPath);
        IDemoBS demoBS = (IDemoBS) BSUtils
        .getBusinessService("demoBS", this, mapping.getModuleConfig());
        DemoAddAF demoAddAF=(DemoAddAF)form;
        Demo demo=demoAddAF.getDemo();
        demoAddAF.setIsNeedDel("1");
        demo.setPhotoUrl(path);
        System.out.println(path);
        demoBS.updateDemo(demo);
        
    }
    catch(IOException e)
    {
        e.printStackTrace();
    }
   
    return mapping.findForward("to_demo_add");
  }
}
